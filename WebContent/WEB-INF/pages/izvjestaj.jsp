<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="asseco.beans.IncidentiBean" %>
<%@page import="asseco.beans.KorisniciBean" %>
<%@page import="asseco.beans.PomocniBean" %>
<%@page import="asseco.dto.Incidenti" %>
<jsp:useBean id="incidentiBeanLista" type="asseco.beans.IncidentiBean" scope="session"/>
<jsp:useBean id="pomocniBean" type="asseco.beans.PomocniBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Izvjestaj</title>

<link rel="stylesheet" type="text/css"	href="bootstrap/css/bootstrap.css">

<style>
	#main {
	   font-size: 12px;
	}
</style>

</head>
<body>

<div class="header">
		<%@include file="header.jsp"%>
	</div>




<div id ="main">



<div class="table-sm">
		  <table id="tabela_izvjestaj" name = "tabela_izvjestaj" class="table table-bordered">
			<thead class="thead-dark">
			<tr>
				<th scope="col">Client</th>
				<th scope="col">Project</th>
				<th scope="col">Incident</th>
				<th scope="col">Broj zahtjeva</th>
				<th scope="col">TaskType</th>
				<th scope="col">TaskName</th>

				<th scope="col">Work</th>
				<th scope="col">BaselineWork</th>
				<th scope="col">ActualWork</th>
				<th scope="col">Duration</th>
				<th scope="col">StartDate</th>
				<th scope="col">CreatedBy</th>
				
				<th scope="col">Payment Status</th>
				<th scope="col">Product BSW</th>
				<th scope="col">Status</th>
<!-- 				<th scope="col">ID</th> -->
<!-- 				<th scope="col">Modification Date</th> -->
<!-- 				<th scope="col">ModifiedBY</th> -->
			</tr>
		  </thead>
		  
			  <tbody>
			  		<%
						for( Incidenti inc : incidentiBeanLista.getLista()){
							out.println("<tr id = \"" + inc.getId()  + "\">");
							
							out.println("<td>" + inc.getClient() +"</td>");
							out.println("<td>" + inc.getProject() + "</td>");
							out.println("<td>Incident</td>");
							out.println("<td>" + inc.getBrojZahteva() + "</td>");
							out.println("<td>" + inc.getTaskType() + "</td>");
							out.println("<td>" + inc.getTaskName() +  "</td>");
							
							out.println("<td>" + inc.getWork() + "</td>");
							out.println("<td>" + inc.getWork() + "</td>");
							out.println("<td>" + inc.getWork() + "</td>");
							out.println("<td>1</td>");
							out.println("<td>" + inc.getStartDate() + "</td>");
							out.println("<td>" + inc.getCreatedBy() + "</td>");
							
							out.println("<td>" + inc.getPaymentStatus() + "</td>");
							out.println("<td>" + inc.getProductBSW() + "</td>");
							out.println("<td>");%> 
							<select name = "newToCompleated<%out.print(inc.getId());%>" id = "newToCompleated<%out.print(inc.getId());%>" onchange="updateCompleated(<%out.print(inc.getId());%>)">
								<option selected value = "New">New</option>
								<option value = "Compleated">Compleated</option>
								<option value = "Approved">Approved</option>
								<option value = "Not Acceptable">Not Acceptable</option>
							</select>
							<%
							out.println("</td>");
//  						out.println("<td>" + inc.getId() + "</td>");
// 							out.println("<td>" + inc.getModificationDate() + "</td>");
// 							out.println("<td>" + inc.getModifiedBy() + "</td>");
							out.println("</tr>");
						}
					%>
				
			  </tbody>
		  </table>
		</div>


</div>




	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
	<script src="js/javascript.js"></script>
	<script src="js/ddtf.js"></script>
	
	
	<script>
	
	
	jQuery('#tabela_izvjestaj').ddTableFilter();
	</script>
	
</body>
</html>




