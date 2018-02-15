package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Arbre {
	private List<Risque> risques;
	private int id;

	public Arbre() {
		this.risques = new ArrayList<>();
	}

	public Risque getRisque(String nom) {
		Risque r = null;
		for (Risque risque : risques) {
			if (risque.getNom().equals(nom)) {
				return risque;
			}
		}
		return r;
	}

	public String toString() {
		String ret = "";
		ret += id;
		ret += "\n";
		for (Risque r : risques) {
			ret += r.toString();
		}
		return ret;
	}

	public List<Risque> getRisques() {
		return risques;
	}

	public void setRisques(List<Risque> risques) {
		this.risques = risques;
	}

	public void addAll(List<Risque> risques2) {
		this.risques.addAll(risques2);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
