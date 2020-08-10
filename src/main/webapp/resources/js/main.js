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
    tdFrom.innerHTML = "<input name=\"sourceStation\" type=\"text\" placeholder=\"Station from\">";
    tdTo.innerHTML = "<input name=\"destinationStation\" type=\"text\" placeholder=\"Station to\">";
    tdTravel.innerHTML = "<input name=\"travelDuration\" type=\"number\" placeholder=\"Travel duration\">";
    tdStop.innerHTML = "<input name=\"stopDuration\" type=\"number\" placeholder=\"Stop duration\">";
    tdPrice.innerHTML = "<input name=\"price\" type=\"number\" placeholder=\"Price\">";
    tdDelete.innerHTML = "<button class=\"btn-danger round-btn\" onclick=\"deleteRowInNewTrainInfo(this)\">-</button>";
}

function addRowForDepartures() {
    let table = document.getElementById('tab_body_departures');
    let newRow = document.createElement('tr');
    table.appendChild(newRow);
    let newTd = document.createElement('td');
    let tdDelete = document.createElement('td');
    newRow.appendChild(newTd);
    newRow.append(tdDelete);
    newTd.innerHTML = `<input type="datetime-local" name="departureTime" placeholder="Departure time">`;
    tdDelete.innerHTML = `<button class="btn-danger round-btn" onclick="deleteRowInNewTrainInfo(this)">-</button>`;
}

function deleteRowInNewTrainInfo(button) {
    let table = button.parentElement.parentElement.parentElement;
    let rowToDelete = button.parentElement.parentElement;
    table.removeChild(rowToDelete);
}

function addTrain() {
    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
    let segmentsTable = document.getElementById('tab_body_segments_data');
    let departuresTable = document.getElementById('tab_body_departures');
    let request = {
        id: String(document.getElementById('train-number').value),
        capacity: Number(document.getElementById('train-capacity').value),
        segments: Array.from(segmentsTable.querySelectorAll("tr")).map(segmentFromRow),
        departureTimes: Array.from(departuresTable.querySelectorAll("tr")).map(departureTimeFromRow)
    };
    fetch(new Request(`/api/trains`), {
        method: 'POST',
        body: JSON.stringify(request),
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken,
        },
    }).then(response =>
        console.debug(response)
    );
}

function segmentFromRow(row) {
    return {
        sourceStation: String(row.querySelector('input[name="sourceStation"]').value),
        destinationStation: String(row.querySelector('input[name="destinationStation"]').value),
        travelDurationMinutes: Number(row.querySelector('input[name="travelDuration"]').value),
        stopDurationMinutes: Number(row.querySelector('input[name="stopDuration"]').value),
        priceFranks: Number(row.querySelector('input[name="price"]').value),
    };
}

function departureTimeFromRow(row) {
    return String(row.querySelector('input[name="departureTime"]').value);
}
