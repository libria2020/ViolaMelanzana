<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
	</head>	

	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
	
	<section class="h-10  h-custom" style="background-color: #eee;">
	<div class="row p-1 m-1">
	<div class="d-flex justify-content-between">
			<div class="continua">
				
  

				<button type="button" class="btn go-back text-white ">
					<i class="fa fa-arrow-left"></i>
				 Continua a visualizzare ricette</button>
				<!-- go-back serve per far tornare indietro di una pagina il sito  -->
			</div>
		
		
			<div class="paga">
				<a href="shippingList">
					<button type="button" class="btn text-white" > Paga
					<i class="fa fa-arrow-right"></i></button>
					
				</a>
			</div>		
		</div>
		</div>
	</section >

		<section class="h-10 h-custom p-1" style="background-color: #eeef;">
		<div class="row p-1 m-1">
			<div class="m-5 col-md-11">
				<div class="card bg-primary text-white rounded-3 p-3" id="card">
					<div class="row">
						<div class="m-5 col-md-2">
							<input type="checkbox" class="checkbox"> 
							<a href="index.html">
	                   			<img src="../img/pastaAlForno.jpg" class="img-rounded m-4" alt="Pasta Al Forno" > 
	                    	</a>
						</div>
						<div class="m-5 col-md-5" style="min-width:250px">
							<div class="m-2">
	                    		<label>Titolo Ricetta: </label>
	                    		<br/>
	                    		<label>Ingredienti</label>
	                    		<br/>
	                    	</div>
							<table style="position:relative;" border="1" class="m-2">
	                    		<thead>
	                    			<tr>
	                    				<th class="p-2"><input type="checkbox"> </th>
	                    				<th class="p-2"><a href="..."> Pomodori</a> </th>
	                    				<th class="p-2"><select name="quantita">
	                    						<option value="250">250 grammi</option>
	                    						<option value="500">500 grammi</option>
	                    						<option value="750">750 grammi</option>
	                    						<option value="1000">1000 grammi</option>
	                    					</select></th>
	                    				<th class="p-2 text-white">Prezzo </th>
		                    		</tr>
		                    		
		                    		<tr>
	                    				<th class="p-2"><input type="checkbox"> </th>
	                    				<th class="p-2"><a href="..."> Pomodori</a> </th>
	                    				<th class="p-2"><select name="quantita">
	                    						<option value="250">250 grammi</option>
	                    						<option value="500">500 grammi</option>
	                    						<option value="750">750 grammi</option>
	                    						<option value="1000">1000 grammi</option>
	                    				</select></th>
										<th class="p-2 text-white">Prezzo </th>
		                    		</tr>
	                    			
		                    		<tr>
		                    			<th class="p-2"><input type="checkbox"> </th>
		                    			<th class="p-2"><a href="..."> Pomodori</a> </th>
		                    			<th class="p-2"><select name="quantita">
		                    					<option value="250">250 grammi</option>
		                    					<option value="500">500 grammi</option>
		                    					<option value="750">750 grammi</option>
		                    					<option value="1000">1000 grammi</option>
		                    				</select></th>
		                    			<th class="p-2 text-white">Prezzo </th>
			                    	</tr>
	                    			
		                    		<tr>
		                    			<th class="p-2"><input type="checkbox"> </th>
		                    			<th class="p-2"><a href="..."> Pomodori</a> </th>
		                    			<th class="p-2"><select name="quantita">
		                    					<option value="250">250 grammi</option>
		                    					<option value="500">500 grammi</option>
		                    					<option value="750">750 grammi</option>
		                    					<option value="1000">1000 grammi</option>
		                    				</select></th>
		                    			<th class="p-2 text-white">Prezzo </th>
			                    	</tr>
	                    		</thead>
	                    	</table>
						</div>
						<div class="m-5 col-md-2 align-bottom">
						<div class="align-bottom" style="position:absolute; bottom:20%;text-align:center;height:50px;">
							Totale prezzo ricetta
							<br/>
							500 euro
						</div>
						</div>
					</div>
				</div>
			</div>	    
		</div>
	</section>
	
	
	<section style="background-color: #eeef;">
	<div class="row p-1 m-1"> 
	<div class="m-5 col-11">
	
	                <div class="card bg-primary text-white rounded-3 p-3" id="card">
	
	                   <!-- <hr class="my-4">  --> 
	
	                    <div class="d-flex justify-content-between">
	                      <p class="mb-2">Subtotale</p>
	                      <p class="mb-2">4798.00 euro</p>
	                    </div>
	
					<div class="d-flex justify-content-between">
						<p class="mb-2">Spedizione</p>
						<p class="mb-2">20.00 euro</p>
					</div>
	
					<div class="d-flex justify-content-between mb-4">
						<p class="mb-2">Totale</p>
						<p class="mb-2">4818.00 euro</p>
					</div>
					
					<form action="shippingList">
						<button type="submit" class="btn-primary btn-block btn-lg" id="button" >
		    				<div class="d-flex justify-content-between">
		                        <span>Euro 4818.00</span>
								<span>Checkout <i class="fas fa-long-arrow-alt-right ms-2"></i></span>
							</div>
						</button>
					</form>
				</div>
			</div>
			</div>
		</section>		
	</body>
</html>