package test;

import java.util.List;

import base.BaseMariaDB;
import bean.mariadb.Personne;

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

		b.fermer();
	}

}
