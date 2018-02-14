package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Projet {
	private String nom;
	private List<Avance> avances;
	private double id;

	public String toString() {
		String ret = "";
		ret += "		" + id + " " + nom + "\n";
		for (Avance a : avances) {
			ret += a.toString();
		}
		return ret;
	}

	public Projet() {
		this.avances = new ArrayList<>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Avance> getAvances() {
		return avances;
	}

	public void setAvances(List<Avance> avances) {
		this.avances = avances;
	}

	public void addAll(List<Avance> avances) {
		this.avances.addAll(avances);
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

}
