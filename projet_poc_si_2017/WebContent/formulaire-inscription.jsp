<!doctype html>

<%@page import="bean.mariadb.Personne"%>
<%@page import="bean.mariadb.Connexion"%>
<%@page import="bean.mariadb.ReponsePersonne"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="bean.mariadb.ReponseQuestion"%>
<%@page import="java.util.List"%>
<%@page import="bean.mariadb.Formulaire"%>
<%@page import="bean.mariadb.Question"%>
<%@page import="base.BaseMariaDB"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="com.sun.glass.ui.Window"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	BaseMariaDB b = new BaseMariaDB();
	b.ouvrir();
	HashMap<Question, ReponsePersonne> questionnaire = new HashMap<Question, ReponsePersonne> ();
	Formulaire f = b.getFormulaire();
	List<Question> liste_question = f.getListeQuesstion();
	if (request.getParameter("submit") != null) {
		
		String mail =request.getParameter("mail");
		String nom = request.getParameter("nom");
		String prenom =request.getParameter("prenom");
		String mdp=request.getParameter("mdp");
		String risque =request.getParameter("risque");
		
		Connexion connexion =new Connexion();
		connexion.setCon_idMail(mail);
		connexion.setCon_motDePasse(mdp);
		
		
		Personne personne =new Personne();
		personne.setConnexion(connexion);
		personne.setPer_nom(nom);
		personne.setPer_prenom(prenom);
		personne.setPer_risque(false);	
		
		for (int i = 0; i < liste_question.size(); i++) {
			int identifiant = liste_question.get(i).getQue_id();
			String valeur =request.getParameter(identifiant+"");
			ReponsePersonne reponse = new ReponsePersonne();
			reponse.setIdQuestion(identifiant);
			reponse.setValeur(valeur);
			questionnaire.put(liste_question.get(i),reponse);
		}
		
		personne.setReponses(questionnaire);
		boolean i=b.inscription(personne);
		System.out.println("inscription:"+i);
	}
	request.setAttribute("liste_question", liste_question);
	b.fermer();
%>






<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">


<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
<!--                                           -->
<!-- Any title is fine                         -->
<!--                                           -->
<title>We moë</title>


<!-- <script type="text/javascript" language="javascript" src="project_poc_2017/project_poc_2017.nocache.js"></script> -->
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<br>
	<h2 class="w3-center">Formulaire d'inscription</h2>

	<div class="w3-row-padding">


		<form class="w3-container w3-card-4" method="post" action="formulaire-inscription.jsp"  >
			<h2>Informations personnelles</h2>
			<div class="w3-section">
				<input class="w3-input" type="text" required name="nom"> <label>Nom</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="text" required name="prenom"> <label>Prenom</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="text" required name="mail"> <label>Email</label>
			</div>
			
			<div class="w3-section">
				<input class="w3-input" type="password" required name="mdp"> <label>Mot de passe</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="password" required name="mdpv"> <label>Retaper Mot de passe</label>
			</div>
			
			<div class="w3-section">
				<label>A quelle informations préferez-vous avoir accès ?</label> <br>
				<input id="male" class="w3-radio" type="radio" name="risque"
					value="rique"> <label>Risque</label> <br> <input
					id="female" class="w3-radio" type="radio" name="risque" value="ras">
				<label>RAS</label> <br>
			</div>

			<hr>
			<h2>Questionnaire</h2>

			<c:forEach items="${liste_question}" var="question">
				<ul class="w3-ul w3-border-top">
					<li>
						<h3>${question.getQue_question()}</h3>
						<select name="${question.getQue_id()}">
						 <c:forEach items="${question.getQue_listeReponse()}"
							var="reponse">							
								<option value="${reponse.getReq_texte()}">${reponse.getReq_texte()}</option>
							<label></label><br>
						</c:forEach>
						</select>
					</li>
				</ul>
			</c:forEach>
			<hr>



           

			<input type="submit" value="S'inscrire" name="submit" class="w3-btn w3-theme">
		</form>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>