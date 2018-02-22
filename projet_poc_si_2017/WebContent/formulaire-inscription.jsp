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

	HashMap<String, ArrayList<String>> questionnaire = new HashMap<>();

	Formulaire f = b.getFormulaire();
	List<Question> liste_question = f.getListeQuesstion();
	for (int i = 0; i < liste_question.size(); i++) {
		ArrayList<String> reponses = new ArrayList<>();
		List<ReponseQuestion> liste_reponse = liste_question.get(i).getQue_listeReponse();
		for (int y = 0; y < liste_reponse.size(); y++) {
			reponses.add(liste_reponse.get(y).getReq_texte());
		}
		questionnaire.put(liste_question.get(i).getQue_question(), reponses);
	}

	request.setAttribute("questionnaire", questionnaire);

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
<%HistoriqueUtil.creer(session).addPageHistorique("formulaire-inscription"); %>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<br>
	<h2 class="w3-center">Forms and Lists</h2>

	<div class="w3-row-padding">


		<form class="w3-container w3-card-4" >
			<h2>Information personnel</h2>
			<div class="w3-section">
				<input class="w3-input" type="text" required> <label>Nom</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="text" required> <label>Prenom</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="text" required> <label>Email</label>
			</div>
			<div class="w3-section">
				<label>A quelle information vous préfere avoir acces ?</label> <br>
				<input id="male" class="w3-radio" type="radio" name="risque"
					value="rique"> <label>Risque</label> <br> <input
					id="female" class="w3-radio" type="radio" name="risque" value="ras">
				<label>RAS</label> <br>
			</div>

			<hr>
			<h2>Questionnaire</h2>

			<c:forEach items="${questionnaire}" var="question">
				<ul class="w3-ul w3-border-top">
					<li>
						<h3>${question.key}</h3>
						<select name="${question.value}">
						 <c:forEach items="${question.value}"
							var="reponse">							
								<option value="${reponse}">${reponse}</option>
							<label></label><br>
						</c:forEach>
						</select>
					</li>
				</ul>
			</c:forEach>
			<hr>



           

			<input type="submit" value="S'inscrire" class="w3-btn w3-theme">
		</form>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
