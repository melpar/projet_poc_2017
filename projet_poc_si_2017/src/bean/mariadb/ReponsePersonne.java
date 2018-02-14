package bean.mariadb;

import annotation.Table;

@Table(name = "T_REPONSEPERSONNE_REP")
public class ReponsePersonne {
	private int idQuestion;
	private String valeur;

	public ReponsePersonne() {

	}

	public ReponsePersonne(int idQuestion, String valeur) {
		this.idQuestion = idQuestion;
		this.valeur = valeur;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public String getValeur() {
		return valeur;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

}
