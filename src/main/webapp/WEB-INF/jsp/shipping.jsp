<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta charset="utf-8">
		
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script language="javascript" src="../js/shippingJS.js"></script>
		<script language="javascript" src="../js/indirizzoJS.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>	
	<body>
		

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

		
		
		<div class="container" id="card">
			<h3>Indirizzo</h3>
			
			<hr/>
			
			<div class="cart-container mod-container">
				<form name="DatiSpedizione" action="pay" method="post">
					<label class="m-2">Indirizzo</label>
					<input type="text" id="indirizzo">
					
					<label class="m-2">Numero civico</label>
					<input type="text" id="n_civico"> <br>
					
					<label class="m-2">CAP</label>
					<input type="text" id="cap"> <br>
					
					<label class="m-2">Città</label>
					<input type="text" id="citta"> <br>
					
					<label class="m-2">Provincia</label>
					<input type="text" id="provincia"> <br>
					
					<label class="m-2">Telefono</label>
					<input type = "text" id="telefono"> <br>
					
					<div class="vm-mod">
						<input class="vm-btn-prod vm-btn-cart mt-4" id="btnSalva" type="button" value="Salva indirizzo" />
					</div>
				</form>
			</div>
		
		</div>
		
	</body>
</html>