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
  		<h1 class="header center orange-text">Envoyer un mail à la newsletter</h1>
  		<div class="col s12">
  		<div class="input-field col s12 m6">
  			<select>
		      <option value="" disabled selected>Choisir une région</option>
		      <option value="1">Toute la france</option>
		      <option value="2">Bretagne</option>
		      <option value="3">Paris</option>
		      <option value="4">Pays de la loire</option>
		      <option value="5">Auvergne</option>
		      <option value="6">Gironde</option>
		    </select>
		    <label>Région</label>
  		</div>
  		</div>
	    <div class="row">
	    <form class="col s12">
	      <div class="row">
          <div class="input-field col s12">
            <textarea id="textarea1" class="materialize-textarea" data-length="500"></textarea>
            <label for="textarea1">Contenu du mail</label>
          </div>
          <button class="btn waves-effect waves-light" type="submit" name="action">Envoyer
		    <i class="material-icons right">send</i>
		  </button> 
        </div>
	    </form>
	  </div>
	    
	    <div class="fixed-action-btn horizontal click-to-toggle">
    	<a class="btn-floating btn-large red">
      		<i class="material-icons">menu</i>
    	</a>
	    <ul>
	      <li><a class="btn-floating red" href="suivi.jsp"><i class="material-icons">assessment</i></a></li>
	      <li><a class="btn-floating yellow darken-1" href="mail.jsp"><i class="material-icons">email</i></a></li>
	      <li><a class="btn-floating green" href="agenda.jsp"><i class="material-icons">event</i></a></li>
	      <li><a class="btn-floating blue" href="editionPages.jsp"><i class="material-icons">edit</i></a></li>
	    </ul>
  	</div>
  </div>
</body>
<script>
$(document).ready(function() {
    $('select').material_select();
  });
       
</script>
</html>

