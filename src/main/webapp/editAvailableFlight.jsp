<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>Admin panel</title>
<link rel="stylesheet" href="admin.css">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Manrope:wght@600;700;800&display=swap');
</style>
</head>
<body>
<div id="main">
    <div id="top">
      <a href="index.jsp"><img src="logo.png" id="logo"></a>
    </div>
  <div>
  <h3 class="text-center">You are authorised as: <%= (String) session.getAttribute("username") %> </h3>
	<div class="tables">
  <div class="tbl">
   <table>
		<tr>
			<form method="post" action='editAvailableFlight' autocomplete = "off" style="display:inline; ">	
		</tr>
		<tr>
			<input type="hidden" name="id" value="${availableFlight.id}">
			<th>From</th><th><input class="addInput" name="fromCity" value="${availableFlight.fromCity}"></th>
		</tr>
		<tr>
			<th>To</th><th><input class="addInput" name="toCity" value="${availableFlight.toCity}"></th>
		</tr>
		<tr>
			<th>Number of tickets</th><th><input class="addInput" name="ticketsNum" value="${availableFlight.ticketsNum}"></th>
		</tr>
		<tr>
			<th>Period</th><th><input class="addInput" name="period" value="${availableFlight.period}"></th>
		</tr>
		<tr>
			<th>Departure time</th><th><input class="addInput" name="depTime" value="${availableFlight.depTime}"></th>
		</tr>
		<tr>
			<th>Arrive time</th><th><input class="addInput" name="arrTime" value="${availableFlight.arrTime}"></th>
		</tr>
		<tr>
			<th></th><th><input class="addInput" type="submit" value="Save"></th>
		</tr>
		    </form>
	</table>
  </div>
	<br>	
  <div class="tbl" style="margin-left:20px">
    <table>
		<tr><th>Number</th><th>From</th><th>To</th><th>Number of tickets</th><th>Period</th><th>Departure time</th><th>Arrive time</th></tr>
		<c:set var = "num" value = "${1}"/>
		<c:set var = "con" value = "con"/>
		<c:forEach var="availableFlight" items="${availableFlights}">
		 	<tr><td><c:out value="${num}" /></td>		 	
		    <td>${availableFlight.fromCity}</td>
		    <td>${availableFlight.toCity}</td>
		    <td>${availableFlight.ticketsNum}</td>
		    <td>${availableFlight.period}</td>
		    <td>${availableFlight.depTime}</td>
		    <td>${availableFlight.arrTime}</td>
		    <td style="width:100px" class="invisibleTd">
		    <!-- <a href='<c:url value="/editAvailableFlight?id=${availableFlight.id}" />'>Edit</a> -->
		    <form method="GET" action='<c:url value="/editAvailableFlight" />' style="display:inline;">
			    <input type="hidden" name="id" value="${availableFlight.id}">
				<input class="eOrDbtn" type="submit" value="Edit">
			</form>
			
		    <form method="POST" action='<c:url value="/delete" />' style="display:inline;" onsubmit="return areYouSure1(this)">				
				<input type="hidden" name="id" value="${availableFlight.id}">
				<input class="eOrDbtn" type="submit" value="Delete" class="confBtn">						
			</form>			
		 </td>
		 </tr>
		 <c:set var="num" value="${num + 1}"/>
		</c:forEach>
		</table>
  </div>  
	</div>
  </div>
</div>
  <script>   	
  	document.getElementById('main').style.height = window.screen.height - 90 + "px";
  </script>
</body>
</html>