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
	Personne personne=new Personne();
	
	if (/*request.getParameter("modification") != null*/true) {		
		String mail =request.getParameter("mail");		
		personne =b.getPersonne("nicolas@gmail.com");		
	}
	
	request.setAttribute("liste_question", liste_question);
	request.setAttribute("personne", personne);
	
	
	if(request.getParameter("submit") != null){
		//TODO
		//a finir !!!!!!!!!!!!!!!!!!
	
	}
	
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
	<h2 class="w3-center">Modification des informations du compte</h2>

	<div class="w3-row-padding">

<%


%>
		<form class="w3-container w3-card-4" method="post" action="formulaire-inscription.jsp"  >
			<h2>Information personnel</h2>
			<div class="w3-section">
				<input class="w3-input" type="text" disabled="disabled" name="pnom" value="${personne.getPer_nom()}" ><label>Nom</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="text" disabled="disabled" name="prenom" value="${personne.getPer_prenom()}" ><label>Prenom</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="text"  disabled="disabled" name="mail" value="${personne.getConnexion().getCon_idMail()}"> 
				<label>Email</label>
			</div>
			
			<div class="w3-section">
				<label>A quelle information préferez vous avoir acces ?</label> <br>
				<c:if test="${personne.isPer_risque()}">
					<input id="male" class="w3-radio" type="radio" name="risque" value="rique" checked="checked">
						<label>Risque</label> <br> 
					<input	id="female" class="w3-radio" type="radio" name="risque" value="ras">
						<label>RAS</label> <br>
				</c:if> 
				<c:if test="${!personne.isPer_risque()}">
					<input id="male" class="w3-radio" type="radio" name="risque" value="rique" >
						<label>Risque</label> <br> 
					<input	id="female" class="w3-radio" type="radio" name="risque" value="ras" checked="checked">
						<label>RAS</label> <br>
				</c:if> 
					
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

							<c:if test="${personne.getReponsePersone(question.getQue_id())!=null}">
								<option value="${personne.getReponses().get(personne.getReponsePersone(question.getQue_id())).getValeur()}" selected>${personne.getReponses().get(personne.getReponsePersone(question.getQue_id())).getValeur()} </option>
							</c:if>
							<c:if test="${personne.getReponsePersone(question.getQue_id())==null}">							
								<option value="${reponse.getReq_texte()}">${reponse.getReq_texte()}</option>
							</c:if>
							<label></label><br>
						</c:forEach>
						</select>
					</li>
				</ul>
			</c:forEach>
			<hr>



           

			<input type="submit" value="Sauvegarder" name="submit" class="w3-btn w3-theme">
		</form>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>