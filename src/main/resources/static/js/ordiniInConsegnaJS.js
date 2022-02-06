window.addEventListener("load", function(){
	aggiungiEventi();
});

function aggiungiEventi(){
	
	var btnVisualizza = document.querySelectorAll(".btnVisualizza");

	for(let i = 0; i < btnVisualizza.length; ++i){
		btnVisualizza[i].addEventListener("click",function(e){
			
	        	$.ajax({
					type: "POST",
					url: "/getOrdineDaVisualizzare",
					contentType: "application/json",
					data: JSON.stringify(e.target.name),
					success: function(risposta){
						console.log(risposta);
						if (risposta.status === "OK"){
							window.location.href = '/visualizzaOrdiniInConsegna';
						}
					},
					error: function(xhr){
						console.log(xhr);
						var res = JSON.parse(xhr.responseText);
						alert(res.messaggio);
						window.location.href = '/';
					}
			});
		});
	}
}