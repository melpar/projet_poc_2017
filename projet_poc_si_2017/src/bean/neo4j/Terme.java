package bean.neo4j;

public class Terme {
	private String nom;
	private double id;

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

}
