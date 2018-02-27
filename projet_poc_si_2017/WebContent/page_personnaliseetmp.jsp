<!doctype html>
<%@page import="util.HistoriqueUtil"%>
<%@page import="org.bson.Document"%>
<%@page import="base.BaseNeo4j"%>
<%@page import="bean.neo4j.Arbre"%>
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


<title>Changer d'assurance</title>
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
    <div class="w3-row-padding">
    	<h1 id="nom">Je  veux changer d'assurance</h1>
    	<div id="contenu">
    		<p>
    			Il n'est pas évident de s'y retrouver dans tous les différents textes de loi qui entourent le changement de son assurance emprunteur : loi Chatel, loi Lagarde, loi Hamon, loi Bourquin sur la résiliation annuelle... Si vous avez souscrit à un emprunt immobilier et que vous vous demandez si l'on peut changer d'assurance de crédit immobilier et comment faire pour la remplacer, voici les réponses à toutes vos questions :
    		</p>
    		<img alt="infos" style="width: 20%" src="img/etapes-changement-hamon.png">
    	</div>
		<div id="contenu"><button class="w3-button w3-theme-d3">Télécharger le kit d'aide</button></div>
		<div id="contenu"><div class="w3-row-padding w3-center w3-margin-top">
        <div class="w3-row w3-border">
            <div class="w3-half w3-container w3-blue w3-border">
                <h5>Ne ratez pas les actualités de we moë</h5>
                <p>Inscrivez-vous à  la newsletter</p>
            </div>
            <div class="w3-half w3-container">
                <form method="GET">
                    <input name="mail" class="w3-input" type="text" placeholder="Adresse email" required>
                    <input type="submit" name="newsletter" class="w3-button w3-theme-d3" style="font-weight:900;" value="Valider">
                </form>
            </div>
        </div>
    </div></div>
		<div id="contenu"><button class="w3-button w3-theme-d3">Afficher les contrats proposés</button></div>
    </div>
    
    <br>
	<hr>
    
        <jsp:include page="footer.jsp"></jsp:include>

    
  </body>
</html>
