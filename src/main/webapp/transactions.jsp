<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>Transactions</title>
<link rel="stylesheet" href="admin.css">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Manrope:wght@600;700;800&display=swap');
  .tables {
  	display: block;
  	text-align: center;
  }
  
  table {
  	margin-left: auto;
  	margin-right: auto;
  	font-size: 30px;
  	margin-top: 100px;
  }
  
  table, td {
  	width: auto;
  	padding: 10px;
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
				<input class="currentBtn" type="submit" value="Transactions">	
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
		<tr><th>Number</th><th>Transaction</th></tr>
		<c:set var = "num" value = "${1}"/>
		<c:forEach var="trn" items="${transactionsData}">
		 	<tr><td><c:out value="${num}" /></td>
		    <td style="width:800px; padding: 20px;">${trn.trn}</td>
		    <td style="width:100px" class="invisibleTd">		    
		    <form method="POST" action='<c:url value="/transactionRollback" />' style="display:inline;">
		        <input type="hidden" name="id" value="${trn.id}">
		        <input class="eOrDbtn" type="submit" value="Cancel">
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