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

<title>Detaljnije</title>
</head>
<body>


	<c:if test="${empty user}">
		<h3>Forbiden</h3>
		<a href="/EasyFood/index.jsp">Pocetna</a>
	</c:if>

	<c:if test="${ !empty user}">

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
				
				
			       <c:if test="${user.roll == 'admin' }">	
			 
					 <li class="nav-item mx-0 mx-lg-1">
              	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/EasyFood/korisnik.jsp">Sva jela</a>  
                 </li>
                 </c:if>
			
				 
				 
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



		<div style="margin-top: 200px">
			<div style="float: right; margin-right: 110px;">


				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col" style="width:100px;">Korisnik</th>
							<th scope="col" style="width:650px;">Komentar</th>
							<c:if test="${user.roll == 'admin' }">
								<th scope="col">Admin</th>
							</c:if>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${detalji.komentars}" var="kom">
							<tr>
								<td>${kom.korisnik.username}</td>
								<td>${kom.text}</td>
								<c:if test="${user.roll == 'admin' }">
									<td><a
										href="/EasyFood/obrisiKomentar?idKomentara=${kom.idKomentar}">Obrisi</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>

				</table>

				<form action="/EasyFood/dodajKomentar" method="post">

					<div class="form-group  mb-0 pb-2">
						<textarea rows="4" cols="1"  style=" width: 750px;" name="text">
				</textarea>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xl"
							id="sendMessageButton">Dodaj komentar</button>
					</div>

				</form>


			</div>

			<div style="float: left; margin-left: 110px;">


				<div class="card" style="width: 30rem;">
					<img src="img/${detalji.slika}" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">${detalji.naziv}</h5>
						<p class="card-text">${detalji.cena}rsd</p>
						<c:if test="${user.roll == 'admin' }">
							<form action="/EasyFood/azururajCenu" method="post">
								<div class="form-group  mb-0 pb-2">
									<input class="form-control" id="name" type="text"
										name="cena" placeholder="Nova cena" />
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-m"
										id="sendMessageButton">OK</button>
								</div>
							</form>
						</c:if>
						<p class="card-text">${detalji.vrstaKuhinje}</p>
						<p class="card-text">${detalji.kategorija.nazivKategorije}</p>
						<p class="card-text">${detalji.detalji}</p>


					</div>
				</div>
			</div>
	</c:if>


</body>
</html>