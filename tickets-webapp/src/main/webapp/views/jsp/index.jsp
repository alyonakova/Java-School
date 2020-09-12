<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>SBB: main page</title>

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
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">
                        Home <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">
                        Timetable
                    </a>
                </li>
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/passengers">
                            Passengers
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/crud">
                            CRUD <span class="sr-only"></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/trains">
                            Trains <span class="sr-only"></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/stations">
                            Stations <span class="sr-only">(current)</span>
                        </a>
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
                        <button class="btn btn-outline-success my-2 my-sm-0" data-test-id="sign-in-button" type="submit">
                            Sign out
                        </button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="form-inline mt-2 mt-md-0" method="get"
                          action="${pageContext.request.contextPath}/sign_in">
                        <button class="btn btn-outline-success my-2 my-sm-0" data-test-id="sign-in-button" type="submit">
                            Sign in
                        </button>
                    </form>
                </c:otherwise>
            </c:choose>

        </div>
    </nav>
</header>

<main role="main">

    <div class="mx-auto p-md-4 bg-light">
        <h1 class="my-3" style="text-align: center">Start your trip with SBB!</h1>
        <div class="container my-3">
            <c:forEach var="message" items="${messageConnectionSearchData}">
                <t:messageAlert message="${message}"/>
            </c:forEach>
            <form:form method="post" action="${pageContext.request.contextPath}/connections"
                       modelAttribute="outboundQuery">
                <div class="row">
                    <div class="col-md-6">
                        <label>
                            From
                        </label>
                        <form:input path="departureStationName" type="text" class="form-control"
                                    placeholder="Station" required="required"/>
                    </div>
                    <div class="col-md-6">
                        <label>
                            To
                        </label>
                        <form:input path="arrivalStationName" type="text" class="form-control"
                                    placeholder="Station" required="required"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label></label>
                        <form:input path="departureTime" type="datetime-local" class="form-control"/>
                    </div>
                    <div class="col-md-6">
                        <label></label>
                        <form:input path="arrivalTime" type="datetime-local" class="form-control"/>
                    </div>
                </div>
                <div class="my-3">
                    <button class="btn btn-lg btn-success find-button mx-auto" type="submit">
                        Find routes
                    </button>
                </div>
            </form:form>
        </div>
    </div>

    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <div class="row my-4">
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/resources/images/red_train.jpg" class="round-pic">
                <h2>Find train</h2>
                <p>
                    Enter the departure and arrival stations & departure time interval and you'll get
                    all the available trains.
                </p>
                <p>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/" role="button">
                        View details &raquo;
                    </a>
                </p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/resources/images/clock.jpg" class="round-pic">
                <h2>Timetable</h2>
                <p>You can easily get the timetable of every station you need!</p>
                <p>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/timetable" role="button">
                        View details &raquo;
                    </a>
                </p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/resources/images/ticket.jpg" class="round-pic">
                <h2>Buy ticket</h2>
                <p>Have you already chosen the available train? Sign in and buy a ticket and have a nice trip!</p>
                <p>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/" role="button">
                        View details &raquo;
                    </a>
                </p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->

        <hr class="featurette-divider">

    </div><!-- /.container -->

    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>&copy; 2020 SBB CFF FFS &middot; Alyona Kovalyova </p>
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