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
    <div class="row">
      <div class="col s6">
        <div style="padding: 35px;" align="center" class="card">
          <div class="row">
            <div class="left card-title">
              <b>Nombre d'utilisateurs par jour</b>
            </div>
          </div>

          <canvas id="utilisateursParJour" width="400" height="200"></canvas>
        </div>
      </div>

      <div class="col s6">
        <div style="padding: 35px;" align="center" class="card">
          <div class="row">
            <div class="left card-title">
              <b>Nombre d'utilisateurs par mois</b>
            </div>
          </div>
          <canvas id="utilisateursParMois" width="400" height="200"></canvas>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col s6">
        <div style="padding: 35px;" align="center" class="card">
          <div class="row">
            <div class="left card-title">
              <b>Nombre d'utilisateurs par page</b>
            </div>
          </div>

          <canvas id="utilisateursParPage" width="400" height="200"></canvas>
        </div>
      </div>

      <div class="col s6">
        <div style="padding: 35px;" align="center" class="card">
          <div class="row">
            <div class="left card-title">
              <b>Pourcentage d'utilisateurs par page</b>
            </div>
          </div>
          <canvas id="pourcentage" width="400" height="200"></canvas>
        </div>
      </div>
    </div>

  </div>
	
<script>
<% 
	java.util.List<Integer> valeursParJours=new java.util.ArrayList<Integer>();
	java.util.List<String> labelsParJours=new java.util.ArrayList<String>();
	base.BaseMongoDB base = new base.BaseMongoDB();
	base.ouvrir();
	com.mongodb.client.FindIterable<org.bson.Document> documentsJours = base.requete();
	java.util.Map<java.util.Date, Integer> mapParJours = base.utilisateurParJour(documentsJours);
	java.util.Map<java.util.Date, Integer> treeMap = new java.util.TreeMap(mapParJours);
	for(java.util.Date key : treeMap.keySet()){
		valeursParJours.add(treeMap.get(key));
		labelsParJours.add(key.getDay()+"/"+(key.getMonth()+1)+"/"+(key.getYear()+1900));
	}
%>
var ctx = document.getElementById("utilisateursParJour").getContext('2d');
var valeursParJours=[];
var labelsParJours=[];
<% for (int i=0; i<valeursParJours.size(); i++) { %>
	valeursParJours[<%= i %>] = "<%= valeursParJours.get(i) %>";
	labelsParJours[<%= i %>] = "<%= labelsParJours.get(i) %>";
<% } %>
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: labelsParJours,
        datasets: [{
            data: valeursParJours,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        },
        legend: {
            display: false
        }
    }
});
</script>

<script>
<% 
	java.util.List<Integer> valeursParMois=new java.util.ArrayList<Integer>();
	java.util.List<String> labelsParMois=new java.util.ArrayList<String>();
	base = new base.BaseMongoDB();
	base.ouvrir();
	com.mongodb.client.FindIterable<org.bson.Document> documentsMois = base.requete();
	java.util.Map<java.util.Date, Integer> mapParMois = base.utilisateurParMois(documentsMois);
	java.util.Map<java.util.Date, Integer> treeMapMois = new java.util.TreeMap(mapParMois);
	for(java.util.Date key : treeMapMois.keySet()){
		valeursParMois.add(treeMapMois.get(key));
		labelsParMois.add((key.getMonth()+1)+"/"+(key.getYear()+1900));
	}
%>
var ctx = document.getElementById("utilisateursParMois").getContext('2d');
var valeursParMois=[];
var labelsParMois=[];
<% for (int i=0; i<valeursParMois.size(); i++) { %>
	valeursParMois[<%= i %>] = "<%= valeursParMois.get(i) %>";
	labelsParMois[<%= i %>] = "<%= labelsParMois.get(i) %>";
<% } %>
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: labelsParMois,
        datasets: [{
            data: valeursParMois,
            backgroundColor: [
                'rgba(54, 162, 235, 0.2)'
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        },
        legend: {
            display: false
        }
    }
});
</script>

<script>
<% 
	java.util.List<Integer> valeursParPage=new java.util.ArrayList<Integer>();
	java.util.List<String> labelsParPage=new java.util.ArrayList<String>();
	base = new base.BaseMongoDB();
	base.ouvrir();
	com.mongodb.client.FindIterable<org.bson.Document> documents = base.requete();
	java.util.Map<String, Integer> mapParPage = base.utilisateurParPage(documents);
	for(String key : mapParPage.keySet()){
		valeursParPage.add(mapParPage.get(key));
		labelsParPage.add(key);
	}
%>
	var ctx = document.getElementById("utilisateursParPage").getContext('2d');
	var valeursParPage=[];
	var labelsParPage=[];
	<% for (int i=0; i<valeursParPage.size(); i++) { %>
		valeursParPage[<%= i %>] = "<%= valeursParPage.get(i) %>";
		labelsParPage[<%= i %>] = "<%= labelsParPage.get(i) %>";
	<% } %>
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: labelsParPage,
	        datasets: [{
	            data: valeursParPage,
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        },
	        legend: {
	            display: false
	        }
	    }
	});
</script>



<script>

var ctx = document.getElementById("pourcentage").getContext('2d');
var pourcentages = [];
var total = 0;
var i;
for(i = 0; i < valeursParPage.length; i++){
	total =parseInt(total)+ parseInt(valeursParPage[i]);
}
for(i = 0; i < valeursParPage.length; i++){
	pourcentages[i] = (valeursParPage[i]/total)*100;
}
var myChart = new Chart(ctx, {
    type: 'polarArea',
    data: {
        labels: labelsParPage,
        datasets: [{
            label: '# of Votes',
            data: pourcentages,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        },
        legend: {
            display: false
        }
    }
});
</script>
  <footer class="indigo page-footer">

    <div class="footer-copyright">
      <div class="container">
         <span><a style='font-weight: bold;' href="https://github.com/piedcipher" target="_blank">We Moë</a></span>
      </div>
    </div>
  </footer>
</body>

</html>

