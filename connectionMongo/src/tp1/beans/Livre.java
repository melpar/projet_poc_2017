package tp1.beans;

import annotation.Id;
import annotation.Table;

@Table(name="t_livre")
public class Livre {
	@Id
	private Integer idLivre;
	private String titre;
	private String auteur;
	private int annee;
	public Livre(){
		
	}
	public Livre(String titre,String auteur,int annee){
		this.titre=titre;
		this.auteur=auteur;
		this.annee=annee;
	}

	public String toString() {
		return this.titre + " : " + this.auteur + " : " + this.annee ;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public Integer getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(Integer idLivre) {
		this.idLivre = idLivre;
	}
}
