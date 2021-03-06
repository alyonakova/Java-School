<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">

    <title>Timetable</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/carousel/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet">
</head>
<body>
<header>
    <sec:authorize var="loggedIn" access="isAuthenticated()" />
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable<span class="sr-only">(current)</span></a
                    >
                </li>
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/passengers">Passengers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/crud">CRUD<span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains<span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/stations">Stations<span class="sr-only">(current)</span></a>
                    </li>
                </sec:authorize>
            </ul>
            <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                <a href="${pageContext.request.contextPath}/employee_account">
                    <img src="${pageContext.request.contextPath}/resources/images/account.png" class="account_logo">
                </a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                <a href="${pageContext.request.contextPath}/customer_account">
                    <img src="${pageContext.request.contextPath}/resources/images/account.png" class="account_logo">
                </a>
            </sec:authorize>
            <c:choose>
                <c:when test="${loggedIn}">
                    <form class="form-inline mt-2 mt-md-0" method="get" action="${pageContext.request.contextPath}/logout">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="form-inline mt-2 mt-md-0" method="get" action="${pageContext.request.contextPath}/sign_in">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>

<main role="main" class="md-3 p-3 p-lg-3">
    <header class="my-3 container-fluid">
        <h1>Timetable</h1>
        <div class="row align-items-baseline">
            <div class="col-md-auto">Show trains that</div>
            <div class="col-md-auto btn-group btn-group-toggle" data-toggle="buttons">
                <label class="btn btn-primary active">
                    <input type="radio" name="route" value="departure" checked
                           autocomplete="off" id="radio-departure"
                           onchange="tableSearchByStation()">
                    depart from
                </label>
                <label class="btn btn-primary">
                    <input type="radio" name="route" value="arrival"
                           autocomplete="off" id="radio-arrival"
                           onchange="tableSearchByStation()">
                    arrive to
                </label>
            </div>
            <input id="search-text" type="text" class="col form-control"
                   placeholder="any station" onkeyup="tableSearchByStation()">
        </div>
    </header>
    <div class="container-fluid">
        <table class="table" id="timetable">
            <thead class="thead-light">
            <tr>
                <th scope="col">Train</th>
                <th scope="col">Departure date</th>
                <th scope="col">From</th>
                <th scope="col">Arrival date</th>
                <th scope="col">To</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="segment" items="${allSegments}">
                <tr>
                    <td>${segment.train.id}</td>
                    <td><javatime:format value="${segment.departureTime}"
                                         pattern="HH:mm (z) / d MMM uuuu"
                                         locale="C"/></td>
                    <td>${segment.from.name}</td>
                    <td><javatime:format value="${segment.arrivalTime}"
                                         pattern="HH:mm (z) / d MMM uuuu"
                                         locale="C"/></td>
                    <td>${segment.to.name}</td>
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
<script src="${pageContext.request.contextPath}/resources/js/jquery.serializejson.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
