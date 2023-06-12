package web;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.GestionUserImpJpa;
import dao.IGestionUser;
import dao.entities.User;

/**
 * Servlet implementation class Regis
 */
@WebServlet("/Regis")
public class Regis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    IGestionUser gestion;
	public void init(ServletConfig config) throws ServletException {
		gestion = new GestionUserImpJpa();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null ) {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	
		}else {
			HttpSession session= (HttpSession) request.getSession(false);
			if(session != null) {
				session.invalidate();
				//request.getRequestDispatcher(request.getContextPath()+"/").forward(request, response);
				request.getRequestDispatcher("Auth").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String login = request.getParameter("login");
		String password =request.getParameter("password");
		String username = request.getParameter("username");
		String role = request.getParameter("role");

		User u = new User(username,login,password,role);
		gestion.registration(u);
		System.out.println("User created ");
		request.getRequestDispatcher("Auth").forward(request, response);

		
	}

}
