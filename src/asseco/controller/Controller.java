package asseco.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asseco.beans.IncidentiBean;
import asseco.beans.KorisniciBean;
import asseco.beans.PomocniBean;
import asseco.dao.IncidentiDAO;
import asseco.dao.KorisniciDAO;
import asseco.dto.Incidenti;

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
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");		
		String address = "WEB-INF/pages/login.jsp";
		System.out.println("action = " + action);
		
		
		HttpSession session = request.getSession();
		System.out.println((PomocniBean)session.getAttribute("pomocniBean"));
		KorisniciBean logovaniKorisnik = (KorisniciBean) session.getAttribute("logovanKorisnik");
		session.setAttribute("notification", "");
		
		if ((action == null) || (action.equals("")))
		{
			
			if (logovaniKorisnik != null && logovaniKorisnik.isLogedIn())
			{
				address = "WEB-INF/pages/incidenti.jsp";
			}
			else
				address = "WEB-INF/pages/login.jsp";
			
			request.getRequestDispatcher(address).forward(request, response);
		}
		
		else if ("incidenti".equals(action)) {
			PomocniBean pomocni = (PomocniBean) session.getAttribute("pomocniBean");
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
			session.setAttribute("incidentiBeanLista", inc);
			
			address = "WEB-INF/pages/incidenti.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}		
		///////////////////////////////////////
		//               LOGIN
		///////////////////////////////////////		
		else if ("login".equals(action))
		{
			
			if (logovaniKorisnik != null && logovaniKorisnik.isLogedIn())
			{
				PomocniBean pomocni = (PomocniBean) session.getAttribute("pomocniBean");
				IncidentiBean inc = new IncidentiBean();
				inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
				session.setAttribute("incidentiBeanLista", inc);
				
				address = "WEB-INF/pages/incidenti.jsp";
				request.getRequestDispatcher(address).forward(request, response);
			}
			else 
			{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				logovaniKorisnik = new KorisniciBean();
				if(username != null && password != null && logovaniKorisnik.login(username, password)) {
					address = "WEB-INF/pages/incidenti.jsp";
					PomocniBean pomocni = new PomocniBean("&uarr;", "1", "50", "All");
					session.setAttribute("logovanKorisnik", logovaniKorisnik);
					session.setAttribute("pomocniBean", pomocni);
					IncidentiBean inc = new IncidentiBean();
					inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
					session.setAttribute("incidentiBeanLista", inc);
				}
				else
					session.setAttribute("notification", "Korisnicko ime i lozinka nisu ispravni.");
				
				request.getRequestDispatcher(address).forward(request, response);
			}		
		}
		
		///////////////////////////////////////
		//               lgoout
		//////////////////////////////////////
		else if ("logout".equals(action)) {
			session.invalidate();
			address ="WEB-INF/pages/login.jsp";
			
			request.getRequestDispatcher(address).forward(request, response);
		}

		///////////////////////////////////////
		//               Paging
		//////////////////////////////////////
		
		else if ("paging".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			String start = request.getParameter("startPage");
			String end = request.getParameter("endPage");
			address = "WEB-INF/pages/incidenti.jsp";
			
			PomocniBean pomocni = (PomocniBean)session.getAttribute("pomocniBean");
			pomocni.setStartPaging(start);
			pomocni.setEndPaging(end);
			session.setAttribute("pomocniBean", pomocni);
			
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
			session.setAttribute("incidentiBeanLista", inc);
			
			response.sendRedirect("Controller");
			//request.getRequestDispatcher(address).forward(request, response);
			
		}

		///////////////////////////////////////
		//         Fitriranje po imenu
		//////////////////////////////////////
		
		else if ("filterByName".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			String filter = request.getParameter("filterCteatedBy");
			address = "WEB-INF/pages/incidenti.jsp";
			PomocniBean pomocni = (PomocniBean)session.getAttribute("pomocniBean");
			pomocni.setFilterCreatedBy(filter);
			session.setAttribute("pomocniBean", pomocni);
			
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
			session.setAttribute("incidentiBeanLista", inc);
			
			response.sendRedirect("Controller");
			//request.getRequestDispatcher(address).forward(request, response);
			
			
		}

		///////////////////////////////////////
		//       Sortiranje po datumu
		//////////////////////////////////////
		
		else if ("sort".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			address = "WEB-INF/pages/incidenti.jsp";
			PomocniBean pomocni = (PomocniBean)session.getAttribute("pomocniBean");
			if ("&darr;".equals(pomocni.getSort()))
				pomocni.setSort("&uarr;");
			else pomocni.setSort("&darr;");
			session.setAttribute("pomocniBean", pomocni);
			
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
			session.setAttribute("incidentiBeanLista", inc);
			
			response.sendRedirect("Controller");
			//request.getRequestDispatcher(address).forward(request, response);
		}
		
		///////////////////////////////////////
		//      Unos novog incidenta
		//////////////////////////////////////
		
		
		else if ("noviIncident".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			address = "WEB-INF/pages/new.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		
		else if ("insertIncident".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			String taskName = request.getParameter("taskName");
			String client = request.getParameter("client");
			if (client.equals("(none)"))
				client = "";
			String project = request.getParameter("project");
			String taskType = request.getParameter("taskType");
			String brojZahtjeva = request.getParameter("brojZahteva");
			String work = request.getParameter("work");
			String startDate = request.getParameter("datum");
			String productBSW = request.getParameter("productBSW");
			String status = request.getParameter("status");
			String paymentStatus = request.getParameter("payment");
			
//			System.out.println("taskname :" + taskName + ", client:"+ client + ", project:"+ project + ", tasktype:"+ taskType 
//					+ ", brojZahtjeva:"	+ brojZahtjeva + ", work:"+ work + ", startDate:"+ startDate + ", productBSW:"+ productBSW 
//					+ ", status:" + status + ", paymentStatus:" + paymentStatus);
			IncidentiDAO.insertIncident(client, project, brojZahtjeva, taskType, taskName, work, startDate, 
					logovaniKorisnik.getKorisnik().getId(), status, paymentStatus, productBSW);
			
			PomocniBean pomocni = (PomocniBean)session.getAttribute("pomocniBean");
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
			session.setAttribute("incidentiBeanLista", inc);

			address = "/incidenti.jsp";	
			response.sendRedirect("Controller");
		}
		///////////////////////////////////////
		//      brisanje incidenta
		///////////////////////////////////////
		else if ("deleteIncident".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			String id = request.getParameter("id");
			IncidentiDAO.deleteIncident(id);
			System.out.println("delete id = " + id);
			IncidentiBean inc = (IncidentiBean)session.getAttribute("incidentiBeanLista");
			ArrayList<Incidenti> lista = inc.getLista();
			for (Incidenti incident : lista) {
				if (incident.getId() == Integer.parseInt(id) )
					{
						lista.remove(incident);
						session.setAttribute("incidentiBeanLista", inc);
						break;
					}
				
			}
			return;
		}
		///////////////////////////////////////
		//      brisanje incidenta
		///////////////////////////////////////
		else if ("updateCompleated".equals(action) && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			String id = request.getParameter("id");
			String status = request.getParameter("vrijednost");
			IncidentiDAO.updateStatus(id,status);
			System.out.println("update id = " + id + " vrijednost statusa je " + status);
			return;
		}
		///////////////////////////////////////
		//      izmjena incidenta
		///////////////////////////////////////
		else if (action.startsWith("izmjenaIncidenta_") && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			String id = action.split("_")[1];
			IncidentiBean incBean = new IncidentiBean();
			incBean.setIncidentZaModifikaciju(id);
			session.setAttribute("inc", incBean);
			
			address = "WEB-INF/pages/edit.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else if (action.startsWith("izmijeniIncident") && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			
			String taskName = request.getParameter("taskName");
			String client = request.getParameter("client");
			if (client.equals("(none)"))
				client = "";
			String project = request.getParameter("project");
			String taskType = request.getParameter("taskType");
			String brojZahtjeva = request.getParameter("brojZahteva");
			String work = request.getParameter("work");
			String startDate = request.getParameter("datum");
			String productBSW = request.getParameter("productBSW");
			String status = request.getParameter("status");
			String paymentStatus = request.getParameter("payment");
			
			IncidentiBean incBean = (IncidentiBean) session.getAttribute("inc");
			int idIncidenta = incBean.getInc().getId();
			int idKorisnika = logovaniKorisnik.getKorisnik().getId();
			System.out.println(startDate);
			IncidentiDAO.updateIncident(client, project, brojZahtjeva, taskType, taskName, work, 
					startDate, status, paymentStatus, productBSW, idKorisnika, idIncidenta);
			
			PomocniBean pomocni = (PomocniBean) session.getAttribute("pomocniBean");
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidents(pomocni.getSort(), pomocni.getStartPaging(), pomocni.getEndPaging(), pomocni.getFilterCreatedBy());
			session.setAttribute("incidentiBeanLista", inc);
					
			
			address = "WEB-INF/pages/incidenti.jsp";	
			response.sendRedirect("Controller");
			//request.getRequestDispatcher(address).redirect() forward(request, response);
		}
		////////////////////////////////////////
		//		promjenaLozinke
		////////////////////////////////////////
		else if (action.equals("promjenaLozinke")  && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			address = "WEB-INF/pages/password.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else if (action.equals("promjeniLozinku")  && logovaniKorisnik != null && logovaniKorisnik.isLogedIn()) {
			
			logovaniKorisnik.getKorisnik().getId();
			String password = request.getParameter("password");
			KorisniciDAO.changePassword(logovaniKorisnik.getKorisnik().getId(), password);
			address = "WEB-INF/pages/incidenti.jsp";
			request.getRequestDispatcher(address).forward(request, response);
			
			
		}
		////////////////////////////////////////
		//Dodavanje projekta
		////////////////////////////////////////
		else if ("dodajProjekatPage".equals(action) && logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn()) {
			address = "WEB-INF/admin/projectAdd.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else if ("dodajProjekat".equals(action) && logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn()) {
			String projekat = request.getParameter("projekat");
			new PomocniBean().insertPorject(projekat);
			
			address = "WEB-INF/pages/incidenti.jsp";	
			response.sendRedirect("Controller");
		}
		////////////////////////////////////////
		//Dodavanje Clienta
		////////////////////////////////////////
		else if ("dodajClientPage".equals(action) && logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn()) {
			address = "WEB-INF/admin/clientAdd.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else if ("dodajClient".equals(action) && logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn()) {
			String client = request.getParameter("client");
			new PomocniBean().insertClient(client);
			
			address = "WEB-INF/pages/incidenti.jsp";	
			response.sendRedirect("Controller");
		}
		////////////////////////////////////////
		//Dodavanje radnika
		////////////////////////////////////////
		else if ("dodajRadnikaPage".equals(action) && logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn()) {
			address = "WEB-INF/admin/userAdd.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else if ("dodajRadnika".equals(action) && logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn()) {
			String username = request.getParameter("username");
			String imeIPrezime = request.getParameter("imeIPrezime");
			String[] admin = request.getParameterValues("admin");
			int adminn = 0;
			
			if(admin != null)
				adminn = 1;
			
			if(KorisniciDAO.usernameExists(username)) {
				session.setAttribute("notification", "Korisnik sa tim korisnickim imenom vec postoji");
				address = "WEB-INF/admin/userAdd.jsp";
				request.getRequestDispatcher(address).forward(request, response);
			}
			else {
			
			KorisniciDAO.insertKorisnik(username,imeIPrezime,adminn);
			session.setAttribute("notification", "");
			address = "WEB-INF/pages/incidenti.jsp";	
			response.sendRedirect("Controller");
			}
		}
		////////////////////////////////////////
		//izvjestaj
		////////////////////////////////////////
		else if ("pravljenjeIzvjestaja".equals(action)&& logovaniKorisnik != null && logovaniKorisnik.getKorisnik().getAdmin() > 0  && logovaniKorisnik.isLogedIn())
		{
			PomocniBean pomocni = (PomocniBean) session.getAttribute("pomocniBean");
			IncidentiBean inc = new IncidentiBean();
			inc.getIncidentsForReport();
			session.setAttribute("incidentiBeanLista", inc);
			
			address = "WEB-INF/pages/izvjestaj.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else
			request.getRequestDispatcher(address).forward(request, response);
	}

}
