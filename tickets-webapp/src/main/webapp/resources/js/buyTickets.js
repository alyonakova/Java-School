function buyTickets(form) {
    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
    let formObject = $(form).serializeJSON();
    let requestMethod = form.getAttribute('method');
    let requestURL = form.getAttribute('action');
    let requestBody = JSON.stringify(formObject);
    fetch(
        new Request(requestURL), {
            method: requestMethod,
            body: requestBody,
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken,
            },
        }
    ).then(response => response.json())
        .then(result => {
            let alertList = document.querySelector('.buying-messages-list');
            createAlert(result, alertList);
        });
    return false; // disable default form action
}