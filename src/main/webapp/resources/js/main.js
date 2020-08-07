function tableSearchByTrain() {
    let trainNumber = document.getElementById('search-text');
    let table = document.getElementById('passengers_table');
    let regPhrase = new RegExp(trainNumber.value, 'i');
    let flag = false;
    for (let i = 1; i < table.rows.length; i++) {
        flag = false;
        flag = regPhrase.test(table.rows[i].cells[0].innerHTML);
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }
    }
}

function tableSearchByStation() {
    let stationName = document.getElementById('search-text');
    let table = document.getElementById('timetable');
    let regPhrase = new RegExp(stationName.value, 'i');
    let flag = false;

    let departure = true;
    let routeTypes = document.getElementsByName('route');
    if (routeTypes[1].checked) departure = false;
    for (let i = 1; i < table.rows.length; i++) {
        flag = false;
        if (departure) {
            flag = regPhrase.test(table.rows[i].cells[2].innerHTML);
        } else {
            flag = regPhrase.test(table.rows[i].cells[4].innerHTML);
        }
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }
    }
}

function addRowForSegmentData() {
    let table = document.getElementById('tab_body_segments_data');
    let newRow = document.createElement('tr');
    table.appendChild(newRow);
    let tdFrom = document.createElement('td');
    tdFrom.classList.add('td-new-train');
    let tdTo = document.createElement('td');
    tdTo.classList.add('td-new-train');
    let tdTravel = document.createElement('td');
    tdTravel.classList.add('td-new-train');
    let tdStop = document.createElement('td');
    tdStop.classList.add('td-new-train');
    let tdPrice = document.createElement('td');
    tdPrice.classList.add('td-new-train');
    let tdDelete = document.createElement('td');
    tdDelete.classList.add('td-new-train');
    newRow.appendChild(tdFrom);
    newRow.appendChild(tdTo);
    newRow.appendChild(tdTravel);
    newRow.appendChild(tdStop);
    newRow.appendChild(tdPrice);
    newRow.appendChild(tdDelete);
    tdFrom.innerHTML = "<input type=\"text\" placeholder=\"Station from\">";
    tdTo.innerHTML = "<input type=\"text\" placeholder=\"Station to\">";
    tdTravel.innerHTML = "<input type=\"text\" placeholder=\"Travel time\">";
    tdStop.innerHTML = "<input type=\"text\" placeholder=\"Stop time\">";
    tdPrice.innerHTML = "<input type=\"text\" placeholder=\"Price\">";
    tdDelete.innerHTML = "<button class=\"btn-danger round-btn\" onclick=\"deleteRowForSegmentData()\">-</button>";
}

function addRowForDepartures() {
    let table = document.getElementById('tab_body_departures');
    let newRow = document.createElement('tr');
    table.appendChild(newRow);
    let newTd = document.createElement('td');
    let tdDelete = document.createElement('td');
    newRow.appendChild(newTd);
    newRow.append(tdDelete);
    newTd.innerHTML = "\n" +
        "<input type=\"datetime-local\" placeholder=\"Departure time\">";
    tdDelete.innerHTML = "<button class=\"btn-danger round-btn\" onclick=\"deleteRowForDepartures()\">-</button>";
}

function deleteRowForSegmentData() {

}

function deleteRowForDepartures() {

}