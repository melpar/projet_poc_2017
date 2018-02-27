<!doctype html>

<%@page import="util.HistoriqueUtil"%>
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

	<%
	System.out.println("test : "+session.getAttribute("connexion"));
	System.out.println("test : "+session.getAttribute("mail"));
	%>
	<%HistoriqueUtil.creer(session).addPageHistorique("creation_compte"); %>
  <body>

    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <jsp:include page="header.jsp"></jsp:include>
    

    

    
    <hr>
    <h2 class="w3-center">Inscription ou connexion</h2>
    
    
    <div class="w3-row-padding">
        <div class="w3-half">
        	<button type="button" onclick="window.location='formulaire-inscription.jsp';">Créer un compte</button>
        </div>
        
        <div class="w3-half">
            <form class="w3-container w3-card-4">
                <h2>J'ai déjà un compte</h2>
                <div class="w3-section">
                    <input class="w3-input" type="text" required>
                    <label>Mail</label>
                    </div>
                <div class="w3-section">
                    <input class="w3-input" type="password" required>
                    <label>Mot de passe</label>
                </div>
        		<button type="button" onclick="window.location='connexion.jsp';">Connexion</button>
            </form>
            </div>
        </div>
    <hr>
    
    
    <hr>

    <br>

    
        <jsp:include page="footer.jsp"></jsp:include>

    
  </body>
</html>
