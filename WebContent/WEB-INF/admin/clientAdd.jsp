<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dodavanje clienta</title>

<link rel="stylesheet" type="text/css"	href="bootstrap/css/bootstrap.css">

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-4 col-md-3 col-sm-12 col-xs-12"></div>
			<div class="col-xl-4 col-md-6 col-sm-12 col-xs-12">
				<form method="POST" action="?action=dodajClient">
					<div class="form-group">
						<label for="password">Ime Klijenta</label> <input type="text"
							class="form-control" id="client" name="client"
							required="required" maxlength="255">
					</div>
					

					<div class="d-flex justify-content-center align-items-center text-danger">
						<%=session.getAttribute("notification")!=null? session.getAttribute("notification").toString():""%>
					</div>
					
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary mr-1">Dodaj klijenta</button>
						<a class="btn btn-primary mr-1" href="?action=incidenti">Odustani</a>
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