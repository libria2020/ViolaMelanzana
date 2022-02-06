var selectedUserMail = null;

var msga = "'Sei sicuro di voler ACCETTARE questa richesta?'";
var msgr = "'Sei sicuro di voler RIFIUTARE questa richesta?'";
var msgb = "'Sei sicuro di voler RIMUOVERE questa ricetta?'"

function addClickOnRow(mail_utente,i) {
	$("#rowUser_" + i).click(function() {
		document.getElementById("userReqTable").style.display="block";
		document.getElementById("banTable").style.display="none";
		clearUsersTable();
		selectedUserMail = mail_utente;
		getRequest();
	});

}

function addClickOnRequest(id_ricetta) {
	$("#row_" + id_ricetta).click(function() {
			console.log("id= " + id_ricetta);
			modalRecipe (id_ricetta);
			$('#modalRecipe').modal();

	});

}


function modalRecipe(id_ricetta){
	$.ajax({

		url: "/getRecipe",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(id_ricetta),
		dataType: "json",

	}).done(function(ricetta) {

			$("#container-insert").append ($(
					' <div class="modal-header">' +
					'<h1>Inserisci la tua ricetta</h1>'+
					'</div>'+
					'<div class="modal-body">'+
						'<div class="form-group">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Titolo: </label>'+
							'<text name="testo" >'+ ricetta.titolo + '</text>'+
						'</div>'+
						'<div class="form-group">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Descrizione</label>'+
							'<textarea rows="6" cols="50" id="descrizione">'+ricetta.descrizione + '</textarea>'+
							'<label class="label lbl-warning" for="descrizione">Max 5000 characters</label>'+
						'</div>'+				
						'<div class="form-group">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Preparazione</label>'+
							'<textarea rows="6" cols="50" id="preparazione" >'+ ricetta.preparazione+ '</textarea>'+
							'<label class="label lbl-warning" for="preparazione">Max 10000 characters</label>'+			
						'</div>'+
						'<div class="form-group">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Tempo preprazione: </label>'+
							'<text name="testo" >' + ricetta.tempoPreparazione + '</text>'+
							'<label>Tempo cottura: </label>'+
							'<text name="testo" >'+ ricetta.tempoCottura + '</text>'+
							'<label>Dosi: </label>'+
							'<text name="testo" >'+ ricetta.dosi + '</text>'+
							'<label>Difficoltà: </label>'+
							'<text name="testo" >'+ ricetta.difficolta + '</text>'+
						'</div>'+
					'</div>'));
			});
}

function clearUsersTable() {
	$("#requestTableView").empty();
}

function getRequest() {
	if (selectedUserMail == null) {
		
		return;
	}
	$.ajax({

		url: "/getRequestForUser",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(selectedUserMail),
		dataType: "json",

	}).done(function(selectedUserRequest) {
			
			if (selectedUserRequest.length != 0) {
				for (var j = 0; j < selectedUserRequest.length; j++) {
					if (selectedUserRequest[j][0] != "promozione a master" && selectedUserRequest[j][0] != "declassare utente master"){
						$('#requestTableView').append($('<tr class="shadow-sm p-3 mb-5" id="row_' +selectedUserRequest[j][2] + '">'+
						'<td>' + selectedUserRequest[j][0] + '</td>'+
						'<td>' + selectedUserRequest[j][1] + '</td>'+
						'<td>' + selectedUserRequest[j][2] + '</td>'+
						'<td>' + selectedUserRequest[j][3] + '</td>'+
						'<td>'+
							'<form action="accept" id="accept" method="post" onclick="return confirm('+ msga +')">'+
								'<input type="hidden" id="requestType" name="requestType" value="' +selectedUserRequest[j][0] + '"/>'+
									'<button type="submit" id="accept" name="accept" value="' +selectedUserRequest[j][2] + '">Accetta</button>'+
							'</form>'+
						'</td>'+
						'<td>'+
							'<form action="refuse" id="refuse" method="post"  onclick="return confirm('+ msgr +')">'+
								'<input type="hidden" id="requestType" name="requestType" value="' +selectedUserRequest[j][0] + '"/>'+
								'<button type="submit" id="refuse" name="refuse" value="' +selectedUserRequest[j][2] + '">Rifiuta</button>'+
							'</form>'+
						'</td>'+
						'</tr>'));
						addClickOnRequest(selectedUserRequest[j][2]);
					}
				
					else{
							$('#requestTableView').append($('<tr class="shadow-sm p-3 mb-5" id="row_' +selectedUserRequest[j][2] + '">'+
							'<td>' + selectedUserRequest[j][0] + '</td>'+
							'<td>' + selectedUserRequest[j][1] + '</td>'+
							'<td>' + selectedUserRequest[j][2] + '</td>'+
							'<td>' + selectedUserRequest[j][3] + '</td>'+
							'<td>'+
								'<form action="accept" id="accept" method="post" onclick="return confirm('+ msga +')">'+
									'<input type="hidden" id="requestType" name="requestType" value="' +selectedUserRequest[j][0] + '"/>'+
										'<button type="submit" id="accept" name="accept" value="' +selectedUserMail + '">Accetta</button>'+
								'</form>'+
							'</td>'+
							'<td>'+
								'<form action="refuse" id="refuse" method="post"  onclick="return confirm('+ msgr +')">'+
									'<input type="hidden" id="requestType" name="requestType" value="' +selectedUserRequest[j][0] + '"/>'+
									'<button type="submit" id="refuse" name="refuse" value="' +selectedUserMail + '">Rifiuta</button>'+
								'</form>'+
							'</td>'+
							'</tr>'));
						
					}
				}
			}
			
			else {
				$('#requestTableView').append($('<h1>Nessuna richiesta in sospeso</h1>'));
			}
			
	});
}

$(document).ready(function(){	
	$.ajax({
		type: "GET",
		url: "/allUsers",

		success: function(risposta) {
			for (var i = 0; i <risposta.length; i++) {
				$('#allUsersTableView').append($('<tr class="shadow-sm p-3 mb-5 " id="rowUser_'+ i + '">'+
										    	'<td>' + risposta[i].username + '</td>' +
												'<td>' + '</td>' +
												'</tr>'));
					addClickOnRow(risposta[i].mail,i);
					
			}
		}
	});
	
	banList();
});

function banList (){
	
	$.ajax({
		type: "GET",
		url: "/allBanRequest",

		success: function(risposta) {
				
			for (var i = 0; i <risposta.length; i++) {

			$('#banView').append($('<tr class="shadow-sm p-3 mb-5" id="rowBan_' +risposta[i].id + '">'+
				 
				'<td>' + '<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+ risposta[i].titolo + '</a>'+'</td>'+
				'<td>' + risposta[i].id + '</td>'+
				'<td>' + risposta[i].utentePubblicatore.mail + '</td>'+
				'<td>' + risposta[i].segnalazioni + '</td>'+
				'<td>'+
					'<form action="banRecipe" id="banRecipe" method="post" onclick="return confirm('+ msgb +')">'+
						'<button type="submit" id="banRecipe" name="banRecipe" value="' +risposta[i].id + '">Elimina</button>'+
					'</form>'+
				'</td>'+
				'</tr>'));
			}
		}
	});
}


