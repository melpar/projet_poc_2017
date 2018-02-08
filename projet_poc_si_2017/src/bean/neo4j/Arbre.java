package bean.neo4j;

import java.util.ArrayList;
import java.util.List;

public class Arbre {
	private List<Risque> risques;
	
	public Arbre (){
		this.risques = new ArrayList<>();
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
}
