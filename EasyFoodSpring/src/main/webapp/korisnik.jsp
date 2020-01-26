<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Plugin CSS -->
<link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->
<link href="/css/freelancer.min.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Plugin CSS -->
<link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->
<link href="css/freelancer.min.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
body {
  background-color:#EAFFE5;
}

header{
  
	background-image: url('img/restoran1.jpg');
	background-position: center;
	 background-repeat:no-repeat;
	  background-size: cover;
    width: 100%;
    height: 500px;
}

</style>

<title>Korisnik</title>
</head>
<body>



	<c:if test="${(empty user) || (user.roll != 'dostavljac')}">
		<h3>Forbiden</h3>
		<a href="/EasyFood/index.jsp">Pocetna</a>
	</c:if>

	<c:if test="${(user.roll == 'korisnik') || (user.roll == 'admin') }">


	<nav
		class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase"
		id="mainNav">
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="/EasyFood/${user.roll}.jsp">Pocetna</a>
		<button
			class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded"
			type="button" data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fa fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
			
			
					 <li class="nav-item mx-0 mx-lg-1">
              	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/EasyFood/korpa.jsp">Moja korpa</a>  
                 </li>

				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="/EasyFood/logout">Izloguj se</a></li>


			</ul>

		</div>
	</div>
	</nav>


	<header >
		
	</header>


		<br>
		<div class="row">
			<div class=col-3>
			<form action="/EasyFood/getJela" method="post">
					

					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-l"
							id="sendMessageButton">Sva jela</button>
					</div>
				</form>
		<p class="text-center text-uppercase text-secondary mb-0">Pretraga
			po vrsti</p>
		<br>
				<form action="/EasyFood/getByVrsta" method="get">
					<div class="form-group  mb-0 pb-2">
						<select class="form-control" name="vrstaKuh">
							<c:forEach items="${vrsta}" var="v">
								<option value="${v}">${v}</option>

							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-l"
							id="sendMessageButton">Pretraga</button>
					</div>
				</form>
		
			
		<p class="text-center text-uppercase text-secondary mb-0">Pretraga
			po kategoriji</p>
		<br>
				<form action="/EasyFood/getByKategorija" method="get">

					<div class="form-group  mb-0 pb-2">
						<select class="form-control" name="kategorija">
							<c:forEach items="${kategorije}" var="k">
								<option value="${k.idKategorija}">${k.nazivKategorije}</option>

							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-l"
							id="sendMessageButton">Pretraga</button>
					</div>


				</form>
			
			</div>
			
			<div class="col">
				<div class="row">
					<c:forEach items="${jela}" var="j" varStatus="loop">
						<div class="col">
							<div class="card" style="width: 15rem;">
								<img src="img/${j.slika}" class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">${j.naziv}</h5>
									<p class="card-text">${j.cena} rsd</p>
									<p class="card-text">${j.vrstaKuhinje}</p>
									<p class="card-text">${j.kategorija.nazivKategorije}</p>
									<a href="/EasyFood/detalji?idJelo=${j.idJelo}" class="btn btn-primary">Detaljnije</a><br>
									
									<p class="card-text">Dodaj u korpu</p>
									
									<form action="/EasyFood/dodajKorpu" method="post">
										<input type="text" name="kolicina" placeholder="kolicina"><br>
										<input type="hidden" name="j" value="${j.idJelo}"><br>
										
										<input type="submit" value="Dodaj u korpu" class="btn btn-primary">
						
									</form>
									
								</div>
							</div>
						</div>
						<c:if test="${(loop.index+1) % 2 != 1}">
							<div class="w-80"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>


	</c:if>

	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/freelancer.min.js"></script>

</body>
</html>