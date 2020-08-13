<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">

    <title>Buy tickets</title>

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
    <sec:authorize var="loggedIn" access="isAuthenticated()" />
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable</a>
                </li>
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/passengers">Passengers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/crud">CRUD<span class="sr-only">(current)</span></a>
                    </li>
                </sec:authorize>
            </ul>
            <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                <a href="${pageContext.request.contextPath}/employee_account">
                    <img src="../resources/images/account.png" class="account_logo">
                </a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                <a href="${pageContext.request.contextPath}/customer_account">
                    <img src="../resources/images/account.png" class="account_logo">
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

<%--    <h4>${segments.get(0).from.name } (${segments.get(0).departure}) →--%>
<%--        ${segments.get(segments.size()-1).to.name } (${segments.get(segments.size()-1).arrival})</h4>--%>
    <h3 class="text-center">Route info</h3>
    <ul class="container list-group">
        <li class="list-group-item">
            <div class="lead">
                <span class="badge badge-info">Red Volcano</span> (01.10.2020 13:30) → <span class="badge badge-info">Chemical Plant</span> (02.10.2020 14:50)
            </div>
            <div class="alert alert-warning mrgn-top" role="alert">
                ❗ All dates are in local time ❗
            </div>
        </li>
        <li class="list-group-item lead">
            Train <span class="badge badge-info">121a</span>

            <%! int tickets = Integer.MAX_VALUE;
                int price = 0;%>
        </li>
        <li class="list-group-item lead">
            Tickets available: <span class="badge badge-info">15</span>
        </li>
        <li class="list-group-item lead">
            Total price: <span class="badge badge-info" id="tickets-price">15</span>₣
            <input type="hidden" value="15" id="one-ticket-price"/>
        </li>
    </ul>

<%--    <h4>Train ${ segments.get(0).train.id}</h4>--%>

<%--    <c:forEach var="segment" items="${segments}">--%>
<%--        <c:set var="left" value="${segment.ticketsLeft}"/>--%>
<%--        <% tickets = Math.min(tickets, (Integer) pageContext.getAttribute("left")); %>--%>
<%--        <c:set var="price" value="${segment.price}"/>--%>
<%--        <% price += (Integer) pageContext.getAttribute("price"); %>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>

<%--    <h4><%= tickets %> tickets available, total price: <%= price %>₣</h4>--%>
    <h3 class="text-center mrgn-top">Passengers</h3>
    <div class="alert alert-danger" role="alert" style="display: none" id="passengers-form-alert">
        Passengers number can not be less than 1 and more than 15
    </div>
    <div class="row">
        <label class="col-md-2">Number of tickets
            <input type="number" min="1" max="15" value="1" class="form-control" id="tickets-count" onchange="generatePassengerForms()">
        </label>
    </div>
    <form action="post">

        <table class="table" id="passengers-for-tickets">
            <thead class="thead-light">
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Birthday</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <input type="text" class="form-control" placeholder="Name" name="passenger-name"/>
                </td>
                <td>
                    <input type="text" class="form-control" placeholder="Surname" name="passenger-surname"/>
                </td>
                <td>
                    <input type="date" class="form-control" name="passenger-birthday"/>
                </td>
            </tr>
            </tbody>
        </table>

        <p><button class="btn btn-lg btn-success find-button mx-auto" type="submit">Buy</button></p>
    </form>

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
<script src="../../resources/js/main.js"></script>
</body>
</html>
