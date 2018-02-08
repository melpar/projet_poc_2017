package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Risque {
	private String nom;
	private List<Projet> projets;

	public Risque() {
		this.projets = new ArrayList<Projet>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addAll(List<Projet> projets2) {
		this.projets.addAll(projets2);

	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
}
