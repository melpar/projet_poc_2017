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
	private String que_question;
	private boolean que_typeMultiple;

	public Question(int id) {
		this.que_id = id;
		this.que_listeReponse = new ArrayList<ReponseQuestion>();
	}

	public Question() {
		this.que_listeReponse = new ArrayList<ReponseQuestion>();
	}

	public void ajouterReponse(String texte) {
		this.que_listeReponse.add(new ReponseQuestion(texte));
	}

	public List<ReponseQuestion> getQue_listeReponse() {
		return que_listeReponse;
	}

	public String getQue_question() {
		return que_question;
	}

	public void setQue_question(String que_question) {
		this.que_question = que_question;
	}

	public int getQue_id() {
		return que_id;
	}

	public void setQue_id(int que_id) {
		this.que_id = que_id;
	}

	public boolean isQue_typeMultiple() {
		return que_typeMultiple;
	}

	public void setQue_typeMultiple(boolean que_typeMultiple) {
		this.que_typeMultiple = que_typeMultiple;
	}

	public void setQue_listeReponse(List<ReponseQuestion> que_listeReponse) {
		this.que_listeReponse = que_listeReponse;
	}

}
