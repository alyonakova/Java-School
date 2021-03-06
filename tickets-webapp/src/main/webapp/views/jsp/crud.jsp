<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.0.1">

    <title>Crud</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/carousel/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet">

    <!-- Bootstrap 4 theme for tail.select -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/tail.select@0.5.15/css/bootstrap4/tail.select-default.min.css">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/timetable">Timetable</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/passengers">Passengers</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/crud">CRUD<span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains<span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/stations">Stations<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <a href="${pageContext.request.contextPath}/employee_account">
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
        <div class="container">

            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="addTrain-tab" data-toggle="tab" href="#addTrain" role="tab"
                       aria-controls="addTrain" aria-selected="true">Add train</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="addStation-tab" data-toggle="tab" href="#addStation" role="tab"
                       aria-controls="addStation" aria-selected="false">Add station</a>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="addTrain" role="tabpanel" aria-labelledby="addTrain-tab">

                    <h2>Add train</h2>

                    <div class="messages-list">
                    </div>

                    <div class="row">
                        <label for="train-number" class="col-md-auto">Train number:</label>
                        <input type="text" class="col form-control" placeholder="Train number" id="train-number">
                        <div class="w-100"></div>
                        <label for="train-capacity" class="col-md-auto">Number of seats:</label>
                        <input type="number" class="col form-control mrgn-top" placeholder="Capacity"
                               id="train-capacity" min="10" max="110" value="10">
                    </div>
                    <div>
                        <table class="table table-new-train mrgn-top">
                            <thead class="thead-light">
                            <tr>
                                <th class="td-new-train">From</th>
                                <th class="td-new-train">To</th>
                                <th class="td-new-train">Travel duration (minutes)</th>
                                <th class="td-new-train">Stop (minutes)</th>
                                <th class="td-new-train">Price (₣)</th>
                                <th class="td-new-train"></th>
                            </tr>
                            </thead>
                            <tbody id="tab_body_segments_data">
                            <tr>
                                <td class="td-new-train">
                                    <input name="sourceStation" type="text" placeholder="Station from">
                                </td>
                                <td class="td-new-train">
                                    <input name="destinationStation" type="text" placeholder="Station to">
                                </td>
                                <td class="td-new-train">
                                    <input name="travelDuration" type="number" min="0" placeholder="Travel duration">
                                </td>
                                <td class="td-new-train">
                                    <input name="stopDuration" type="number" min="0" placeholder="Stop duration">
                                </td>
                                <td class="td-new-train">
                                    <input name="price" type="number" min="0" placeholder="Price">
                                </td>
                                <td>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <button class="btn-success round-btn" onclick="addRowForSegmentData()">+</button>
                    </div>
                    <div>
                        <table class="table table-new-train mrgn-top">
                            <thead class="thead-light">
                            <tr>
                                <th>Train departures (UTC)</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="tab_body_departures">
                            <tr>
                                <td>
                                    <input name="departureTime" type="datetime-local" placeholder="Departure time">
                                </td>
                                <td>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <button class="btn-success round-btn" onclick="addRowForDepartures()">+</button>
                    </div>
                    <p>
                        <button class="btn btn-lg btn-success find-button mx-auto" onclick="addTrain()">Add train
                        </button>
                    </p>
                </div>

                <div class="tab-pane fade" id="addStation" role="tabpanel" aria-labelledby="addStation-tab">

                    <h2>Add station</h2>

                    <div class="station-messages-list"></div>

                    <c:forEach var="message" items="${stationCreationMessages}">
                        <t:messageAlert message="${message}"/>
                    </c:forEach>

                    <div class="row">
                        <label for="station-name" class="col-md-auto">Station name:</label>
                        <input type="text" class="col form-control" placeholder="Station name" id="station-name">

                        <div class="w-100"></div>

                        <label for="zone-id" class="col-md-auto">
                            Zone:
                        </label>
                        <select name="zone-id" id="zone-id"
                                class="col form-control select zoneid-select mrgn-top">
                            <c:forEach items="${knownTimeZoneIds}" var="zoneId">
                                <option value="${zoneId}">${zoneId}</option>
                            </c:forEach>
                        </select>
                        <%-- <input type="text" class="col form-control mrgn-top" placeholder="Zone id" id="zone-id"> --%>
                        <p>
                            <a href="https://en.wikipedia.org/wiki/List_of_tz_database_time_zones" class="col-md-auto">
                                All time zones
                            </a>
                        </p>
                    </div>
                    <p>
                        <button class="btn btn-lg btn-success find-button mx-auto" onclick="addStation()">
                            Add station
                        </button>
                    </p>
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
<script>
    const requestContextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<!-- tail.select -->
<script type="application/javascript"
        src="https://cdn.jsdelivr.net/npm/tail.select@0.5.15/js/tail.select-full.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        tail.select(".zoneid-select", {
            animate: false,
            search: true,
            width: '100%',
        });
    });
</script>
</body>
</html>