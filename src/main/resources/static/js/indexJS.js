function myFunction() {
	var elem = document.getElementById("cat_bar");
	
	if (elem.className === "vm-cat-content") {
		elem.className += " responsive";
	} else {
		elem.className = "vm-cat-content";
	}
}



$(document).ready(function(){
	
	var count = 0;
	var recent = 0;
	var chef = 0;
	
	$('#recent_previous').disabled = true;
	
	if ($('#recent_previous').className === "vm-nav-btn") {
		$('#recent_previous').className += " disabled";
	} else {
		$('#recent_previous').className = "vm-nav-btn"
	}	
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Chef
	$.ajax({
		type: "GET",
		url: "/categorie",
		
		success: function(risposta) { 
			
			for (var i = 0; i < risposta.length; i++) {
				$('#cat_bar').append($('<a href="/categoria?categoria=' + risposta[i].id + '"><b>' + risposta[i].nome + '</b></a>'));
			}
		}		
			
	});
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Chef
	$.ajax({
		type: "GET",
		url: "/tuttiChef",
		data: {
    		"offset" : chef*4
		},
		
		success: function(risposta) { // TODO controlar la dimension del array antes de estamparlo
		
				chef++;
				
				$("#chef_previous").disabled = true;
				$("#chef_previous").className += " disabled";
				
				$("#chef-row").innerHTML = "";
					
				for (var i = 0; i < risposta.length; i++) {
					$('#chef-row').append($('<div class="col-md-3 center-space" id="c-' + risposta[i].id + '">'+
												'<div>'+
													'<h4><b>' + risposta[i].nome +'</b></h4>'+	
												'</div>'+									
												'<a href="chef?key='+ risposta[i].id +'" >'+
													'<img src="'+ risposta[i].img_link +'" class="img-circle person" alt="Avatar">'+
												'</a>'+										
											'</div>'));
				}
		}
	});
	
	chef_previous.addEventListener("click", function() {
		
		document.getElementById("chef_next").disabled = false; 
		document.getElementById("chef_next").className = "vm-nav-btn";

		$.ajax({
			type: "GET",
			url: "/tuttiChef",
			data : {
        		"offset" : (chef-2) * 4
    		},
		
			success: function(risposta) {
					chef--;
					
					if ( chef === 1 ) {
						document.getElementById("chef_previous").disabled = true; 
						document.getElementById("chef_previous").className += " disabled";
					}
				
					
					document.getElementById("chef-row").innerHTML = "";
					
					for (var i = 0; i < risposta.length; i++) {
					$('#chef-row').append($('<div class="col-md-3 center-space" id="c-' + risposta[i].id + '">'+
												'<div>'+
													'<h4><b>' + risposta[i].nome +'</b></h4>'+	
												'</div>'+									
												'<a href="chef?key='+ risposta[i].id +'" >'+
													'<img src="'+ risposta[i].img_link +'" class="img-circle person" alt="Avatar">'+
												'</a>'+										
											'</div>'));
					}
			}
		});
		
	});
	
	chef_next.addEventListener("click", function() {
		
		document.getElementById("chef_previous").disabled = false; 
		document.getElementById("chef_previous").className = "vm-nav-btn";
				
		$.ajax({
			type: "GET",
			url: "/tuttiChef",
			data : {
        		"offset" : chef * 4
    		},
		
			success: function(risposta) {
					chef++;
					// TODO arreglar en base al numero de tuplas
					if ( risposta.length < 4 ) { // TODO cambiar recent === 2 con el numero maximo de paginas (multiplo de 4) - 2
						document.getElementById("chef_next").disabled = true;
						document.getElementById("chef_next").className += " disabled";
					}
					
					if ( risposta.length !== 0 ) {
						document.getElementById("chef-row").innerHTML = "";	
					
						for (var i = 0; i < risposta.length; i++) {
						$('#chef-row').append($('<div class="col-md-3 center-space" id="c-' + risposta[i].id + '">'+
												'<div>'+
													'<h4><b>' + risposta[i].nome +'</b></h4>'+	
												'</div>'+									
												'<a href="chef?key='+ risposta[i].id +'" >'+
													'<img src="'+ risposta[i].img_link +'" class="img-circle person" alt="Avatar">'+
												'</a>'+										
											'</div>'));
						}
					}
			}
		});
		
	});
	
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Popular Recipes
	$.ajax({
		type: "GET",
		url: "/ricettePopolari",
		data: {
    		"offset" : count*8
		},
		
		success: function(risposta) { // TODO controlar la dimension del array antes de estamparlo
				for (var i = (0 + count * 2); i < (2 + count*2); i++) {
					
					$('#popular').append($('<div id="row-' + i + '" class="row"></div>'));
					
					for (var j = (0 + i * 4); j < (4 + i*4); j++) {
						$('#row-' + i).append($('<div class="col-md-3">'+
													'<div id="p-' + risposta[j].id + '"class="vm-card">'+
														'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
															'<img src="' + risposta[j].base64Image + '" alt="Avatar" class="card-img">'+
														 '</a>'+
														 '<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
														 	'<div class="vm-container"><h4><b>' + risposta[j].titolo + '</b></h4></div>'+
														 '</a>'+
														 '<div class="vm-container-s">'+
															'<i class="glyphicon glyphicon-comment vm-color icon"></i>'+
															'<span class="number vm-color">' + risposta[j].commenti.length + '</span>'+
															'<i class="glyphicon glyphicon-heart vm-color icon"></i>'+
															'<span class="number vm-color">' + risposta[j].likes + '</span>'+
														 '</div>'+
													'</div>'+
												'</div>'));		
					}
				}
				
				count++;
		}
	});
	
	var countload = 0;

	load.addEventListener("click", function() {
		
		$.ajax({
			type: "GET",
			url: "/ricettePopolari",
			data: {
	    		"offset" : count*8
			},
			
		success: function(risposta) {
					// TODO arreglar en base al numero de tuplas
					// https://stackoverflow.com/questions/44839753/returning-json-object-as-response-in-spring-boot
					// https://stackoverflow.com/questions/9098649/jquery-ajax-request-with-json-response-how-to/59733878
					if ( risposta.length < 8 ) {
						document.getElementById("load").disabled = true;
					}
					
					for (var i = 0; i < 2; i++) {
												
						$('#popular').append($('<div id="row-' + i + count * 2  + '" class="row"></div>'));
						
						for (var j = (0 + i * 4); j < (4 + i*4) && j < risposta.length; j++) {
							$('#row-' + i + count * 2).append($('<div class="col-md-3">'+
																	'<div id="p-' + risposta[j].id + '"class="vm-card">'+
																		'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
																			'<img src="' + risposta[j].base64Image + '" alt="Avatar" class="card-img">'+
																		'</a>'+
																		'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
																		 	'<div class="vm-container"><h4><b>' + risposta[j].titolo + '</b></h4></div>'+
																		'</a>'+
																		'<div class="vm-container-s">'+
																			'<i class="glyphicon glyphicon-comment vm-color icon"></i>'+
																			'<span class="number vm-color">' + risposta[j].commenti.length + '</span>'+
																			'<i class="glyphicon glyphicon-heart vm-color icon"></i>'+
																			'<span class="number vm-color">' + risposta[j].likes + '</span>'+
																		'</div>'+
																	'</div>'+
																'</div>'));
						}
					}
					count++;
			}
		});
		
	});
	
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Recently Added
	$.ajax({
		type: "GET",
		url: "/ultimeRicette",
		data : {
    		"offset" : 0
		},
	
		success: function(risposta) {
				recent++;
				
				document.getElementById("recent_previous").disabled = true;
				document.getElementById("recent_previous").className += " disabled";
				
				stampa(risposta);
				
		}
	});
		
	
	
	recent_previous.addEventListener("click", function() {
		
		document.getElementById("recent_next").disabled = false; 
		document.getElementById("recent_next").className = "vm-nav-btn";
				
		$.ajax({
			type: "GET",
			url: "/ultimeRicette",
			data : {
        		"offset" : (recent-2) * 4
    		},
		
			success: function(risposta) {
					recent--;
					
					if ( recent === 1 ) {
						document.getElementById("recent_previous").disabled = true;
						document.getElementById("recent_previous").className += " disabled";
					}
					
					stampa(risposta);
			}
		});
		
	});
	
	recent_next.addEventListener("click", function() {
		
		document.getElementById("recent_previous").disabled = false; 
		document.getElementById("recent_previous").className = "vm-nav-btn";
				
		$.ajax({
			type: "GET",
			url: "/ultimeRicette",
			data : {
        		"offset" : recent * 4
    		},
		
			success: function(risposta) {
					recent++;
					
					if ( risposta.length < 4 ) {
						document.getElementById("recent_next").disabled = true;
						document.getElementById("recent_next").className += " disabled";
					}
					
					if ( risposta.length !== 0 ) {
						stampa(risposta);
					}
			}
		});
		
	});
});

