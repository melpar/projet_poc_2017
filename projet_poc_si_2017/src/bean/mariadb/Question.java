package bean.mariadb;

import java.util.ArrayList;
import java.util.List;

import annotation.Id;
import annotation.Table;

@Table(name = "T_QUESTION_QUE")
public class Question {
	@Id
	int que_id;
	private List<ReponseQuestion> que_listeReponse;

	public Question(int id) {
		this.que_listeReponse = new ArrayList<ReponseQuestion>();
	}

	public void ajouterReponse(String texte) {
		this.que_listeReponse.add(new ReponseQuestion(texte));
	}

	public List<ReponseQuestion> getQue_listeReponse() {
		return que_listeReponse;
	}

}
