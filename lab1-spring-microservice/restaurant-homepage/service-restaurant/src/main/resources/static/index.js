const formNode = document.getElementById("form");
const ordersList = document.getElementById("orders");
const userIDInput = document.getElementById("userID");

formNode.addEventListener('submit', (event) => {
    event.preventDefault();

    const data = new FormData(formNode);
    const userID = data.get('userID');
    const orderText = data.get('orderText');

    fetch('http://localhost:5000/api/order', {
        method: 'POST',
        body: JSON.stringify({userID, orderText}),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.text())
    .then(result => {
        const listItem = document.createElement('li');
        listItem.textContent = result;
        ordersList.appendChild(listItem);
        userIDInput.focus();
    })
    .catch(error => {
        console.error('Error:', error);
    });

    formNode.reset();
 });