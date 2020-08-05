<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">

    <title>Account</title>

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
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <a href="${pageContext.request.contextPath}/customer_account">
                <img src="../resources/images/account.png" class="account_logo">
            </a>
            <form class="form-inline mt-2 mt-md-0" method="get" action="${pageContext.request.contextPath}/logout">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
            </form>
        </div>
    </nav>
</header>
<main role="main" class="d-flex flex-column h-100">
    <div class="flex-shrink-0">
        <div class="container bg-light">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">User data</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Tickets</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Change data</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">


                    <h4>User data</h4>
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Surname</th>
                            <th scope="col">Birthday</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${user.passenger.name}</td>
                                <td>${user.passenger.surname}</td>
                                <td>${user.passenger.birthday}</td>
                            </tr>
                        </tbody>
                    </table>


                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">


                    <h4>Tickets</h4>


                </div>
                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                    <h4>Change user data</h4>
                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                    <label>
                        Change name
                    </label>
                        <form:input path="newName" type="text" class="form-control" placeholder="${user.passenger.name}"/>
                        <form:hidden path="login" value="${user.login}"/>
                        <p>
                            <button class="btn btn-outline-info" type="submit">Change</button>
                        </p>
                    </form:form>
                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                    <label>
                        Change surname
                    </label>
                        <form:input path="newSurname" type="text" class="form-control"
                                    placeholder="${user.passenger.surname}"/>
                        <form:hidden path="login" value="${user.login}"/>
                        <p><button class="btn btn-outline-info" href="#" role="button">Change</button></p>
                    </form:form>
                    <label>
                        Change birthday
                    </label>
                    <input type="date" class="form-control" placeholder="${user.passenger.birthday}">
                    <p><a class="btn btn-outline-info" href="#" role="button">Change</a></p>
                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                        <label>
                            Change login
                        </label>
                        <form:input path="newLogin" type="text" class="form-control" placeholder="${user.login}"/>
                        <form:hidden path="login" value="${user.login}"/>
                        <p>
                            <button class="btn btn-outline-info" type="submit">Change</button>
                        </p>
                    </form:form>
                    <label>
                        Change password
                    </label>
                    <input type="password" class="form-control" placeholder="Enter new password">
                    <p><a class="btn btn-outline-info" href="#" role="button">Change</a></p>
                </div>
            </div>
        </div>
    </div>
    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>&copy; 2020 SBB CFF FFS &middot; Alyona Kovalyova </p>
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