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
<title>Korpa</title>
</head>
<body>

	<c:if
		test="${(empty user) || (user.roll == 'dostavljac')  }">
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
			
			
			 <c:if test="${user.roll == 'admin' }">	
					 <li class="nav-item mx-0 mx-lg-1">
              	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/EasyFood/korisnik.jsp">Sva jela</a>  
                 </li>
                 </c:if>
			
			

				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="/EasyFood/logout">Izloguj se</a></li>


			</ul>

		</div>
	</div>
	</nav>
	
	
		
		
		<div style="margin: 150px;">
			<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Kolicina</th>
							<th scope="col">Jelo</th>
							<th scope="col">Cena</th>
							<th scope="col">Cena*kolicina</th>
							
								<th scope="col">Izbaci</th>
							
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${korpa}" var="kj" varStatus="loop">
							<tr>
								<td>${kj.kolicina}</td>
								<td>${kj.jelo.naziv}</td>
								<td>${kj.jelo.cena}</td>
								<td>${kj.jelo.cena * kj.kolicina}</td>
									<td><a
										href="/EasyFood/izbaciKorpa?index=${loop.index}">Izbaci</a></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				
				<form action="/EasyFood/naruci" method="post">
					
					<div class="form-group  mb-0 pb-2" >
					<p >UKUPNO: ${ukupno} dinara</p> 
					
				
					
					<input type="text" name="adresa" placeholder="Adresa">
					
				
						<button type="submit" class="btn btn-primary btn-l"
							id="sendMessageButton">Naruci</button>
					</div>
				</form>
		
		</div>
		
		
		
		



	</c:if>




</body>
</html>