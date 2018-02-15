package bean.neo4j;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import base.BaseMariaDB;
import bean.mariadb.Personne;
import bean.mariadb.Question;
import bean.mariadb.ReponsePersonne;

public class ChoixPersonne {
	private boolean ras;
	private boolean assurance;
	private String etat;
	private boolean longTerme;

	public ChoixPersonne(String mail) {
		BaseMariaDB base = new BaseMariaDB();
		base.ouvrir();
		Personne personne = base.getPersonne(mail);
		Map<Question, ReponsePersonne> map = personne.getReponses();

		Set<Question> cles = map.keySet();
		Iterator<Question> it = cles.iterator();
		while (it.hasNext()) {
			Question cle = it.next();
			ReponsePersonne valeur = map.get(cle);
			if (cle.getQue_question().contains("risque aggravé de santé")) {
				if (valeur.getValeur().equals("oui")) {
					ras = true;
				} else {
					ras = false;
				}
			} else if (cle.getQue_question().contains("changer d'assurance")) {
				if (valeur.getValeur().equals("oui")) {
					assurance = true;
				}
			} else if (cle.getQue_question().contains("faire un emprunt")) {
				if (valeur.getValeur().equals("oui")) {
					assurance = true;
				}
			} else if (cle.getQue_question().contains("moyen/long terme")) {
				if (valeur.getValeur().equals("oui")) {
					assurance = true;
				}
			} else if (cle.getQue_question().contains("court terme")) {
				if (valeur.getValeur().equals("oui")) {
					assurance = false;
				}
			} else if (cle.getQue_question().contains("refus")) {
				if (valeur.getValeur().equals("oui")) {
					etat = "refus";
				}
			} else if (cle.getQue_question().contains("perdu")) {
				if (valeur.getValeur().equals("oui")) {
					etat = "intermediaire";
				}
			}
		}

		if (etat == null) {
			etat = "projet";
		}
	}

	public ChoixPersonne() {
		this.ras = true;
		this.assurance = true;
		this.etat = "";
		this.longTerme = true;
	}

	public boolean isRas() {
		return ras;
	}

	public void setRas(boolean ras) {
		this.ras = ras;
	}

	public boolean isAssurance() {
		return assurance;
	}

	public void setAssurance(boolean assurance) {
		this.assurance = assurance;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public boolean isLongTerme() {
		return longTerme;
	}

	public void setLongTerme(boolean longTerme) {
		this.longTerme = longTerme;
	}
}
