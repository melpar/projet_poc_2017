package com.project.poc.server.base;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.project.poc.shared.bean.Personne;

public class BaseMariaDB {
	private static String config = "resources/mariadb";
	private Connection co;
	
	public static void main(String[]args){
		ResourceBundle resource = ResourceBundle.getBundle(config);
		String url = resource.getString("url");
		System.out.println(url);
	}
	
	public boolean ouvrir(){
		ResourceBundle resource = ResourceBundle.getBundle(config);
		String url = resource.getString("url");
		String user = resource.getString("user");
		String password = resource.getString("password");
		try {
			co = (Connection) DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void fermer(){
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Personne> getPersonnes() {
		List<Personne> personnes = new ArrayList<>();
		ResultSet rs;
		Statement st;
		try {
			st = (Statement) co.createStatement();
			rs = (ResultSet) st.executeQuery("select * from T_PERSONNE_PER");
			while(rs.next()){
				Personne p = new Personne();
				p.setPER_id(rs.getString("PER_id"));
				p.setPER_nom(rs.getString("PER_nom"));
				p.setPER_prenom(rs.getString("PER_prenom"));
				p.setPER_risque(rs.getBoolean("PER_risque"));
				personnes.add(p);
			}
			if(st != null){
				st.close();
				if(rs!=null){
					rs.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return personnes;
		
	}
}
