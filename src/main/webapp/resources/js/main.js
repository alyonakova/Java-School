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