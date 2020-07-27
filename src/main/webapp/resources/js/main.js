function tableSearch() {
    let trainNumber = document.getElementById('search-text');
    let table = document.getElementById('passengers_table');
    let regPhrase = new RegExp(trainNumber.value, 'i');
    let flag = false;
    for (let i = 1; i < table.rows.length; i++) {
        flag = false;
        flag = regPhrase.test(table.rows[i].cells[0].innerHTML);
        //if (flag) break;
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }
    }
}