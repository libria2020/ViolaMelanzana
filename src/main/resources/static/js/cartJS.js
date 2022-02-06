window.addEventListener("load", function(){
	controlloCarrelloVuoto();
	inserimentoDatiPrezzo();
	
	aggiungiEventi();
});

function aggiungiEventi(){
	btnIndirizzo.addEventListener("click",function(){
		
		if(!controlloQuantita() || !controlloNumerico())		
			return;

		$.ajax({
				type: "POST",
				url: "/changeStatus",
				data: 
				{
					"prezzo" : localStorage.getItem("prezzo_totale")
					
				},
				success: function(risposta){
					console.log(risposta);
					if (risposta.status === "OK"){
						window.location.href = 'shippingList';
					}
				},
				error: function(xhr){
					console.log(xhr);
					var res = JSON.parse(xhr.responseText);
					alert(res.messaggio);
					//window.location.href = '/';
				}
			});
		});	
	
	delete_btn.addEventListener("click",function(e){
	$.ajax({
			type: "POST",
			url: "/deleteProduct",
			data: {
				"nome_prodotto" : e.target.name
			},
			success: function(risposta){
				console.log(risposta);
				if (risposta.status === "OK"){
				
					window.location.href = 'cart';
				}
			},
			error: function(xhr){
				console.log(xhr);
				var res = JSON.parse(xhr.responseText);
				alert(res.messaggio);
				//window.location.href = '/';
			}
		});
	});	
	
}

function controlloCarrelloVuoto(){
	var element = document.getElementById("cart_prodotti");
	var numberOfChildren = element.getElementsByTagName("*").length;
	if(numberOfChildren === 0){
		document.getElementById("carrelloVuoto").style.display = "block";
		document.getElementById("card_paga").style.display = "none";
	}
}

function controlloQuantita(){
	var quantita_label = document.getElementsByClassName('quantita');
	var quantitaMax_label = document.getElementsByClassName('quantitaMax');
	var disponibile = true;
	for(i = 0; i < quantita_label.length; i++){
		if(Number(quantita_label[i].value) > Number(quantitaMax_label[i].textContent)){
			quantita_label[i].value = quantitaMax_label[i].textContent;
			alert("Quantità non disponibile in magazzino");
			disponibile = false;
		}
	}
	return true;
}

function controlloNumerico(){
	var quantita_label = document.getElementsByClassName('quantita');
	for(i = 0; i < quantita_label.length; i++){
		if(!is_int(quantita_label[i].value)){
			quantita_label[i].value=0;
			alert("controllare valori numerici");
			return false;
		}
	}
	return true;
}

function is_numeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function is_int(n){
	if (!is_numeric(n)) return false
	else return (n % 1 == 0);
}

function inserimentoDatiPrezzo(){

	controlloQuantita();
	controlloNumerico();
	
	var spedizione;
	var prezzo_label = document.getElementsByClassName('prezzo');
	var quantita_label = document.getElementsByClassName('quantita');
	var i, sum = 0;
	for(i = 0; i < prezzo_label.length; i++){
		prezzo = Number(prezzo_label[i].textContent);
		quantita = Number(quantita_label[i].value);
		sum += prezzo*quantita;
	}
	
	document.getElementById('subtotale').innerHTML = sum + " euro";
	if(sum > 29.99)
		spedizione = 0;
	else spedizione = 3.50;
	if(spedizione == 0)
		document.getElementById('spedizione').innerHTML = "Gratuita";
	else document.getElementById('spedizione').innerHTML = spedizione + " euro";
	
	var total = sum  + spedizione;
	document.getElementById('totale').innerHTML = total + " euro";
	
	localStorage.setItem("prezzo_totale", total);
}
