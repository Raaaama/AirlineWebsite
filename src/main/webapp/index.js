document.getElementById('main').style.height = window.screen.height - 90 + "px";

var today = new Date();
var dd = today.getDate() + 2;
var mm = today.getMonth() + 1;
var yyyy = today.getFullYear();
if (dd < 10) {
  dd = '0' + dd;
}
if (mm < 10) {
  mm = '0' + mm;
} 
today = yyyy + '-' + mm + '-' + dd;
//document.getElementById("whenBack").setAttribute("min", today);
document.getElementById("whenTo").setAttribute("min", today);

var toggleDd = ["idFrom", "idTo", "passengers","ticketType"]
var dd = ["fromDd", "toDd", "psTypes","ticketTypes"]
var closeAll = ['main','search','logo', '', "srch", "whenBack", "whenTo"];
document.body.addEventListener('click',function() {   
  filterFunction1();
  filterFunction2();  
  var evt = window.event || evt;
  var obj = evt.target.id;   
  //alert(obj)  
  if (toggleDd.includes(obj)) {
    var ind = toggleDd.indexOf(obj);
    if (document.getElementById(dd[ind]).classList.toggle("show") == false) {
      document.getElementById(dd[ind]).classList.toggle("show");
    }
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

function enterFrom(fr) {  
  idf = document.getElementById("idFrom");
  idf.value = fr.textContent;  
  document.getElementById("fromDd").classList.toggle("show");  
}
function enterTo(fr) {  
  idf = document.getElementById("idTo");
  idf.value = fr.textContent;
  document.getElementById("toDd").classList.toggle("show");  
}

function selectFrom() {
  idf = document.getElementById("idFrom");
  idf.focus();
  idf.select();
}
function selectTo() {
  idf = document.getElementById("idTo");
  idf.focus();
  idf.select();
}

function filterFunction1() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("idFrom");
    filter = input.value.toUpperCase();
    div = document.getElementById("fromDd");
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
      txtЗначение = a[i].textСодержание || a[i].innerText;
      if (txtЗначение.toUpperCase().indexOf(filter) > -1) {
        a[i].style.display = "";
      } else {
        a[i].style.display = "none";
      }
    }
  }

function filterFunction2() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("idTo");
    filter = input.value.toUpperCase();
    div = document.getElementById("toDd");
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
      txtЗначение = a[i].textСодержание || a[i].innerText;
      if (txtЗначение.toUpperCase().indexOf(filter) > -1) {
        a[i].style.display = "";
      } else {
        a[i].style.display = "none";
      }
    }
  }

function vzKolPlus() {  
  vzKol = document.getElementById("vzKol");
  dKol = document.getElementById("dKol");
  mlKol = document.getElementById("mlKol");
  if (parseInt(vzKol.value) + parseInt(dKol.value) + parseInt(mlKol.value) < 9) {
    vzKol.value = parseInt(vzKol.value) + 1
  }  
}
function vzKolMinus() {  
  vzKol = document.getElementById("vzKol");
  if (parseInt(vzKol.value) > 0) {
    vzKol.value = parseInt(vzKol.value) - 1
  }  
}

function dKolPlus() {  
  vzKol = document.getElementById("vzKol");
  dKol = document.getElementById("dKol");
  mlKol = document.getElementById("mlKol");
  if (parseInt(vzKol.value) + parseInt(dKol.value) + parseInt(mlKol.value) < 9) {
    dKol.value = parseInt(dKol.value) + 1
  }  
}
function dKolMinus() {  
  vzKol = document.getElementById("vzKol");
  dKol = document.getElementById("dKol");
  mlKol = document.getElementById("mlKol");
  if (parseInt(dKol.value) > 0) {
    dKol.value = parseInt(dKol.value) - 1
  }  
}
function mlKolPlus() {  
  vzKol = document.getElementById("vzKol");
  dKol = document.getElementById("dKol");
  mlKol = document.getElementById("mlKol");
  if (parseInt(vzKol.value) + parseInt(dKol.value) + parseInt(mlKol.value) < 9) {
    mlKol.value = parseInt(mlKol.value) + 1
  }  
}
function mlKolMinus() {  
  vzKol = document.getElementById("vzKol");
  dKol = document.getElementById("dKol");
  mlKol = document.getElementById("mlKol");
  if (parseInt(mlKol.value) > 0) {
    mlKol.value = parseInt(mlKol.value) - 1
  }  
}

function pType() {
  var p = document.getElementById('passengers')
  var vzKol = document.getElementById('vzKol').value;
  var dKol = document.getElementById('dKol').value;
  var mlKol = document.getElementById('mlKol').value;
  var sum = parseInt(vzKol) + parseInt(dKol) + parseInt(mlKol)
  var ok = ""
  if (sum != 1) {
    ok = "s"
  }
  p.value = sum + ' passenger' + ok;
}

function tType(a) {  
  var input = document.getElementById('ticketType');
  input.value = a.textContent;
}

function startingType() {
  var input = document.getElementById('ticketType');
  input.value = "Economy";
}