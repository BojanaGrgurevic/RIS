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
	background-color: #EAFFE5;
}
</style>

<title>Easy food</title>
</head>
<body>

	<section id="contact">
	<div class="container">


        	<div class="col-lg-8 mx-auto">
		<form action="/EasyFood/login" method="post">

			<div class="form-group  mb-0 pb-2">
			<input class="form-control"  type="text" name="username" placeholder="username"> 
			</div>
			<div class="form-group  mb-0 pb-2">
			<input class="form-control"  type="password"name="pass" placeholder="password"> 
			</div>
			
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-l"
							id="sendMessageButton">Logovanje</button>
					</div>
			
		</form>
		
		<c:if test="${!empty msgLogin}">
				 <div class="alert alert-danger">
				      ${msgLogin}
				</div>
				</c:if>
		
		
		</div>

		<div class="col-lg-8 mx-auto">
		<form action="/EasyFood/registration" method="post">
		
				<div class="form-group  mb-0 pb-2">
			<input class="form-control" type="text" name="username" placeholder="username"> 
			</div>
				<div class="form-group  mb-0 pb-2">
			<input class="form-control" type="password"name="pass" placeholder="password"> 
			</div>
				<div class="form-group  mb-0 pb-2">
			<input class="form-control" type="text" name="ime" placeholder="ime">
			</div>
				<div class="form-group  mb-0 pb-2">
			 <input class="form-control" type="text" name="prezime" placeholder="prezime"> 
			 </div>
			 	<div class="form-group  mb-0 pb-2">
			 <input class="form-control" type="text" name="email" placeholder="email">
			 </div>
			 	<div class="form-group  mb-0 pb-2">
			<input class="form-control" type="text" name="tel" placeholder="Telefon"> 
			</div>
			
			<div class="form-group">
						<button type="submit" class="btn btn-primary btn-l"
							id="sendMessageButton">Registruj se</button>
					</div>
			
		</form>
		
		<c:if test="${!empty msg}">
				 <div class="alert alert-danger">
				        ${msg}
				</div>
				</c:if>
		
		
		</div>


	</div>

	</section>


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