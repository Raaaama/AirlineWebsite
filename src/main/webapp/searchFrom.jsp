<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>
<%@ page pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Ramavia</title>
  <link rel="stylesheet" href="search.css">
    <style>
      @import url('https://fonts.googleapis.com/css2?family=Manrope:wght@600;700;800&display=swap');
    </style>
    <link rel = "icon" href = "micrologo.png" type = "image/x-icon">  
</head>
<body>
  <div id="main">
    <div class="top">
      <a href="index.jsp"><img src="logo.png" id="logo"></a>
      <div class="auth">
        <form action="isAuthorised" method = "POST">
          <button class="authBtn">Admin</button>
        </form>              
      </div>
    </div>
    <div class="flightInfo">
        <a class="fromTo">
            ${fromCity} -> ${toCity}
        </a>
    </div>
    <div class="dateInfo">
	    <form action="changeDate" id="previousDate">
	        <input type="hidden" name="id" value="${whenTo1}">
	        <input type="submit" class="changeDateBtn" value="<-">
		</form>
        			
        <div class="anotherDate">
            ${whenTo1}
        </div>
        <div class="currentDate">
            ${whenTo2}
        </div>
        <div class="anotherDate">
            ${whenTo3}
        </div>
        
        <form action="changeDate" id="nextDate">
        	<input type="hidden" name="id" value="${whenTo3}">
        	<input type="submit" class="changeDateBtn" value="->">
        </form>	
    </div>
    <div class="chooseTicket">
      <c:set var = "ddId" value = "${1}"/>
      <c:set var = "ticketNum" value = "${0}"/>
      <c:forEach var="ticket" items="${avTickets}">
        <div class="ticket">
          <div class="flights">
            <c:forEach var="flight" items="${ticket.flData}">
              <div class="flight" style="display:inline-block">                            
                <div class="smallInfo">
                  <a class="time">
                    ${flight.fromCity} (${flight.fromAp}) -> ${flight.toCity} (${flight.toAp})
                    </a>
                    
                    <br>
                    <a class="time">
                    ${flight.depTime} - ${flight.arrTime}
                    </a>                    
                </div>             
              </div>
              <br>
            </c:forEach>
		  </div>
		  <div class="price">
	        	<div class="dropdown">
	        		<div id="types${ ddId }" class="dropdown-content" >
			            <div class="ticketType">
			            	<form method="post" action="checkout">
			            		<input type="hidden" name="chosen" value="0 ${ ticketNum }">
			            		<input id="light" type="submit" class="ticketTypeBtn" value="LIGHT"/>
			            	</form>
			            </div>
			            <div class="ticketType">
			            	<form method="post" action="checkout">
			            		<input type="hidden" name="chosen" value="1 ${ ticketNum }">
			            		<input id="ticketType" type="submit" class="ticketTypeBtn" value="AVERAGE"/>
			            	</form>
			            </div>
			            <div class="ticketType">
			            	<form method="post" action="checkout">
			            		<input type="hidden" name="chosen" value="2 ${ ticketNum }">
			            		<input id="ticketType" type="submit" class="ticketTypeBtn" value="FLEX"/>
			            	</form>
			            </div>
		            </div>
		            <div id="typesInfo">
		            	<table class="infoTable">
		            		<tr>
		            			<th></th>
								<th><img src="carryOn.png" class="typeLogo"><a class="microText">5 kg</a></th>
								<th><img src="suitcase.png" class="typeLogo"><a class="microText">10 kg</a></th>
								<th><img src="return.png" class="typeLogo"><a class="microText">return</a></th>
							</tr>
							<tr>
		            			<th>LGT<a class="microText">+0%$</a></th>
								<th><img src="checked.png" class="typeLogo"></th>
								<th><img src="cross.png" class="typeLogo"></th>
								<th><img src="cross.png" class="typeLogo"></th>
							</tr>
							<tr>
		            			<th>AVG<a class="microText">+10%$</a></th>
								<th><img src="checked.png" class="typeLogo"></th>
								<th><img src="checked.png" class="typeLogo"></th>
								<th><img src="cross.png" class="typeLogo"></th>
							</tr>
							<tr>
		            			<th>FLX<a class="microText">+20%$</a></th>
								<th><img src="checked.png" class="typeLogo"></th>
								<th><img src="checked.png" class="typeLogo"></th>
								<th><img src="checked.png" class="typeLogo"></th>
							</tr>
		            	</table>
		            </div>
		            <input readonly class="chooseTicketBtn" id="chooseType${ ddId }" type="text" value="Choose">
	        	</div>
	        	<a class="ticketPrice">
		  			${ticket.price} $
		  		</a>
	        </div>
        </div>
       	<c:set var="ddId" value="${ddId + 1}"/>
       	<c:set var="ticketNum" value="${ticketNum + 1}"/>
      </c:forEach>
    </div>
  </div>
  <div class="end">
  </div>
  <script>
      var ddc = document.getElementsByClassName("dropdown-content");
      var dd = [];
      for (var i = 0; i < ddc.length; i++) {
    	  dd.push(ddc[i].id);
      }
      
      var chooseTicket = document.getElementsByClassName("chooseTicketBtn");
      var toggleDd = [];
      for (var i = 0; i < chooseTicket.length; i++) {
    	  toggleDd.push(chooseTicket[i].id); 
      }
      
      var closeAll = ['main','logo', ''];
      document.body.addEventListener('click',function() {   
        var evt = window.event || evt;
        var obj = evt.target.id;   
        //alert(obj);  
        if (toggleDd.includes(obj)) {
          var ind = toggleDd.indexOf(obj);
          document.getElementById(dd[ind]).classList.toggle("show");
          for (var i = 0; i < toggleDd.length; i++) {
            if (i != ind) {        
              if (document.getElementById(dd[i]).classList.toggle("show") == true) {
                document.getElementById(dd[i]).classList.toggle("show");
              }
            }
          }
        }
        if (closeAll.includes(obj)) {            
          for (var i = 0; i < toggleDd.length; i++) {
            if (document.getElementById(dd[i]).classList.toggle("show") == true) {
              document.getElementById(dd[i]).classList.toggle("show");
            }
          }
        }  
      },true);
     
  </script>
</body>
</html>