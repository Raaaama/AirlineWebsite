<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>>
<%@ page pageEncoding="utf-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="mysql.business.AvailableFlightsData" %>
<%@ page import="mysql.business.MyDB" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Airline Tickets</title>
  <link rel="stylesheet" href="index.css">
    <style>
      @import url('https://fonts.googleapis.com/css2?family=Manrope:wght@600;700;800&display=swap');
    </style>    
    <link rel = "icon" href = "micrologo.png" type = "image/x-icon">  
</head>
<body onload="pType(); startingType()">
  <div id="main">
    <div class="top">
      <img src="logo.png" id="logo">
      <div class="auth">
        <form action="isAuthorised" method = "POST">
          <button class="authBtn">Administration</button>
        </form>              
      </div>
    </div>

    <text>Buy a ticket</text>    

    <div id="search">
      <form action="search" method="GET" autocomplete = "off">
          <div class="dropdown">            
            <input onclick="selectFrom()" type="text" placeholder="From" id="idFrom" name="fromCity" onkeyup="filterFunction1()">
            <div id="fromDd" class="dropdown-content" >
              <a href="#" onclick="enterFrom(this)" id="a"><b id="bld">NQZ </b>Nur-Sultan, Kazakhstan</a>
              <a href="#" onclick="enterFrom(this)" id="a"><b id="bld">DME </b>Moscow, Russia</a>
              <a href="#" onclick="enterFrom(this)" id="a"><b id="bld">SVO </b>Moscow, Russia</a>
            </div>
          </div>      

          <div class="dropdown">            
            <input onclick="selectTo()" type="text" placeholder="To" id="idTo" name="toCity" onkeyup="filterFunction2()">
            <div id="toDd" class="dropdown-content">
              <a href="#" onclick="enterTo(this)" id="a"><b id="bld">NQZ </b>Nur-Sultan, Kazakhstan</a>
              <a href="#" onclick="enterTo(this)" id="a"><b id="bld">DME </b>Moscow, Russia</a>
              <a href="#" onclick="enterTo(this)" id="a"><b id="bld">SVO </b>Moscow, Russia</a>
            </div>
          </div>

          <div class="when">
            <a class="whenTxt">Departure Date</a>
            <input type="date" id="whenTo" name="whenTo" style="display: flex;" min="2021-01-01" />
          </div>

          <div class="when">
            <a class="whenTxt">Arrival Date</a>
            <input type="date" id="whenBack" name="whenBack" style="display: flex;" min="2021-01-01">
          </div>          
          
          <div class="dropdown">
            <input type="text" readonly placeholder="Passengers" id="passengers"
              oninput="this.value = ''"
              onclick="selectPassengers()"
              name="passengersCol"
            />
            <div id="psTypes" class="dropdown-content">              
              <div class = "passenger">
                <div class = "passengerType">Adults</div>
                <div class = "passengerKol">
                  <div class = "passengerKolEl"><button onclick="vzKolMinus(); pType();" type="button" class="btnMinus" id="btn">-</button></div>
                  <!-- <div class = "passengerKolEl" id="vzKol" style="width: 15px;">1</div> -->
                  <input type="text" readonly value="1" class="passengerKolEl" id="vzKol" name="vzKol">
                  <div class = "passengerKolEl"><button onclick="vzKolPlus(); pType();" type="button" class="btnPlus" id="btn">+</button></div>
                </div>
              </div>
              <div class = "passenger">
                <div class = "passengerType">Children</div>
                <div class = "passengerKol">
                  <div class = "passengerKolEl"><button onclick="dKolMinus(); pType();" type="button" class="btnMinus" id="btn">-</button></div>
                  <!-- <div class = "passengerKolEl" id="dKol" style="width: 15px;">0</div>  -->
                  <input type="text" readonly value="0" class="passengerKolEl" id="dKol" name="dKol">
                  <div class = "passengerKolEl"><button onclick="dKolPlus(); pType();" type="button" class="btnPlus" id="btn">+</button></div>
                </div>
              </div>
              <div class = "passenger">
                <div class = "passengerType">Infants</div>
                <div class = "passengerKol">
                  <div class = "passengerKolEl"><button onclick="mlKolMinus(); pType();" type="button" class="btnMinus" id="btn">-</button></div>
                  <!-- <div class = "passengerKolEl" id="mlKol" style="width: 15px;">0</div>  -->
                  <input type="text" readonly value="0" class="passengerKolEl" id="mlKol" name="mlKol">
                  <div class = "passengerKolEl"><button onclick="mlKolPlus(); pType();" type="button" class="btnPlus" id="btn">+</button></div>
                </div>
              </div>              
            </div>            
          </div>
          <div class="dropdown">            
            <input readonly type="text" id="ticketType">
            <div id="ticketTypes" class="dropdown-content" >              
              <label onclick="tType(this)"><b><input name="ticketType" value="econom" type="radio" checked="checked"> Economy</b></label>
              <label onclick="tType(this)"><b><input name="ticketType" value="comfort" type="radio"> Comfort</b></label>
              <label onclick="tType(this)"><b><input name="ticketType" value="business" type="radio"> Business</b></label>
              <label onclick="tType(this)"><b><input name="ticketType" value="firstClass" type="radio"> First Class</b></label>
            </div>
          </div>     
          <br><br>
          <input id="srch" type="submit" class="searchBtn" value="Search"/>
      </form>
    </div>
  </div>
  <script src = 'index.js'> </script>
</body>
</html>