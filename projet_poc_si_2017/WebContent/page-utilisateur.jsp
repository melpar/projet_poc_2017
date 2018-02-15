<!doctype html>

<%@page import="base.BaseMariaDB"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="com.sun.glass.ui.Window"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="personne" class="bean.mariadb.Personne" scope="request"></jsp:useBean>
<jsp:useBean id="questions" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="questionnaire" class="java.util.HashMap"
	scope="request"></jsp:useBean>
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

<%
	if (request.getParameter("newsletter") != null) {
		BaseMariaDB base = new BaseMariaDB();
		base.ouvrir();
		String mail = request.getParameter("mail");
		base.ajouterNewsletter(mail);
		base.fermer();
%>

<script>
	var mail = window.location.search.substr(1).split("&")[0].split("=")[1];
	alert("Votre adresse a bien été enregistrée. Merci de votre confiance.");
</script>
<%
	}
%>

<%
	if (request.getParameter("connexion") != null) {
		BaseMariaDB base = new BaseMariaDB();
		base.ouvrir(); 
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		boolean existe = base.connexion(mail, mdp);
		System.out.println("connexion? " + existe);
		base.fermer();
		session.setAttribute("connexion", "vrai");
		session.setAttribute("mail", mail);
	}
%>

<%
	String mail = request.getParameter("mail");
	BaseMariaDB base = new BaseMariaDB();
	base.ouvrir();

	personne = base.getPersonne(mail);
	questionnaire = base.getReponsesPersonne(mail);
	questions = base.getQuestions();

	System.out.println(questionnaire);

	request.setAttribute("personne", personne);
	request.setAttribute("questionnaire", questionnaire);
	request.setAttribute("questions", questions);

	base.fermer();
%>

<body>

	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
		<div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.</div>
	</noscript>

	<jsp:include page="header.jsp"></jsp:include>


	<br>
	<div class="w3-row-padding">

		<div class="w3-half">
			<div class="w3-card white">
				<div class="w3-container w3-indigo">
					<h3>Mes information personnel</h3>
				</div>
				<ul class="w3-ul w3-border-top">
					<li>
						<h3>Nom :</h3>
						<p>${personne.per_nom}</p>
					</li>
					<li>
						<h3>Prenom :</h3>
						<p></p>
					</li>
					<li>
						<h3>Mail</h3>
						<p>${personne.connexion.con_idMail}</p>
					</li>
				</ul>
				<div class="w3-container w3-indigo w3-large">
					<span class="w3-right">
						<button class="w3-btn" style="font-weight: 900;">Modifier</button>
					</span>
				</div>
			</div>
		</div>

		<div class="w3-half">
			<div class="w3-card white">
				<div class="w3-container w3-theme">
					<h3>Liste des page conseiller par We-Moë :</h3>
				</div>
				<ul class="w3-ul w3-border-top">
					<li>
						<p>
							1- <a href="https://www.w3schools.com/w3css/default.asp"
								target="_blank">w3.css</a>
						</p>
					</li>
				</ul>
				<div class="w3-container w3-theme w3-large">
					<span class="w3-right"><button class="w3-btn"
							style="font-weight: 900;">Modifier</button></span>
				</div>
			</div>
		</div>
	</div>
	<br>

	<div class="w3-row-padding">
		<div class="w3-card white">
			<div class="w3-padding w3-white w3-display-container">
				<div class="w3-container w3-orange">
					<h3>Mes reponses au questionnaire :</h3>
				</div>
				<c:forEach items="${questions}" var="question">
					<ul class="w3-ul w3-border-top">
						<li>
							<h3>${question}</h3>
							<p>${questionnaire['${question}']}</p>
						</li>
					</ul>
				</c:forEach>
				<div class="w3-container w3-orange w3-large">
					<span class="w3-right"><button class="w3-btn"
							style="font-weight: 900;">Modifier</button></span>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
