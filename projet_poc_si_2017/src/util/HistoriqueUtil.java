package util;

import java.util.Date;

import javax.servlet.http.HttpSession;

import base.BaseMongoDB;
import bean.mongdb.HistoriqueConnexion;

public class HistoriqueUtil {
	int idConnexion;

	public static HistoriqueUtil creer(HttpSession session) {
		HistoriqueUtil histo = session.getAttribute("histoUtil");
		if (histo == null) {
			// si mail == null => génération d'id
			histo = new HistoriqueUtil(session.getAttribute("mail"));
			session.setAttribute("", histo);
		}
		return histo;
	}

	public void historique(String url, String systeme, String navigateur) {
		// String url = request.getParameter("url");
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
}
