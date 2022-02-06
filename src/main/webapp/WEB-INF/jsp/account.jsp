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

	<jsp:include page="navbar.jsp"></jsp:include>
	
	<div class="row">
 		<div class="container space-nd">
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
					<h2 class="vm-container">Dati personali</h2>
					
					<div class="row vm-container">
						<div class="col-md-8">
							<div class="elem">
								<b>Nome e Cognome</b>
							</div>	
							<div class="sub-elem">
								${utente.nome} ${utente.cognome}
							</div>
						</div>
						<div class="col-md-4 elem">
							<button>Modifica</button>
						</div>
					</div>
					<hr>
					
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
							<button>Modifica</button>
						</div>
					</div>
					<hr>
					
					<div class="row vm-container">
						<div class="col-md-8">
							<div class="elem">
								<b>La tua password</b>
							</div>	
							<div class="sub-elem">
								*********
							</div>
						</div>
						<div class="col-md-4 elem">
							<button>Modifica</button>
						</div>
					</div>
					<hr>
				</div>
				
				<div id="address-tab" class="tabcontent">
					<h2 class="vm-container">Indirizzi</h2>
				</div>
				
				
			
				<div id="orders-tab" class="tabcontent">
					<h2 class="vm-container">Ordini</h2>
				</div>
 			</div>
 		</div>
	</div>
	
	
	
	
	
	
	
	
	
	<!-- The Modal -->
	<div id="myModal" class="modal">
	
		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span>
			<form>
				<div class="form-group">
					<label for="formGroupExampleInput">Nome</label>
					<input type="text" class="form-control" id="formGroupExampleInput" placeholder="Example input">
				</div>
				
				<div class="form-group">
					<label for="formGroupExampleInput2">Cognome</label>
					<input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Another input">
				</div>
			</form>
		</div>
	
	</div>


	


</body>
</html>