function stampa(risposta) {
	document.getElementById("recent-row").innerHTML = "";
				
	for (var i = 0; i < risposta.length; i++) {
		$('#recent-row').append($('<div class="col-md-3"> <div id="r-' + risposta[i].id + '"class="vm-card"> </div></div>'));

		$('#r-' + risposta[i].id).append($('<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
											'<img src="' + risposta[i].base64Image + '" alt="Avatar" class="card-img">'+
										'</a>'+
										'<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
											'<div class="vm-container"><h4><b>' + risposta[i].titolo + '</b></h4></div>'+
									 	'</a>'+
									 	'<div class="vm-container-s">'+
									 		'<a href="#"><i class="glyphicon glyphicon-comment vm-color icon"></i></a>'+
									 		'<span class="number vm-color">' + risposta[i].commenti.length + '</span>'+
									 		'<a href="#"><i class="glyphicon glyphicon-heart vm-color icon"></i></a>'+
									 		'<span class="number vm-color">' + risposta[i].likes + '</span>'+
									 	'</div>'));
	}
}



var user = 1;

// ***** ***** ***** ***** ***** ***** ***** ***** *****
// Recipes Published by User
function previous() {
	
	document.getElementById("user_next").disabled = false; 
	document.getElementById("user_next").className = "vm-nav-btn";
	
	$.ajax({
		type: "GET",
		url: "/ricettePubblicateUtente",
		data : {
    		"offset" : (user-2) * 4
		},
	
		success: function(risposta) {
				user--;
				
				if ( user === 1 ) {
					document.getElementById("user_previous").disabled = true;
					document.getElementById("user_previous").className += " disabled";
				}
				
				document.getElementById("user-row").innerHTML = "";
					
				for (var i = 0; i < risposta.length; i++) {
					$('#user-row').append($('<div class="col-md-3"> <div id="u-' + risposta[i].id + '"class="vm-card"> </div></div>'));

					$('#u-' + risposta[i].id).append($('<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
														'<img src="' + risposta[i].base64Image + '" alt="Avatar" class="card-img">'+
													'</a>'+
													'<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
														'<div class="vm-container"><h4><b>' + risposta[i].titolo + '</b></h4></div>'+
												 	'</a>'+
												 	'<div class="vm-container-s">'+
												 		'<a href="#"><i class="glyphicon glyphicon-comment vm-color icon"></i></a>'+
												 		'<span class="number vm-color">' + risposta[i].commenti.length + '</span>'+
												 		'<a href="#"><i class="glyphicon glyphicon-heart vm-color icon"></i></a>'+
												 		'<span class="number vm-color">' + risposta[i].likes + '</span>'+
												 	'</div>'));
				}
				
		}
	});
}


