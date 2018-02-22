package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

	// Declaration des variables globales
	private static String config = "resources/mongodb";
	private MongoClient mongoClient;
	private MongoDatabase db;

	/////////////////////////////////
	// Test de la base
	/////////////////////////////////

	public static void main(String[] args) {
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		mongo.testCreateId();
		// mongo.testHistorique();
		// mongo.testDelete();
		mongo.visualiser();
		// mongo.testUtilisateurParPage();
		// mongo.testUtilisateurParJour();
		// mongo.testUpdatePage();

		// mongo.visualiser(mongo.requete(new Date(55, 10, 19, 13, 45, 60), new Date(55,
		// 10, 21, 13, 45, 60)));
		mongo.fermer();
	}

	void testHistorique() {
		HistoriqueConnexion connexion = new HistoriqueConnexion(0, "gg@gg.com", "Wind", "GG",
				new Date(55, 10, 20, 13, 45, 60));
		connexion.addPagesVisitées(new Date(55, 10, 22, 13, 50, 60), "maPage2");
		Document document = genererDocument(connexion);
		ajoutDocument(document);
		// visualiser();
	}

	void testCreateId() {
		System.out.println("id : " + createIdConnexion());
	}

	void testDelete() {
		removeConnexion(41);
		visualiser();
	}

	void testUtilisateurParPage() {
		System.out.println(utilisateurParPage(requete()).toString());
	}

	void testUtilisateurParJour() {
		System.out.println(utilisateurParJour(requete()).toString());
	}

	void testUpdateDeconnexion() {
		updateConnexion(42, (new Date(55, 10, 24, 13, 50, 60)));
	}

	void testUpdatePage() {
		updateConnexion(45, new Date(55, 10, 24, 13, 50, 60), "maPage3");
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

	/////////////////////////////////
	// Communication avec la base MongoDB
	/////////////////////////////////

	/**
	 * Création d'une connexion avec la base de donnée.
	 */
	public void ouvrir() {
		ResourceBundle resource = ResourceBundle.getBundle(config);
		String url = resource.getString("url");
		String base = resource.getString("base");
		MongoClientURI uri = new MongoClientURI("mongodb://" + url);
		mongoClient = new MongoClient(uri);
		db = mongoClient.getDatabase(base);
		System.out.println("BASE OUVERTE");
	}

	/**
	 * fermeture de la connexion avec la base de donnée.
	 */
	public void fermer() {
		mongoClient.close();
	}

	/**
	 * récupération de l'ensemble des document de la base de donnée
	 * 
	 * @return documents
	 */
	FindIterable<Document> requete() {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		return coll.find();
	}

	/**
	 * récupération des documents entre la date de début et la date de fin.
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return documents
	 */
	FindIterable<Document> requete(Date debut, Date fin) {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		return coll.find(Filters.and(Filters.gte("dateConnexion", debut), Filters.lte("dateConnexion", fin)));
	}

	/**
	 * récupération du document associer à l'id
	 * 
	 * @param idConnexion
	 * @return document
	 */
	Document requete(int idConnexion) {
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		return coll.find(Filters.eq("idConnexion", idConnexion)).first();
	}

	/**
	 * ajout d'un element dans la base de donnée.
	 * 
	 * @param document
	 */
	public int createIdConnexion() {
		FindIterable<Document> documents = requete();
		List<Integer> listId = new ArrayList<>();
		for (MongoCursor<Document> curseur = documents.iterator(); curseur.hasNext();) {
			Document document = curseur.next();
			int idConnexion = Integer.valueOf(document.getDouble("idConnexion").intValue());
			listId.add(idConnexion);
		}
		int monId = 0;
		while (listId.contains(monId)) {
			monId++;
		}
		System.out.println("PASSER");
		return monId;
	}

	public void ajoutDocument(Document document) {
		MongoCollection<Document> collection = db.getCollection("historiqueConnexions");
		collection.insertOne(document);
	}

	public void ajoutHistoriqueConnexion(HistoriqueConnexion historiqueConnexion) {
		Document document = genererDocument(historiqueConnexion);
		ajoutDocument(document);
	}

	/**
	 * modification d'un element dans la base de donnée
	 * 
	 * @param idConnexion
	 * @param document
	 */
	public void updateDocument(int idConnexion, Document document) {
		MongoCollection<Document> collection = db.getCollection("historiqueConnexions");
		collection.updateOne(Filters.eq("idConnexion", idConnexion), new Document("$set", document));
	}

	/**
	 * supprimer un element dans la base de donnée
	 * 
	 * @param idConnexion
	 */
	public void removeConnexion(Integer idConnexion) {
		Document document = new Document("idConnexion", idConnexion);
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		coll.deleteMany(document);
	}

	/////////////////////////////////
	// Modification des connexion existante dans la base
	/////////////////////////////////

	/**
	 * ajout d'un identifiant d'utilisateur
	 * 
	 * @param idConnexion
	 * @param idUtilisateur
	 */
	public void updateConnexion(Integer idConnexion, String idUtilisateur) {
		Document document = requete(idConnexion);
		HistoriqueConnexion connexion = genererConnexion(document);
		connexion.setIdUtilisateur(idUtilisateur);
		;
		Document documentUpdate = genererDocument(connexion);
		updateDocument(idConnexion, documentUpdate);
	}

	/**
	 * ajout d'une date de déconnexion
	 * 
	 * @param idConnexion
	 * @param dateDeconnexion
	 */
	public void updateConnexion(Integer idConnexion, Date dateDeconnexion) {
		Document document = requete(idConnexion);
		HistoriqueConnexion connexion = genererConnexion(document);
		connexion.setDateDeconnexion(dateDeconnexion);
		Document documentUpdate = genererDocument(connexion);
		updateDocument(idConnexion, documentUpdate);
	}

	/**
	 * ajout d'une page visité(avec sa date) sur un document
	 * 
	 * @param idConnexion
	 * @param date
	 * @param url
	 */
	public void updateConnexion(Integer idConnexion, Date date, String url) {
		Document document = requete(idConnexion);
		HistoriqueConnexion connexion = genererConnexion(document);
		connexion.addPagesVisitées(date, url);
		Document documentUpdate = genererDocument(connexion);
		updateDocument(idConnexion, documentUpdate);
	}

	/////////////////////////////////
	// Recherche des statistiques
	/////////////////////////////////

	/**
	 * prend en parametre une liste de document venant d'une requete. renvois une
	 * association entre chaque pages et son nombre totale de visite.
	 * 
	 * @param listDocument
	 * @return statistiquePage
	 */
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

	/**
	 * prend en parametre une liste de document venant d'une requete. renvois une
	 * association entre chaque jours son nombre totale de connexion.
	 * 
	 * @param listDocument
	 * @return statistiqueJour
	 */
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

	/**
	 * prend en parametre une liste de document venant d'une requete. renvois une
	 * association entre chaque jours son nombre totale de connexion.
	 * 
	 * @param listDocument
	 * @return statistiqueJour
	 */
	public Map<Date, Integer> utilisateurParMois(FindIterable<Document> list) {
		Map<Date, Integer> result = new HashMap<Date, Integer>();
		for (MongoCursor<Document> curseur = list.iterator(); curseur.hasNext();) {
			Document document = curseur.next();
			HistoriqueConnexion connexion = genererConnexion(document);
			Date date = connexion.getDateConnexion();
			date.setDate(0);
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

	/////////////////////////////////
	// Conversion du type
	/////////////////////////////////

	/**
	 * convertie un objet type HistoriqueConnexion en Document.
	 * 
	 * @param historiqueConnexion
	 * @return document
	 */
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

	/**
	 * convertie un objet type Document en HistoriqueConnexion.
	 * 
	 * @param document
	 * @return historiqueConnexion
	 */
	HistoriqueConnexion genererConnexion(Document document) {
		int idConnexion = Integer.valueOf(document.getDouble("idConnexion").intValue());
		String idUtilisateur = document.getString("idUtilisateur");
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
