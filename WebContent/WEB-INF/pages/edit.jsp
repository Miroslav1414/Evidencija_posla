<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="logovanKorisnik" type="asseco.beans.KorisniciBean" scope="session"/>
<jsp:useBean id="pomocniBean" type="asseco.beans.PomocniBean" scope="session"/>
<jsp:useBean id="inc" type="asseco.beans.IncidentiBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Izmjena incidenta</title>
	<link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
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
			<div class="col-xl-2 col-md-2 col-sm-12 col-xs-12"></div>
			<div class="col-xl-8 col-md-8 col-sm-12 col-xs-12">
				<form method="POST" action="?action=izmijeniIncident">
					<table class="table table-sm">
						<tbody>
										
						<tr>
							<td nowrap="true" valign="top" width="190px">
								<h5>TaskName</h5>
							</td>
							<td style="background-color:#e4ebe6" valign="top"  width="400px">
								<input class="form-control" name="taskName" id="taskName" type="text" maxlength="255" title="TaskName" required="required"
								value="<% out.println(inc.getInc().getTaskName());%>">
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
											out.print("<option value=\"" + opcija +  "\"");
											if(opcija.equals(inc.getInc().getClient()))
												out.print("selected=\"selected\"");
											out.print("> " + opcija + " </option>");
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
											out.print("<option value=\"" + opcija +  "\"");
											if(opcija.equals(inc.getInc().getProject()))
												out.print("selected=\"selected\"");
											out.print("> " + opcija + " </option>");
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
									<option <%if(inc.getInc().getTaskType().equals("Administrative"))
												out.print("selected=\"selected\"");%> value="Administrative">Administrative</option>
									<option <%if(inc.getInc().getTaskType().equals("Analyze"))
												out.print("selected=\"selected\"");%> value="Analyze">Analyze</option>
									<option <%if(inc.getInc().getTaskType().equals("Bug"))
												out.print("selected=\"selected\"");%> value="Bug">Bug</option>
									<option <%if(inc.getInc().getTaskType().equals("Business Trip"))
												out.print("selected=\"selected\"");%> value="Business Trip">Business Trip</option>
									<option <%if(inc.getInc().getTaskType().equals("Development"))
												out.print("selected=\"selected\"");%> value="Development">Development</option>
									<option <%if(inc.getInc().getTaskType().equals("Implementation"))
												out.print("selected=\"selected\"");%> value="Implementation">Implementation</option>
									<option <%if(inc.getInc().getTaskType().equals("Pre-Sales"))
												out.print("selected=\"selected\"");%> value="Pre-Sales">Pre-Sales</option>
									<option <%if(inc.getInc().getTaskType().equals("Support"))
												out.print("selected=\"selected\"");%> value="Support">Support</option>
									<option <%if(inc.getInc().getTaskType().equals("Testing"))
												out.print("selected=\"selected\"");%> value="Testing">Testing</option>
									<option <%if(inc.getInc().getTaskType().equals("Training"))
												out.print("selected=\"selected\"");%> value="Training">Training</option>
									<option <%if(inc.getInc().getTaskType().equals("Migration"))
												out.print("selected=\"selected\"");%> value="Migration">Migration</option>
									<option <%if(inc.getInc().getTaskType().equals("Stabilization"))
												out.print("selected=\"selected\"");%> value="Stabilization">Stabilization</option>
									<option <%if(inc.getInc().getTaskType().equals("Integration"))
												out.print("selected=\"selected\"");%> value="Integration">Integration</option>
				
								</select>
								<br>
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px" >
							<h5>Broj zahteva</h5>
						</td>
						<td style="background-color:#e4ebe6" valign="top"  width="400px">
							<input class="form-control" name="brojZahteva" id="brojZahteva" type="text" maxlength="20"  title="Broj zahteva" 
							value="<% 
							if (inc.getInc().getBrojZahteva() != null && !inc.getInc().getBrojZahteva().equalsIgnoreCase("null"))
								out.print(inc.getInc().getBrojZahteva());%>">
							<br>
							Uneti konkretan broj zahteva ili uneti skraceni naziv Klijenta za koji se radi Incident, ili ostaviti blanko			
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px" >
							<h5>Work</h5>
						</td>
						
						<td style="background-color:#e4ebe6" valign="top" width="400px">		
							<input class="form-control" name="work" type="number"  step="any" id="work" title="Work"  size="11" required="required"
							value="<% out.print(inc.getInc().getWork());%>">
							<br>		
						</td>
					</tr>
					
					<tr>
						<td nowrap="true" valign="top" width="190px" >
							<h5>StartDate</h5>
						</td>
						<td style="background-color:#e4ebe6" valign="top" width="400px">
							<input class="form-control" type="date" id="datum" name="datum" <% out.print("value=\"" + 
							new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(inc.getInc().getStartDate().substring(0, 10))) +"\""); %>>
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
									out.print("<option value=\"" + opcija +  "\"");
									if(opcija.equals(inc.getInc().getProductBSW()))
										out.print("selected=\"selected\"");
									out.print("> " + opcija + " </option>");
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
							<option <%if(inc.getInc().getStatus().equals("New"))
												out.print("selected=\"selected\"");%> value="New">New</option>
							<option <%if(inc.getInc().getStatus().equals("Approved"))
												out.print("selected=\"selected\"");%>value="Approved">Approved</option>
							<option <%if(inc.getInc().getStatus().equals("Completed"))
												out.print("selected=\"selected\"");%>value="Completed">Completed</option>
							<option <%if(inc.getInc().getStatus().equals("Not acceptable"))
												out.print("selected=\"selected\"");%>value="Not acceptable">Not acceptable</option>
				
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
						
							<%if (inc.getInc().getPaymentStatus()!= null ){ %>
							<option <%if(inc.getInc().getPaymentStatus().equals("Not For Payment"))
												out.print("selected=\"selected\"");%> value="Not For Payment">Not For Payment</option>
							<option <%if(inc.getInc().getPaymentStatus().equals("For Payment"))
												out.print("selected=\"selected\"");}%>value="For Payment">For Payment</option>
							<%if  (inc.getInc().getPaymentStatus()  == null) {%>
							<option selected="selected" value="Not For Payment">Not For Payment</option>
							<option value="For Payment">For Payment</option>
								<%} %>
						</select>
						<br>
						</td>
					</tr>
					
					</tbody>
					</table>
					<div>
						<p>
						
							<%
								if(inc.getInc().getModificationDate() != null && !inc.getInc().getModificationDate().equalsIgnoreCase("null")){
									out.println("Poslednja izmjena: " + new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(inc.getInc().getModificationDate().substring(0, 10))));						
								}
								else{ 
									out.println("Poslednja izmjena: " + new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(inc.getInc().getStartDate().substring(0, 10))));
								}
								
								if(inc.getInc().getModifiedBy() != null && !inc.getInc().getModifiedBy().equalsIgnoreCase("null")){									
									out.println(inc.getInc().getModifiedBy());
								}
								else {
									out.println(inc.getInc().getCreatedBy());
								}
							
							 %>
						</p>
					</div>
				
				
				
				
					
					<div class="d-flex justify-content-end">
						<button type="submit" class="btn btn-primary mr-1">Izmjeni</button>
						<a class="btn btn-primary mr-1" href="?action=incidenti">Odustani</a>
					</div>

					<%=session.getAttribute("notification")!=null? session.getAttribute("notification").toString():""%>
				</form>
			</div>

		</div>
	</div>
	
	
</body>
</html>