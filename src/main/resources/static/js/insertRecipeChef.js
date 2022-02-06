window.addEventListener("load", function(){
	aggiungiIngredienteView();
	showSelectedImage();
	evento();
	//eventoChangeCategorie();
	
})

function Ingrediente(nome){
	this.nome=nome;
}

function Quantita(quantita, unitaDiMisura){
	this.quantita=quantita;
	this.unitaDiMisura = unitaDiMisura;
	
}

function IngredienteQuantita(I,Q){
	this.ingrediente=I;
	this.quantita=Q;
	
	this.getIngrediente = function(){
		return this.ingrediente;
	}
	
	this.getQuantita = function(){
		return this.quantita;
	}
}

var inserisciRicetta = function(){
	var titolo = $("#titolo").val();
	var descrizione = $("#descrizione").val();
	var preparazione = $("#preparazione").val();
	var consiglio = $("#consiglio").val();
	var curiosita = $("#curiosita").val();
	var difficolta = $("#difficolta").val();
	var tempoP = $("#tempoP").val();
	var tempoC = $("#tempoC").val();
	var dosi = $("#dosi").val();
	var video = $("#video").val();
	var idChef = $("#idChefHidden").val();
	var categoria = $("#categoria").val();
	
	var righeTabellaIngredienti = document.querySelectorAll("#riga");
	var ingredientiQuantita = [];
	righeTabellaIngredienti.forEach(function(riga, indice){
		var nome = riga.cells[0].textContent;
		var quantita = riga.cells[1].textContent;
		var uDiMisura= riga.cells[2].textContent;
				
		var I = new Ingrediente(nome);
		var Q = new Quantita(quantita,uDiMisura);
		
		var ingrQuant = new IngredienteQuantita(I,Q);
		ingredientiQuantita.push(ingrQuant);

	})
	
	$.ajax({
		type: "POST",
		url: "/insertRecipeChef",
		data: {
			titolo:titolo,
			descrizione:descrizione,
			preparazione:preparazione,
			ingredientiQuantita:JSON.stringify(ingredientiQuantita),
			immagineBase64:data.file,
			nameFile:nameFile[0],
			video:video,
			consiglio:consiglio,
			curiosita:curiosita,
			difficolta:difficolta,
			tempoP:tempoP,
			tempoC:tempoC,
			dosi:dosi,
			idChef:idChef,
			categoria:categoria
		},	
		success: function(risposta){
			if(risposta === "OK"){
				alert("aggiunta con successo");
				window.location='/chef?key='+idChef;
			}
			else{
				alert("ricetta non aggiunta, riprova piu' tardi'");
			}
		},
		error: function(xhr){
			
		}
	})
}

function controlli(){
	var res = true;
	
	var titolo = $("#titolo");
	var lblTitolo = document.querySelector("#lblTitolo");
	if(titolo.val().length === 0){
		res = false;
		titolo.addClass("makeRed");
		lblTitolo.innerHTML = "Deve esserci un titolo!";
	} else if (titolo.val().length > 70){
		res = false;
		titolo.addClass("makeRed");
		lblTitolo.innerHTML = "Titolo troppo lungo";
	} else{
		titolo.removeClass("makeRed");
		lblTitolo.innerHTML = "";
	}
	
	/*var chef = $("#chef");
	var lblChef = document.querySelector("#lblChef");
	if(chef.val().length === 0){
		res = false;
		chef.addClass("makeRed");
		lblChef.innerHTML = "Deve essere presente uno chef";
	} else{
		chef.removeClass("makeRed");
		lblChef.innerHTML = "";
	}*/
	
	var descrizione = $("#descrizione");
	var lblDescrizione = document.querySelector("#lblDescrizione");
	if(descrizione.val().length === 0){
		res = false;
		descrizione.addClass("makeRed");
		lblDescrizione.innerHTML = "Deve esserci una descrizione!";
	} else if(descrizione.val().length > 5000){
		res = false;
		descrizione.addClass("makeRed");
		lblDescrizione.innerHTML = "Descrizione troppo lunga!";
	} else{
		descrizione.removeClass("makeRed");
		lblDescrizione.innerHTML = "";
	}
	
	var preparazione = $("#preparazione");
	var lblPreparazione = document.querySelector("#lblPreparazione");
	if(preparazione.val().length === 0){
		res = false;
		preparazione.addClass("makeRed");
		lblPreparazione.innerHTML = "Deve esserci una preparazione!";
	} else if(preparazione.val().length > 10000){
		res = false;
		preparazione.addClass("makeRed");
		lblPreparazione.innerHTML = "Preparazione troppo lunga";
	} else{
		preparazione.removeClass("makeRed");
		lblPreparazione.innerHTML = "";
	}
		
	var immagine = $("#imageRicetta");	
	var lblImmagine = document.querySelector("#lblImmagine");
	if(typeof immagine.attr("src") === 'undefined'){
		immagine.addClass("makeRed");
		res = false;
		lblImmagine.innerHTML = "Immagine obbligatoria";
	} else{
		immagine.removeClass("makeRed");
		lblImmagine.innerHTML = "";
	}
		
	var categoria = $("#categoria");
	var lblCategoria = document.querySelector("#lblCategoria");
	if(categoria.val().length === 0){
		res = false;
		categoria.addClass("makeRed");
		lblCategoria.innerHTML = "Deve esserci una categoria";
	} else{
		categoria.removeClass("makeRed");
		lblCategoria.innerHTML = "";
	}
	
	var difficolta = $("#difficolta");
	var lblDifficolta = document.querySelector("#lblDifficolta");
	if(difficolta.val().length === 0){
		res = false;
		difficolta.addClass("makeRed");
		lblDifficolta.innerHTML = "Deve esserci una difficolta";
	} else if(difficolta.val() < 0 || difficolta.val() > 5){
		res = false;
		difficolta.addClass("makeRed");
		lblDifficolta.innerHTML = "La difficolta va da 1 a 5";
	} else{
		difficolta.removeClass("makeRed");
		lblDifficolta.innerHTML = "";
	}
		
	var tempoP = $("#tempoP");
	var lblTempoP = document.querySelector("#lblTempoP");
	if(tempoP.val().length === 0){
		res = false;
		tempoP.addClass("makeRed");
		lblTempoP.innerHTML = "Deve esserci un tempo di preparazione";
	} else if(tempoP.val() < 0){
		res = false;
		tempoP.addClass("makeRed");
		lblTempoP.innerHTML = "Inserisci un tempo corretto";
	} else{
		tempoP.removeClass("makeRed");
		lblTempoP.innerHTML = "";
	}
		
	var tempoC = $("#tempoC");
	var lblTempoC = document.querySelector("#lblTempoC");
	if(tempoC.val().length === 0){
		res = false;
		tempoC.addClass("makeRed");
		lblTempoC.innerHTML = "Deve esserci un tempo di cottura";
	} else if(tempoC.val() < 0){
		res = false;
		tempoC.addClass("makeRed");
		lblTempoC.innerHTML = "Inserisci un tempo corretto";
	} else{
		tempoC.removeClass("makeRed");
		lblTempoC.innerHTML = "";
	}
	
	var dosi = $("#dosi");
	var lblDosi = document.querySelector("#lblDosi");
	if(dosi.val().length === 0){
		res = false;
		dosi.addClass("makeRed");
		lblDosi.innerHTML = "Indicare le dosi";
	} else if(dosi.val().length > 70){
		res = false;
		dosi.addClass("makeRed");
		lblDosi.innerHTML = "Dosi troppo lunga";
	} else{
		dosi.removeClass("makeRed");
		lblDosi.innerHTML = "";
	}
	
	return res;
}

