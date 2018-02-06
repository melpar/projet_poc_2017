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
	public static void main(String[]args){
		// Création de la connexion à MongoDB
		MongoClientURI uri = new MongoClientURI("mongodb://master041:4229e35z@obiwan2.univ-brest.fr/?authSource=db1");
		MongoClient mongoClient = new MongoClient(uri);
		// Notre base de données
		MongoDatabase db = mongoClient.getDatabase("BDMongomaster041");
		
		MongoCollection<Document> collection = db.getCollection("oeuvres");
		Document myDoc = collection.find().first();
//		System.out.println(myDoc.toJson());
		//System.out.println(artistes.findOne());
		//DBObject artiste = artistes.findOne();
		//System.out.println(artiste);
	}
}
