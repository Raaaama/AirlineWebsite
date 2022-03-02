<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>
<%@ page pageEncoding="utf-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="mysql.business.AvailableFlightsData" %>
<%@ page import="mysql.business.MyDB" %>

<!DOCTYPE html>
<html>
<head>
<style>
	td {
		width: 200px;
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","inline; filename=availableFlights.xls");
		ArrayList<AvailableFlightsData> availableFlights = (ArrayList<AvailableFlightsData>) session.getAttribute("availableFlights"); %>
		<table>        
		<tr><th>Number</th><th>From</th><th>To</th><th>Number of tickets</th><th>Period</th><th>Departure time</th><th>Arrive time</th></tr>
		<c:set var = "num" value = "${1}"/>
		<c:forEach var="availableFlight" items="${availableFlights}">
		 	<tr><td><c:out value="${num}" /></td>		 	
		    <td>${availableFlight.fromCity}</td>
		    <td>${availableFlight.toCity}</td>
		    <td>${availableFlight.ticketsNum}</td>
		    <td>${availableFlight.period}</td>
		    <td>${availableFlight.depTime}</td>
		    <td>${availableFlight.arrTime}</td>
		    <td style="width:100px" class="invisibleTd">		  
		 </td>
		 </tr>
		 <c:set var="num" value="${num + 1}"/>
		</c:forEach>
		</table>
</body>
</html>