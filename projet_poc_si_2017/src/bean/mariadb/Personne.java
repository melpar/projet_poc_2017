package bean.mariadb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import annotation.Table;

@Table(name = "T_PERSONNE_PER")
public class Personne {

	private String per_nom;
	private String per_prenom;
	private boolean per_risque;

	private Connexion connexion;
	private Map<Question, ReponsePersonne> reponses;

	public Personne() {
		this.reponses = new HashMap<>();
	}

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

	public Connexion getConnexion() {
		return connexion;
	}

	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

	public HashMap<Question, ReponsePersonne> getReponses() {
		return (HashMap) reponses;
	}

	public void setReponses(Map<Question, ReponsePersonne> reponses) {
		this.reponses = reponses;
	}

	public Question getReponsePersone(int idQuestion) {
		Question q = null;
		Set cles = this.reponses.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()) {
			Question cle = (Question) it.next();
			if (cle.getQue_id() == idQuestion) {
				return cle;
			}
		}
		return q;
	}

}