function next() {
		
		document.getElementById("user_previous").disabled = false; 
		document.getElementById("user_previous").className = "vm-nav-btn";
							
		$.ajax({
			type: "GET",
			url: "/ricettePubblicateUtente",
			data : {
        		"offset" : user * 4
    		},
		
			success: function(risposta) {				
					user++;
					// TODO arreglar en base al numero de tuplas
					if ( risposta.length < 4 ) { // TODO cambiar recent === 2 con el numero maximo de paginas (multiplo de 4) - 2
						document.getElementById("user_next").disabled = true;
						document.getElementById("user_next").className += " disabled";
					}
					
					if ( risposta.length !== 0 ) {
						document.getElementById("user-row").innerHTML = "";
							
						for (var i = 0; i < risposta.length; i++) {
							$('#user-row').append($('<div class="col-md-3"> <div id="u-' + risposta[i].id + '"class="vm-card"> </div></div>'));
		
							$('#u-' + risposta[i].id).append($('<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
																'<img src="' + risposta[i].base64Image + '" alt="Avatar" class="card-img">'+
															'</a>'+
															'<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
																'<div class="vm-container"><h4><b>' + risposta[i].titolo + '</b></h4></div>'+
														 	'</a>'+
														 	'<div class="vm-container-s">'+
														 		'<a href="#"><i class="glyphicon glyphicon-comment vm-color icon"></i></a>'+
														 		'<span class="number vm-color">' + risposta[i].commenti.length + '</span>'+
														 		'<a href="#"><i class="glyphicon glyphicon-heart vm-color icon"></i></a>'+
														 		'<span class="number vm-color">' + risposta[i].likes + '</span>'+
														 	'</div>'));
						}
					}
					
			}
		});
}
