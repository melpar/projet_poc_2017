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
	private Map<Date,String> pagesVisitées;
	
	public Connexions(double idConnexion, double idUtilisateur, String systeme, String navigateur, Date dateConnexion) {
		super();
		this.idConnexion = idConnexion;
		this.idUtilisateur = idUtilisateur;
		this.systeme = systeme;
		this.navigateur = navigateur;
		this.dateConnexion = dateConnexion;
		this.dateDeconnexion = null;
		this.pagesVisitées = new HashMap<Date,String>();
	}
	public Date getDateDeconnexion() {
		return dateDeconnexion;
	}
	public void setDateDeconnexion(Date dateDeconnexion) {
		this.dateDeconnexion = dateDeconnexion;
	}
	public Map<Date, String> getPagesVisitées() {
		return pagesVisitées;
	}
	public void addPagesVisitées(Date date, String page) {
		this.pagesVisitées.put(date, page);
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
