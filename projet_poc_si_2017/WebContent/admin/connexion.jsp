<%@page import="base.BaseMariaDB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<%
	if (request.getParameter("connexion") != null) {
		BaseMariaDB base = new BaseMariaDB();
		base.ouvrir();
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		boolean existe = base.connexionAdmin(mail, mdp);
		System.out.println("connexion? " + existe);
		base.fermer();
		System.out.println(existe);
		if (existe) {
			session.setAttribute("connexion", "vrai");
			session.setAttribute("mail", mail);
			response.sendRedirect("suivi.jsp");
		}
	}else{
		//response.sendRedirect("../index.jsp");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion admin</title>
</head>
<body>

	<jsp:include page="../header.jsp"></jsp:include>

	<hr>
	<h2 class="w3-center">Connection admin</h2>


	<div class="w3-row-padding">



		<form class="w3-container w3-card-4" method="GET">
			<h2>Connexion</h2>
			<div class="w3-section">
				<input class="w3-input" type="text" name="mail" required> <label>Mail</label>
			</div>
			<div class="w3-section">
				<input class="w3-input" type="password" name="mdp" required> <label>Mot
					de passe</label>
			</div>
			
			<div class="w3-section">
				<input class="w3-input" type="submit" name="connexion" value="connexion" > 
			</div>			
		</form>

	</div>
	<hr>


	<hr>

	<br>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>