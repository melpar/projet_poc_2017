package tp1;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import tp1.beans.Livre;

public class Main {
	

	public static void main(String[] args) {
		
		ResourceBundle resource = ResourceBundle.getBundle("resource/resource");
		String url = resource.getString("url");
		String user = resource.getString("user");
		String password = resource.getString("password");
		try {
			Connection co = (Connection) DriverManager.getConnection(url, user, password);
			Statement st = (Statement) co.createStatement();
			
			//ajout(st,new Livre("jean claude","michelle",2010));
			lister(st);
			
			st.close();
			co.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void lister(Statement st) throws SQLException {
		ResultSet rs = (ResultSet) st.executeQuery("select * from t_livre");
		while(rs.next()) {
			Livre livre = new Livre();
			livre.setTitre(rs.getString("titre"));
			livre.setAuteur(rs.getString("auteur"));
			livre.setAnnee(rs.getInt("annee"));
			System.out.println(livre.toString());
		}
		rs.close();
	}
	
	static void ajout(Statement st,Livre livre) throws SQLException {
		st.executeUpdate("insert into t_livre (titre,auteur,annee) values ('"+livre.getTitre()+"','"+livre.getAuteur()+"',"+livre.getAnnee()+")");
	}

}
