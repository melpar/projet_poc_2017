package tp1.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import annotation.Id;
import annotation.Table;

@Table(name="t_connexions")
public class Connexions {
	@Id
	private double idConnexion;
	private double idUtilisateur;
	private String systeme;
	private String navigateur;
	private Date dateConnexion ;
	private Date dateDeconnexion;
	private Map<Date,String> pagesVisit�es;
	
	public Connexions(double idConnexion, double idUtilisateur, String systeme, String navigateur, Date dateConnexion) {
		super();
		this.idConnexion = idConnexion;
		this.idUtilisateur = idUtilisateur;
		this.systeme = systeme;
		this.navigateur = navigateur;
		this.dateConnexion = dateConnexion;
		this.dateDeconnexion = null;
		this.pagesVisit�es = new HashMap<Date,String>();
	}
	public Date getDateDeconnexion() {
		return dateDeconnexion;
	}
	public void setDateDeconnexion(Date dateDeconnexion) {
		this.dateDeconnexion = dateDeconnexion;
	}
	public Map<Date, String> getPagesVisit�es() {
		return pagesVisit�es;
	}
	public void addPagesVisit�es(Date date, String page) {
		this.pagesVisit�es.put(date, page);
	}
	public double getIdConnexion() {
		return idConnexion;
	}
	public double getIdUtilisateur() {
		return idUtilisateur;
	}
	public String getSysteme() {
		return systeme;
	}
	public String getNavigateur() {
		return navigateur;
	}
	public Date getDateConnexion() {
		return dateConnexion;
	}
}
