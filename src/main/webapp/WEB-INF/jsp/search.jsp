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
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 

	 <link rel="icon" type="image/x-icon" href="/images/favicon.ico">

	<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>
<body>
		<jsp:include page="navbar.jsp"></jsp:include>


								
	<div id="container">
		<c:forEach items="${list}" var="ricetta">
			<div class="container space-s">
				<div class="card mb-3 vm-card">
					<div class="row no-gutters">
						<div class="col-md-4">
							<a href="recipePage?ricetta_id=${ricetta.id}" >
								<img src="${ricetta.base64Image}" class="card-img" alt="...">
							</a>
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<div class="icon-top-right">
									<i class="glyphicon glyphicon-comment vm-color icon"></i>
									<span class="number vm-color">${ricetta.commenti.size()}</span>
									<i class="glyphicon glyphicon-heart vm-color icon"></i>
									<span class="number vm-color"> ${ricetta.likes} </span> 
								</div> 
								<a href="recipePage?ricetta_id=${ricetta.id}">
									<h2><b>${ricetta.titolo}</b></h2>   
								</a>
								<p class="decs">${ricetta.descrizione}</p>
							</div>	
							<div class="icon-botton-left">
								<i class="glyphicon glyphicon-cutlery vm-color icon"></i>
								<span class="number vm-color">${ricetta.difficolta}</span>
								<i class="glyphicon glyphicon-hourglass vm-color icon"></i>
								<span class="number vm-color">${ricetta.tempoPreparazione}</span>
							</div> 
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>