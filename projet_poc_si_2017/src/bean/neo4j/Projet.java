package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Projet {
	private String nom;
	private List<Avance> avances;

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

}
