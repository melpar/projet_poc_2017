<%@page import="util.HistoriqueUtil"%>
<%@page import="com.sun.glass.ui.Window"%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

   
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>We moë</title>
    
    
    <!-- <script type="text/javascript" language="javascript" src="project_poc_2017/project_poc_2017.nocache.js"></script> -->
  </head>
  <body>
<h1>
    	<a href="/projet_poc_si_2017/index.jsp"><img alt="logo" src="img/cropped-logoWeMoe.jpg"></a>
    	<%
    	if(session.getAttribute("connexion") != null){ %>
    	
    	<%
    	HistoriqueUtil.creer(session).setIdUtilisateur((String)session.getAttribute("mail"));
    	%>
    	
    	<a href="page-utilisateur.jsp" class="w3-btn w3-theme" style="background-color:orange !important; float:right;margin-right: 5px">Mon compte</a>
    	<%} %>
    </h1>
    
    
    <header class="w3-container w3-theme w3-padding" id="myHeader">
        <div class="w3-center">
          <h4>We moë, une innovation sociale.</h4>
          <h4>Gaëdig Le Moing, co-fondatrice de we moë nous explique pourquoi elle s'est lancée dans cette aventure et nous partage son témoignage.</h4>
            <div class="w3-padding-32">
              <a href="https://www.facebook.com/ticketforchange/videos/1136535766478310/?hc_ref=ARR9BJ7PCWEd9Fk0W0IN4uXt4jMccNg3KYmq0LrZfW7PgmVKxSfrSQ44T32fLHJUBCI&pnref=story"><button class="w3-button w3-theme-d3" style="font-weight:900;">Pour voir la vidéo, c'est par ici.</button></a>
            </div>
          </div>
          
    </header>
</body>
</html>