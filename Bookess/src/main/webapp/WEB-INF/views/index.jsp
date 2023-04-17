<%@ page import="java.util.List" %>
<%@ page import="com.spring.mvc.service.BookService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            	crossorigin="anonymous"></script>

    <link href="resources/css/index.css/" rel="stylesheet" type="text/css">
</head>
<body>
 	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
 		<div class="container-fluid">
 			<a class="navbar-brand" href="#">Books</a>
 			<button class="navbar-toggler" type="button"
 				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
 				aria-controls="navbarSupportedContent" aria-expanded="false"
 				aria-label="Toggle navigation">
 				<span class="navbar-toggler-icon"></span>
 			</button>

 			<div class="collapse navbar-collapse" id="navbarSupportedContent">
 				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
 					<li class="nav-item"><a class="nav-link active"
 						aria-current="page" href="${pageContext.request.contextPath}/">Home</a></li>
 					<%
 					String id = (String) session.getAttribute("email");

 					if (id == null) {
 					%>
 					<li class="nav-item"><a class="nav-link" href="login">Login</a>
 					</li>
 					<li class="nav-item"><a class="nav-link" href="register">Register</a>
 					</li>
 					<%}else{ %>
 					<li class="nav-item"><a class="nav-link" href="login">Logout</a>
 					</li>
 					<%} %>

 				</ul>
 			</div>
 		</div>
 	</nav>
     <div class="container mt-4">
         <h1 class="mb-4">Available Books:</h1>
         <div class="row">
             <c:forEach var="book" items="${books}">
                 <div class="col-md-4">
                     <div class="card mb-4 shadow-sm">
                         <img src="${book.coverImage}" class="card-img-top" alt="${book.title} Cover Image">
                         <div class="card-body">
                             <h5 class="card-title">${book.title}</h5>
                             <p class="card-text"><strong>Author:</strong> ${book.author}</p>
                             <p class="card-text"><strong>ISBN:</strong> ${book.isbn}</p>
                             <p class="card-text"><strong>Genre:</strong> ${book.genre}</p>
                             <p class="card-text"><strong>Description:</strong> ${book.description}</p>
                             <p class="card-text"><strong>Rating:</strong> ${book.rating}</p>
                             <h6 class="card-subtitle mb-2 text-muted">Price: $${book.price}</h6>
                         </div>
                     </div>
                 </div>
             </c:forEach>
         </div>
     </div>
</body>
</html>