<!doctype html>

<%@page import="util.HistoriqueUtil"%>
<%@page import="bean.mariadb.Question"%>
<%@page import="base.BaseMariaDB"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="com.sun.glass.ui.Window"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="personne" class="bean.mariadb.Personne" scope="request"></jsp:useBean>
<jsp:useBean id="questionnaire" class="java.util.HashMap"
	scope="request"></jsp:useBean>
<jsp:useBean id="questions" class="java.util.HashMap"
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
	System.out.println("recuperation données pour : "+session.getAttribute("mail"));
	String mail = session.getAttribute("mail").toString();
	BaseMariaDB base = new BaseMariaDB();
	base.ouvrir();

	personne = base.getPersonne(mail);
	System.out.println("nom : "+personne.getPer_nom());
	questionnaire = personne.getReponses(); 
	System.out.println(questionnaire);

	questions = new java.util.HashMap<String, String>();
	
	
	java.util.Set<bean.mariadb.Question> cles = questionnaire.keySet();
	java.util.Iterator<bean.mariadb.Question> it = cles.iterator();
	while (it.hasNext()){
	   bean.mariadb.Question cle= it.next(); // tu peux typer plus finement ici
	   bean.mariadb.ReponsePersonne valeur = (bean.mariadb.ReponsePersonne)questionnaire.get(cle); // tu peux typer plus finement ici
	   questions.put(cle.getQue_question(), valeur.getValeur());
	}
	request.setAttribute("personne", personne);
	request.setAttribute("questionnaire", questionnaire);
	request.setAttribute("questions", questions);
	base.fermer();
%>
<%HistoriqueUtil.creer(session).addPageHistorique("page-utilisateur"); %>
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
						<p>${personne.per_prenom}</p>
					</li>
					<li>
						<h3>Mail</h3>
						<p>${personne.connexion.con_idMail}</p>
					</li>
				</ul>
			</div>
		</div>

		<div class="w3-half">
			<div class="w3-card white">
				<div class="w3-container w3-theme">
					<h3>Page conseillée par We-Moë :</h3>
				</div>
				<ul class="w3-ul w3-border-top">
					<li>
						<p>
							<a href="page_personnalisee.jsp">Page personnalisée</a>
						</p>
					</li>
				</ul>
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
							<h3>${question.key}</h3>
							<p>${question.value}</p>
						</li>
					</ul>
				</c:forEach> 
			</div>
		</div>
		<div class="w3-container w3-theme w3-large"><span class="w3-left"><a  href="modification_utilisateur.jsp" style="text-decoration:none">Modifier</a></span></div>
		<br>
	</div>
	

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
