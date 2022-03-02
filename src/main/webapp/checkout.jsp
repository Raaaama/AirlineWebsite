<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Ramavia</title>
  <link rel="stylesheet" href="checkout.css">
  <style>
  	@import url('https://fonts.googleapis.com/css2?family=Manrope:wght@600;700;800&display=swap');
  </style>   
  <link rel = "icon" href = "micrologo.png" type = "image/x-icon">   
</head>
<body onload="pType(); startingType();">
  <div id="main">
    <div class="top">
      <a href="index.jsp"><img src="logo.png" id="logo"></a>
      <div class="auth">
        <form action="isAuthorised" method = "POST">
          <button class="authBtn">Administration</button>
        </form>              
      </div>
    </div>

    <div class="flightInfo">
    	<div>
        <div class="fromTo">
            ${fromCity} -> ${toCity}
        </div>
        <br>
        <div class="flightDate">
            ${when}
        </div>
        </div>
        <div class="price">
            ${price} $
        </div>
    </div>

    <form action="buyTicket" method="post">
    
    <c:set var = "passengerNumber" value = "${1}"/>
    <c:set var = "num" value = "${1}"/>
    <%
    int vzKol = (int) session.getAttribute("vzKol"); 
    int dKol = (int) session.getAttribute("dKol");
    int mlKol = (int) session.getAttribute("mlKol");
    for (int i = 0; i < vzKol; i++) { %>
    <div class="passengerData">
        <a class="passengerType">Adult ${passengerNumber}</a>
        <br><br>
        <input class="passengerInput" placeholder="Name" name="name${ num }">
        <input class="passengerInput" placeholder="Surname" name="surname${ num }">
        <div class="dateOfBirth">
            <a class="dateOfBirthTxt">Date of Birth</a>
            <input id="dateOfBirth" type="date" name="dateOfBirth${ num }">
        </div>
        <br><br>
        <input class="passengerInput" placeholder="Сitizenship" name="citizenship${ num }">
        <input class="passengerInput" placeholder="Passport Number" name="passport${ num }">
    </div>
    <c:set var="passengerNumber" value="${passengerNumber + 1}"/>
    <c:set var="num" value="${num + 1}"/>
    <% } %>
    
    <c:set var = "passengerNumber" value = "${1}"/>
    <% for (int i = 0; i < dKol; i++) { %>
    <div class="passengerData">
	    <a class="passengerType">Child ${passengerNumber}</a>
	    <br><br>
	    <input class="passengerInput" placeholder="Name">
	    <input class="passengerInput" placeholder="Surname">
	    <div class="dateOfBirth">
	        <a class="dateOfBirthTxt">Date of Birth</a>
	        <input id="dateOfBirth" type="date">
	    </div>
	    <br><br>
	    <input class="passengerInput" placeholder="Сitizenship">
	    <input class="passengerInput" placeholder="Passport Number">
	</div>
	<c:set var="passengerNumber" value="${passengerNumber + 1}"/>
	<c:set var="num" value="${num + 1}"/>
	<% } %>
	
	<c:set var = "passengerNumber" value = "${1}"/>
    <% for (int i = 0; i < mlKol; i++) { %>
    <div class="passengerData">
	    <a class="passengerType">Infant ${passengerNumber}</a>
	    <br><br>
	    <input class="passengerInput" placeholder="Name">
	    <input class="passengerInput" placeholder="Surname">
	    <div class="dateOfBirth">
	        <a class="dateOfBirthTxt">Date of Birth</a>
	        <input id="dateOfBirth" type="date">
	    </div>
	    <br><br>
	    <input class="passengerInput" placeholder="Сitizenship">
	    <input class="passengerInput" placeholder="Passport Number">
	</div>
	<c:set var="passengerNumber" value="${passengerNumber + 1}"/>
	<c:set var="num" value="${num + 1}"/>
	<% } %>
    

    <div class="paymentData">
        <a class="passengerType">Payment</a>
        <br><br>
        <input class="passengerInput" placeholder="Phone Number">
        <input class="passengerInput" placeholder="E-Mail">
        <br><br>
        <input class="passengerInput" style="width: 640px" placeholder="Card Number">
        <br><br>
        <input class="passengerInput" placeholder="Expiration Date">
        <input class="passengerInput" placeholder="CVC">
    </div>

    <button type="submit" class="buyBtn">Buy</button>
    </form>

  </div>
  <script>
      //document.getElementById('main').style.height = window.screen.height - 90 + "px";
    </script>
</body>
</html>