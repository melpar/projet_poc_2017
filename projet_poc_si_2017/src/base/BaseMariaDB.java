package base;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.mariadb.Connexion;
import bean.mariadb.Formulaire;
import bean.mariadb.Personne;
import bean.mariadb.Question;
import bean.mariadb.ReponsePersonne;
import util.Cryptage;

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

	public ArrayList<String> getQuestions() {
		ArrayList<String> questions = new ArrayList<>();
		ResultSet rs;
		Statement st;
		try {
			st = (Statement) co.createStatement();
			rs = (ResultSet) st.executeQuery("SELECT * FROM `T_QUESTION_QUE`");
			while (rs.next()) {
				questions.add(rs.getString("QUE_question"));
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

		return questions;
	}

	public HashMap<Question, ReponsePersonne> getReponsesPersonne(String mail) {
		HashMap<Question, ReponsePersonne> reponses = new HashMap<>();
		ResultSet rs;
		try {
			System.out.println("try " + mail);
			String query = "select * from T_REPONSEPERSONNE_REP WHERE REP_idMail = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);

			rs = preparedStmt.executeQuery();
			List<ReponsePersonne> reps = new ArrayList<>();
			while (rs.next()) {
				System.out.println("rs.next");
				ReponsePersonne rep = new ReponsePersonne();
				rep.setIdQuestion(rs.getInt("REP_idQuestion"));
				rep.setValeur(rs.getString("REP_reponse"));

				reps.add(rep);
			}
			for (ReponsePersonne r : reps) {
				System.out.println("for");
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
		try {
			String query = "select * from T_PERSONNE_PER WHERE PER_idMail = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				personne.setPer_nom(rs.getString("PER_nom"));
				personne.setPer_prenom(rs.getString("PER_prenom"));
				personne.setPer_risque(rs.getBoolean("PER_risque"));
				personne.setConnexion(getConnexion(mail));
				System.err.println("get personne");
				personne.setReponses(getReponsesPersonne(mail));
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

	public Formulaire getFormulaire() {
		Formulaire formulaire = new Formulaire();

		ResultSet rs;
		Statement st;
		try {
			st = (Statement) co.createStatement();
			rs = (ResultSet) st.executeQuery("select * from T_QUESTION_QUE");
			while (rs.next()) {
				Question q = new Question();
				q.setQue_question(rs.getString("QUE_question"));
				q.setQue_id(rs.getInt("QUE_id"));
				q.setQue_typeMultiple(rs.getBoolean("QUE_typeMultiple"));
				if (q.isQue_typeMultiple() == true) {
					Statement st2 = (Statement) co.createStatement();
					ResultSet rs2 = (ResultSet) st2.executeQuery(
							"select * from  T_REPONSEQUESTION_REQ WHERE REQ_idQuestion=\'" + q.getQue_id() + '\'');
					while (rs2.next()) {
						q.ajouterReponse(rs2.getString("REQ_reponse"));
					}
					if (st2 != null) {
						st2.close();
						if (rs2 != null) {
							rs2.close();
						}
					}
				} else {
					q.ajouterReponse("oui");
					q.ajouterReponse("non");
				}
				formulaire.ajouterQuestion(q);
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
		return formulaire;
	}

	public boolean connexion(String mail, String mdp) {
		ResultSet rs;
		try {
			String query = "select * from T_CONNEXION_CON WHERE CON_idMail = ? AND CON_motDePasse = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);
			Cryptage c = new Cryptage(mdp);
			preparedStmt.setString(2, c.chiffrer());
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	private boolean ajouterCon(Connexion con) {
		try {
			int rs;
			String query = "INSERT INTO `T_CONNEXION_CON` (`CON_idMail`, `CON_motDePasse`) VALUES (?, ?)";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, con.getCon_idMail());
			Cryptage c = new Cryptage(con.getCon_motDePasse());
			preparedStmt.setString(2, c.chiffrer());
			rs = preparedStmt.executeUpdate();
			if (preparedStmt != null) {
				preparedStmt.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private boolean ajouterPer(Personne per) {
		try {
			int rs;
			String query = "INSERT INTO `T_PERSONNE_PER` (`PER_nom`, `PER_prenom`, `PER_risque`, `PER_idMail`) VALUES (?, ?, ?, ?)";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, per.getPer_nom());
			preparedStmt.setString(2, per.getPer_prenom());
			preparedStmt.setBoolean(3, per.isPer_risque());
			preparedStmt.setString(4, per.getConnexion().getCon_idMail());
			rs = preparedStmt.executeUpdate();
			if (preparedStmt != null) {
				preparedStmt.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean ajouterRep(String idMail, ReponsePersonne rep) {
		try {
			int rs;
			String query = "INSERT INTO `T_REPONSEPERSONNE_REP` (`REP_id`, `REP_idQuestion`, `REP_idMail`, `REP_reponse`) VALUES (NULL, ?, ?, ?)";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setInt(1, rep.getIdQuestion());
			preparedStmt.setString(2, idMail);
			preparedStmt.setString(3, rep.getValeur());
			rs = preparedStmt.executeUpdate();
			if (preparedStmt != null) {
				preparedStmt.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean inscription(Personne per, List<ReponsePersonne> rep) {
		this.ajouterCon(per.getConnexion());
		this.ajouterPer(per);
		for (int i = 0; i < rep.size(); i++) {
			this.ajouterRep(per.getConnexion().getCon_idMail(), rep.get(i));
		}

		return true;
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
