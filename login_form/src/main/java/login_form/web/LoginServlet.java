package login_form.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login_form.database.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init()
	{
		loginDao = new LoginDao();
	}
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password= request.getParameter("password");
		
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);

		try
		{
			if(loginDao.validate(login))
			{
				response.sendRedirect("loginsuccess.jsp");
			}
			else
			{
				HttpSession session = request.getSession();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception caught: "+e);
		}
	}

}
