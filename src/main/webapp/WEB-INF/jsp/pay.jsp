<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
		<meta charset="utf-8">
		
		<link href="../css/payCSS.css" rel="stylesheet" type="text/css" />
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<script language="javascript" src="../js/payJS.js"></script>
		
		<script src="https://www.paypal.com/sdk/js?currency=EUR&client-id=AQOqe9CcBelAniKs5bmhJefXkjjNIln6rFY41LXNvSY8ePO-YcBSqFuJuC6Ot9wmvuAn51vjdAACjCCR"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>	
	<body>
		<section class="h-10 h-custom" style="background-color: #eee;">
			<div class="d-flex justify-content-between m-2 p-2">
				<div class="p-l4 m-4">
					<label class="btn-static-pieno text-white">1</label>
				</div>
				<div class="m-4">
					<label class="btn-static-pieno text-white">2</label>
				</div>
				<div class="m-4">
					<label class="btn-static-vuoto text-white">3</label>
				</div>
			</div>
		</section >
	<section class="h-10 h-custom" style="background-color: #eee;">
	    <div class="row p-1 m-1 text-white">
	        <div class="m-5 col-11">
	            <div class="card ">
	                <div class="card-header">
	                	<input class="btn btn-primary btn-block btn-lg" id="btnPagamentoCosegna" type="submit" value="Pagamento alla consegna" />
	                	<div id="paypal-button-container">
	                	</div>
	                </div>
	            </div>
	        </div>
       </div>
   </section >	
	</body>
</html>