package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.BaseMongoDB;
import bean.mongdb.HistoriqueConnexion;

/**
 * Servlet implementation class Historique
 */
@WebServlet("/Historique")
public class Historique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Historique() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getParameter("url");
		Date date = new Date();
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		// TODO : à rajouter dans la JSP mongo.createIdConnexion(),
		int idConnexion;
		if (request.getSession().getAttribute("idConnexion") == null) {
			request.getSession().setAttribute("idConnexion", mongo.createIdConnexion());
			idConnexion = (int) request.getSession().getAttribute("idConnexion");
			if (request.getSession().getAttribute("idUtilisateur") == null) {
				String systeme = System.getProperties().getProperty("os.name");
				String navigateur = request.getHeader("User-Agent");
				HistoriqueConnexion historique = new HistoriqueConnexion(idConnexion, systeme, navigateur, date);
				mongo.ajoutHistoriqueConnexion(historique);
			} else {
				int idUtilisateur = (int) request.getSession().getAttribute("idConnexion");
				String systeme = System.getProperties().getProperty("os.name");
				String navigateur = request.getHeader("User-Agent");
				HistoriqueConnexion historique = new HistoriqueConnexion(idConnexion, idUtilisateur, systeme,
						navigateur, date);
				mongo.ajoutHistoriqueConnexion(historique);
			}
		} else {
			idConnexion = (int) request.getSession().getAttribute("idConnexion");
		}
		mongo.updateConnexion(idConnexion, date, url);
		mongo.fermer();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
