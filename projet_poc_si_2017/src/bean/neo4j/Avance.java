package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Avance {
	private String nom;
	private List<Terme> termes;

	public Avance() {
		this.termes = new ArrayList<Terme>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addAll(List<Terme> termes2) {
		this.termes.addAll(termes2);
	}

}
