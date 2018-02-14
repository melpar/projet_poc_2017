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
	BaseNeo4j greeter = new BaseNeo4j();
	Arbre a = greeter.creerArbre();
	System.out.println(a.toString());

	String contenu = a.toString();
	String titre = "titre";
	

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
    
    <hr>
    <div class="w3-row-padding">
    	<h1 id="titre"><%=titre%></h1>
    	<div id="contenu"><%=contenu%></div>
    </div>
    
    <br>
	<hr>
    
        <jsp:include page="footer.jsp"></jsp:include>

    
  </body>
</html>
