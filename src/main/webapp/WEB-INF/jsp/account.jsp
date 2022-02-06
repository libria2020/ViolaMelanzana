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
	
	<script src="../js/navbarJS.js"></script>
		
	<link href="../css/navbarCSS.css" rel="stylesheet" type="text/css">
	
</head>

<body>	

	<nav class="vm-navbar">
		<div class="container-fluid">
			<div class="navbar-header">
      			<a class="" href="/">
					<img src= "/images/logo.png" id="logo">
				</a>
    		</div>
		
			<div>
				<ul class="vm-user nav navbar-nav">
				
					<c:if test="${admin == true }">
						<li class="vm-text"> Admin ${utente.username}! </li>
						<li class="dropdown">
							<a class="fa fa-user-o vm-color vm-icon dropdown-toggle" data-toggle="dropdown" href="#"></a>
							
							<ul class="dropdown-menu" style="margin-left: -110px;">
							    <li><a href="requestAdminView">Richieste Utente</a></li>
							    <li><a href="ordiniInConsegna">Ordini</a></li>
							    <li><a href="insertChefPage">Nuovo Chef</a></li>
							    <li><a href="logOut">Logout</a></li>
						    </ul>
						</li>
					</c:if>
					
					<c:if test="${admin == null }">
						<c:if test="${utente != null }">
							<li class="vm-text"> Benvenuto ${utente.username}! </li>
							<li class="dropdown">
								<a class="fa fa-user-o vm-color vm-icon dropdown-toggle" data-toggle="dropdown" href="#"></a>
								
								<ul class="dropdown-menu">
									    <li><a href="account">Il mio Profilo</a></li>
									    <li><a href="account">I miei Ordini</a></li>
									    <li><a href="insertRecipePage">Nuova Ricetta</a></li>
									    <li><a href="logOut">Logout</a></li>
							    </ul>
							</li>
							<li><a href="folderLike" class="fa fa-heart-o vm-color vm-icon"></a></li>
							<li><a href="cart" class="fa fa-opencart fa-lg vm-color vm-icon"></a></li>	
						</c:if>	
						
						<c:if test="${utente == null }">
							<li class="vm-text"> Ciao, accedi! </li>
							<li class="dropdown">
								<a class="fa fa-user-o vm-color vm-icon dropdown-toggle" data-toggle="dropdown" href="#"></a>
								
								<ul class="dropdown-menu" style="margin-left: -109px;">
									    <li><a href="loginPage">Il mio Profilo</a></li>
									    <li><a href="loginPage">I miei Ordini</a></li>
									    <li><a href="loginPage">Nuova Ricetta</a></li>
									    <li><a href="loginPage">Login</a></li>
							    </ul>
							</li>
							<li><a href="loginPage" class="fa fa-heart-o vm-color vm-icon"></a></li>
							<li><a href="loginPage" class="fa fa-opencart fa-lg vm-color vm-icon"></a></li>	
						</c:if>
					</c:if>
				
				</ul>	
			</div>
			 
			<div class="vm-input-group">
				<form action="/ricerca" method="get">
					<select id="nav-bar-cat" name="filter" class="vm-text">
					    <option value="all" selected="selected"> Tutte le categorie </option>
				  	</select>
					<input type="text" placeholder="Search" name="search">
					<button type="submit" class="fa fa-search vm-color vm-icon"></button>
				</form> 
			</div>	
		</div>
	</nav>
	
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
						<div class="col-md-8">
							<div class="elem">
								<b>Nome e Cognome</b>
							</div>	
							<div class="sub-elem">
								${utente.nome} ${utente.cognome}
							</div>
						</div>
						<div class="col-md-4 elem">
							<button class="vm-btn-cart vm-btn-mod vm-color glyphicon glyphicon-edit" data-toggle="modal" data-target="#dataModal"></button>
						</div>
					</div>
					
					<hr>
					
					<div class="row right-container">
						<div class="col-md-8">
							<div class="elem">
								<b>La tuo nome utente</b>
							</div>	
							<div class="sub-elem">
								${utente.username}
							</div>
						</div>
						<div class="col-md-4 elem">
							<button class="vm-btn-cart vm-btn-mod vm-color glyphicon glyphicon-edit" data-toggle="modal" data-target="#usernameModal"></button>
						</div>
					</div>
					<!--  
					<div class="row vm-container">
						<div class="col-md-8">
							<div class="elem">
								<b>La tua email</b>
							</div>	
							<div class="sub-elem">
								${utente.mail}
							</div>
						</div>
						<div class="col-md-4 elem">
							<button class="vm-btn-cart vm-btn-mod vm-color glyphicon glyphicon-edit" data-toggle="modal" data-target="#emailModal"></button>
						</div>
					</div>
					-->
					<hr>
					
					<div class="row right-container">
						<div class="col-md-8">
							<div class="elem">
								<b>La tua password</b>
							</div>	
							<div class="sub-elem">
								*********
							</div>
						</div>
						<div class="col-md-4 elem">
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
	          				<button type="submit" class="btn btn-default">Salva</button>
	        			</div>
					</form>
				</div>	
			</div>
		</div>
	</div>



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
							<input type="text" class="form-control" id="formGroupExampleInput" placeholder="Nome utente" name="newUsername">
						</div>
						
						<div class="modal-footer">
			          		<button type="submit" class="btn btn-default">Salva</button>
			        	</div>
					</form>
				</div>
				
				
			</div>
		</div>
	</div>
	
	
	
	<div class="modal fade" id="emailModal" role="dialog">
    	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Modifica i tuo indirizzo email</h2>
					<span class="close" data-dismiss="modal">&times;</span>
				</div>
				
				<div class="modal-body">
					<div> <b> Email corrente</b></div>
					
					<div class="sep"> ${utente.mail} </div>
					
					<form>
						<div class="form-group">
							<label for="formGroupExampleInput">Nuova email</label>
							<input type="text" class="form-control" id="formGroupExampleInput" placeholder="Indirizzo email">
						</div>
						
						<div class="form-group">
							<label for="formGroupExampleInput2">Conferma la nuova email</label>
							<input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Indirizzo email">
						</div>
						
						<div class="form-group">
							<label for="formGroupExampleInput2">La tua password</label>
							<input type="password" class="form-control" id="formGroupExampleInput2" placeholder="La tua password">
						</div>
					</form>
				</div>
				
				<div class="modal-footer">
	          		<button class="btn btn-default">Salva</button>
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