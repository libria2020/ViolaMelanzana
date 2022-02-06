<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>


<header>
	<!-- Main Navigation Bar -->
	<nav class="vm-navbar space-nav">
		<div class="row">
			<div class="col-md-3">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">
						<img src= "/images/logo.png" id="logo">
					</a>
				</div>
			</div>
			
			<div class="col-md-6">
				<form action="/ricerca" method="get" class="vm-search-bar">
					<div class="vm-input-group">
						<select id="nav-bar-cat" name="filter"  class="vm-btn vm-filter-btn">
						    <option value="all" selected="selected"><fmt:message key="filter.all"/></option>
						    <c:forEach items="${tutteLeCategorie}" var="categoria">
						    	<option value="' + risposta[i].id + '"> ${categoria.nome} </option>
						    </c:forEach>
					  	</select>
				  	
						<input type="text" placeholder="Search" name="search">
						
						<button type="submit" class="vm-btn">
							<i class="fa fa-search fa-lg vm-color" aria-hidden="true"></i>
						</button>
					</div>
				</form> 
			</div>
	
			<div class="col-md-3">
				<div class="vm-user-navbar">
					
					<c:if test="${utente != null }">
						<a href="cart">
					  		<i class="fa fa-opencart fa-lg vm-color" aria-hidden="true"></i>
						</a>
					
						<a href="folderLike">
							<i class="fa fa-heart-o fa-lg vm-color" aria-hidden="true"></i>
						</a>
					</c:if>	
					<c:if test="${utente == null }">
						<a href="loginPage">
							<span data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="log.messages"/>">
						  		<i class="fa fa-opencart fa-lg vm-color" aria-hidden="true"></i>
							</span>
						</a>
						<a href="loginPage">
							<span data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="log.messages"/>">
								<i class="fa fa-heart-o fa-lg vm-color" aria-hidden="true"></i>
							</span>
						</a>
					</c:if>		
					
					<div class="vm-dropdown">
						<button class="vm-btn">
							<i class="fa fa-user-o fa-lg vm-color" aria-hidden="true"></i>
						</button>
						
						<div class="vm-dropdown-content">					
							<c:if test="${utente != null}">
								<a href="account"><fmt:message key="profile"/></a>
								<a href="account"><fmt:message key="order"/></a>
								<a href="insertRecipePage"><fmt:message key="add.recipe"/></a>
								<a href="logOut"><fmt:message key="logout"/></a>
							</c:if>	
							
							<c:if test="${utente == null}">
								<a href="loginPage"><fmt:message key="profile"/></a>
								<a href="loginPage"><fmt:message key="order"/></a>
								<a href="loginPage"><fmt:message key="add.recipe"/></a>
								<a href="loginPage"><fmt:message key="login"/></a>
							</c:if>
					    </div>
					</div>
					
					<c:if test="${utente != null }">
						<p><fmt:message key="welcome"/> ${utente.username}!</p>
					</c:if>	
					<c:if test="${utente == null }">
						<p><fmt:message key="greetings"/>!</p>
					</c:if>			
				</div>
			</div>
		</div>		
	</nav>
</header>

	
