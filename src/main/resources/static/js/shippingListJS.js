window.addEventListener("load", function(){
	localStorage.setItem("id_indirizzo", null);
	aggiungiEventi();
	controlloListaIndirizzi();
});

function aggiungiEventi(){
	btnModifica.addEventListener("click",function(){
		var radiobuttonID = document.querySelector( 'input[name="indirizzi"]:checked');
		if(radiobuttonID != null) {   
        	localStorage.setItem("id_indirizzo", radiobuttonID.getAttribute("id"));  
			window.location.href = '/shipping';
 		}else {
        	alert("selezionare radiobutton di un indirizzo");
		}
	});
	
	btnNuovo.addEventListener("click",function(){
		localStorage.setItem("id_indirizzo", 0); 
		window.location.href = '/shipping';
	});
	
	btnElimina.addEventListener("click",function(){
		var radiobuttonID = document.querySelector( 'input[name="indirizzi"]:checked');
		if(radiobuttonID != null) {   
        	$.ajax({
				type: "POST",
				url: "/deleteShipping",
				contentType: "application/json",
				data: JSON.stringify(radiobuttonID.getAttribute("id")),
				success: function(risposta){
					//status = 200
					console.log(risposta);
					if (risposta.status === "OK"){
						//alert(risposta.messaggio);
						window.location.href = '/shippingList';
					}
				},
				error: function(xhr){
					console.log(xhr);
					var res = JSON.parse(xhr.responseText);
					alert(res.messaggio);
					window.location.href = '/';
				}
		});
 		}else {  
        	alert("selezionare radiobutton di un indirizzo");
		}
	});
	
	btnPaga.addEventListener("click",function(){
		var radiobuttonID = document.querySelector( 'input[name="indirizzi"]:checked');
			if(radiobuttonID != null) {   
        	$.ajax({
				type: "POST",
				url: "/changeStatusAddress",
				contentType: "application/json",
				data: JSON.stringify(Number(radiobuttonID.id)),
				success: function(risposta){
					//status = 200
					console.log(risposta);
					if (risposta.status === "OK"){
						//alert(risposta.messaggio);
						window.location.href = '/pay';
					}
				},
				error: function(xhr){
					console.log(xhr);
					var res = JSON.parse(xhr.responseText);
					alert(res.messaggio);
					window.location.href = '/';
				}
		});
 		}else {  
        	alert("selezionare radiobutton di un indirizzo");
		}
	});	
}

function controlloListaIndirizzi(){
	var element = document.getElementById("indirizzi");
	var numberOfChildren = element.getElementsByTagName("*").length;
	if(numberOfChildren === 0){
		document.getElementById("IndirizziAssenti").style.display = "block";
		document.getElementById("pulsantiSecondari").style.display = "none";
	}
}


