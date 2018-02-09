package bean;

import annotation.Id;
import annotation.Table;

@Table(name = "T_PERSONNE_PER")
public class Personne {

	private String per_nom;
	private String per_prenom;
	private boolean per_risque;

	@Id
	private String per_id;

	public String getPer_nom() {
		return per_nom;
	}

	public void setPer_nom(String pER_nom) {
		per_nom = pER_nom;
	}

	public String getPer_prenom() {
		return per_prenom;
	}

	public void setPer_prenom(String pER_prenom) {
		per_prenom = pER_prenom;
	}

	public boolean isPer_risque() {
		return per_risque;
	}

	public void setPer_risque(boolean pER_risque) {
		per_risque = pER_risque;
	}

	public String getPer_id() {
		return per_id;
	}

	public void setPer_id(String pER_id) {
		per_id = pER_id;
	}
}
