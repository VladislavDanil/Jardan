

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JardanException
 */
public class JardanException extends HttpServlet {
	public String x_start;
	public String y_start;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JardanException() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Data.x_start = new String(request.getParameter("x_start").getBytes("ISO-8859-1"),
				"UTF-8");
		Data.y_start = new String(request.getParameter("y_start").getBytes("ISO-8859-1"),
				"UTF-8");
		x_start = new String(request.getParameter("x_start").getBytes("ISO-8859-1"),
				"UTF-8");
		y_start = new String(request.getParameter("y_start").getBytes("ISO-8859-1"),
				"UTF-8");
		request.setAttribute("x_start", x_start);
		request.setAttribute("y_start", y_start);
		request.getRequestDispatcher("Input.jsp").forward(request,
				response);
        
	}

}
