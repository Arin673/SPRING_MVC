<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Register </title>
<link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous">

<script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous">
</script>
	<link href="resources/css/register.css/" rel="stylesheet" type="text/css">

<style>
    .error{
       color: red;
    }
</style>
</head>
<body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Books</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">

                    <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
                </li>


                <%
                	String id = (String) session.getAttribute("id");

                	if (id == null) {
                	%>
                	<li class="nav-item"><a class="nav-link" href="login">Login</a>
      				</li>
                	<li class="nav-item"><a class="nav-link" href="register">Register</a>
                	</li>
                	<%
                	}
                	if (id != null) {
                	%>
                	<li class="nav-item"><a class="nav-link" href="logout">Logout</a>
                	</li>
                	<%
                	}
                	%>
              </ul>
            </div>
          </div>
        </nav>
        <div class="container">
            <div>
                <div class="wrapper fadeInDown">
                    <div id="fadeIn first">
                        <h2 class='register'>Register Now!</h2>
                    </div>
                    <br/>
                    <form action="register" method="POST">
                        <input type='text' id='username' class="fadeIn second" name='username'
                        placeholder="Username" value=""/>
                        <input type='text' id='email' class="fadeIn third" name='email'
                        placeholder="Email" value=""/>
                        <input type='password' id='password' class="fadeIn fourth" name='password'
                        placeholder="Password" value=""/>
                        <input type='password' id='confirm_password' class="fadeIn fifth"
                        name='confirm_password' placeholder="Confirm Password" value=""/>

                        </select> <input type="submit" class="btn-login" value="Login" />
                    </form>
                </div>
            </div>
        </div>
</body>
</html>