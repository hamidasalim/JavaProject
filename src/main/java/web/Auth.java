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
 * Servlet implementation class Auth
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
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
		System.out.println(action);
		if(action == null ) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	
		}
		else {
			HttpSession session=  request.getSession(false);
			if(session != null) {
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/");
				//request.getRequestDispatcher(request.getContextPath()+"/").forward(request, response);
				//request.getRequestDispatcher("Controller").forward(request, response);

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ollllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
		// TODO Auto-generated method stub
		//HttpSession session =  request.getSession(false);
		String login = request.getParameter("login");
		String password =request.getParameter("password");
		String username=request.getParameter("username");
		String role=request.getParameter("role");
		String r= gestion.verification(new User(login,password));
		if(r == null) {
			request.setAttribute("erreur", "login ou password est incorrect");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			HttpSession session =  request.getSession();

			session.setAttribute("login" , login);
			session.setAttribute("password" , password);
			session.setAttribute("role", role);
			session.setAttribute("username", username);
			System.out.println("login done");

			request.getRequestDispatcher("Controller").forward(request, response);
		}
	}

}
