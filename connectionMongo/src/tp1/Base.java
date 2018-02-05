package tp1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import annotation.Table;
import tp1.beans.Livre;


import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class Base {

	Connection co;
	Statement st;
	public void ouvrirMongo(){
		 MongoClientURI connectionString = new MongoClientURI("mongodb://obiwan2.univ-brest.fr:27017");
		 //MongoClient mongoClient = new MongoClient("obiwan2.univ-brest.fr");
		 MongoClient mongoClient = new MongoClient(connectionString);
		 MongoDatabase database = mongoClient.getDatabase("BDMongomaster033");
		 MongoCollection<Document> collection = database.getCollection("oeuvres");
		 System.out.println(collection.count());
		 System.out.println(collection.find().first().toJson());
		 mongoClient.close();
	
	}
	public void ouvrirMongo2(){
		 String user="master033"; // the user name
		 String database="BDMongomaster033"; // the name of the database in which the user is defined
		 char[] password={'m','7','6','a','9','7','1','a'}; // the password as a character array
		 // ...

		 MongoCredential credential = MongoCredential.createCredential(user, database, password);

		 MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

		 MongoClient mongoClient = new MongoClient(new ServerAddress("obiwan2.univ-brest.fr", 27017),
		                                           Arrays.asList(credential),
		                                           options);
		 MongoDatabase database2 = mongoClient.getDatabase("BDMongomaster033");
		 MongoCollection<Document> collection = database2.getCollection("oeuvres");
		 System.out.println(collection.count());
		 System.out.println(collection.find().first().toJson());
		 mongoClient.close();
	}
	public void ouvrir() {
		ResourceBundle resource = ResourceBundle.getBundle("resource/resource");
		String url = resource.getString("url");
		String user = resource.getString("user");
		String password = resource.getString("password");
		try {
			co = (Connection) DriverManager.getConnection(url, user, password);
			st = (Statement) co.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fermer() {
		try {
			co.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void enregistrer(Object obj) {
		
		Class<? extends Object> c = obj.getClass();
		try {
			Method m = this.getClass().getMethod("enregistrer"+c.getSimpleName(),c);
			m.invoke(this,obj);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
public void enregistrer2(Object obj) {
		
		Class<? extends Object> c = obj.getClass();
		java.lang.reflect.Field[] f = c.getDeclaredFields();
		//Table annot = c.getAnnotation(Table.class);
		String sql = "insert into "+c.getAnnotation(Table.class).name()+" (";
		sql+=f[0].getName();
		for(int i=1;i<f.length;i++) {
			sql+=","+f[i].getName();
		}
		sql+=") value (?";
		
		for(int i=1;i<f.length;i++) {
			sql+=",?";
		}
		sql+=")";
		
		try {
			java.sql.PreparedStatement ps = co.prepareStatement(sql);
			for(int i=0;i<f.length;i++) {
				String nom = f[i].getName();
				String nomGetter = "get" + nom.substring(0, 1).toUpperCase() + nom.substring(1);
				Method m = c.getMethod(nomGetter);
				ps.setObject(i+1, m.invoke(obj));
			}
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int enregistrerLivre(Livre livre) {
		int res = 0;
		String sql = "insert into t_livre (titre,auteur,annee) values (? , ? , ?)";
		try {
			java.sql.PreparedStatement ps = co.prepareStatement(sql);
			ps.setString(1, livre.getTitre());
			ps.setString(2, livre.getAuteur());
			ps.setInt(3, livre.getAnnee());
			res = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Object> lister(){
		List<Object> result = new ArrayList<>();
		result.addAll(listerLivre());
		return result;
	}
	
	public List<Livre> listerLivre(){
		List<Livre> result = new ArrayList<>();
		try {
			ResultSet rs = (ResultSet) st.executeQuery("select * from t_livre");
			while(rs.next()) {
				Livre livre = new Livre();
				livre.setTitre(rs.getString("titre"));
				livre.setAuteur(rs.getString("auteur"));
				livre.setAnnee(rs.getInt("annee"));
				result.add(livre);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*public void modifierLivre(int id,int champs,String valeur) {
		st.executeUpdate("update into t_livre (titre,auteur,annee) values ('"+livre.getTitre()+"','"+livre.getAuteur()+"',"+livre.getAnnee()+")");
	}*/
	
}
