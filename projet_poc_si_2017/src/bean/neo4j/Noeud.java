package bean.neo4j;

public class Noeud {
	private String nom;
	private String titre;
	private String contenu;
	private String kit;
	private boolean diffusion;
	private boolean contrat;
	private boolean relance;

	public Noeud() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public boolean isDiffusion() {
		return diffusion;
	}

	public void setDiffusion(boolean diffusion) {
		this.diffusion = diffusion;
	}

	public boolean isContrat() {
		return contrat;
	}

	public void setContrat(boolean contrat) {
		this.contrat = contrat;
	}

	public boolean isRelance() {
		return relance;
	}

	public void setRelance(boolean relance) {
		this.relance = relance;
	}
}
