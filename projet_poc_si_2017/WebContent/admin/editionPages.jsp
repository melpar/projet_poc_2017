<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    
  <link href="suivi.css" rel="stylesheet">
	 

</head>


<body class="loaded">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
<script type="text/javascript" src="suivi.js"></script>
<script type="text/javascript" src="chart/Chart.min.js">
$('.button-collapse').sideNav();

$('.collapsible').collapsible();

$('select').material_select();</script>
  

  <header class="indigo" style="padding-left: 0">
   	<img alt="logo" src="../img/cropped-logoWeMoe2.png" style="width:20%">

  </header>

  <div id="main">
  		<h1 class="header center orange-text">Editer les pages personnalisées</h1>
  		<jsp:include page="menu.jsp"></jsp:include>
  </div>	
</body>

</html>

