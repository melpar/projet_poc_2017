package bean;

import annotation.Id;
import annotation.Table;

@Table(name = "T_CONNEXION_CON")
public class Connexion {
	@Id
	private String con_idMail;
	private String con_motDePasse;

	public String getCon_idMail() {
		return con_idMail;
	}

	public void setCon_idMail(String con_idMail) {
		this.con_idMail = con_idMail;
	}

	public String getCon_motDePasse() {
		return con_motDePasse;
	}

	public void setCon_motDePasse(String con_motDePasse) {
		this.con_motDePasse = con_motDePasse;
	}
}
