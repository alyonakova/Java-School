<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
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

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet">
</head>
<body>
<header>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/">
                        Home <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">
                        Timetable
                    </a>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0" method="get" action="${pageContext.request.contextPath}/sign_in">
                <button type="submit" class="btn btn-outline-success my-2 my-sm-0"
                        data-test-id="sign-in-button">
                    Sign in
                </button>
            </form>
        </div>
    </nav>
</header>
<main role="main">

    <div class="container">
        <div class="row sign-in-up-container">
            <div class="col bg-light rounded-container">
                <div class="sign-in-form">
                    <h3 class="text-center">Sign in</h3>
                    <c:choose>
                        <c:when test="${messageWrongCredentials.severity == 'ERROR'}">
                            <div class="alert alert-danger" role="alert" data-test-id="alert-message">
                                    ${messageWrongCredentials.text}
                            </div>
                        </c:when>
                    </c:choose>
                    <form action="${pageContext.request.contextPath}/perform_login" method="post">
                        <label> Login
                            <input name="username" type="text" class="form-control"
                                   placeholder="Enter your login" required/></label>
                        <label> Password
                            <input name="password" type="password" class="form-control" placeholder="password"
                                   required/></label>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <p>
                            <button class="btn btn-lg btn-success find-button mx-auto" name="sign-btn" type="submit">
                                Sign in
                            </button>
                        </p>
                    </form>

                </div>
            </div>
            <div class="col bg-light sign-up-container">
                <div class="sign-up-form">
                    <h4>Do not have an account? Create it!</h4>

                    <c:forEach var="message" items="${messages}">
                        <t:messageAlert message="${message}"/>
                    </c:forEach>
                    <c:forEach var="error" items="${validationErrors}">
                        <div class="alert alert-danger" role="alert">
                                ${error.defaultMessage}
                        </div>
                    </c:forEach>

                    <form:form method="post"
                               modelAttribute="registrationFormDTO"
                               action="${pageContext.request.contextPath}/registration_status">
                        <label> Name
                            <form:input path="name" type="text" class="form-control"
                                        placeholder="Enter your name"
                                        minlength="2" maxlength="30"
                                        required="true"/></label>
                        <label> Surname
                            <form:input path="surname" type="text" class="form-control"
                                        minlength="2" maxlength="30"
                                        placeholder="Enter your surname" required="true"/></label>
                        <label> Login
                            <form:input path="login" type="text" class="form-control"
                                        minlength="3" maxlength="60"
                                        placeholder="Enter your login" required="true"/></label>
                        <label> Birthday
                            <form:input path="birthday" type="date" class="form-control" required="true"/></label>
                        <label> Password
                            <form:input path="userPassword" type="password" class="form-control"
                                        minlength="4" maxlength="20"
                                        placeholder="password" required="true"/></label>
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
