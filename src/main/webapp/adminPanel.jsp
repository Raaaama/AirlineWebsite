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
				<form action='airports'>			
					<input class="tabBtn" type="submit" value="Airports">
				</form>
			</div>
			<div class="tab">							
				<input class="currentBtn" type="submit" value="Available Flights">
			</div>
		</div>
    </div>
  <div>
  <h3 class="text-center">You are authorised as: <%= (String) session.getAttribute("username") %> </h3>
<div class="tables">
  <div class="tbl">
   <table>
		<tr>
	<form method="POST" action='addAvailableFlight' style="display:inline;">				
		</tr>
		<tr>
			<th>From</th><th>
			<c:set var="airportsData" value="${ MyDB.selectAirports() }"/>
			
			<select class="addInput" name="fromCity">
        	<c:forEach var="ap" items="${airportsData}">
                <option>${ap.apCode}</option>
        	</c:forEach>
      		</select>
			
			</th>
		</tr>
		<tr>
			<th>To</th><th>
			<select class="addInput" name="toCity">
        	<c:forEach var="ap" items="${airportsData}">
                <option>${ap.apCode}</option>
        	</c:forEach>
      		</select>
			
			</th>
		</tr>
		<tr>
			<th>Number of tickets</th><th><input class="addInput" name="ticketsNum"></th>
		</tr>
		<tr>
			<th>Period</th><th><input class="addInput" name="period"></th>
		</tr>
		<tr>
			<th>Departure time</th><th><input class="addInput" name="depTime"></th>
		</tr>
		<tr>
			<th>Arrive time</th><th><input class="addInput" name="arrTime"></th>
		</tr>
		<tr>
			<th class="invisibleTd"></th><th class="invisibleTd"><input class="eOrDbtn" type="submit" value="Add"></th>
		</tr>
	</form>
	</table>
  </div>
	<br>	
  <div class="tbl" style="margin-left: auto; margin-right: 0;">
  	<c:set var = "kol" value = "${0}"/>
		<c:forEach var="item" items="${availableFlights}">
			<c:set var="kol" value="${kol + 1}"/>
		</c:forEach>
		<c:if test="${kol gt 0}">
  	<div class="justForPadding"><a class="txt">Available flights</a></div>
  	
  	<div class="filters">
  		<form class="fromFilter" action="filterAF" method="get">  
	  		<div class="dropdown">
	  			<input class="filterInput" id="btnFrom" readonly value="From">           
	            <div id="fromDd" class="dropdown-content">	            	     
	            	<div class="filterOptions">    
	            		<br>	            	
	            			            		
	            		<c:forEach var="airport" items="${AirportsData}">	            		 	 
	            			<c:set var="checked" value="false" />
							<c:forEach var="ff" items="${fromCheckedFilters}">				
								<c:if test="${ff eq airport.apName}">									
									<c:set var="checked" value="true" />
								</c:if>				
							</c:forEach>	
							
							<c:set var="contains" value="false" />							
							<c:forEach var="fff" items="${fromFilters}">				
								<c:if test="${fff eq airport.apName}">									
									<c:set var="contains" value="true" />
								</c:if>											
							</c:forEach>												
							
		            		<c:choose>		            					            	
							  <c:when test="${checked eq true}">
							    <label class="lbl"><input type="checkbox" checked="checked" name="fromCheckBox" value='${airport.apName}'/>${airport.apName}</label>
				            	<br>
							  </c:when>
							  <c:when test="${contains eq true && checked eq false}">
							    <label class="lbl"><input type="checkbox" name="fromCheckBox" value='${airport.apName}'/>${airport.apName}</label>
							    <br>							  
							  </c:when>
							</c:choose>           				            							            	
		            	</c:forEach>
		            	

	            		
	            		<!-- 
	            		<c:forEach var="airport" items="${AirportsData}"> 			            		
			            	<label class="lbl"><input type="checkbox" name="fromCheckBox" value='${airport.apCode}'/>${airport.apCode}</label>
			            	<br>		            		          	
		            	</c:forEach>
		            	 -->
	            	</div>	            	
	            </div>
	        </div>
	        <div class="dropdown">
	  			<input class="filterInput" id="btnTo" readonly value="To">           
	            <div id="toDd" class="dropdown-content">	            	     
	            	<div class="filterOptions">    
	            		<br>
	            		
	            		
	            		<c:forEach var="airport" items="${AirportsData}">	            		 	 
	            			<c:set var="checked" value="false" />	            			
							<c:forEach var="ff" items="${toCheckedFilters}">				
								<c:if test="${ff eq airport.apName}">									
									<c:set var="checked" value="true" />
								</c:if>
							</c:forEach>
							
							<c:set var="contains" value="false" />							
							<c:forEach var="fff" items="${toFilters}">				
								<c:if test="${fff eq airport.apName}">									
									<c:set var="contains" value="true" />
								</c:if>											
							</c:forEach>							
							
		            		<c:choose>		            					            	
							  <c:when test="${checked eq true}">
							    <label class="lbl"><input type="checkbox" checked="checked" name="toCheckBox" value='${airport.apName}'/>${airport.apName}</label>
				            	<br>
							  </c:when>
							  <c:when test="${contains eq true && checked eq false}">
							    <label class="lbl"><input type="checkbox" name="toCheckBox" value='${airport.apName}'/>${airport.apName}</label>
							    <br>							  
							  </c:when>
							</c:choose>           				            							            	
		            	</c:forEach>
	            	</div>	            	
	            </div>
	        </div>
	    <input type="submit" value="Apply" class="appplyBtn">	    
        </form>                   
        <form action="isAuthorised">
        	<input type="submit" value="Reset" class="appplyBtn">
        </form>
  	</div>
  	<a href="downloadAF.jsp" class="downloadBtn">Download Report</a>
  	<br><br>
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
		<br><br>		
		</c:if>
		<c:set var = "kol" value = "${0}"/>
		<c:forEach var="item" items="${deletedAvailableFlights}">
			<c:set var="kol" value="${kol + 1}"/>
		</c:forEach>
		
		<c:if test="${kol gt 0}">
    	   <div class="justForPadding"><a class="txt">Deleted flights</a></div>
	  	<div>
	    <table style="margin-left: auto; margin-right: 0;">
			<tr><th>Number</th><th>From</th><th>To</th><th>Number of tickets</th><th>Period</th><th>Departure time</th><th>Arrive time</th></tr>
			<c:set var = "num" value = "${1}"/>
			<c:forEach var="deletedAvailableFlight" items="${deletedAvailableFlights}">
			 	<tr><td><c:out value="${num}" /></td>
			 	<c:set var="num" value="${num + 1}"/>
			    <td>${deletedAvailableFlight.fromCity}</td>
			    <td>${deletedAvailableFlight.toCity}</td>
			    <td>${deletedAvailableFlight.ticketsNum}</td>
			    <td>${deletedAvailableFlight.period}</td>
			    <td>${deletedAvailableFlight.depTime}</td>
			    <td>${deletedAvailableFlight.arrTime}</td>
			    <td style="width:100px" class="invisibleTd">
			    <!-- <a href='<c:url value="/restoreAF?id=${deletedAvailableFlight.id}" />'>Restore</a> -->
			    <form method="POST" action='<c:url value="/restoreAF" />' style="display:inline;">
			    	<input type="hidden" name="id" value="${deletedAvailableFlight.id}">
			        <input class="eOrDbtn" type="submit" value="Restore">
			    </form>
			    
			    <form method="POST" action='<c:url value="/deleteCompletelyAF" />' style="display:inline;" onsubmit="return areYouSure2(this)">
					<input type="hidden" name="id" value="${deletedAvailableFlight.id}">
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