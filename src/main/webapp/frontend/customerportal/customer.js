// Main JS file to run customer service workflows from the client side.
let baseUri = 'https://book-store-luc.herokuapp.com/';
//let baseUri = 'http://localhost:8080/'

let $ = (id) => {
    return document.getElementById(id);
}

/*****************************************************
 * Handle setting up customer context
 */
$('setCustomerBttn').addEventListener('click', () => {
	setCustomerContext();
})

setCustomerContext = () => {
    getRequest(`${baseUri}service/customerservice/customer/${$('customerId').value}`, (res) => {
        console.log(res);
        $('customerContext').innerHTML = `<p>First Name:\t${res.firstName}</p>
            <p>Last Name:\t${res.lastName}</p>
            <p>Customer Id:\t${res.customerId}</p>`;
    });

    loadAllProducts();
}

let loadAllProducts = () => {
    getRequest(`${baseUri}service/productservice/product`, (res) => {
        res.forEach(product => {
            let productIdNum = product.id;
            let item = $('products').appendChild(document.createElement('li'));
            item.innerHTML = `<p>Product Id:\t${productIdNum}</p>
                <p>Title:\t${product.title}</p>
                <p>Price:\t${product.price}</p>`;
            let bttn = item.appendChild(document.createElement('button'))
            bttn.innerText = "Order Item";
            bttn.addEventListener('click', () => {
                let req = { 
                    customerId : $('customerId').value,
                    orderLineReqs : [{productId : productIdNum, quantity : 1}]   
                }
                //TODO update links on representation and get it from this
                console.log(JSON.stringify(req));
                postRequest(`${baseUri}service/orderservice/order`, req, (response) => {displayOrder(response);})

            })
        });
    })
}

let displayOrder = (order) => {
    clearAllNodes($('workspace'));
    let workItems = $('workspace').appendChild(document.createElement('ul'));
    let item = workItems.appendChild(document.createElement('li'));
    let itemDetails = item.appendChild(document.createElement('pre'));
    itemDetails.innerHTML=`Order Id:\t${order.orderId}\n` +
            `Order Status: \t${order.orderState}\n` +
            `Customer Id:\t${order.customerId}\n`;
    let button = document.createElement('button');
    button.innerHTML = "Pay Order";
    let urlPath;
    console.log(order.links);
    order.links.forEach(link => {
        if(link.rel.includes("pay")){
            urlPath = link.href;
            console.log(urlPath);
        }   
    })
    if(urlPath){
        itemDetails.appendChild(button);
        button.addEventListener('click', () => {
            postRequest(urlPath,{}, () => {
                window.alert("Order Payed successfully");
            })               
         })
        };
}




/*******************************************************************
 * HELPER FUNCTIONS
 */

// Helper to make request, callback function param handles the response.
let getRequest = (urlPath, callback) => {
    let xhr = new XMLHttpRequest();
    xhr.onload = function() {
       callback(JSON.parse(this.responseText));      
    }
    xhr.open('GET', urlPath, true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(null);
}

let deleteRequest = (urlPath, callback) => {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", urlPath, true);
    xhr.onload = function () {
        if(xhr.status == "200") {
            window.alert("Successfully deleted");
            callback();
        }else{
            window.alert("Unable to delete");
        }
    }
    xhr.send(null);
}

let putRequest = (urlPath, body, callback) => {
    let xhr = new XMLHttpRequest();
    xhr.open("PUT", urlPath, true);
    xhr.onload = function() {
        if(xhr.status == "202" || xhr.status == "200"){
            window.alert("Successfully Updated");
            callback();
        } else {
            window.alert("Could not update");
        }
    }
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send(JSON.stringify(body));
}

let postRequest = (urlPath, body, callback) => {
    let xhr = new XMLHttpRequest();
    //Change to put to see if that will work better. weird behavior with true post
    xhr.open("POST", urlPath, true);
    xhr.onload = function() {
        callback(JSON.parse(this.responseText));
    }
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send(JSON.stringify(body));
}

// Clear all child nodes from components on page. Contents can be replaced with a message.
let clearAllNodes = (parent, messageText) => {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
    if(messageText){
        let message = document.createElement('p');
        message.innerHTML = messageText;
        parent.appendChild(message);
    }   
}

