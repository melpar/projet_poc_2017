package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Avance {
	private String nom;
	private List<Terme> termes;
	private double id;
	private String titre;
	private String contenu;

	public Avance() {
		this.termes = new ArrayList<Terme>();
	}

	public Terme getTerme(boolean isLongTerme) {
		String nom = isLongTerme ? "moyenLongTerme" : "courtTerme";
		for (Terme terme : termes) {
			if (terme.getNom().equals(nom)) {
				return terme;
			}
		}
		return null;
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

	public List<Terme> getTermes() {
		return termes;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

}
