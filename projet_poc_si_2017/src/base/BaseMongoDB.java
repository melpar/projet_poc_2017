package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import bean.mongdb.HistoriqueConnexion;

public class BaseMongoDB {
	
	private MongoClient mongoClient;
	MongoDatabase db;
	
	public static void main(String[]args){
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		//mongo.testHistorique();
		mongo.testDelete();
		mongo.fermer();
	}
	public void ouvrir(){
		MongoClientURI uri = new MongoClientURI("mongodb://obiwan2.univ-brest.fr");
		mongoClient = new MongoClient(uri);
		db = mongoClient.getDatabase("BDMongomasterProjet041");
	}
	
	public void fermer(){
		mongoClient.close();
	}
	
	public void test(){
		MongoDatabase dbtest = mongoClient.getDatabase("BDMongomaster041");
		MongoCollection<Document> collection = dbtest.getCollection("oeuvres");
		Document myDoc = collection.find().first();
		System.out.println(myDoc);
	}
	
	public void testHistorique(){
		HistoriqueConnexion connexion = new HistoriqueConnexion(42, 22, "Wind", "GG", new Date(55, 10, 22, 13 , 45, 60));
		connexion.addPagesVisitées(new Date(55, 10, 22, 13 , 47, 60), "maPage");
		connexion.addPagesVisitées(new Date(55, 10, 22, 13 , 50, 60), "maPage2");
		Document document = genererDocument(connexion);
		ajoutDocument(document);
		visualiser();
	}
	
	public void testDelete(){
		removeConnexion(41);
		visualiser();
	}
	
	public void creation(){
		/*db.createCollection("historiqueConnexions");
		MongoCollection<Document> collection = db.getCollection("historiqueConnexions");
		//db.runCommand("hola");*/
	}
	
	public void ajoutDocument(Document document){
		MongoCollection<Document> collection = db.getCollection("historiqueConnexions");
		collection.insertOne(document);
	}
	
	public void visualiser(){
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		for(MongoCursor<Document> curseur = coll.find().iterator();curseur.hasNext();){
			System.out.println(curseur.next());
		}
	}
	
	public HistoriqueConnexion genererConnexion(Document document){
		HistoriqueConnexion connexion = new HistoriqueConnexion(
				document.getInteger("idConnexion"),
				document.getInteger("idUtilisateur"),
				document.getString("systeme"),
				document.getString("navigateur"),
				(Date) document.get("dateConnexion"));
		if(document.containsKey("dateDeconnexion")){
			connexion.setDateDeconnexion((Date) document.get("dateDeconnexion"));
		}
		List<Document> pages = (List<Document>) document.get("pagesVisitees");
		for(Document page : pages){
			connexion.addPagesVisitées(
					(Date) page.get("dateVisite"),
					page.getString("url"));
		}
		return connexion;
	}
	
	public void removeConnexion(Integer id){
		Document document = new Document("idConnexion",id);
		MongoCollection<Document> coll = db.getCollection("historiqueConnexions");
		coll.deleteMany(document);
	}
	
	public Document genererDocument(HistoriqueConnexion connexion){
		Document document = new Document();
		document.append("idConnexion", connexion.getIdConnexion());
		document.append("idUtilisateur", connexion.getIdUtilisateur());
		document.append("systeme", connexion.getSysteme());
		document.append("navigateur", connexion.getNavigateur());
		document.append("dateConnexion", connexion.getDateConnexion());
		document.append("dateDeconnexion", connexion.getDateDeconnexion());
		List<Document> pages = new ArrayList<Document>();
		for(Date date : connexion.getPagesVisitées().keySet()){
			Document page = new Document();
			page.append("dateVisite", date);
			page.append("url", connexion.getPagesVisitées().get(date));
			pages.add(page);
		}
		document.append("pagesVisitees", pages);
		return document;
	}
}
