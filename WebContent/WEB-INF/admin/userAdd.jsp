<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>unos novog korisnika</title>
	<link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
	<script src="js/javascript.js"></script>
	

	
</head>
<body>
	

	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-4 col-md-3 col-sm-12 col-xs-12"></div>
			<div class="col-xl-4 col-md-6 col-sm-12 col-xs-12">
				<form method="POST" action="?action=dodajRadnika" >
					<div class="form-group">
						<label for="username">Korisnicko ime</label> 
						<input type="text"
							class="form-control" id="username" name="username"
							required="required" maxlength="200" onblur="checkUsername()">
					</div>
					
					<div class="form-group">
						<label for="password">Ime i prezime</label> 
						<input type="text"
							class="form-control" id="imeIPrezime" name="imeIPrezime"
							required="required" maxlength="220" onblur="checkImeIPrezime()">
					</div>
					<div class="form-group">
						<input class="" type="checkbox" name="admin" id="admin">
						  <label class="form-check-label" for="admin">
						    Admin
						  </label>
					</div>
					

					<div class="d-flex justify-content-center align-items-center text-danger">
						<%=session.getAttribute("notification")!=null? session.getAttribute("notification").toString():""%>
					</div>
					
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary mr-1">Dodaj korisnika</button>
						<a class="btn btn-primary mr-1" href="?action=incidenti">Odustani</a>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>