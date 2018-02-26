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
	String mail;
	if ((mail=(String)request.getSession().getAttribute("mail"))!=null) {		
		if(request.getParameter("submit") != null){
			for(int i=0;i<liste_question.size();i++){
				int id =liste_question.get(i).getQue_id();
				String reponse =request.getParameter(id+"");
				System.out.println(id +" :"+reponse);
				if(b.updateReponsePersonne(id, reponse,mail)==false){
					System.out.println("erreur update") ;
				}
			}
			System.out.println("update ok") ;
			response.sendRedirect("page-utilisateur.jsp");
		}		
		personne =b.getPersonne(mail);		
	}
	
	request.setAttribute("liste_question", liste_question);
	request.setAttribute("personne", personne);
	
	
	
	
	/*List<ReponseQuestion> l = f.getListeQuesstion().get(5).getQue_listeReponse();
	for (int i=0;i< l.size();i++){
		System.out.println(l.get(i).getReq_texte());
	}*/
	
	
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
		<form class="w3-container w3-card-4" method="post" action="modification_utilisateur.jsp"  >
			<h2>Informations personnelles</h2>
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
				<label>A quelle informations préferez-vous avoir accès ?</label> <br>
				<c:if test="${personne.isPer_risque()}">
					<input id="male" class="w3-radio" type="radio" name="risque" value="oui" checked="checked">
						<label>Risque aggravé de santé</label> <br> 
					<input	id="female" class="w3-radio" type="radio" name="risque" value="non">
						<label>Pas de risque aggravé de santé</label> <br>
				</c:if> 
				<c:if test="${!personne.isPer_risque()}">
					<input id="male" class="w3-radio" type="radio" name="risque" value="oui" >
						<label>Risque aggravé de santé</label> <br> 
					<input	id="female" class="w3-radio" type="radio" name="risque" value="non" checked="checked">
						<label>Pas de risque aggravé de santé</label> <br>
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

							<c:if test="${personne.getReponses().get(personne.getReponsePersone(question.getQue_id())).getValeur().equals(reponse.getReq_texte())}">
								<option value="${personne.getReponses().get(personne.getReponsePersone(question.getQue_id())).getValeur()}" selected>${personne.getReponses().get(personne.getReponsePersone(question.getQue_id())).getValeur()} </option>
							</c:if>
							<c:if test="${!personne.getReponses().get(personne.getReponsePersone(question.getQue_id())).getValeur().equals(reponse.getReq_texte())}">							
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