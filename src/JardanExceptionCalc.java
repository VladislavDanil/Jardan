
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JardanExceptionCalc
 */
public class JardanExceptionCalc extends HttpServlet {

	public float[][] start_matrix;
	public float[][] end_matrix;
	private static final long serialVersionUID = 1L;
	public int replacement_x;
	public int replacement_y;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JardanExceptionCalc() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int x = Integer.parseInt(Data.x_start);
		int y = Integer.parseInt(Data.y_start);
		int k = 1;
		start_matrix = new float[x][y];
		end_matrix = new float[x][y];
		/*
		 * условие исключающее блок первоначального ввода при повторной
		 * обработке
		 */
		if(request.getParameter("input_form")=="1"){
			Data.retry = false;
		}
		if (Data.retry == false) {
			Data.k.add(0);
			int num = 1;
			/* чтение данных из html в массив */
			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++) {
					start_matrix[i][j] = Integer.parseInt(new String(request
							.getParameter("number_input_form" + num).getBytes(
									"ISO-8859-1")));
					num = num + 1;
				}

			replacement_x = Integer.parseInt(new String(request.getParameter(
					"replacement_x").getBytes("ISO-8859-1"), "UTF-8"));
			replacement_y = Integer.parseInt(new String(request.getParameter(
					"replacement_y").getBytes("ISO-8859-1"), "UTF-8"));


		} else {
			String a = new String(request.getParameter("input_form")
					.getBytes("ISO-8859-1"), "UTF-8");
			String[] b = a.split("|");
			replacement_x = Integer.parseInt(b[0])+1;
			replacement_y = Integer.parseInt(b[2])+1;
			start_matrix=Data.s_matrix;
			/*проверка на множественное решение*/
				for (int i = 0; i < x; i++) {
					if(start_matrix[i][0]==0){
						for (int j = 1; j < y; j++) {
							  if(Data.k.indexOf(j)==-1){
								  if(start_matrix[i][j]==0){
									  Data.result=true;
								  }
								  else
								  {
									  Data.result=false;
								  }
							  }
						}
						
					}
				}
				/*проверка на единственное решение
						for (int j = 0; j < y; j++) {
							for (int column: Data.k){
								if(j==column){
									 Data.result=true;
								  }
								else{
									Data.result=false;
								}
							}
						}*/
		}
		if (Data.k.indexOf(replacement_y) == (-1)||Data.result==false) {
			/* вычисление матрицы после перестановки */
			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++) {
					if ((i != replacement_x - 1) || (j != replacement_y - 1)) {
						end_matrix[i][j] = (start_matrix[i][j]
								* start_matrix[replacement_x - 1][replacement_y - 1] - start_matrix[i][replacement_y - 1]
								* start_matrix[replacement_x - 1][j])
								/ start_matrix[replacement_x - 1][replacement_y - 1];

					}
					if (i == replacement_x - 1) {
						end_matrix[i][j] = start_matrix[i][j]
								/ start_matrix[replacement_x - 1][replacement_y - 1];
					}
					if (j == replacement_y - 1) {
						end_matrix[i][j] = -start_matrix[i][j]
								/ start_matrix[replacement_x - 1][replacement_y - 1];
					}
				}
			end_matrix[replacement_x - 1][replacement_y - 1] = 1 / (start_matrix[replacement_x - 1][replacement_y - 1]);

		}
		/* вывод таблиц на страницу */
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				response.getOutputStream(), "UTF8"), true);
		out.println("<HTML><HEAD><TITLE>");
		out.println("</TITLE></HEAD><BODY>");
		out.println("<h1>-----------------</h1>");
		out.println("<TABLE border=1px>");
		for (int i = 0; i < x; i++) {
			out.println("<tr>");
			for (int j = 0; j < y; j++) {
				out.println("<td>");
				out.println(start_matrix[i][j]);
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</TABLE>");
		out.println("<h1>------------------</h1>");
		if (Data.k.indexOf(replacement_y-1) != (-1)) {
			out.println("you have chosen has already found a column , choose another");
			end_matrix=start_matrix;
			
		}
		if (Data.result==true){
			out.println("<h1>picked up the win column</h1>");
			end_matrix=start_matrix;
		}
		out.println("<TABLE border=1px>");

		for (int i = 0; i < x; i++) {
			out.println("<tr>");
			for (int j = 0; j < y; j++) {
				out.println("<td>");
				out.println("<form name='form " + i + "/" + j
						+ "' action='JardanExceptionCalc' method='post'>");
				out.println("<button type='submit' name='input_form' value='"
						+ i
						+ "|"
						+ j
						+ "|"
						+ k
						+ "' size='2'/>"
						+ end_matrix[i][j] + "</button>");
				out.println("</form>");
				out.println("</td>");
				k = k + 1;
			}
			out.println("</tr>");

		}
		Data.s_matrix=end_matrix;
		Data.k.add(replacement_y-1);
		out.println("</TABLE>");
		for (int column: Data.k){
			out.println(column+"|");
		}
		out.println("</BODY></HTML>");
		out.close();
		/* приравниваем переменную индикатора повтора к true */
		Data.retry = true;
		

	}

}
