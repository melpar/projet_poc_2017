package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Avance {
	private String nom;
	private List<Terme> termes;
	private double id;

	public Avance() {
		this.termes = new ArrayList<Terme>();
	}

	public String toString() {
		String ret = "";
		ret += "		" + id + " " + nom;
		ret += "\n";
		for (Terme t : termes) {
			ret += t.toString();
			ret += "\n";
		}
		return ret;
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

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

}
