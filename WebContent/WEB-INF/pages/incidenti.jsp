<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="asseco.beans.IncidentiBean" %>
<%@page import="asseco.beans.KorisniciBean" %>
<%@page import="asseco.beans.PomocniBean" %>
<%@page import="asseco.dto.Incidenti" %>
<jsp:useBean id="incidentiBeanLista" type="asseco.beans.IncidentiBean" scope="session"/>
<jsp:useBean id="pomocniBean" type="asseco.beans.PomocniBean" scope="session"/>
<jsp:useBean id="logovanKorisnik" type="asseco.beans.KorisniciBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Incidenti</title>

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
	
<%
	if (logovanKorisnik.getKorisnik().getAdmin() > 0){
		%>
		<div class="header">
			<%@include file="headerAdmin.jsp"%>
		</div>
		<%
	}
%>




<nav class="navbar navbar-light bg-light">
  <form class="form-inline">
    
  </form>
  
  <form action="?action=paging" method="post">    
	<input type="number" min="1" value="<%out.print(Integer.parseInt(pomocniBean.getStartPaging())); %>" step="1" id="startPage" name="startPage"> - 
	<input type="number" value="<%out.print(Integer.parseInt(pomocniBean.getEndPaging())); %>" min="2" step="1" id="endPage" name="endPage"> 
    <input type="submit" name="page" value="OK">
    
	</form>
</nav>

<div id ="main">



<div class="table-sm">
		  <table class="table table-striped table-bordered">
			<thead class="thead-dark">
			<tr>
				<th scope="col">Client</th>
				<th scope="col">Project</th>
				<th scope="col">Broj zahtjeva</th>
				<th scope="col">TaskType</th>
				<th scope="col">TaskName</th>

				<th scope="col">Work</th>
				<th scope="col">StartDate<a href="?action=sort"> <%out.print(pomocniBean.getSort()); %> </a>
				</th>
				<th scope="col">CreatedBy
					<form action="?action=filterByName" method="post">
						<select name="filterCteatedBy" id="filterCteatedBy" onchange="this.form.submit()">
							<option value="All">All</option>
						<% for (String s: new KorisniciBean().sviKorisnici()){
							if(s.equals(pomocniBean.getFilterCreatedBy()))
								out.println("<option selected value=\"" +s + "\">"+s+"</option>");
							else
								out.println("<option value=\"" +s + "\">"+s+"</option>");
						}						
						%>
						</select>
					</form>
				</th>
				<th scope="col">Status</th>
				<th scope="col">Payment Status</th>
				<th scope="col">Product BSW</th>
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
							out.println("<td>" + inc.getBrojZahteva() + "</td>");
							out.println("<td>" + inc.getTaskType() + "</td>");
							out.print("<td>");
					%>

							
							<div class="dropdown">
							<%out.print(inc.getTaskName()); %>
							  <button class="btn btn-sm dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    
							  </button>
							  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
							  	<form method="post" action="?action=izmjenaIncidenta_<%out.println(inc.getId());%>">
							  		<button class="dropdown-item" type="submit" >Izmjeni</button>
							  	</form>							    
							    <button class="dropdown-item" type="button" onclick="deleteIncident(<%out.println(inc.getId());%>)">Izbrisi</button>
							  </div>
							</div>


							
							<%out.println("</td>");
							out.println("<td>" + inc.getWork() + "</td>");
							out.println("<td>" + inc.getStartDate() + "</td>");
							out.println("<td>" + inc.getCreatedBy() + "</td>");
							out.println("<td>" + inc.getStatus() + "</td>");
							out.println("<td>" + inc.getPaymentStatus() + "</td>");
							out.println("<td>" + inc.getProductBSW() + "</td>");
// 							out.println("<td>" + inc.getId() + "</td>");
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
</body>
</html>




