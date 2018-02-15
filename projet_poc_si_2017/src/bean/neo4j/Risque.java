package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Risque {
	private String nom;
	private List<Projet> projets;
	private long id;

	public Risque() {
		this.projets = new ArrayList<Projet>();
	}

	public Projet getProjet(String nom) {
		Projet projet = null;
		for (Projet p : projets) {
			if (p.getNom().equals(nom)) {
				return p;
			}
		}
		return projet;
	}

	public String toString() {
		String ret = "";
		ret += "	" + id + " " + nom;
		ret += "\n";
		for (Projet p : projets) {
			ret += p.toString();
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

	public void addAll(List<Projet> projets2) {
		this.projets.addAll(projets2);

	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
