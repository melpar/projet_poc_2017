package base;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.mariadb.Connexion;
import bean.mariadb.Formulaire;
import bean.mariadb.Personne;
import bean.mariadb.Question;
import bean.mariadb.ReponsePersonne;
import util.Cryptage;

/**
 * 
 * @author M1 - informatique Louarn - Parlant - Maresceaux - Le Guyader Classe
 *         qui contient les fonctionalite pour la basse mariadb
 */
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

	/**
	 * Ouverture de la basse
	 * 
	 * @return true, si la connection a la base a reussi
	 */
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

	/**
	 * Fermeture de la base
	 */
	public void fermer() {
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Recupere l'objet Connexion stocker dans la basse
	 * 
	 * @param mail
	 *            Mail representent l'identifiant de l'objet a recuperer
	 * @return Retourne l'objet Connexion
	 */
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

	/**
	 * Recuperation des questions
	 * 
	 * @return Liste de Question
	 */
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

	/**
	 * Recuperation de l'ensemble des reponse d'une personne
	 * 
	 * @param mail
	 *            Mail de la personne
	 * @return l'emsemble des reponses
	 */
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

	/**
	 * Recupere l'objet Personne en fonction du mail
	 * 
	 * @param mail
	 *            Mail de la personne
	 * @return l'objet Personne
	 */

	public Personne getPersonne(String mail) {
		Personne personne = new Personne();
		ResultSet rs;
		try {
			String query = "select * from T_PERSONNE_PER WHERE PER_idMail = ?";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);
			// System.out.println(preparedStmt.toString());
			rs = preparedStmt.executeQuery();
			if (rs.next()) {
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

	/**
	 * L'enseble des personne stocker dans la basse
	 * 
	 * @return La liste de personne
	 */
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

	/**
	 * Recupere le formulaire necessaire pour l'inscription
	 * 
	 * @return le formulaire
	 */
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

	/**
	 * Verification des identifiants de connexion
	 * 
	 * @param mail
	 *            mail de l'utilisateur
	 * @param mdp
	 *            Mot de passe de l'utilisateur
	 * @return true,si les identifiants son correct, false sinon
	 */
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

	/**
	 * Verification des identifiants de connexion
	 * 
	 * @param mail
	 *            mail de l'utilisateur
	 * @param mdp
	 *            Mot de passe de l'utilisateur
	 * @return true,si les identifiants son correct, false sinon
	 */
	public boolean connexionAdmin(String mail, String mdp) {
		ResultSet rs;
		try {
			String query = "select * from T_CONNEXION_ADMIN_COA WHERE COA_idMail = ? AND COA_motDePasse = ?";
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

	/**
	 * Ajouter les identifiant de connexion dans la basse
	 * 
	 * @param con
	 *            objet Connexion
	 * @return true, si l'ajout est un succes , false sinon
	 */
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
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Ajouter les informations d'une personne dans la basse
	 * 
	 * @param per
	 *            personne a ajouter
	 * @return true, si l'ajout est un succes , false sinon
	 */
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

	/**
	 * Ajout de la reponse d'une personne dans la basse
	 * 
	 * @param idMail
	 *            Mail de la personne
	 * @param rep
	 *            Reponse de la personne
	 * @return true, si l'ajout est un succes , false sinon
	 */
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

	/**
	 * inscription d'une personne
	 * 
	 * @param per
	 *            Personne
	 * @param rep
	 *            Reponse au formulaire
	 * @return true, si l'ajout est un succes , false sinon
	 * 
	 */

	public boolean inscription(Personne per) {
		if (this.ajouterCon(per.getConnexion()) != true) {
			return false;
		}

		if (this.ajouterPer(per) != true) {
			return false;
		}

		Set cles = per.getReponses().keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()) {
			Question cle = (Question) it.next();
			if (this.ajouterRep(per.getConnexion().getCon_idMail(), per.getReponses().get(cle)) != true) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Enregistre un mail pour la liste de diffusion
	 * 
	 * @param mail
	 *            Mail a enregistrer
	 * @return true, si l'ajout est un succes , false sinon
	 */
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

	public boolean updateReponsePersonne(int idQuestion, String reponse, String mail) {
		try {
			int rs;
			String query = "UPDATE `T_REPONSEPERSONNE_REP` SET `REP_idMail` = ?, `REP_reponse` = ? WHERE `T_REPONSEPERSONNE_REP`.`REP_idQuestion` = ?  ";
			java.sql.PreparedStatement preparedStmt = co.prepareStatement(query);
			preparedStmt.setString(1, mail);
			preparedStmt.setString(2, reponse);
			preparedStmt.setInt(3, idQuestion);
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
}
