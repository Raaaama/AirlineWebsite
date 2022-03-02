<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>>
<%@ page pageEncoding="utf-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="mysql.business.AvailableFlightsData" %>
<%@ page import="mysql.business.MyDB" %>

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
		<div class="tabs">
			<div class="tab">						
                <input class="currentBtn" type="submit" value="Customers">				
			</div>
			<div class="tab">							
				<form action='myTransactions'>			
					<input class="tabBtn" type="submit" value="Transactions">
				</form>
			</div>
			<div class="tab">
				<form action='airports'>			
					<input class="tabBtn" type="submit" value="Airports">
				</form>
			</div>
			<div class="tab">		
                <form action='isAuthorised'>
				    <input class="tabBtn" type="submit" value="Available Flights">
                </form>					
			</div>
		</div>
    </div>
  <div>
  <h3 class="text-center">You are authorised as: <%= (String) session.getAttribute("username") %> </h3>
<div class="tables">		
	<div class="tbl">
  		<table>	
		<tr><th>Number</th><th>Passport Number</th><th>Name</th><th>Surname</th><th>From</th><th>To</th><th>When</th><th>Ticket Price</th><th>Class Type</th><th>Type</th></tr>
		<c:set var = "num" value = "${1}"/>
		<c:forEach var="cm" items="${customersData}">
		 	<tr>
		 		<td><c:out value="${num}" /></td>
		    	<td>${cm.passportNum}</td>
		    	<td>${cm.name}</td>
		    	<td>${cm.surname}</td>
		    	<td>${cm.from}</td>
		    	<td>${cm.to}</td>
		    	<td>${cm.dep}</td>
		    	<td>${cm.price}</td>
		    	<td>${cm.classType}</td>
		    	<td>${cm.type}</td>
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
  	
  	function areYouSure1(x) {
  		if(confirm("This may delete the data in the related table."))
  			return true;
  		else
  			return false;
  	}
  	
  	function areYouSure2(x) {
  		if(confirm("This may delete the data in the related table. \nThe data will not be recoverable."))
  			return true;
  		else
  			return false;
  	}
  	
  	document.body.addEventListener('click',function() {   
  		var evt = window.event || evt;
  		var obj = evt.target.id;   
  		if (obj == "btnFrom") {
  			document.getElementById('fromDd').classList.toggle("show");
  		}  		
  	},true);
  	
  	document.body.addEventListener('click',function() {   
  		var evt = window.event || evt;
  		var obj = evt.target.id;   
  		if (obj == "btnTo") {
  			document.getElementById('toDd').classList.toggle("show");
  		}  		
  	},true);
  </script>
</body>
</html>