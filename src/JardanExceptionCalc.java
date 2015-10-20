import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

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
		ArrayList<Integer> k_not = new ArrayList<Integer>();
		int k = 1;
		start_matrix = new float[x][y];
		end_matrix = new float[x][y];

		/*
		 * условие исключающее блок первоначального ввода при повторной
		 * обработке
		 */
		if (request.getParameter("input_form") == "1") {
			Data.retry = false;
		}
		try{
			@SuppressWarnings("unused")
			String a = new String(request.getParameter("input_form").getBytes(
					"ISO-8859-1"), "UTF-8");
		}
		catch(NullPointerException e){
			Data.retry = false;
		}
		if (Data.retry == false) {
			Data.k.clear();
			Data.saveColumn.clear();
			Data.s.clear();
			Data.result=false;
			Data.not_result=false;
			Data.one_result=false;
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
			String a = new String(request.getParameter("input_form").getBytes(
					"ISO-8859-1"), "UTF-8");
			String[] b = a.split("|");
			replacement_x = Integer.parseInt(b[0]) + 1;
			replacement_y = Integer.parseInt(b[2]) + 1;
			start_matrix = Data.s_matrix;

		}
		Integer x1 = replacement_x;
		Integer y1 = replacement_y-1;
        Data.saveColumn.put(x1, y1);
		if (Data.k.indexOf(replacement_y) == (-1) || Data.result == false) {
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
			Data.k.add(replacement_y - 1);
			Data.s.add(replacement_x - 1);
			/* проверка на множественное решение */
			for (int i = 0; i < x; i++) {
				if (end_matrix[i][0] == 0) {
					for (int j = 1; j < y; j++) {
						if (Data.k.indexOf(j) == -1) {
							if (end_matrix[i][j] == 0) {
								Data.result = true;
							} else {
								Data.result = false;
							}
						}
					}

				}
			}
			/* проверка на отсутствие решения */
			for (int j = 0; j < y; j++) {
				if (Data.k.indexOf(j) == -1) {
					k_not.add(j);
				}
			}
			for (int j = 0; j < x; j++) {
				if (Data.not_result == true) {
					break;
				}
				for (int t : k_not) {

					if ((end_matrix[j][0] != 0) && (end_matrix[j][t] == 0)
							&& (Data.s.indexOf(j) == -1)) {
						Data.not_result = true;
					} else {
						Data.not_result = false;
						break;
					}
				}
			}
			/* проверка на единственное решение */
			for (int j = 1; j < y; j++) {
				if (Data.k.indexOf(j) == -1) {
					Data.one_result = false;
					break;
				} else {

					Data.one_result = true;
				}
			}
		}
		/* вывод таблиц на страницу */
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				response.getOutputStream(), "UTF8"), true);
		out.println("<HTML><HEAD>"
				+ "<TITLE>");
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
		/* вывод на экран результата */
		if ((Data.result == true)&&(start_matrix[replacement_x-1][replacement_y-1]!=0)) {
			out.println("<h1>picked up the win column</h1>");
			/* вывод результата на экран */

			for (int i = 0; i < x; i++) {

				for (int j = 0; j < y; j++) {

					if (j == 0) {
						if (Data.saveColumn.get(i+1)!=null) {
							Integer t = Data.saveColumn.get(i+1);
							out.print("<br>");
							out.print(" x" + t + "=");
							out.print(end_matrix[i][j]);
						}

					} else {
						if ((Data.k.indexOf(j) == -1)
								&& (end_matrix[i][1] != 0)) {
							out.print("-" + end_matrix[i][j] + "*" + "x" + j);
						}
					}
				}
			}
			int d = 0;
			Collections.sort(Data.s);
			for (int column : Data.s) {
					d = d + 1;
					out.print("<br>");
					out.print("x" + (column+1) + "=" + "a" + d);
			}
		}

		/* вывод несовместимости системы */
		if ((Data.not_result == true)&&(start_matrix[replacement_x-1][replacement_y-1]!=0)) {
			out.print("system is not compatible");
		}
		/* вывод единственного решения */
		if ((Data.one_result == true)&&(start_matrix[replacement_x-1][replacement_y-1]!=0)) {
			out.print("the system has one solution");
			Set<Map.Entry<Integer, Integer>> set = Data.saveColumn.entrySet();
			int i=-1;
			for (Map.Entry<Integer, Integer> me : set) {
				i=i+1;
				out.print("<br>");
				out.print("x" + me.getValue() + "=" + end_matrix[i][0]);
			}
		}
		/*обработка ввода 0*/
		if (start_matrix[replacement_x-1][replacement_y-1]==0) {
			out.print("iput 0!!!");
			end_matrix=start_matrix;
			Data.one_result=false;
			Data.not_result=false;
			Data.result=false;
		}
		out.println("<TABLE border=1px>");
		if ((Data.result == true) || (Data.not_result == true)
				|| (Data.one_result == true)) {
			out.println("<TABLE border=1px>");
			for (int i = 0; i < x; i++) {
				out.println("<tr>");
				for (int j = 0; j < y; j++) {
					if ((Data.k.indexOf(j) == -1) ||(j == 0)) {
						out.println("<td>");
						out.println(end_matrix[i][j]);
						out.println("</td>");
					}
				}
				out.println("</tr>");
			}
			out.println("</TABLE>");
		} else 
		if (start_matrix[replacement_x-1][replacement_y-1]==0){
			for (int i = 0; i < x; i++) {
				out.println("<tr>");
				for (int j = 0; j < y; j++) {
						out.println("<td>");
						out.println("<form name='form "
								+ i
								+ "/"
								+ j
								+ "' action='JardanExceptionCalc' method='post'"
								+ "onsubmit='return lert('"+i+"|"+j+"|"+k+"','"+k+"')'>");
						out.println("<button type='submit' name='input_form' value='"
								+ i
								+ "|"
								+ j
								+ "|"
								+ k
								+ "' "
								+ "id='"
								+ i
								+ "|"
								+ j
								+ "|"
								+ k
								+ "' size='2'/>"
								+ end_matrix[i][j] + "</button>");
						out.println("<span style='color: red' id='"+k+"'></span><br />");
						out.println("</form>");
						out.println("</td>");
						k = k + 1;
				}
				out.println("</tr>");

			}
			Data.s_matrix = end_matrix;
			out.println("</TABLE>");
		}
		else{

			for (int i = 0; i < x; i++) {
				out.println("<tr>");
				for (int j = 0; j < y; j++) {
					if ((Data.k.indexOf(j) == -1) || (j == 0)) {
						out.println("<td>");
						out.println("<form name='form "
								+ i
								+ "/"
								+ j
								+ "' action='JardanExceptionCalc' method='post'"
								+ "onsubmit='return lert('"+i+"|"+j+"|"+k+"','"+k+"')'>");
						out.println("<button type='submit' name='input_form' value='"
								+ i
								+ "|"
								+ j
								+ "|"
								+ k
								+ "' "
								+ "id='"
								+ i
								+ "|"
								+ j
								+ "|"
								+ k
								+ "' size='2'/>"
								+ end_matrix[i][j] + "</button>");
						out.println("<span style='color: red' id='"+k+"'></span><br />");
						out.println("</form>");
						out.println("</td>");
						k = k + 1;
					}
				}
				out.println("</tr>");

			}
			Data.s_matrix = end_matrix;
			out.println("</TABLE>");
		}
		out.println(""
				+ "<script type='text/javascript'>"
				+ "function  lert(id,k){n = document.document.getElementById(id).innerHTML;"
				+ "if(n==0){"
				+ "document.getElementById(k).innerHTML = 'вы ввели 0';"
						+ "return false;"
						+ "}"
						+ "}"
						+ "</script></BODY></HTML>");
		out.close();
		
		/* приравниваем переменную индикатора повтора к true */
		Data.retry = true;

	}

}
