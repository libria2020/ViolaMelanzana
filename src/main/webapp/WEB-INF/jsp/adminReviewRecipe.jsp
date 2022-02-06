<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
    <head>
    	<meta charset="UTF-8">
        <title>Richieste ricette</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
				
		<link href="../css/listProductToAccept.css" rel="stylesheet" type="text/css">
		
    </head>
    <body>
		<h2>Richieste ricette</h2>
		<c:forEach items="${pendingRecipes}" var="ricetta">
			<div class="review container">
				<div class=><h4>${ricetta.titolo} di ${ricetta.mailUtentePubblicatore}</h4></div>
				<div class="col-md-5">
					 <label><fmt:message key="description"/></label></br>
					 ${ricetta.descrizione }
				</div>
				<div class="col-md-7">
					<label><fmt:message key="preparation"/></label></br>
					${ricetta.preparazione }
				</div>
				
				<c:if test="${ricetta.consiglio != null }">
					<div class="col-md-3">
						<label>Consiglio</label></br>
						${ricetta.consiglio }
					</div>
				</c:if>
				
				<c:if test="${ricetta.curiosità != null }">
					<div class="col-md-3">
						<label>Curiosità</label></br>
						${ricetta.curiosità }
					</div>
				</c:if>
				
				<c:if test="${ricetta.immagine != null }">
					<div class="col-md-3">
						<label>Immagine ricetta</label></br>
						<img src="${ricetta.base64Image }"  style="width: 200; height: 200;"/>
					</div>
				</c:if>
				
				<div id="div-buttons">
					<button class="btn btn-success">accetta</button>
					<button class="btn btn-danger">rifiuta</button>
				</div>
				
				<c:if test="${ricetta.video != null }">
					<div>				
	 					<iframe width="420" height="315"
							src="https://www.youtube.com/embed/${ricetta.video }" allowfullscreen>
						</iframe>
					</div>
				</c:if>  
				
				
			</div>
		</c:forEach>
		
		
        
    </body>

</html>