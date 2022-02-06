<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>


<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista Ingredienti Disponibili</title>	
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/util.css" rel="stylesheet" type="text/css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>

	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<c:if test="${error != null}">
			<label style="background-color: white;">${error}</label>
		</c:if>
		
		<c:if test="${lista != null}">
			<div class="col-md-5" style="background-color: white;">
				<table id="tableIngredienti" class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Nome</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lista }" var="ingr">
							<tr>
								<td>${ingr.nome}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</body>
</html>