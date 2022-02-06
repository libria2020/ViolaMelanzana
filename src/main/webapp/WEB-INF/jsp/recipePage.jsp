<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>



<html>

	<head>
	<meta charset="UTF-8">
	<title>Viola Melanzana</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
	<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/recipePageCSS.css" rel="stylesheet" type="text/css">
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="../js/indexJS.js"></script>
	<script src="../js/recipePageJS.js"></script>
	
	</head>
	
<body>
		<jsp:include page="navbar.jsp"></jsp:include>

	<div class="container space-nd" id="mainContainer">
		   <div class="likeUp">		   
			<c:if test="${utente != null }">	
			   <form id="handleLike" method="post" action="handleLike">	
				   	<c:if test="${like == false }">	
						<button type="submit" class="fa fa-heart-o fa-2x" aria-hidden="true" id="submitLikeUp" name="submitLike" onClick="myFunction(this)"></button>
						<span class="likeUpNum">${ricetta.likes}</span>	
					</c:if>
					<c:if test="${like == true }">	
						<button type="submit" class="fa fa-heart fa-2x" aria-hidden="true" id="submitLikeUp" name="submitLike" onClick="myFunction(this)"></button>
						<span class="likeUpNum">${ricetta.likes}</span>	
					</c:if>
				</form>
				
			</c:if>
			
			<c:if test="${utente == null }">	
				<button  type="submit" class="fa fa-heart-o fa-2x" aria-hidden="true" id="submitLikeUp" onClick="notLoggedFunction()"></button>
					<span class="likeUpNum">${ricetta.likes}</span>					
			</c:if>
		</div>
		<h1 class="heading-1">${ricetta.titolo}</h1>
	 <div class="row justify-content-center">
	  <div class="shadow">
		<div class="media" >
			<div class="media-left col-md-8 ">
				<img id="img" src="${ricetta.base64Image}" alt="recipe image">
				<p id="by">by ${ricetta.utentePubblicatore.mail}</p>
				
			</div>
			<div class="remove">
				<c:if test="${ricetta.utentePubblicatore == utente}">
						<c:if test="${ricetta.utentePubblicatore.master == true}">	
							<form id="removeRecipeMaster"  method="post" action="removeRecipeMaster" >
								<button type= "submit" id="remove" class="fa fa-trash-o fa-2x" aria-hidden="true" onClick="return confirm('Sicuro di voler rimuovere DEFINITIVAMENTE la tua ricetta dal sito web?')">Rimuovi</button>
							</form>
						</c:if>
						<c:if test="${ricetta.utentePubblicatore.master == false}">	
								<button type= "submit" id="remove" class="fa fa-trash-o fa-2x" aria-hidden="true" onClick="modalFunction('#modalRemoveRecipe')"></button>
						</c:if>
					</c:if>
			</div>
			<div class="media-body col-md-4 col-sm-8 col-xs-1" >
				
				<dl >

					<dt >
						<span class="fa fa-cutlery" aria-hidden="true" id="icon"></span>   Difficolta:
						<c:forEach var = "i" begin ="1" end = "${ricetta.difficolta}">
      						   <span class="fa fa-star" aria-hidden="true"></span>
      					</c:forEach>
					 </dt>
						
					<dt >
						<span class="fa fa-hourglass-o" aria-hidden="true" id="icon"></span> Tempo preparazione:
						${ricetta.tempoPreparazione} minuti
						
					</dt>
					
					<dt >
						<span class="fa fa-clock-o" aria-hidden="true" id="icon"></span> Tempo Cottura:
						${ricetta.tempoCottura} minuti
						
					</dt >
					
					<dt >
						<span class="	fa fa-balance-scale" aria-hidden="true" id="icon"></span> Dosi per:
						${ricetta.dosi}
						
					</dt>
				</dl>
			</div>
		</div>
	</div>
	</div>
	<div class="container1">

		<h2 class="titleSection">Presentazione</h2>
		<hr>		
		<p>${ricetta.descrizione}</p>

		<h3 class="titleSection">Ingredienti</h3>
		<hr>		
	<ul>
		<c:forEach items="${ingredienti}" var="ingrediente">
			<li>${ingrediente}</li>
		</c:forEach>
	</ul>

	<div>
		<h3 class="titleSection">Preparazione</h3>
		<hr>		
		<p>${ricetta.preparazione}<p>
	</div>
	<c:if test="${ricetta.video != null }">	
		<div id="video">
			   <h1 id="videoDesc">Cuciniamo insieme <span class="fa fa-smile-o" aria-hidden="true"></span></h1> 
			<iframe src="https://www.youtube.com/embed/${ricetta.video }"> </iframe>
		</div>
	</c:if>
	<div>
		<h3 class="titleSection">Consigli</h3>
		<hr>		
		<p>${ricetta.consiglio}</p>	
	</div>
	<div>
		<h3 class="titleSection">Curiosita</h3>
		<hr>		
		<p>${ricetta.curiosita}</p>
	</div>
	</div>
	
		<c:if test="${utente != null }">					 
			<nav class="navbar navbar-light blue-grey lighten-5" id="buttonBar">
					<form id="handleLike" method="post" action="handleLike" >	
						<button type="submit"  id="buttonType1" >			
							<span class="fa fa-heart-o" aria-hidden="true" id="imgBut"></span>Mi piace
						</button>
			 		</form>
			 		
					<button id="buttonType1" class="openmodal" onClick="modalFunction('#modalSave')">
						<span class="fa fa-folder" aria-hidden="true" id="imgBut"></span>  Salva
					</button>
					
					<form id="addCart" method="post" action="addCart" >	

						<button type="submit" id="buttonType1" onClick="return confirm('Alcuni ingredienti potrebbero non essere presenti nel nostro store.Aggiungere i prodotti disponibli?');">
							<span class="fa fa-shopping-basket" aria-hidden="true" id="imgBut"></span>Acquista
						</button>
					</form>
				
				<c:if test="${ban == false }">		
					<button id="buttonType1" class="openmodal" onClick="modalFunction('#modalBan')">		
						<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala
					</button>
				</c:if>
				
				<c:if test="${ban == true }">		
					<button id="buttonType1" class="button disabled" title="Hai già segnalato questa ricetta" >		
						<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala
					</button>
				</c:if>
		 	</nav>
		</c:if>
		 	
		 	<div class="modal myModal" id="modalSave">
				  <div class="modal-content">
					    <div class="modal-header">
							<h2>Aggiungi a raccolta</h2>
							<span class="close" data-dismiss="modal">&times;</span>
				
						</div>
							    
						<div class="modal-body">
							<c:if test="${folderList != null }">					 						
								<form action= "addRecipe" method="post">
										<c:forEach items="${folderList}" var="raccolta">
											<div class="form-check">
												<input name="raccoltaSel" type="radio" id="${raccolta}" value="${raccolta}" checked="checked">
												<label for="${raccolta}">${raccolta}</label>
											</div>
												   
										</c:forEach>									     
									    <button type="submit" class="btn btn-block mbtn tx-tfm vm-background-color">Aggiungi</button>									
							   </form>
							   <h1 id="opp">Oppure</h1>
							    <button type="submit" class="btn btn-block mbtn tx-tfm vm-background-color" onClick="modalFunction('#myModal')">+ Nuova Raccolta</button>	
							   
							</c:if>
								
							<c:if test="${folderList == null }">	
								 <button id="myBtn" onClick="modalFunction('#myModal')">+ Nuova Raccolta</button>	
							</c:if>
						</div> 
				</div>
			</div>
			
			<div class="modal myModal" id="modalBan">
					  <div class="modal-content" >
					    <div class="modal-header">
					    	<h2>Segnala ricetta</h2>
					      <span class="close" data-dismiss="modal">&times;</span>
		
					    </div>
					    
					    <div class="modal-body">
							  <form action= "ban" method="post">
									  <div class="form-check">
										    <input name="segnalazione" type="radio" id="Il contenuto è inappropriato" value="Il contenuto è inappropriato" checked="checked">
										    <label for="Il contenuto è inappropriato">Il contenuto è inappropriato</label>
									  </div>
									  
									   <div class="form-check">
										    <input name="segnalazione" type="radio" id="E' spam" value="E' spam" >
										    <label for="E' spam">E' spam</label>
									   </div>
									   <div class="form-check">
									  	  <input name="segnalazione" type="radio" value="La descrizione non corrisponde al titolo" >
									   	 <label for="La descrizione non corrisponde al titolo">La descrizione non corrisponde al titolo</label>
									   </div>
									   
									   <div class="form-check"> 
									   	  <input name="segnalazione" type="radio" value="altro" >							   
									   	  <label for="altro">altro</label>
									   </div>
				

							      	<button type="submit" id="ban" class="btn btn-block mbtn tx-tfm vm-background-color">Invia</button>
								
							 </form>
							</div> 
					    </div>
				</div>
				
				<div class="modal myModal" id="modalRemoveRecipe">
					  <div class="modal-content" >
					    <div class="modal-header">
					    	<h2>Richiesta di rimozione della ricetta</h2>
					      <span class="close" data-dismiss="modal">&times;</span>
		
					    </div>
					    
					    <div class="modal-body">
							  <form action= "removeRecipe" method="post">
									  <div class="form-check">
										    <input name="remove" type="radio" id="r1" value="Voglio migliorare la ricetta" checked="checked">
										    <label for="r1">Voglio migliorare la ricetta</label>
									  </div>
									  
									   <div class="form-check">
										    <input name="remove" type="radio" id="r2" value="Non è stata apprezzata dagli altri utenti" checked="checked">
										    <label for="r2">Non è stata apprezzata dagli altri utent</label>
									  </div>
									   <div class="form-check">
										    <input name="remove" type="radio" id="r3" value="Non mi piace più" checked="checked">
										    <label for="r3">Non mi piace più</label>
									  </div>
									   
									   <div class="form-check">
										    <input name="remove" type="radio" id="r4" value="Ci sono degli errori" checked="checked">
										    <label for="r4">Ci sono degli errori</label>
									  </div>
									     <div class="form-check">
										    <input name="remove" type="radio" id="r5" value="altro" checked="checked">
										    <label for="r5">altro</label>
									  </div>
				

							      	<button type="submit" id="removeR" class="btn btn-block mbtn tx-tfm vm-background-color">Invia</button>
								
							 </form>
							</div> 
					    </div>
				</div>
				
			<div id="myModal" class="modal">
			  <!-- Modal content -->
			  <div class="modal-content">
			    <div class="modal-header">
			    	<h2>Crea una raccolta</h2>
			      <span class="close" data-dismiss="modal">&times;</span>

			    </div>
			    
			    <div class="modal-body">
			      <form action="newFolderRec"  method="post" >
					<div class="form-group">
						<label>Nome</label>
						<input type="text" id="nome" class="form-control" placeholder="Assegna un nome alla tua raccolta..." name="nome">
						<label id="Nome"></label>
					<button type="submit" id="saveFolder" name="submit" value="Submit" class="btn btn-block mbtn tx-tfm vm-background-color">Crea</button>
					<button type="reset" data-dismiss="modal"class="btn btn-block mbtn tx-tfm vm-background-color">Annulla</button>
					</div>
				</form>
			    </div>
			    
			  </div>			
		</div>
		
		<c:if test="${utente == null }">					 
		  <nav class="navbar navbar-light blue-grey lighten-5" id="buttonBar">
       		
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >			
						<span class="fa fa-heart-o" aria-hidden="true" id="imgBut"></span>  Mi piace
				</button>				
	
				<button  type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >
					<span class="fa fa-folder" aria-hidden="true" id="imgBut"></span>  Salva
				</button>

				
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >
					<span class="fa fa-shopping-basket" aria-hidden="true" id="imgBut"></span>  Acquista
				</button>

							
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >		
					<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala
				</button>
				
							
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >		
					<span class="fa fa-comments-o" aria-hidden="true" id="imgBut"></span>  Commenta
				</button>
			</nav>				
		</c:if>
		<c:if test="${utente != null }">
			<div class="text-center">
				<form action="saveComment" id="saveComment" method="post" >
					<div class="form-group">
						<textarea name="contenuto"  rows="4" id="contenuto" class="form-control" placeholder="Scrivi qui il tuo commento "></textarea>
						 <input type="submit"  class="fa fa-comment" aria-hidden="true" value="Commenta" id="submitComment" onClick="alert('Commento inserito');">
					</div>
				</form>
			</div>
		</c:if>
		<div class="text-center">
	
		<c:forEach items="${commentiRicetta}" var="commento" >
				<div class="card text-center">
  					<div class="card-header">
						<h1>${commento.pubblicatore.username}</h1>
						<img src="/images/logo.png"></img>
					</div>
					 <div class="card-body">
					 	<p class="card-text ">${commento.contenuto}</p>
					 </div>
					  <div class="card-footer text-muted">
					  		${commento.pubblicatore.mail}
					  </div>
				</div>
		   </c:forEach> 
		</div>
		
   </div>
   			<div id="overlay">
				 <div class="notification with-icon success dismissable w-25" role="alert" aria-labelledby="not2dms-title" id="not2dms" class="animate__animated animate__slideInDown">
							<h2 id="not2dms-title">Attenzione</h2>
							<h5>Effettuare il login per utilizzare questa funzionalità</h5>
				
							<span class="close" aria-hidden="true">&times;</span>
				</div>
			</div>	
				
	</body>
</html>	
