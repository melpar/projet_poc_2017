package test;

import java.util.List;

import base.BaseMariaDB;
import bean.mariadb.Formulaire;
import bean.mariadb.Personne;
import bean.mariadb.Question;
import bean.mariadb.ReponseQuestion;

public class TestMariaDB {

	public static void main(String[] args) {
		BaseMariaDB b = new BaseMariaDB();
		b.ouvrir();
		List<Personne> personnes = b.getPersonnes();
		for (Personne p : personnes) {
			System.out.println(p.getPer_nom() + " " + p.getPer_prenom() + " " + p.isPer_risque());
		}
		b.fermer();
		b.ouvrir();
		Personne p = b.getPersonne("test@gmail.com");

		System.out.println(p.getPer_nom() + " " + p.getPer_prenom() + " " + p.isPer_risque());
		p.getReponses();

		Formulaire f = b.getFormulaire();
		List<Question> liste_question = f.getListeQuesstion();
		for (int i = 0; i < liste_question.size(); i++) {
			System.out.println(liste_question.get(i).getQue_question());
			List<ReponseQuestion> liste_reponse = liste_question.get(i).getQue_listeReponse();
			for (int y = 0; y < liste_reponse.size(); y++) {
				System.out.println(liste_reponse.get(y).getReq_texte());
			}
		}

		b.fermer();
	}

}
