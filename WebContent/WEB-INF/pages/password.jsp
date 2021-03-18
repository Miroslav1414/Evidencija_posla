<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Promjena lozinke</title>

<link rel="stylesheet" type="text/css"	href="bootstrap/css/bootstrap.css">

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-4 col-md-3 col-sm-12 col-xs-12"></div>
			<div class="col-xl-4 col-md-6 col-sm-12 col-xs-12">
				<form method="POST" action="?action=promjeniLozinku">
					<div class="form-group">
						<label for="password">Nova lozinka</label> <input type="password"
							class="form-control" id="password" name="password"
							required="required"
							onblur="checkPassword()" maxlength="20">
					</div>
					<div class="form-group">
						<label for="password2">Ponovi lozinku</label> <input type="password"
							class="form-control" id="password2" name="password2" 
							required="required"
							onkeyup="checkSeccondPassword()" maxlength="32">
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary mr-1">Izmjeni lozinku</button>
						<a class="btn btn-primary mr-1" href="?action=incidenti">Odustani</a>
					</div>

					<div class="d-flex justify-content-center align-items-center text-danger">
						<%=session.getAttribute("notification")!=null? session.getAttribute("notification").toString():""%>
					</div>
					
				</form>
			</div>

		</div>
	</div>




	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
	<script src="js/javascript.js"></script>
</body>
</html>