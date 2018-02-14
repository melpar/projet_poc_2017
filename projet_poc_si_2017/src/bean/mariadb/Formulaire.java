package bean.mariadb;

import java.util.ArrayList;
import java.util.List;

public class Formulaire {

	List<Question> listeQuesstion;

	public Formulaire() {
		this.listeQuesstion = new ArrayList<Question>();
	}

	public void ajouterQuestion(Question q) {
		this.listeQuesstion.add(q);
	}

	public List<Question> getListeQuesstion() {
		return listeQuesstion;
	}

}
