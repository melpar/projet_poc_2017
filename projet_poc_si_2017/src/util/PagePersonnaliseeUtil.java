package util;

import bean.neo4j.ChoixPersonne;
import bean.neo4j.Noeud;

public class PagePersonnaliseeUtil {

	public static Noeud getNoeud(String mail) {
		ChoixPersonne choix = new ChoixPersonne(mail);
		Noeud noeud = new Noeud(choix);
		return noeud;
	}
}
