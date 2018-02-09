package test;

import java.util.List;

import base.BaseMariaDB;
import bean.Personne;

public class TestMariaDB {

	public static void main(String[] args) {
		BaseMariaDB b = new BaseMariaDB();
		b.ouvrir();
		List<Personne> personnes = b.getPersonnes();
		for (Personne p : personnes) {
			System.out.println(p.getPer_id() + " " + p.getPer_nom() + " " + p.getPer_prenom() + " " + p.isPer_risque());
		}

	}

}
