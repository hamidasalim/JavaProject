package web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import java.util.List;
import dao.GestionClubImpJpa;
import dao.GestionEventImpJpa;
import dao.GestionFaculteImpJpa;
import dao.GestionUserImpJpa;
import dao.IGestionClub;
import dao.IGestionEvent;
import dao.IGestionFaculte;
import dao.IGestionUser;
import dao.entities.Club;
import dao.entities.Event;
import dao.entities.Faculte;
import dao.entities.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    IGestionClub gClub;
    IGestionEvent gEvent;
    IGestionFaculte gFaculte;
    IGestionUser gUser;
    
    
	public void init(ServletConfig config) throws ServletException {
		gClub=new GestionClubImpJpa();
		gEvent = new GestionEventImpJpa();
		gFaculte = new GestionFaculteImpJpa();
		gUser = new GestionUserImpJpa();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		
		if (session !=null)
		{
			String action = request.getParameter("action");
			if(action == null  ) {
				List<Event> liste = gEvent.getAllEvents();
				int itemsPerPage = 5; // Number of items to display per page
				int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

				request.setAttribute("listeE", liste);
				int totalEvents = liste.size();
			    int totalPages = (int) Math.ceil((double) totalEvents / itemsPerPage);

			    int startIndex = (currentPage - 1) * itemsPerPage;
			    int endIndex = Math.min(startIndex + itemsPerPage, totalEvents);

			    List<Event> eventsForPage = liste.subList(startIndex, endIndex);

			    request.setAttribute("listeE", eventsForPage);
			    request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("Events.jsp").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postaddFaculte") )
			{
				String nomFac=request.getParameter("nomFac");
				gFaculte.ajouterFaculte(new Faculte (nomFac));
			
				//response.sendRedirect(request.getContextPath()+"/Controller");
				request.getRequestDispatcher("/Controller?action=listeFaculte").forward(request, response);


			}
			else if(action.equalsIgnoreCase("postrechercherFaculte") )
			{
				String txt = request.getParameter("rech");
				request.setAttribute("listeF", gFaculte.getFaculteParNom(txt));
				request.getRequestDispatcher("Facultes.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("postmodifierFaculte") )
			{
				int id = Integer.parseInt(request.getParameter("id"));
				String nomFac = request.getParameter("nomFac");

				
				gFaculte.modifFaculte(new Faculte (id,nomFac));
				//response.sendRedirect(request.getContextPath()+"/listeFaculte");
				request.getRequestDispatcher("Controller?action=listeFaculte").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postaddClub") )
			{
				String nomClub=request.getParameter("nomClub");
				String descriptionClub=request.getParameter("descriptionClub");
				int idfaculte = Integer.parseInt(request.getParameter("idfaculte"));
				Faculte f = gFaculte.rechercherParId(idfaculte);
				gClub.ajouterClub(new Club (nomClub,descriptionClub,f));
				//response.sendRedirect(request.getContextPath()+"/listeClub");
				request.getRequestDispatcher("Controller?action=listeClub").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postrechercherClub") )
			{
				String txt = request.getParameter("rech");
				request.setAttribute("listeC", gClub.getClubParNom(txt));
				request.getRequestDispatcher("Clubs.jsp").forward(request, response);
				
				

			}
			else if(action.equalsIgnoreCase("postmodifierClub") )
			{
				int id = Integer.parseInt(request.getParameter("id"));
				String nomClub = request.getParameter("nomClub");
				String descriptionClub = request.getParameter("descriptionClub");
				int idfaculte = Integer.parseInt(request.getParameter("idfaculte"));
				Faculte f = gFaculte.rechercherParId(idfaculte);				
				gClub.modifClub(new Club (id,nomClub,descriptionClub,f));
				request.getRequestDispatcher("Controller?action=listeClub").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postaffecterUserClub") )
			{
				int idclub = Integer.parseInt(request.getParameter("idclub"));
				
				int iduser = Integer.parseInt(request.getParameter("iduser"));
				
				gClub.affecterUserClub(iduser, idclub);
				//response.sendRedirect(request.getContextPath()+"/listeClub");
				request.getRequestDispatcher("Controller?action=listeClub").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postaddEvent") )
			{
				String nomEvent=request.getParameter("nomEvent");
				String descriptionEvent=request.getParameter("descriptionEvent");
				int idclub = Integer.parseInt(request.getParameter("idclub"));
				Club c = gClub.rechercherParId(idclub);
				gEvent.ajouterEvent(new Event (nomEvent,descriptionEvent,c));
				//response.sendRedirect(request.getContextPath()+"/listeEvent");
				request.getRequestDispatcher("Controller?action=listeEvent").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postrechercherEvent") )
			{
				String txt = request.getParameter("rech");
				request.setAttribute("listeE", gEvent.getEventParNom(txt));
				request.getRequestDispatcher("Events.jsp").forward(request, response);
				

			}
			else if(action.equalsIgnoreCase("postmodifierEvent") )
			{
				int id = Integer.parseInt(request.getParameter("id"));
				String nomEvent = request.getParameter("nomEvent");
				String descriptionEvent = request.getParameter("descriptionEvent");
				int idclub = Integer.parseInt(request.getParameter("idclub"));
				Club c = gClub.rechercherParId(idclub);				
				gEvent.modifEvent(new Event (id,nomEvent,descriptionEvent,c));
				//response.sendRedirect(request.getContextPath()+"/listeEvent");
				request.getRequestDispatcher("Controller?action=listeEvent").forward(request, response);

			}
			else if(action.equalsIgnoreCase("postrechercherUser") )
			{
				String txt = request.getParameter("rech");
				request.setAttribute("listeU", gUser.getUserParNom(txt));
				request.getRequestDispatcher("Users.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("postmodifierUser"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				String username = request.getParameter("username");
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				String role = request.getParameter("role");

				
				gUser.modifUser(new User (id,username,login,password,role));
				//response.sendRedirect(request.getContextPath()+"/listeUser");
				request.getRequestDispatcher("Controller?action=listeUser").forward(request, response);

			}
			else if(action.equalsIgnoreCase("addFaculte"))
			{
				request.getRequestDispatcher("ajouterFaculte.jsp").forward(request, response);

			}
			
		
		
			else if (action.equalsIgnoreCase("supprimerFaculte"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gFaculte.supprimerFaculte(id);
				//response.sendRedirect(request.getContextPath()+"/listeFaculte");
				request.getRequestDispatcher("Controller?action=listeFaculte").forward(request, response);

			}
			else if (action.equalsIgnoreCase("modifierFaculte")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("faculte", gFaculte.rechercherParId(id));
				request.getRequestDispatcher("modifierFaculte.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("listeClub"))
			{
				List<Club> liste = gClub.getAllClubs();
				int itemsPerPage = 5; // Number of items to display per page
				int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

				request.setAttribute("listeC", liste);
				int totalClubs = liste.size();
			    int totalPages = (int) Math.ceil((double) totalClubs / itemsPerPage);

			    int startIndex = (currentPage - 1) * itemsPerPage;
			    int endIndex = Math.min(startIndex + itemsPerPage, totalClubs);

			    List<Club> clubsForPage = liste.subList(startIndex, endIndex);

			    request.setAttribute("listeC", clubsForPage);
			    request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("Clubs.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("listeEvent"))
			{
					List<Event> liste = gEvent.getAllEvents();
					int itemsPerPage = 5; // Number of items to display per page
					int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

					request.setAttribute("listeE", liste);
					int totalEvents = liste.size();
				    int totalPages = (int) Math.ceil((double) totalEvents / itemsPerPage);

				    int startIndex = (currentPage - 1) * itemsPerPage;
				    int endIndex = Math.min(startIndex + itemsPerPage, totalEvents);

				    List<Event> eventsForPage = liste.subList(startIndex, endIndex);

				    request.setAttribute("listeE", eventsForPage);
				    request.setAttribute("totalPages", totalPages);
				    request.setAttribute("currentPage", currentPage);
					request.getRequestDispatcher("Events.jsp").forward(request, response);

			}
			else if (action.equalsIgnoreCase("addClub"))
			{
				request.setAttribute("facultes", gFaculte.getAllFacultes());
				request.getRequestDispatcher("ajouterClub.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("supprimerClub"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gClub.supprimerClub(id);
				//response.sendRedirect(request.getContextPath()+"/listeClub");
				request.getRequestDispatcher("Controller?action=listeClub").forward(request, response);

			}
			else if (action.equalsIgnoreCase("modifierClub"))
			{
				System.out.println(request.getParameter("id"));
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("club", gClub.rechercherParId(id));
				request.setAttribute("facultes", gFaculte.getAllFacultes());
				request.getRequestDispatcher("modifierClub.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("affecterUserClub"))
			{
				request.setAttribute("clubs", gClub.getAllClubs());
				request.setAttribute("users", gUser.getAllUsers());
				request.getRequestDispatcher("affecterUserClub.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("listeFaculte"))
			{
				List<Faculte> liste = gFaculte.getAllFacultes();
				int itemsPerPage = 5; 
				int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter
				request.setAttribute("listeF", liste);
				int totalFacultes = liste.size();
			    int totalPages = (int) Math.ceil((double) totalFacultes / itemsPerPage);
			    int startIndex = (currentPage - 1) * itemsPerPage;
			    int endIndex = Math.min(startIndex + itemsPerPage, totalFacultes);
			    List<Faculte> facultesForPage = liste.subList(startIndex, endIndex);
			    request.setAttribute("listeF", facultesForPage);
			    request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("Facultes.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("addEvent")) {
				request.setAttribute("clubs", gClub.getAllClubs());
				request.getRequestDispatcher("ajouterEvent.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("supprimerEvent")) {
				int id = Integer.parseInt(request.getParameter("id"));
				gEvent.supprimerEvent(id);
				//response.sendRedirect(request.getContextPath()+"/listeEvent");
				request.getRequestDispatcher("Controller?action=listeEvent").forward(request, response);

			}
			else if (action.equalsIgnoreCase("modifierEvent"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("event", gEvent.rechercherParId(id));
				request.setAttribute("clubs", gClub.getAllClubs());
				request.getRequestDispatcher("modifierEvent.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("logout"))
			{
					session.invalidate();
					request.getRequestDispatcher("login.jsp").forward(request, response);

			}
			
			else if (action.equalsIgnoreCase("listeUser"))
			{
				List<User> liste = gUser.getAllUsers();
				int itemsPerPage = 4; // Number of items to display per page
				int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

				request.setAttribute("listeU", liste);
				int totalUsers = liste.size();
			    int totalPages = (int) Math.ceil((double) totalUsers / itemsPerPage);

			    int startIndex = (currentPage - 1) * itemsPerPage;
			    int endIndex = Math.min(startIndex + itemsPerPage, totalUsers);

			    List<User> usersForPage = liste.subList(startIndex, endIndex);

			    request.setAttribute("listeU", usersForPage);
			    request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("Users.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("supprimerUser"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gUser.supprimerUser(id);
				//response.sendRedirect(request.getContextPath()+"/listeUser");
				request.getRequestDispatcher("Controller?action=listeUser").forward(request, response);


			}
			else if (action.equalsIgnoreCase("modifierUser"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("user", gUser.rechercherParId(id));
				request.getRequestDispatcher("modifierUser.jsp").forward(request, response);
			}
		


		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
