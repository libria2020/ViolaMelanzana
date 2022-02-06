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
	
	<script src="../js/indexJS.js"></script>
	
</head>
<body>

		<jsp:include page="navbar.jsp"></jsp:include>

	
	
	<!-- Carousel -->
	<div class="container space-st">	
		<!-- Slide Show -->
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			 <!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
	
			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="/images/pasta.jpeg" alt="" class="carousel-img">
				</div>
			
				<div class="item">
					<img src="images/pizza.jpeg" alt="" style="width:100%;">
				</div>
			
				<div class="item">
					<img src="images/tiramisu.jpeg" alt="" style="width:100%;">
				</div>
			</div>
			
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
				<span class="sr-only">Previous</span>
			</a>
			
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<!-- TODO cargamento dinamico de las categorias -->
		<!-- Secondary Navigation Bar -->
		<nav class="vm-cat-navbar space-rd">
			<div class="vm-cat-content" id="cat_bar">
				<c:forEach items="${tutteLeCategorie}" var="categoria">
					<a href="/categoria?categoria=${categoria.id}"><b>  ${categoria.nome} </b></a>
				</c:forEach>
				<a href="javascript:void(0);" class="icon" onclick="myFunction()">
		   			<i class="fa fa-bars fa-lg vm-color"></i>
		 		</a>
			</div>
		</nav>

	</div>



	<!-- TODO cargamento dinamico de los chefs -->
	<!-- Chefs Section -->
	<div class="container space-nd">	
		<div class="row">
			<div class="vm-sec-header">
				<h2><fmt:message key="chefs"/></h2>
			</div>
			<div class="vm-sec-cntl">
				<button id="chef_previous" class="vm-nav-btn disabled">
					<i class="fa fa-chevron-left vm-color" aria-hidden="true"></i>
				</button>
				<button id="chef_next" class="vm-nav-btn">
					<i class="fa fa-chevron-right vm-color" aria-hidden="true"></i>
				</button>
			</div>
		</div>
		
		<div id="chef-row" class="row">
			
		</div>
	</div>
	
	
	
	<!-- Recipes Published by User -->
	<c:if test="${(utente != null) and (not empty ricettaUtente)}">
		<div class="container space-nd">
			<div class="row">
				<div class="vm-sec-header">
					<h2><fmt:message key="your.recipes"/></h2>
				</div>
				<div class="vm-sec-cntl">
					<button id="user_previous" class="vm-nav-btn disabled" disabled onclick="previous()">
						<i class="fa fa-chevron-left vm-color" aria-hidden="true"></i>
					</button>
					<button id="user_next" class="vm-nav-btn" onclick="next()">
						<i class="fa fa-chevron-right vm-color" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			
			<div id = "user-row" class="row">
				<c:forEach items="${ricettaUtente}" var="ricetta" begin="0" end="3">
					<div class="col-md-3">
						<div class="vm-card">
							<a href="recipePage?ricetta_id=${ricetta.id}">
								<img src="${ricetta.img}" alt="Avatar" class="card-img">
							</a>
							
							<a href="recipePage?ricetta_id=${ricetta.id}">
								<div class="vm-container">
									<h4><b>${ricetta.titolo}</b></h4>
								</div>
							</a>
							
							<div class="vm-container-s">
								<i class="glyphicon glyphicon-comment vm-color icon"></i>
								<span class="number vm-color">125</span>
								<i class="glyphicon glyphicon-star vm-color icon"></i>
								<span class="number vm-color">${ricetta.likes}</span>
							</div>
						</div> 
					</div>
				</c:forEach>	
			</div>
		</div>
	</c:if>
	

	
	<!-- Recipes Recently Added -->
	<div class="container space-nd">
		<div class="row">
			<div class="vm-sec-header">
				<h2><fmt:message key="recently.added"/></h2>
			</div>
			<div class="vm-sec-cntl">
				<button id="recent_previous" class="vm-nav-btn disabled">
					<i class="fa fa-chevron-left vm-color" aria-hidden="true"></i>
				</button>
				<button id="recent_next" class="vm-nav-btn">
					<i class="fa fa-chevron-right vm-color" aria-hidden="true"></i>
				</button>
			</div>
		</div>
		
		<div id = "recent-row" class="row"></div>
	</div>



	<!-- Popular Recipes -->
	<div id="popular" class="container space-nd">
		<div class="row">
			<div class="vm-sec-header">
				<h2><fmt:message key="most.voted"/></h2>
			</div>
		</div>
	</div>
	
	
	
	<!-- Load More Content -->
	<div class="container space">
		<div>
			<button id="load" class="vm-btn-load"> Load more </button>
		</div>
	</div>

</body>
</html>