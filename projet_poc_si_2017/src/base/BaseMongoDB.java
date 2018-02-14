package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import bean.mongdb.HistoriqueConnexion;

public class BaseMongoDB {

	private MongoClient mongoClient;
	MongoDatabase db;

	public static void main(String[] args) {
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		// mongo.testHistorique();
		// mongo.testDelete();
		mongo.visualiser();
		// mongo.testUtilisateurParPage();
		// mongo.testUtilisateurParJour();
		mongo.testUpdate();
		mongo.visualiser();
		mongo.fermer();
	}

	public void test() {
		MongoDatabase dbtest = mongoClient.getDatabase("BDMongomaster041");
		MongoCollection<Document> collection = dbtest.getCollection("oeuvres");
		Document myDoc = collection.find().first();
		System.out.println(myDoc);
	}

	public void testHistorique() {
		HistoriqueConnexion connexion = new HistoriqueConnexion(43, 22, "Wind", "GG", new Date(55, 10, 22, 13, 45, 60));
		connexion.addPagesVisitées(new Date(55, 10, 22, 13, 50, 60), "maPage2");
		Document document = genererDocument(connexion);
		ajoutDocument(document);
		// visualiser();
	}

	public void testDelete() {
		removeConnexion(41);
		visualiser();
	}

	public void testUtilisateurParPage() {
		System.out.println(utilisateurParPage(requete()).toString());
	}

	public void testUtilisateurParJour() {
		System.out.println(utilisateurParJour(requete()).toString());
	}

	public void testUpdate() {
		updateConnexion(42, (new Date(55, 10, 24, 13, 50, 60)));
	}

	public void ouvrir() {
		MongoClientURI uri = new MongoClientURI("mongodb://obiwan2.univ-brest.fr");
		mongoClient = new MongoClient(uri);
		db = mongoClient.getDatabase("BDMongomasterProjet041");
	}

	public void fermer() {
		mongoClient.close();
	}

	void visualiser() {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		for (MongoCursor<Document> curseur = coll.find().iterator(); curseur.hasNext();) {
			System.out.println(curseur.next());
		}
	}

	void visualiser2() {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		for (MongoCursor<Document> curseur = coll.find(Filters.eq("idConnexion", 40)).iterator(); curseur.hasNext();) {
			System.out.println(curseur.next());
		}
	}

	void visualiser(FindIterable<Document> list) {
		for (MongoCursor<Document> curseur = list.iterator(); curseur.hasNext();) {
			System.out.println(curseur.next());
		}
	}

	FindIterable<Document> requete() {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		return coll.find();
	}

	FindIterable<Document> requete(Date debut, Date fin) {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		return coll.find();
	}

	Document requete(int idConnexion) {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		return coll.find(Filters.eq("idConnexion", idConnexion)).first();
	}

	public void ajoutDocument(Document document) {
		MongoCollection<Document> collection = db.getCollection("historiqueConnexions");
		collection.insertOne(document);
	}

	public void updateDocument(int idConnexion, Document document) {
		MongoCollection<Document> collection = db.getCollection("historiqueConnexions");
		collection.updateOne(Filters.eq("idConnexion", idConnexion), new Document("$set", document));
	}

	public void removeConnexion(Integer idConnexion) {
		Document document = new Document("idConnexion", idConnexion);
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		coll.deleteMany(document);
	}

	public void updateConnexion(Integer idConnexion, Date dateDeconnexion) {
		Document document = requete(idConnexion);
		HistoriqueConnexion connexion = genererConnexion(document);
		connexion.setDateDeconnexion(dateDeconnexion);
		Document documentUpdate = genererDocument(connexion);
		updateDocument(idConnexion, documentUpdate);
	}

	public Map<String, Integer> utilisateurParPage(FindIterable<Document> list) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (MongoCursor<Document> curseur = list.iterator(); curseur.hasNext();) {
			Document document = curseur.next();
			HistoriqueConnexion connexion = genererConnexion(document);
			for (Date date : connexion.getPagesVisitées().keySet()) {
				String url = connexion.getPagesVisitées().get(date);
				if (result.containsKey(url)) {
					result.put(url, result.get(url) + 1);
				} else {
					result.put(url, 1);
				}
			}
		}
		return result;
	}

	public Map<Date, Integer> utilisateurParJour(FindIterable<Document> list) {
		Map<Date, Integer> result = new HashMap<Date, Integer>();
		for (MongoCursor<Document> curseur = list.iterator(); curseur.hasNext();) {
			Document document = curseur.next();
			HistoriqueConnexion connexion = genererConnexion(document);
			Date date = connexion.getDateConnexion();
			date.setSeconds(0);
			date.setMinutes(0);
			date.setHours(0);

			if (result.containsKey(date)) {
				result.put(date, result.get(date) + 1);
			} else {
				result.put(date, 1);
			}
		}
		return result;
	}

	Document genererDocument(HistoriqueConnexion connexion) {
		Document document = new Document();
		document.append("idConnexion", connexion.getIdConnexion());
		document.append("idUtilisateur", connexion.getIdUtilisateur());
		document.append("systeme", connexion.getSysteme());
		document.append("navigateur", connexion.getNavigateur());
		document.append("dateConnexion", connexion.getDateConnexion());
		document.append("dateDeconnexion", connexion.getDateDeconnexion());
		List<Document> pages = new ArrayList<Document>();
		for (Date date : connexion.getPagesVisitées().keySet()) {
			Document page = new Document();
			page.append("dateVisite", date);
			page.append("url", connexion.getPagesVisitées().get(date));
			pages.add(page);
		}
		document.append("pagesVisitees", pages);
		return document;
	}

	public HistoriqueConnexion genererConnexion(Document document) {
		int idConnexion = Integer.valueOf(document.getDouble("idConnexion").intValue());
		int idUtilisateur = Integer.valueOf(document.getDouble("idUtilisateur").intValue());
		String systeme = document.getString("systeme");
		String navigateur = document.getString("navigateur");
		Date dateConnexion = document.getDate("dateConnexion");

		HistoriqueConnexion connexion = new HistoriqueConnexion(idConnexion, idUtilisateur, systeme, navigateur,
				dateConnexion);
		if (document.containsKey("dateDeconnexion")) {
			connexion.setDateDeconnexion(document.getDate("dateDeconnexion"));
		}
		if (document.get("pagesVisitees") instanceof List<?>) {
			List<Document> pages = (List<Document>) document.get("pagesVisitees");
			for (Document page : pages) {
				connexion.addPagesVisitées(page.getDate("dateVisite"), page.getString("url"));
			}
		}
		return connexion;
	}
}
