package bean.mariadb;

import annotation.Table;

@Table(name = "T_REPONSEQUESTION_REQ")
public class ReponseQuestion {

	private String req_texte;

	public String getReq_texte() {
		return req_texte;
	}

	public void setReq_texte(String req_texte) {
		this.req_texte = req_texte;
	}

}
