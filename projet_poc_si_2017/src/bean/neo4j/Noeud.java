package bean.neo4j;

import base.BaseNeo4j;

public class Noeud {
	private String nom;
	private String titre;
	private String contenu;
	private String kit;
	private boolean diffusion;
	private boolean contrat;
	private boolean relance;

	public Noeud(ChoixPersonne choix) {
		BaseNeo4j base = new BaseNeo4j();
		Arbre arbre = base.creerArbre();
		arbre.getRisques();
		Risque risque;
		if (choix.isRas()) {
			risque = arbre.getRisque("RAS");
		} else {
			risque = arbre.getRisque("nonRAS");
		}
		Projet projet;
		if (choix.isAssurance()) {
			projet = risque.getProjet("changerAssurance");
		} else {
			projet = risque.getProjet("faireEmprunt");
		}

		if (projet.getAvances() == null || projet.getAvances().isEmpty()) {
			this.nom = projet.getNom();
			this.titre = projet.getTitre();
			this.contenu = projet.getContenu();
			this.kit = projet.getKit();
			this.diffusion = projet.isListeDiffusion();
			this.contrat = projet.isAccesContrat();
			this.relance = false;
		} else {
			Avance etat = projet.getAvance(choix.getEtat());
			if (etat.getTermes() == null || etat.getTermes().isEmpty()) {
				this.nom = etat.getNom();
				this.titre = etat.getTitre();
				this.contenu = etat.getContenu();
				this.kit = null;
				this.diffusion = true;
				this.contrat = true;
				this.relance = false;
			} else {
				Terme terme = etat.getTerme(choix.isLongTerme());
				this.nom = terme.getNom();
				this.titre = terme.getTitre();
				this.contenu = terme.getContenu();
				this.kit = null;
				this.diffusion = terme.isListeDiffusion();
				this.contrat = terme.isAccesContrat();
				this.relance = terme.isMailRelance();
			}
		}
	}

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
