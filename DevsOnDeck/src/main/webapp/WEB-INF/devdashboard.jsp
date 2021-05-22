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
				<div></div>
					<h2 class="col-sm-4"><c:out value="${dev.fname}"> <c:out value="${dev.lname}"></c:out></c:out></h2>
					<h2 class="col-sm-4">DevsOnDeck</h2>
					<h3 class="col-sm-4 text-right">
						<a href="" class="text-right">Logout</a>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-3">
					<div class="col-sm-12">
						<h4 class="text-center">Associated Organizations</h4>
						<c:forEach items="${orgs}" var="o">
						<div class="col-sm-12 text-center">
						
						<h4><a href="/organization/${o.id}"><c:out value="${o.orgName}"/></a></h4>
						</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-sm-12 col-md-6">
					<h2>Available Positions</h2>
					<div class="col-sm-12 scroll text-center">
					<c:forEach items="${positions}" var="p">
					<div class="col-sm-7 text-center">
					<a href="/position/${p.id}"><c:out value="${p.title}"/></a></div>
					</c:forEach></div>
				</div>
				<div class="col-sm-12 col-md-3">
					<div class="col-sm-12">
						<h4 class="text-center">Applied Positions</h4>
						<c:forEach items="${dev.applications}" var="a">
						<div class="col-sm-12 text-center">
						
						<h4><c:out value="${a.title}"/></h4>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>