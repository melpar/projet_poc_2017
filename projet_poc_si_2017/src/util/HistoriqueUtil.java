package util;

import java.util.Date;

import javax.servlet.http.HttpSession;

import base.BaseMongoDB;
import bean.mongdb.HistoriqueConnexion;

public class HistoriqueUtil {
	int idConnexion;

	public HistoriqueUtil(HttpSession session) {
		Date dateConnexion = new Date();
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		idConnexion = mongo.createIdConnexion();
		String systeme = System.getProperties().getProperty("os.name");
		// String navigateur = request.getHeader("User-Agent");
		String navigateur = null;
		HistoriqueConnexion historique;
		if (session.getAttribute("mail") == null) {
			historique = new HistoriqueConnexion(idConnexion, systeme, navigateur, dateConnexion);
		} else {
			int idUtilisateur = getIdUtilisateur((String) session.getAttribute("mail"));
			historique = new HistoriqueConnexion(idConnexion, idUtilisateur, systeme, navigateur, dateConnexion);
		}
		mongo.ajoutHistoriqueConnexion(historique);
		mongo.fermer();
	}

	public static HistoriqueUtil creer(HttpSession session) {
		HistoriqueUtil histo = (HistoriqueUtil) session.getAttribute("histoUtil");
		if (histo == null) {
			// si mail == null => génération d'id
			// if(session.getAttribute("mail") == null)
			// histo = new HistoriqueUtil(session.getAttribute("mail"));
			histo = new HistoriqueUtil(session);
			session.setAttribute("histoUtil", histo);
		}
		return histo;
	}

	public void setIdUtilisateur(int idUtilisateur) {

		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		mongo.updateConnexion(idConnexion, idUtilisateur);
		mongo.fermer();
	}

	public void addPageHistorique(String url) {
		Date date = new Date();
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		mongo.updateConnexion(idConnexion, date, url);
		mongo.fermer();
	}

	public void setDeconnexion() {
		Date date = new Date();
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		mongo.updateConnexion(idConnexion, date);
		mongo.fermer();
	}

	/*
	 * public void historique(String url, String systeme, String navigateur) { //
	 * String url = request.getParameter("url"); Date date = new Date(); BaseMongoDB
	 * mongo = new BaseMongoDB(); mongo.ouvrir(); // TODO : à rajouter dans la JSP
	 * mongo.createIdConnexion(), int idConnexion; if
	 * (request.getSession().getAttribute("idConnexion") == null) {
	 * request.getSession().setAttribute("idConnexion", mongo.createIdConnexion());
	 * idConnexion = (int) request.getSession().getAttribute("idConnexion"); if
	 * (request.getSession().getAttribute("idUtilisateur") == null) { String systeme
	 * = System.getProperties().getProperty("os.name"); String navigateur =
	 * request.getHeader("User-Agent"); HistoriqueConnexion historique = new
	 * HistoriqueConnexion(idConnexion, systeme, navigateur, date);
	 * mongo.ajoutHistoriqueConnexion(historique); } else { int idUtilisateur =
	 * (int) request.getSession().getAttribute("idConnexion"); String systeme =
	 * System.getProperties().getProperty("os.name"); String navigateur =
	 * request.getHeader("User-Agent"); HistoriqueConnexion historique = new
	 * HistoriqueConnexion(idConnexion, idUtilisateur, systeme, navigateur, date);
	 * mongo.ajoutHistoriqueConnexion(historique); } } else { idConnexion = (int)
	 * request.getSession().getAttribute("idConnexion"); }
	 * mongo.updateConnexion(idConnexion, date, url); mongo.fermer();
	 * response.getWriter().append("Served at: ").append(request.getContextPath());
	 * }
	 */

	int getIdUtilisateur(String mail) {
		return 0;
	}
}
