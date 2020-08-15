<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Sign in</title>
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
    <link href="../resources/css/styles.css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <img src="../resources/images/SBB_Logo.jpg" class="logo">
        <a class="navbar-brand logo-text" href="${pageContext.request.contextPath}/">SBB CFF FFS</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable</a
                    >
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0" method="get" action="${pageContext.request.contextPath}/sign_in">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>
            </form>
        </div>
    </nav>
</header>
<main role="main">

    <div class="container">
        <div class="row sign-in-up-container">
            <div class="col bg-light sign-in-container">
                <div class="sign-in-form">
                    <h3 class="text-center">Sign in</h3>
                    <c:choose>
                        <c:when test="${messageWrongCredentials.severity == 'ERROR'}">
                            <div class="alert alert-danger" role="alert">
                                    ${messageWrongCredentials.text}
                            </div>
                        </c:when>
                    </c:choose>
                    <form action="perform_login" method="post">
                        <label> Login
                            <input name="username" type="text" class="form-control"
                                   placeholder="Enter your login"/></label>
                        <label> Password
                            <input name="password" type="password" class="form-control"/></label>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <p>
                            <button class="btn btn-lg btn-success find-button mx-auto" type="submit">Sign in</button>
                        </p>
                    </form>
                </div>
            </div>
            <div class="col bg-light sign-up-container">
                <div class="sign-up-form">
                    <h4>Do not have an account? Create it!</h4>

                    <c:forEach var="message" items="${messages}">
                        <c:choose>
                            <c:when test="${message.severity == 'INFORMATIONAL'}">
                                <div class="alert alert-info" role="alert">
                                        ${message.text}
                                </div>
                            </c:when>
                            <c:when test="${message.severity == 'WARNING'}">
                                <div class="alert alert-warning" role="alert">
                                        ${message.text}
                                </div>
                            </c:when>
                            <c:when test="${message.severity == 'ERROR'}">
                                <div class="alert alert-danger" role="alert">
                                        ${message.text}
                                </div>
                            </c:when>
                            <c:when test="${message.severity == 'TECHNICAL_ERROR'}">
                                <div class="alert alert-danger" role="alert">
                                        ${message.text}
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-secondary" role="alert">
                                        ${message.text}
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <form:form method="post"
                               modelAttribute="registrationFormDTO"
                               action="/registration_status">
                        <label> Name
                            <form:input path="name" type="text" class="form-control"
                                        placeholder="Enter your name"/></label>
                        <label> Surname
                            <form:input path="surname" type="text" class="form-control"
                                        placeholder="Enter your surname"/></label>
                        <label> Login
                            <form:input path="login" type="text" class="form-control"
                                        placeholder="Enter your login"/></label>
                        <label> Birthday
                            <form:input path="birthday" type="date" class="form-control"/></label>
                        <label> Password
                            <form:input path="userPassword" type="password" class="form-control"/></label>
                        <p>
                            <button class="btn btn-lg btn-success find-button mx-auto" type="submit">Sign up</button>
                        </p>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <footer class="fixed-bottom page-footer bg-secondary">
        <a href="#" class="float-right footer-text">Back to top</a>
        <p class="text-center footer-text">&copy; 2020 SBB CFF FFS &middot; Alyona Kovalyova </p>
    </footer>
</main>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
