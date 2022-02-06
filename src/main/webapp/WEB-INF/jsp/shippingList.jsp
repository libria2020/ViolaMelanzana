<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta charset="utf-8">

		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script language="javascript" src="../js/shippingListJS.js"></script>
		<script language="javascript" src="../js/indirizzoJS.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	</head>	
	<body>
	
		<section class="h-10 h-custom" style="background-color: #eee;">
			<div class="d-flex justify-content-between m-2 p-2">
				<div class="p-l4 m-4">
					<label class="btn-static-pieno text-white">1</label>
				</div>
				<div class="m-4">
					<label class="btn-static-vuoto text-white">2</label>
				</div>
				<div class="m-4">
					<label class="btn-static-vuoto text-white">3</label>
				</div>
			</div>
		</section >



		<div class="container" id="card">
			<h3>Liste indirizzi per le consegne</h3>
			
			<hr/>
			
			<div class="prod-container" >
				<div style="display:none;" id="IndirizziAssenti">
					<label>Indirizzi assenti!</label>
				</div>
					
					<div id="indirizzi">
						<c:forEach items="${indirizzi}" var="ind">
							<div class="cart-container address-container">
						    	<div id="card">
						        	<div class="row ms-4 p-1">
						        		<div class="col-md-6"> <h6>Via:</h6></div>
						            	<div class="col-md-6"> ${ind.indirizzo} </div>
						            </div>
						            
						            <div class="row ms-4 p-1">
						        		<div class="col-md-6"> <h6>Numero civico:</h6> </div>
						            	<div class="col-md-6"> ${ind.n_civico} </div>
						            </div>
						            
						            <div class="row ms-4 p-1">
						        		<div class="col-md-6"> <h6>CAP:</h6> </div>
						            	<div class="col-md-6"> ${ind.cap} </div>
						            </div>
						            
						            <div class="row ms-4 p-1">
						        		<div class="col-md-6"> <h6>Città:</h6> </div>
						            	<div class="col-md-6"> ${ind.citta} </div>
						            </div>
						            
						            <div class="row ms-4 p-1">
						        		<div class="col-md-6"> <h6>Provincia:</h6> </div>
						            	<div class="col-md-6"> ${ind.provincia} </div>
						            </div>
						            
						            <div class="row ms-4 p-1">
						        		<div class="col-md-6"> <h6>Telefono:</h6> </div>
						            	<div class="col-md-6"> ${ind.telefono} </div>
						            </div>
						            
						             <div class="ms-4 p-3">
						             	<input type="radio" name="indirizzi" id="${ind.id}">
						            	 <label> Indirizzo di Consegna</label>
						           	</div>
						           
									<div class="vm-mod">
										<button class="vm-btn-cart vm-btn-mod bi-trash vm-color" id="btnElimina"></button>
						            	<button class="vm-btn-cart vm-btn-mod bi-pencil-square vm-color" id="btnModifica"></button>
									</div>
									
								</div>
							</div>
						</c:forEach>	
					</div>				
				</div>
				
				
				
				<div class="vm-mod">
					<button class="vm-btn-cart vm-btn-mod bi-plus-square mt-4 vm-color" id="btnNuovo"></button>
				</div>
				
				<div id="pulsantiSecondari" class="vm-mod">
					<input id="btnPaga" class="vm-btn-prod vm-btn-cart mt-4" type="button" value="Scegli metodo di pagamento" />
				</div>
				
		</div>
		
	</body>
</html>