package bean.mariadb;

import annotation.Id;
import annotation.Table;

@Table(name = "T_REPONSEPERSONNE_REP")
public class ReponsePersonne {
	@Id
	private int idQuestion;
	private String valeur;

	public ReponsePersonne(int idQuestion, String valeur) {
		super();
		this.idQuestion = idQuestion;
		this.valeur = valeur;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public String getValeur() {
		return valeur;
	}

}
