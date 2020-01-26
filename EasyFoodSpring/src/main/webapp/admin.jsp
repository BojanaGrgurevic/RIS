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

<title>Admin</title>
</head>
<body>

<c:if test="${(empty user) || (user.roll != 'admin') }">
<h3>Forbiden</h3>
<a href="/EasyFood/index.jsp" >Pocetna</a>
</c:if>

<c:if test="${user.roll == 'admin' }">

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
              	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/EasyFood/korisnik.jsp">Sva jela</a>  
                 </li>
			
			
			
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
	
	
	
	<section id="contact">
	<div class="container">

		<h1 class="text-center text-uppercase text-secondary mb-0">Novo jelo</h1><br>

		<div class="row">

			<div class="col-lg-8 mx-auto">
				<form action="/EasyFood/dodajJelo" method="post">
					<div class="form-group  mb-0 pb-2">
						<input class="form-control" id="name" type="text" name="naziv"
							placeholder="Naziv jela" />
					</div>


					<div class="form-group  mb-0 pb-2">
						<input class="form-control" id="name" type="text" name="cena"
							placeholder="Cena" />
					</div>


					<div class="form-group  mb-0 pb-2">
						<input class="form-control" id="name" type="text" name="vrstaKuhinje"
							placeholder="Vrsta kuhinje" />
					</div>
					
					<div class="form-group  mb-0 pb-2">
						<textarea rows="4" cols="1"  style=" width: 400px;" name="detalji" placeholder="Detalji jela">
				</textarea>
					</div>
					
					
					<div class="form-group  mb-0 pb-2">
						<select class="form-control" name="kategorija">
							<c:forEach items="${kategorije}" var="k">
								<option value="${k.idKategorija}">${k.nazivKategorije}</option>

							</c:forEach>
						</select>
					</div>
					
					


					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xl"
							id="sendMessageButton">Dodaj jelo</button>
					</div>
				</form>

			
	
		
		
	
			<form action="/EasyFood/osveziNarudzbine" method="post">
			
				<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xl"
							id="sendMessageButton">Osvezi narudzbine</button>
					</div>
			</form>
			
			<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Id narudzbine</th>
							<th scope="col">Vrednost</th>
							<th scope="col">Adresa</th>
							<th scope="col">Dostavljac</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${neisporuceno}" var="n" varStatus="loop">
							<tr>
								<td>${n.idNarudzbina}</td>
								<td>${n.ukupno}</td>
								<td>${n.adresa}</td>
								<td><form action="/EasyFood/dodajDostavu" method="post">
								<div class="form-group  mb-0 pb-2">
						          <select class="form-control" name="idD">
							      <c:forEach items="${dostavljaci}" var="d">
								 <option value="${d.idUser}">${d.username} </option>
								 
				

							</c:forEach>
						</select>
						</div>
						
						<input type="hidden" name="idN" value="${n.idNarudzbina}">
						
						<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xl"
							id="sendMessageButton">Potvrdi</button>
					</div>
								
								</form></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
		
		</div>
		
			<form action="/EasyFood/getPDF" method="get">
			
				<div class="form-group  mb-0 pb-2">
								 <p>Od:</p>
						          <select class="form-control" name="datumOdS">
							      <c:forEach items="${datumi}" var="d">
								 <option value="${d}">${d} </option>
								 
				

							</c:forEach>
						</select>
						
						 <p>Do:</p>
						 <select class="form-control" name="datumDoS">
							      <c:forEach items="${datumi}" var="d">
								 <option value="${d}">${d} </option>
								 
				

							</c:forEach>
						</select>
						
						<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xl"
							id="sendMessageButton">Izvestaj</button>
					</div>
						
						</div>
			
			
			</form>
		
		
		</div>
	</section>
	
	
	
	




</c:if>


</body>
</html>