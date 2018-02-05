package com.project.poc.test;

import java.util.List;

import com.project.poc.server.base.BaseMariaDB;
import com.project.poc.shared.bean.Personne;

public class TestMariaDB {

	public static void main(String[] args) {
		BaseMariaDB b = new BaseMariaDB();
		b.ouvrir();
		List<Personne> personnes = b.getPersonnes();
		for(Personne p : personnes){
			System.out.println(p.getPER_id()+" "+p.getPER_nom()+" "+p.getPER_prenom()+" "+p.isPER_risque());
		}

	}

}
