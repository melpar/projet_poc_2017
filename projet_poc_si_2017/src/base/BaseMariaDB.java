package base;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.mariadb.Connexion;
import bean.mariadb.Personne;
import bean.mariadb.Question;
import bean.mariadb.ReponsePersonne;

public class BaseMariaDB {
	private static String config = "resources/mariadb";
	private Connection co;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ResourceBundle resource = ResourceBundle.getBundle(config);
		String url = resource.getString("url");
		System.out.println(url);
	}

	public boolean ouvrir() {
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

	public void fermer() {
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connexion getConnexion(String mail) {
		Connexion connexion = new Connexion();
		ResultSet rs;
		try {
			String query = "select * from T_CONNEXION_CON WHERE CON_idMail = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);

			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				connexion.setCon_idMail(mail);
				connexion.setCon_motDePasse(rs.getString("CON_motDePasse"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connexion;
	}

	public Map<Question, ReponsePersonne> getReponsesPersonne(String mail) {
		Map<Question, ReponsePersonne> reponses = new HashMap<>();
		ResultSet rs;
		try {
			String query = "select * from T_REPONSEPERSONNE_REP WHERE REP_idMail = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);

			rs = preparedStmt.executeQuery();
			List<ReponsePersonne> reps = new ArrayList<>();
			while (rs.next()) {
				ReponsePersonne rep = new ReponsePersonne();
				rep.setIdQuestion(rs.getInt("REP_idQuestion"));
				rep.setValeur(rs.getString("REP_reponse"));

				reps.add(rep);
			}
			for (ReponsePersonne r : reps) {
				String query2 = "select * from T_QUESTION_QUE WHERE QUE_id = ?";
				java.sql.PreparedStatement preparedStmt2 = co.prepareStatement(query2);
				preparedStmt2.setInt(1, r.getIdQuestion());
				ResultSet rs2 = preparedStmt2.executeQuery();
				Question question = new Question();
				while (rs2.next()) {
					question.setQue_id(r.getIdQuestion());
					question.setQue_question(rs2.getString("QUE_question"));
				}
				reponses.put(question, r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reponses;
	}

	public Personne getPersonne(String mail) {
		Personne personne = new Personne();
		ResultSet rs;
		Statement st;
		try {
			st = (Statement) co.createStatement();
			rs = (ResultSet) st.executeQuery("select * from T_PERSONNE_PER");
			while (rs.next()) {
				personne.setPer_nom(rs.getString("PER_nom"));
				personne.setPer_prenom(rs.getString("PER_prenom"));
				personne.setPer_risque(rs.getBoolean("PER_risque"));
				personne.setConnexion(getConnexion(mail));
				personne.setReponses(getReponsesPersonne(mail));
			}
			if (st != null) {
				st.close();
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personne;
	}

	public List<Personne> getPersonnes() {
		List<Personne> personnes = new ArrayList<>();
		ResultSet rs;
		Statement st;
		try {
			st = (Statement) co.createStatement();
			rs = (ResultSet) st.executeQuery("select * from T_PERSONNE_PER");
			while (rs.next()) {
				Personne p = new Personne();
				p.setPer_nom(rs.getString("PER_nom"));
				p.setPer_prenom(rs.getString("PER_prenom"));
				p.setPer_risque(rs.getBoolean("PER_risque"));
				personnes.add(p);
			}
			if (st != null) {
				st.close();
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personnes;

	}

	public boolean connexion(String mail, String mdp) {
		ResultSet rs;
		try {
			String query = "select * from T_CONNEXION_CON WHERE CON_idMail = ? AND CON_motDePasse = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);
			preparedStmt.setString(2, mdp);

			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				return true;
			}
			if (preparedStmt != null) {
				preparedStmt.close();
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean ajouterNewsletter(String mail) {
		ResultSet rs;
		PreparedStatement st;
		try {
			String query = " insert into T_LISTEDIFFUSION_LIS (LIS_mail)" + " values (?)";

			// create the mysql insert preparedstatement
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);

			// execute the preparedstatement
			preparedStmt.execute();

			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
