<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Stations</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/carousel/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet">
</head>
<body>
<header>
    <sec:authorize var="loggedIn" access="isAuthenticated()"/>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <img src="${pageContext.request.contextPath}/resources/images/SBB_Logo.jpg" class="logo">
        <a class="navbar-brand logo-text" href="${pageContext.request.contextPath}/">SBB CFF FFS</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/passengers">Passengers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/crud">CRUD<span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains<span class="sr-only"></span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/stations">Stations<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <a href="${pageContext.request.contextPath}/employee_account">
                <img src="${pageContext.request.contextPath}/resources/images/account.png" class="account_logo">
            </a>
            <c:choose>
                <c:when test="${loggedIn}">
                    <form class="form-inline mt-2 mt-md-0" method="get"
                          action="${pageContext.request.contextPath}/logout">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="form-inline mt-2 mt-md-0" method="get"
                          action="${pageContext.request.contextPath}/sign_in">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>
<main role="main">

    <div class="container bg-light mt-4 p-md-4 col-6 rounded-container">
        <h2>All stations</h2>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Station name</th>
                <th scope="col">Zone id</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="station" items="${stations}">
                <tr>
                    <td>${station.id}</td>
                    <td>${station.name}</td>
                    <td>${station.zoneId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- FOOTER -->
    <footer class="fixed-bottom page-footer bg-secondary">
        <a href="#" class="float-right footer-text">Back to top</a>
        <p class="text-center footer-text">&copy; 2020 SBB CFF FFS &middot; Alyona Kovalyova </p>
    </footer>
</main>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>