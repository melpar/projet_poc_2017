package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Projet {
	private String nom;
	private List<Avance> avances;
	private double id;
	private String titre;
	private String contenu;
	private String kit;
	private boolean listeDiffusion;
	private boolean accesContrat;

	public Avance getAvance(String nom) {
		for (Avance av : avances) {
			if (av.getNom().equals(nom)) {
				return av;
			}
		}
		return null;
	}

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

	public String getKit() {
		return kit;
	}

	public void setKit(String kit) {
		this.kit = kit;
	}

	public boolean isListeDiffusion() {
		return listeDiffusion;
	}

	public void setListeDiffusion(boolean listeDiffusion) {
		this.listeDiffusion = listeDiffusion;
	}

	public boolean isAccesContrat() {
		return accesContrat;
	}

	public void setAccesContrat(boolean accesContrat) {
		this.accesContrat = accesContrat;
	}

}
