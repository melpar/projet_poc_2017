package base;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;

import bean.neo4j.Arbre;
import bean.neo4j.Avance;
import bean.neo4j.Projet;
import bean.neo4j.Risque;
import bean.neo4j.Terme;

public class BaseNeo4j implements AutoCloseable {
	private final Driver driver;
	private String uri = "bolt://obiwan2.univ-brest.fr:7687";

	public BaseNeo4j() {
		driver = GraphDatabase.driver(uri);
	}

	@Override
	public void close() throws Exception {
		driver.close();
	}

	public List<Risque> getRisques() {
		List<Risque> risques = new ArrayList<Risque>();
		try (Session session = driver.session()) {
			final StatementResult sr = session.run("MATCH (r:MP_Risque) RETURN r");
			while (sr.hasNext()) {
				Record ligne = sr.next();
				Value colonneR = ligne.get("r");
				Node noeud = colonneR.asNode();
				Value nom = noeud.get("nom");
				Risque risque = new Risque();
				risque.setNom(nom.asString());

				// Projet
				risque.addAll(getProjet(nom.asString()));

				risques.add(risque);
			}
		}
		return risques;
	}

	private List<Projet> getProjet(String nomRisque) {
		List<Projet> projets = new ArrayList<Projet>();
		try (Session session = driver.session()) {
			final StatementResult sr = session
					.run("MATCH (r:MP_Risque{nom:\"" + nomRisque + "\"})-[MP:MP_SOUHAITE]->(p:MP_PROJET) RETURN p");

			while (sr.hasNext()) {
				Record ligne = sr.next();
				Value colonneR = ligne.get("p");
				Node noeud = colonneR.asNode();
				Value nom = noeud.get("nom");
				Projet projet = new Projet();
				projet.setNom(nom.asString());

				// Avance
				projet.addAll(getAvances(nom.asString()));
				projets.add(projet);
			}

		}
		return projets;
	}

	private List<Avance> getAvances(String nomProjet) {
		List<Avance> avances = new ArrayList<Avance>();
		try (Session session = driver.session()) {
			final StatementResult sr = session
					.run("MATCH (r:MP_PROJET{nom:\"" + nomProjet + "\"})-[MP_A_ETAT_DE]->(a:MP_AVANCE) RETURN a");

			while (sr.hasNext()) {
				Record ligne = sr.next();
				Value colonneR = ligne.get("a");
				Node noeud = colonneR.asNode();
				Value nom = noeud.get("nom");
				Avance avance = new Avance();
				avance.setNom(nom.asString());

				// Terme
				avance.addAll(getTerme(nom.asString()));
				avances.add(avance);
			}

		}
		return avances;
	}

	private List<Terme> getTerme(String nomAvance) {
		List<Terme> termes = new ArrayList<Terme>();
		try (Session session = driver.session()) {
			final StatementResult sr = session
					.run("MATCH (r:MP_AVANCE{nom:\"" + nomAvance + "\"})-[MP_DANS]->(t:MP_TERME) RETURN t");

			while (sr.hasNext()) {
				System.out.println(nomAvance);
				Record ligne = sr.next();
				Value colonneR = ligne.get("t");
				Node noeud = colonneR.asNode();
				Value nom = noeud.get("nom");
				Terme terme = new Terme();
				terme.setNom(nom.asString());

				termes.add(terme);
			}

		}
		return termes;
	}

	public Arbre creerArbre() {
		Arbre arbre = new Arbre();
		arbre.addAll(getRisques());
		return arbre;
	}

	public static void main(String... args) throws Exception {
		try (BaseNeo4j greeter = new BaseNeo4j()) {
			greeter.getRisques();
		}
	}
}