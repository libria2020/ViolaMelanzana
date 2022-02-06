<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
	<head>
		<meta charset="utf-8">
		
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<script language="javascript" src="../js/cartJS.js"></script>		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
		
	</head>	
	
	
	<body>
		
		<jsp:include page="navbar.jsp"></jsp:include>
			
		<div class="container">
			<div class="prod-container" style="display:none;" id="carrelloVuoto">
					<label>Carrello vuoto!</label>
			</div>
				
			<div  class="cart-container prod-container" id="cart_prodotti">
			
				<table>
					<thead>
						<tr>
							<td>Nome</td>
							<td>Quantità</td>
							<td>Disponibilità</td>
							<td>Prezzo</td>
							<td></td>
						</tr>	
					</thead>
					
					<tbody>
						<c:forEach items="${ordine.prodottiInOrder}" var="ord">
							<tr>
								<td id="${ord.key.nome}" class="nomeProdotti">${ord.key.nome}</td>	
								<td> <input type="number" class="quantita" style="width:75px;" value="${ord.value}" onchange="inserimentoDatiPrezzo()"> </td>
								<td> <label class="quantitaMax">${ord.key.quantitaDisponibile}</label>  </td>
								<td> <label class="prezzo">${ord.key.prezzo}</label> <label> c/d </label> </td>
								<td>
									<button class="vm-btn-cart vm-btn-mod bi-trash vm-color" id="delete_btn" name="${ord.key.nome}"></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		
	
			<div class="cart-container prod-container">
		    	<div id="card">
		        	<div class="row ms-4 p-3">
		        		<div class="col-md-9"> Subtotale </div>
		            	<div class="col-md-3" id="subtotale">  </div>
		            </div>
		            
		            <div class="row ms-4 p-3">
		        		<div class="col-md-9"> Spedizione* </div>
		            	<div class="col-md-3" id="spedizione">  </div>
		            </div>
		            
		            <div class="row ms-4 p-3">
		        		<div class="col-md-9"> Totale </div>
		            	<div class="col-md-3" id="totale">  </div>
		            </div>
		
					<input class="vm-btn-pay vm-btn-cart mb-2" id="btnIndirizzo" type="button" value="Paga" />
					<div class="ms-4 p-3"><label>* Gratuita oltre i 29.99 euro</label></div>
				</div>
			</div>
	
		</div>		
	
	</body>
</html>