function evento(){
	var btn = document.querySelector("#sendRecipe");
	btn.addEventListener("click", function(event){
		event.preventDefault();
		
		if(controlli()){
			inserisciRicetta();
			/*var form = document.querySelector("#form-insert");
			form.submit();*/	
		}
	});
}

function aggiungiIngredienteView(){
	var btnAggiungiIngrediente = $("#btnAggiungiIngrediente");

	btnAggiungiIngrediente.click(function(){
		
		
		var nomeIngrediente = $("#nomeIngrediente");
		var quantita = $("#quantita");
		var udMisura = $("#udmisura");
		
		var lblNomeIngrediente = document.querySelector("#lblNomeIngrediente");
		if(nomeIngrediente.val() === ""){
			nomeIngrediente.addClass("makeRed");
			lblNomeIngrediente.innerHTML = "Questo campo non può essere vuoto";
			return;
		} else{
			nomeIngrediente.removeClass("makeRed");
			lblNomeIngrediente.innerHTML = "";
		}
			
		
		var table = document.querySelector("#tableIngredienti");
		table.style.visibility = "visible";
		
		var allIngredienti = document.querySelectorAll("#riga");
		allIngredienti.forEach(function(nome, indice){
				
				console.log(nome.cells[0].textContent);
		});
		
		var bodyTable = document.querySelector("#tableIngredienti tbody");
		var riga = bodyTable.insertRow(-1);
		
		riga.setAttribute("id","riga");
		
		var col = riga.insertCell(0);
		col.innerHTML =   nomeIngrediente.val() ;
		col.setAttribute("id", "nomeInserito");
		
		var col2 = riga.insertCell(1);
		col2.innerHTML = quantita.val(); 
		
		var col3 = riga.insertCell(2);
		col3.innerHTML = udMisura.val(); 
		
		var col4 = riga.insertCell(3);
		col4.innerHTML = "<input class=\"btn\" id=\"btnRimuoviIngrediente" + nomeIngrediente.val() + "\""  + " type=\"button\" value = \"-\"/>";
		var rimuovi = $("#btnRimuoviIngrediente" + nomeIngrediente.val());
		
		rimuovi.click(function(){
			riga.remove();
			
			if(table.rows.length === 1)
				table.style.visibility = "hidden";
		})
		
		nomeIngrediente.val("");
		quantita.val("");
		udMisura.val("");
	})
}

var data;
var nameFile;

function showSelectedImage(){
	var upload = document.querySelector("#uploadImage");
	var image = document.querySelector("#imageRicetta");

	upload.addEventListener("change", function(event){
		
		nameFile = (upload.files[0].name).split('.');
		let result;
        const fr = new FileReader();
		fr.onload = () => {
			result = fr.result;
            image.src = result;
			data = {
                filetype: nameFile[nameFile.length-1],
                file:result
			};

		};
		 fr.readAsDataURL(this.files[0]);
	
	})
	
}

/*function eventoChangeCategorie(){
	var inputCategorie = document.querySelector("#inputCategorie");
	inputCategorie.addEventListener("input", function(e){
		
		$.ajax({
			type: "POST",
			url: "/getCategorie",
			data: {
				chiave:e.target.value
			},	
			success: function(risposta){
				console.log(risposta);
				for(var i = 0 ; i < risposta.length ; ++i)
					document.querySelector("#consiglio").innerHTML += risposta[i].nome;
			},
			error: function(xhr){
				
			}
		})
	})

}*/