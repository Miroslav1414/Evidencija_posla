<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="logovanKorisnik" type="asseco.beans.KorisniciBean" scope="session"/>
<jsp:useBean id="pomocniBean" type="asseco.beans.PomocniBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
	

	
</head>
<body>
	<div class="header">
		<%@include file="header.jsp"%>
	</div>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2 col-md-2 col-sm-12 col-xs-12"></div>
			<div class="col-xl-8 col-md-8 col-sm-12 col-xs-12">
				<form method="POST" action="?action=insertIncident">
					<table class="table table-sm">
						<tbody>
						
<!-- 						<tr class="form-group" >
							<td nowrap="true" valign="top" width="190px"  >
								<h5>Incident</h5>
							</td>
							<td style="background-color:#e4ebe6" valign="top" width="400px" >
								<input class="form-control" name="" id ="" type="text" value="Incident" maxlength="255" title="Incident"><br>	
							</td>
						</tr>
 -->					
						<tr>
							<td nowrap="true" valign="top" width="190px">
								<h5>TaskName</h5>
							</td>
							<td style="background-color:#e4ebe6" valign="top"  width="400px">
								<input class="form-control" name="taskName" id="taskName" type="text" maxlength="255" title="TaskName" required="required">
								<br>
							</td>
						</tr>
					
						<tr>
							<td nowrap="true" valign="top" width="190px">
								<h5>Client</h5>
							</td>
						
							<td style="background-color:#e4ebe6" valign="top" width="400px">
				
								<select class="form-control" name="client" id="client" title="Client">
									<%
										for (String opcija : pomocniBean.getClients()){
											out.print("<option value=\"" + opcija +  "\"> "+opcija + "</option>");
										}
									%>
								</select>
								<br>	
							</td>
						</tr>
					
						<tr>
						<td nowrap="true" valign="top" width="190px">
							<h5>Project</h5>
						</td>
						
						<td style="background-color:#e4ebe6" valign="top" width="400px">
							<select class="form-control" name="project" id="project" title="Project">
									
									<%
										for (String opcija : pomocniBean.getProject()){
											out.print("<option value=\"" + opcija +  "\"> "+opcija + "</option>");
										}
									%>				
								</select>
								<br>
							
						</td>
					</tr>
					
						<tr>
							<td nowrap="true" valign="top" width="190px">
								<h5>TaskType</h5>
							</td>
							<td style="background-color:#e4ebe6" valign="top" width="400px">
						
							<select class="form-control" name="taskType" id="taskType" title="TaskType" >
									<option selected="selected" value="Administrative">Administrative</option>
									<option value="Analyze">Analyze</option>
									<option value="Bug">Bug</option>
									<option value="Business Trip">Business Trip</option>
									<option value="Development">Development</option>
									<option value="Implementation">Implementation</option>
									<option value="Pre-Sales">Pre-Sales</option>
									<option value="Support">Support</option>
									<option value="Testing">Testing</option>
									<option value="Training">Training</option>
									<option value="Migration">Migration</option>
									<option value="Stabilization">Stabilization</option>
									<option value="Integration">Integration</option>
				
								</select>
								<br>
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px" >
							<h5>Broj zahteva</h5>
						</td>
						<td style="background-color:#e4ebe6" valign="top"  width="400px">
							<input class="form-control" name="brojZahteva" id="brojZahteva" type="text" maxlength="20"  title="Broj zahteva" >
							<br>
							Uneti konkretan broj zahteva ili uneti skraceni naziv Klijenta za koji se radi Incident, ili ostaviti blanko			
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px" >
							<h5>Work</h5>
						</td>
						
						<td style="background-color:#e4ebe6" valign="top" width="400px">		
							<input class="form-control" name="work" type="number"   step="any" id="work" title="Work"  size="11" required="required">
							<br>		
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px" >
							<h5>StartDate</h5>
						</td>
						<td style="background-color:#e4ebe6" valign="top" width="400px">
							<input class="form-control" type="date" id="datum" name="datum" <% out.print("value=\"" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +"\""); %>>
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px">
							<h5>Product BSW</h5>
						</td>
						
						<td style="background-color:#e4ebe6" valign="top" width="400px">
						<select class="form-control" name="productBSW" id="productBSW" title="Product BSW">
							<%
								for (String opcija : pomocniBean.getProductBSW()){
									out.print("<option value=\"" + opcija +  "\"> "+opcija + "</option>");
								}
							%>

						</select>
						<br>
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px">
							<h5>Status</h5>
						</td>
						
						<td style="background-color:#e4ebe6" valign="top" width="400px">
						<select class="form-control" name="status" id="status" title="Status">
							<option selected="selected" value="New">New</option>
							<option value="Approved">Approved</option>
							<option value="Completed">Completed</option>
							<option value="Not acceptable">Not acceptable</option>
				
						</select>
						<br>	
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px">
							<h5>Payment Status</h3>
						</td>
						
						<td style="background-color:#e4ebe6" valign="top" width="400px">
						<select class="form-control" name="payment" id="payment" title="Payment Status">
							<option selected="selected" value="Not For Payment">Not For Payment</option>
							<option value="For Payment">For Payment</option>
						</select>
						<br>
						</td>
					</tr>
					
					</tbody>
					</table>
				
				
				
				
					
					<div class="d-flex justify-content-end">
						<button type="submit" class="btn btn-primary mr-1">OK</button>
						<a class="btn btn-primary mr-1" href="?action=incidenti">Cancel</a>
					</div>

					<%=session.getAttribute("notification")!=null? session.getAttribute("notification").toString():""%>
				</form>
			</div>

		</div>
	</div>
	
	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>