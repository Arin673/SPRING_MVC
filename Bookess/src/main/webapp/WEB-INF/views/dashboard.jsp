<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Of Books</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
<link href="resources/css/dashboard.css/" rel="stylesheet" type="text/css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="dashboard">Welcome, ${sessionScope.username}</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="addBook">Add Book</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="readLater">Read Later</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="liked">Loved Books</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Logout</a>
          </li>
        </ul>
      </div>
    </nav>

    <div class="container mt-4">
             <h1 class="mb-4">List Of Books</h1>
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
                         <div class="d-flex justify-content-between align-items-center">
                           <div class="btn-group">
                             <form action="${pageContext.request.contextPath}/readLater" method="POST">
                               <input type="hidden" name="bookId" value="${book.id}">
                               <button type="submit" class="btn btn-sm btn-outline-secondary">Read Later</button>
                             </form>
                             <form action="${pageContext.request.contextPath}/liked" method="POST">
                               <input type="hidden" name="bookId" value="${book.id}">
                               <button type="submit" class="btn btn-sm btn-outline-secondary">Liked</button>
                             </form>
                           </div>
                         </div>
                       </div>
                     </div>
                   </div>
                 </c:forEach>
             </div>
         </div>
</body>
</html>
