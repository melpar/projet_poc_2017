package test;

import bean.neo4j.Noeud;
import util.PagePersonnaliseeUtil;

public class NoeudTest {
	public static void main(String[] args) {
		Noeud noeud = PagePersonnaliseeUtil.getNoeud("user1@gmail.com");
		System.out.println(noeud);
		System.out.println(noeud.getContenu() + " " + noeud.getKit() + " " + noeud.getNom() + " " + noeud.getTitre());
	}
}
