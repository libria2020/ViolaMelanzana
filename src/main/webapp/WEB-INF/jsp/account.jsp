<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
<head>
	<meta charset="UTF-8">
	<title>Account</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
	
	
	<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/accountCSS.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
	<script src="../js/accountJS.js"></script>
	
		
	
</head>

<body>	

	<div class="main-container container space-nd">
 		<div class="row view-container">
 			<div class="col-md-3 left">
				<h2 class="left-bar">Il mio account</h2>			
				<dl>
					<dt>
						<button class="tablinks" onclick="profile(event)" id="defaultOpen">Dati Personali</button>
					</dt>
					<dt>
						<button class="tablinks" onclick="address(event)" id="address">Indirizi</button>
					</dt>
					<dt>
						<button class="tablinks" onclick="orders(event)" id="orders">Ordini</button>
					</dt>
				</dl>
 			</div>
 			
 			
 			
 			
 			
 			
 			<div class="col-md-9">
 				<div id="Dati" class="tabcontent">
					<h2 class="right-container">Dati personali</h2>
					
					<div class="row right-container">
						<div class="col-md-8 col-sm-8 col-xs-8">
							<div class="elem">
								<b>Nome e Cognome</b>
							</div>	
							<div class="sub-elem" id="user-Data">
								${utente.nome} ${utente.cognome}
							</div>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 elem">
							<button class="vm-btn-cart vm-btn-mod vm-color glyphicon glyphicon-edit" data-toggle="modal" data-target="#dataModal"></button>
						</div>
					</div>
					<hr>
					
					<div class="row right-container">
						<div class="col-md-8 col-sm-8 col-xs-8">
							<div class="elem">
								<b>La tuo nome utente</b>
							</div>	
							<div class="sub-elem" id="user-Username">
								${utente.username}
							</div>
						</div>
						<div class="ccol-md-4 col-sm-4 col-xs-4 elem">
							<button class="vm-btn-cart vm-btn-mod vm-color glyphicon glyphicon-edit" data-toggle="modal" data-target="#usernameModal"></button>
						</div>
					</div>
					<hr>
					
					<div class="row right-container">
						<div class="col-md-8 col-sm-8 col-xs-8">
							<div class="elem">
								<b>La tua password</b>
							</div>	
							<div class="sub-elem">
								*********
							</div>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 elem">
							<button class="vm-btn-cart vm-btn-mod vm-color glyphicon glyphicon-edit" data-toggle="modal" data-target="#passwordModal"></button>
						</div>
					</div>
					<hr>
				</div>
				
				<div id="address-tab" class="tabcontent">
					<h2 class="right-container">Indirizzi</h2>
				</div>
	
				<div id="orders-tab" class="tabcontent">
					<h2 class="right-container">Ordini</h2>
				</div>
				
 			</div>
 		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	<!-- Chage Name and Lastname -->
	<div class="modal fade" id="dataModal" role="dialog">
    	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Modifica i tuoi dati</h2>
					<span class="close" data-dismiss="modal">&times;</span>
				</div>
				
				<div class="modal-body">
					<form action="/data" method="post">
						<div class="form-group">
							<label for="mane">Nome</label>
							<input type="text" class="form-control" id="name" placeholder="Nome" name="name">
						</div>
						
						<div class="form-group">
							<label for="lastName">Cognome</label>
							<input type="text" class="form-control" id="lastName" placeholder="Cognome" name="lastName">
						</div>
						
						<div class="modal-footer">
	          				<button type="submit" class="btn btn-default" id="btn-save-data">Salva</button>
	        			</div>
					</form>
				</div>	
			</div>
		</div>
	</div>



	<!-- Chage Username -->
	<div class="modal fade" id="usernameModal" role="dialog">
    	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Modifica i tuo nome utente</h2>
					<span class="close" data-dismiss="modal">&times;</span>
				</div>
				
				<div class="modal-body">
					<div> <b> Nome utente corrente</b></div>
					
					<div class="sep"> ${utente.username} </div>
					
					<form action="/username" method="post">
						<div class="form-group">
							<label for="formGroupExampleInput">Nuovo nome utente</label>
							<input type="text" class="form-control" id="newUsername" placeholder="Nome utente" name="newUsername">
						</div>
						
						<div class="modal-footer">
			          		<button type="submit" class="btn btn-default" id="btn-save-username">Salva</button>
			        	</div>
					</form>
				</div>
				
				
			</div>
		</div>
	</div>
	
	
	
	<div class="modal fade" id="passwordModal" role="dialog">
    	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Modifica la tua password</h2>
					<span class="close" data-dismiss="modal">&times;</span>
				</div>
				
				<div class="modal-body">
					<div class="sep">Cambia la tua password ogni volta che vuoi per mantenere sicuro il tuo acount.</div>
					
					<form action="/password" method="post" id="changePasword">
						<div class="form-group">
							<label for="oldPassword">La tua password</label>
							<input type="password" class="form-control" id="oldPassword" placeholder="La tua password" name="oldPassword">
							<label id="lblOldPassword" style="color:red; font-size: 12"></label>
						</div>
						
						<div class="form-group">
							<label for="newPassword">Nuova password</label>
							<input type="password" class="form-control" id="newPassword" placeholder="Nuova password" name="newPassword">
							<label id="lblNewPassword" style="color:red; font-size: 12"></label>
						</div>
						
						<div class="form-group">
							<label for="confirmedpassword">Conferma la tua password</label>
							<input type="password" class="form-control" id="confirmpassword" placeholder="Conferma la tua password">
							<label id="lblConfirmPassword" style="color:red; font-size: 12"></label>
						</div>
						
						<div class="modal-footer">
	          				<button type="submit" class="btn btn-default" id="btn-save">Salva</button>
	        			</div>
					</form>
				</div>
				
				
			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="addressModal" role="dialog">
    	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Agginge un nuovo indirizzo</h2>
					<span class="close" data-dismiss="modal">&times;</span>
				</div>
				
				<div class="modal-body">
					<form action="/saveAddres" method="post">
						<div class="form-group">
							<label for="via">Via</label>
							<input type="text" class="form-control" id="via" placeholder="Via" name="via">
						</div>
						
						<div class="form-group">
							<label for="num">Numero civico</label>
							<input type="text" class="form-control" id="num" placeholder="Numero civico" name="num">
						</div>
						
						<div class="form-group">
							<label for="cap">CAP</label>
							<input type="text" class="form-control" id="cap" placeholder="CAP" name="cap">
						</div>
						
						<div class="form-group">
							<label for="city">Città</label>
							<input type="text" class="form-control" id="city" placeholder="Città" name="city">
						</div>
						
						<div class="form-group">
							<label for="prov">Provincia</label>
							<input type="text" class="form-control" id="prov" placeholder="Provincia" name="prov">
						</div>
						
						<div class="form-group">
							<label for="tel">Telefono</label>
							<input type="text" class="form-control" id="tel" placeholder="Telefono" name="tel">
						</div>
						
						<div class="modal-footer">
	          				<button type="submit" class="btn btn-default">Salva</button>
	        			</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>