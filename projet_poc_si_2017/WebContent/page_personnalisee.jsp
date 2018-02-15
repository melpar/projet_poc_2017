<!doctype html>
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
<%

	bean.neo4j.Noeud noeud = util.PagePersonnaliseeUtil.getNoeud(session.getAttribute("mail").toString());
	String contenu = noeud.getContenu();
	String titre = noeud.getTitre();
	String nom = noeud.getNom();
	String kit = noeud.getKit();
	boolean diffusion = noeud.isDiffusion();
	boolean contrat = noeud.isContrat();
	boolean relance = noeud.isRelance();
	

%>

<title><%=nom%></title>
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
    	<h1 id="non"><%=titre%></h1>
    	<div id="contenu"><%=contenu%></div>
		<div id="contenu"><%=kit%></div>
		<div id="contenu"><%=diffusion%></div>
		<div id="contenu"><%=contrat%></div>
		<div id="contenu"><%=relance%></div>
    </div>
    
    <br>
	<hr>
    
        <jsp:include page="footer.jsp"></jsp:include>

    
  </body>
</html>
