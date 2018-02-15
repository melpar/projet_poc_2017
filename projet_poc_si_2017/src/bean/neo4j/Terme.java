package bean.neo4j;

public class Terme {
	private String nom;
	private double id;
	private String titre;
	private String contenu;
	private boolean listeDiffusion;
	private boolean accesContrat;
	private boolean mailRelance;

	public String toString() {
		String ret = "";
		ret += "			" + id + " " + nom;
		ret += "\n";
		return ret;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public boolean isMailRelance() {
		return mailRelance;
	}

	public void setMailRelance(boolean mailRelance) {
		this.mailRelance = mailRelance;
	}

}
