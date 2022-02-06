$(document).ready(function(){
	
	// Get the element with id="defaultOpen" and click on it
	document.getElementById("defaultOpen").click();
	
	$.ajax({
	type: "GET",
	url: "/account/address",
	
	success: function(risposta) { // TODO controlar la dimension del array antes de estamparlo
			
			for (var i = 0; i < risposta.length; i++) {
				$('#address-tab').append($('<div class="row address">'+
												'<div>'+														
													'<h4> <b>Via</b> ' + risposta[i].via + '</h4>'+
													'<h4> <b>N. Civico</b> ' + risposta[i].n_civico + '</h4>'+
													'<h4> ' + risposta[i].citta + ', ' + risposta[i].provincia + ' </h4>'+
													'<h4> <b>Cap</b> ' + risposta[i].cap + '</h4>'+
													'<h4> <b>Telefono</b> ' + risposta[i].telefono + '</h4>'+
													'<div>'+
														'<button>Elimina</button>'+
														'<button>Modifica</button>'+
													'</div>'+
												'</div>'+
											'</div>'));
			}
			
			$('#address-tab').append($('<div>'+
											'<button>Aggingi indirizzi</button>'+
										'</div>'));
		}
	});
	
	$.ajax({
	type: "GET",
	url: "/account/orders",
	
	success: function(risposta) { // TODO controlar la dimension del array antes de estamparlo
			
			for (var i = 0; i < risposta.length; i++) {
				$('#orders-tab').append($('<div class="row order">'+
												'<a><h3>Numero dell’ordine: ' + risposta[i].id + '</h3></a>'+
												'<div class="row"> '+
													'<div class="col-md-4">'+
														'<b>Data dell’ordine</b> <br> ' + risposta[i].data_completamento + ' </div>'+
													'<div class="col-md-4">'+
														'<b>Totale</b> <br> ' + risposta[i].totale + ' €</div>'+
													'<div class="col-md-4">'+
														'<b>Stato del ordine</b> <br> ' + risposta[i].stato + '</div>'+
												'</div>'+
											'</div>'));
			}
		}
	});
	
	// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
		

});

function load(evt, elem) {
	var tabcontent = document.getElementsByClassName("tabcontent");
	for (var i = 0; i < tabcontent.length; i++) {
	  tabcontent[i].style.display = "none";
	}
	
	var tablinks = document.getElementsByClassName("tablinks");
	for (var i = 0; i < tablinks.length; i++) {
	  tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	
	document.getElementById(elem).style.display = "block";
	evt.currentTarget.className += " active";
}

function profile(evt) {	
	load(evt, "Dati");
}
	
function address(evt) {
	
	load(evt, "address-tab");
}

function orders(evt) {
	load(evt, "orders-tab");
}
	
	
	
	
	
	
	
	