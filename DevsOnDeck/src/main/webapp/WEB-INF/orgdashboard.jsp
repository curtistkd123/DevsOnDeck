<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="row">
					<h2 class="col-sm-4"><c:out value="${org.orgName}"></c:out></h2>
					<h2 class="col-sm-4">DevsOnDeck</h2>
					<h3 class="col-sm-4 text-right">
						<a href="" class="text-right">Logout</a>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-4">
					<h3 class="col-sm-12 text-center"><a href="/newPosition"><button class=" col-sm-10 text-center">List a New Position</button></a></h3>
					<div class="col-sm-12">
						<h4 class="text-center">Positions To Fill</h4>
						<c:forEach items="${org.openPositions}" var="p">
						<div class="col-sm-12 text-center">
						<h3><a href="/position/${p.id}"><c:out value="${p.title}"/></a></h3>
						</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-sm-12 col-md-6">
					<h2>Available Devs</h2>
					<div class="col-sm-12 scroll"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>