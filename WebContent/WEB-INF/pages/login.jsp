<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" type="text/css"	href="bootstrap/css/bootstrap.css">
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>

</head>
<body>
	<div class="header">
		<%@include file="header.jsp"%>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-4 col-md-3 col-sm-12 col-xs-12"></div>
			<div class="col-xl-4 col-md-6 col-sm-12 col-xs-12">
				<form method="POST" action="?action=login">
					<div class="form-group" style="text-align: center;">
						<label for="uesrname" >Korisnicko ime</label> 
						<input type="text"
							class="form-control" id="username" name="username">
					</div>
					<div class="form-group" style="text-align: center;">
						<label for="password" >Lozinka</label> 
						<input type="password"
							class="form-control" id="password" name="password">
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary">Prijavi se</button>
					</div>

					<div class="text-danger" style="text-align: center;">
						<%=session.getAttribute("notification")!=null? session.getAttribute("notification").toString():""%>
					</div>
					
				</form>
			</div>

		</div>
	</div>
	

</body>
</html>