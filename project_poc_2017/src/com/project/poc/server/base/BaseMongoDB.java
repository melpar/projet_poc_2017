package com.project.poc.server.base;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BaseMongoDB {
	
	private MongoClient mongoClient;
	MongoDatabase db;
	
	public static void main(String[]args){
		BaseMongoDB mongo = new BaseMongoDB();
		mongo.ouvrir();
		mongo.test();
		mongo.fermer();
//		System.out.println(myDoc.toJson());
		//System.out.println(artistes.findOne());
		//DBObject artiste = artistes.findOne();
		//System.out.println(artiste);
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
	public void creation(){
		
	}
}
