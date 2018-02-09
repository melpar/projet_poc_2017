package bean;

import annotation.Table;

@Table(name = "T_REPONSEQUESTION_REQ")
public class ReponseQuestion {

	private String req_texte;

	public ReponseQuestion(String texte) {
		super();
		this.req_texte = texte;

	}

	public String getTexte() {
		return req_texte;
	}

}
