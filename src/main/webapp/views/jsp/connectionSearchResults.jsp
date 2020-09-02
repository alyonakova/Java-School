<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Trains</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/carousel/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/4.5/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/4.5/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/4.5/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon.ico">
    <meta name="msapplication-config" content="/docs/4.5/assets/img/favicons/browserconfig.xml">
    <meta name="theme-color" content="#563d7c">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
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
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/passengers">Passengers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/crud">CRUD<span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains<span class="sr-only">(current)</span></a>
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

    <h2 class="text-center mrgn-top">
        ${query.departureStationName}
        &LongRightArrow;
        ${query.arrivalStationName}
    </h2>

    <c:forEach var="connection" items="${discoveredConnections}">
        <div class="container bg-light mt-4 mb-4 p-4 rounded-lg">
            <h1>
                    ${connection.totalDuration.toHours()}h
                    ${connection.totalDuration.toMinutesPart()}min
            </h1>

            <ul class="list-group mb-1">
                <c:forEach var="chain" items="${connection.chains}">
                    <li class="list-group-item container">
                        <div class="row">
                            <div class="col text-right">
                                    ${chain.departureStation.name}
                            </div>
                            <div class="col-2 text-center small align-self-end">
                                Train ${chain.trainNumber}
                            </div>
                            <div class="col text-left">
                                    ${chain.arrivalStation.name}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-right font-weight-bold">
                                <javatime:format value="${chain.departureTime}"
                                                 pattern="HH:mm"
                                                 locale="C"/>
                            </div>
                            <div class="col-2 text-center">
                                &LongRightArrow;
                            </div>
                            <div class="col text-left font-weight-bold">
                                <javatime:format value="${chain.arrivalTime}"
                                                 pattern="HH:mm"
                                                 locale="C"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-right small">
                                <javatime:format value="${chain.departureTime}" pattern="d MMM uuuu (z)"
                                                 locale="C"/>
                            </div>
                            <div class="col-2 text-center small">
                                    ${chain.duration.toHours()}h
                                    ${chain.duration.toMinutesPart()}min
                            </div>
                            <div class="col text-left small">
                                <javatime:format value="${chain.arrivalTime}" pattern="d MMM uuuu (z)"
                                                 locale="C"/>

                            </div>
                        </div>
                        <c:if test="${not empty chain.viaStationsText}">
                            <div class="text-center small">
                                via ${chain.viaStationsText}
                            </div>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>

            <div>
                <a href="${pageContext.request.contextPath}/connections/${connection.id}"
                   class="btn btn-lg btn-primary mt-2 mx-auto align-baseline">
                    Buy ticket (${connection.priceFranks}&nbsp;₣)
                </a>
                <span class="ml-1">
                        Tickets available: ${connection.availableTicketsCount}
                    </span>
            </div>
        </div>
    </c:forEach> <%-- connections --%>

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
</body>
</html>
