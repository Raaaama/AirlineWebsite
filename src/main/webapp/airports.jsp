<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>Airports</title>
<link rel="stylesheet" href="admin.css">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Manrope:wght@600;700;800&display=swap');
  
  .apName {
	width: 400px;
  }     
</style>
</head>
<body>
<div id="main">
    <div id="top">
    	<a href="index.jsp"><img src="logo.png" id="logo"></a>
		<div class="tabs">
			<div class="tab">							
				<form action='customers'>			
					<input class="tabBtn" type="submit" value="Customers">
				</form>
			</div>
			<div class="tab">							
				<form action='myTransactions'>			
					<input class="tabBtn" type="submit" value="Transactions">
				</form>
			</div>
			<div class="tab">						
                <input class="currentBtn" type="submit" value="Airports">				
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
		<tr>
			<form method="POST" action='addAirport' style="display:inline;">				
		</tr>
		<tr>
			<th>Country</th><th><input class="addInput" name="fromCity"></th>
		</tr>
		<tr>
			<th>City</th><th><input class="addInput" name="toCity"></th>
		</tr>
		<tr>
			<th>Airport Name</th><th><input class="addInput" name="ticketsNum"></th>
		</tr>
		<tr>
			<th>Aiport Code</th><th><input class="addInput" name="period"></th>
		</tr>		
		<tr>
			<th class="invisibleTd"></th><th class="invisibleTd"><input class="eOrDbtn" type="submit" value="Add"></th>
		</tr>
		    </form>
	</table>
  </div>
	<br>	
  <div class="tbl" style="margin-left: auto; margin-right: 0;"">
  	<div class="justForPadding"><a class="txt">Airports</a></div>  	
    <table>
		<tr><th>Number</th><th>Country</th><th>City</th><th class="apName">Airport Name</th><th>Aiport Code</th></tr>
		<c:set var = "num" value = "${1}"/>
		<c:forEach var="airport" items="${AirportsData}">
		 	<tr><td><c:out value="${num}" /></td>
		 	<c:set var="num" value="${num + 1}"/>
		    <td>${airport.country}</td>
		    <td>${airport.city}</td>
		    <td>${airport.apName}</td>
		    <td>${airport.apCode}</td>
		    <td style="width:100px" class="invisibleTd">		    
		    <form method="GET" action='<c:url value="/editAirport" />' style="display:inline;">
			    <input type="hidden" name="id" value="${airport.id}">
				<input class="eOrDbtn" type="submit" value="Edit">
			</form>
		    <form method="POST" action='<c:url value="/deleteAirport" />' style="display:inline;">
		        <input type="hidden" name="id" value="${airport.id}">
		        <input class="eOrDbtn" type="submit" value="Delete">
		    </form>
		 </td>
		 </tr>
		</c:forEach>
		</table>
		<br><br>
		
		<c:set var = "kol" value = "${0}"/>
		<c:forEach var="item" items="${deletedAirports}">
			<c:set var="kol" value="${kol + 1}"/>
		</c:forEach>
		
		<c:if test="${kol gt 0}">
    	   <div class="justForPadding"><a class="txt">Deleted flights</a></div>
	  	    <div style="margin-left: auto; margin-right: 0;">
	        <table>
                <tr><th>Number</th><th>Country</th><th>City</th><th class="apName">Airport Name</th><th>Aiport Code</th></tr>
                <c:set var = "num" value = "${1}"/>
                <c:forEach var="deletedAirport" items="${deletedAirports}">
                    <tr><td><c:out value="${num}" /></td>
                    <c:set var="num" value="${num + 1}"/>
                    <td>${deletedAirport.country}</td>
                    <td>${deletedAirport.city}</td>
                    <td>${deletedAirport.apName}</td>
                    <td>${deletedAirport.apCode}</td>
                    <td style="width:100px">                    
                    <form method="POST" action='<c:url value="restoreAirport" />' style="display:inline;">
                        <input type="hidden" name="id" value="${deletedAirport.id}">
                        <input class="eOrDbtn" type="submit" value="Restore">
                    </form>
                    <form method="POST" action='<c:url value="/deleteCompletelyAP" />' style="display:inline;">
                        <input type="hidden" name="id" value="${deletedAirport.id}">
                        <input class="eOrDbtn" type="submit" value="Delete">
                    </form>
                </td>
                </tr>
                </c:forEach>
			</table>
			</div>	 
		</c:if>
					
		 	
  </div>      
</div>	
  </div>
</div>
  <script>   	
  	document.getElementById('main').style.height = window.screen.height - 90 + "px";
  </script>
</body>
</html>