<!doctype html>

<%@page import="util.HistoriqueUtil"%>
<%@page import="base.BaseMariaDB"%>
<%@page import="javafx.scene.control.Alert"%>
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
<%
HistoriqueUtil historique = HistoriqueUtil.creer(session);
historique.addPageHistorique("index");
%>
<%
if(request.getParameter("newsletter") != null){
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
	if(request.getParameter("connexion") != null){
		BaseMariaDB base = new BaseMariaDB();
		base.ouvrir();
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		boolean existe = base.connexion(mail, mdp); 
		System.out.println("connexion? "+existe);
		base.fermer();
		if(existe){
			session.setAttribute("connexion", "vrai");
			session.setAttribute("mail", mail);
		}
	}
%>
  <body>

    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <jsp:include page="header.jsp"></jsp:include>
    
    <div class="w3-row-padding w3-center w3-margin-top">
        <div class="w3-half">
            <div class="w3-card w3-container" style="min-height:460px">
                <h3>Je veux changer d'assurance</h3><br>
                <img alt="logo" src="img/logo-changement.png" height="60px">
                <p>Il n'est pas évident de s'y retrouver dans tous les différents textes de 
                loi qui entourent le changement de son assurance emprunteur : loi Chatel, loi 
                Lagarde, loi Hamon, loi Bourquin sur la résiliation annuelle. Si vous avez souscrit
                 à  un emprunt et que vous vous demandez si l'on peut changer d'assurance 
                 de crédit et comment faire pour la remplacer, voici les réponses à  toutes 
                 vos questions</p>
				<button id="bouton_infos_ass" class="w3-button w3-theme-d3" onclick="" style="font-weight:900;">En savoir plus</button>                 
            </div>
        </div>
        
        <div class="w3-half">
            <div class="w3-card w3-container" style="min-height:460px">
                <h3>Je veux faire un emprunt et j'ai besoin d'informations</h3><br>
                <img alt="logo" src="img/logo-cheque.png" height="60px">
                <p>Vous souhaitez réaliser un emprunt mais vous àªtes perdu dans les démarches à 
                suivre? Voici les réponses à  vos questions. Pour des informations plus adaptées 
                à  votre parcours, n'hésitez pas à  vous inscrire.</p>
                <button id="bouton_infos_cre" class="w3-button w3-theme-d3" onclick="" style="font-weight:900;">En savoir plus</button>                 
                
            </div>
        </div>
    </div>
    <br>
    <div class="w3-row-padding w3-center w3-margin-top">
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
    </div>
    <br>
    

    
    <hr>
    <h2 class="w3-center">Inscription ou connexion</h2>
    
    
    <div class="w3-row-padding">
        <div class="w3-half">
        	<button type="button" class="w3-button w3-theme-d3" style="font-weight:900;" onclick="window.location='creation_compte.jsp';">Créer un compte</button>
        </div>
        
        <div class="w3-half">
            <form class="w3-container w3-card-4" method="GET">
                <h2>Connexion</h2>
                <div class="w3-section">
                    <input class="w3-input" name="mail" type="text" required>
                    <label>Mail</label>
                    </div>
                <div class="w3-section">
                    <input class="w3-input" name="mdp" type="password" required>
                    <label>Mot de passe</label>
                </div>
				<input type="submit" name="connexion" class="w3-button w3-theme-d3" style="font-weight:900;" value="Valider">            </form>
            </div>
        </div>
    <hr>
    
    
    <hr>
    <div class="w3-center">
        <h2>Les enjeux de we moë</h2>
    </div>
    
    <div class="w3-row-padding">
        
        <div class="w3-half">
            <div class="w3-card white">
                <div class="w3-container w3-indigo">
                    <h3>Strategie de referencement</h3>
                </div>
                <div class="w3-container">
                    <h3 class="w3-text-indigo">Movies 2014</h3>
                </div>
                <ul class="w3-ul w3-border-top">
                    <li>
                        <h3>Frozen</h3>
                        <p>The response to the animations was ridiculous</p>
                    </li>
                    <li>
                        <h3>The Fault in Our Stars</h3>
                        <p>Touching, gripping and genuinely well made</p>
                    </li>
                    <li>
                        <h3>The Avengers</h3>
                        <p>A huge success for Marvel and Disney</p>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="w3-half">
            <div class="w3-card white">
                <div class="w3-container w3-theme">
                    <h3>Accesibiliter et disponibiliter</h3>
                </div>
                <div class="w3-container">
                    <h3 class="w3-text-theme">Movies 2014</h3>
                </div>
                <ul class="w3-ul w3-border-top">
                    <li>
                        <h3>Frozen</h3>
                        <p>The response to the animations was ridiculous</p>
                    </li>
                    <li>
                        <h3>The Fault in Our Stars</h3>
                        <p>Touching, gripping and genuinely well made</p>
                    </li>
                    <li>
                        <h3>The Avengers</h3>
                        <p>A huge success for Marvel and Disney</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <br>

	    <jsp:include page="footer.jsp"></jsp:include>
	
  </body>
</html>
