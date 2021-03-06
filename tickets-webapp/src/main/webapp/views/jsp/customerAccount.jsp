<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <a href="${pageContext.request.contextPath}/customer_account">
                <img src="${pageContext.request.contextPath}/resources/images/account.png" class="account_logo">
            </a>
            <form class="form-inline mt-2 mt-md-0" method="get" action="${pageContext.request.contextPath}/logout">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
            </form>
        </div>
    </nav>
</header>
<main role="main" class="d-flex flex-column h-100">
    <div class="flex-shrink-0">
        <div class="container bg-light container-pdng">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="true">Change data</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="false">Passenger data</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Tickets</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                    <h4>Change user data</h4>

                    <c:forEach var="error" items="${validationErrors}">
                        <div class="alert alert-danger" role="alert">
                                ${error.defaultMessage}
                        </div>
                    </c:forEach>
                    <c:forEach var="message" items="${messages}">
                        <t:messageAlert message="${message}"/>
                    </c:forEach>

                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                        <label>
                            Change name
                        </label>
                        <form:input path="newName" type="text" class="form-control" placeholder="${user.passenger.name}"/>
                        <form:hidden path="userId" value="${user.id}"/>
                        <p>
                            <button class="btn btn-outline-info mrgn-top" type="submit">Change</button>
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
                        <form:hidden path="userId" value="${user.id}"/>
                        <p><button class="btn btn-outline-info mrgn-top" type="submit">Change</button></p>
                    </form:form>
                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                        <label>
                            Change birthday
                        </label>
                        <form:input path="newBirthday" type="date" class="form-control"
                                    placeholder="${user.passenger.birthday}"/>
                        <form:hidden path="userId" value="${user.id}"/>
                        <p><button class="btn btn-outline-info mrgn-top" type="submit">Change</button></p>
                    </form:form>
                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                        <label>
                            Change login
                        </label>
                        <form:input path="newLogin" type="text" class="form-control" placeholder="${user.login}"/>
                        <form:hidden path="userId" value="${user.id}"/>
                        <p>
                            <button class="btn btn-outline-info mrgn-top" type="submit">Change</button>
                        </p>
                    </form:form>
                    <form:form method="post"
                               modelAttribute="changeUserDataDTO"
                               onsubmit="return '/customer_account'">
                        <label>
                            Change password
                        </label>
                        <form:input path="newPassword" type="password" class="form-control" placeholder="Enter new password"/>
                        <form:hidden path="userId" value="${user.id}"/>
                        <p><button class="btn btn-outline-info mrgn-top" type="submit">Change</button></p>
                    </form:form>
                    <br>
                </div>

                <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">


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
                    <ul class="list-group">
                        <c:forEach var="ticket" items="${tickets}">
                            <li class="list-group-item">
                                <div>Ticket ${ticket.id}</div>
                                <div>Departure date:
                                <javatime:format value="${ticket.segments.get(0).trainDepartureTime}" pattern="d MMM uuuu (z) HH:mm"
                                                 locale="C"/></div>
                            </li>
                        </c:forEach>
                    </ul>

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