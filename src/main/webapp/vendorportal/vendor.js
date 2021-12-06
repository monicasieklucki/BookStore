// Main JS file to run vendor service workflows from the client side.
let baseUri = 'https://book-store-luc.herokuapp.com/';
//let baseUri = 'http://localhost:8080/'

let $ = (id) => {
    return document.getElementById(id);
}

/*******************************************************************
 * RESPONSE HANDLERS
 * These would be the call back functions on different responses.
 */
// Displays the vendor context
let displayVendorContext = (responseJson) => {
    clearAllNodes($('vendorDetails'));
    console.log(responseJson);
    let vendContext = $('vendorDetails');
    let vendorText = document.createElement('p');
    vendorText.appendChild(document.createTextNode(`Vendor ID: \t${responseJson.vendorId}`));
    vendContext.appendChild(vendorText);
    vendorText = document.createElement('p');
    vendorText.appendChild(document.createTextNode(`Vendor Name:\t${responseJson.vendorName}`));
    vendContext.appendChild(vendorText);
    clearAllNodes($("contextControls"), manageVendorControls); 
    $('refreshVendor').addEventListener('click', () => {
        clearAllNodes($('workspace'));
        getRequest(responseJson.links[1].href, displayVendorContext) 
    })
    $('deleteVendor').addEventListener("click", () => {
        deleteRequest(responseJson.links[1].href, () => {
            clearAllNodes($('vendorDetails'), "Select a vendor to begin.");
            clearAllNodes($('workspace'));
            clearAllNodes($('contextControls'), findVendorControls);
            setupFindControls();
        })
    })
    $('manageOrders').addEventListener('click', () => {
        getRequest(responseJson.links[2].href, displayVendorOrders);
    })
    clearAllNodes($('workspace'));
    displayAllProducts(responseJson);
}

let displayAllProducts = (responseJson) => {
    let workItems = $('workspace').appendChild(document.createElement('ul'));
    responseJson.vendorLines.forEach(vl => {
        let item = workItems.appendChild(document.createElement('li'));
        let detail = item.appendChild(document.createElement('pre'));
        detail.innerHTML = `Product ID:\t${vl.productId}\n` +
            `Product Name:\t${vl.productName}\n` +
            `Quantity:\t${vl.quantity}\n` +
            `<button id="deleteprodid${vl.productId}">Delete Product</button>`;
        let buttonid = "deleteprodid" + vl.productId;
        $(buttonid).addEventListener('click', () => {
            console.log(vl.links[1].href);
            deleteRequest(vl.links[1].href, function (workItems, item) {
                    console.log("is this being ran?");
                    workItems.removeChild(item);
                })
        })

    })
}

//Displays all vendors and button to update context
let displayAllVendors = (responseJson) => {
    console.log("Displaying all Vendors");
    console.log(responseJson);
    let workItems = $('workspace').appendChild(document.createElement('ul'));
    responseJson.vendors.forEach(vendor => {
        //Display content
        let item = workItems.appendChild(document.createElement('li'));
        let itemDetails = item.appendChild(document.createElement('pre'));
        itemDetails.appendChild(
            document.createTextNode(`Vendor Id:\t${vendor.vendorId}\n` +
            `Vendor Name: \t${vendor.vendorName}\n`));
        //Display link options
        let setContextBttn = item.appendChild(document.createElement('button'));
        setContextBttn.innerHTML = "Set as Vendor Context";
        setContextBttn.addEventListener('click', () => {
            clearAllNodes($('vendorDetails'));
            getRequest(vendor.links[0].href, displayVendorContext);
        })
    });
}

let displayVendorOrders = (responseJson) => {
    console.log("Displaying All Orders");
    console.log(responseJson);
    clearAllNodes($('workspace'));
    let workItems = $('workspace').appendChild(document.createElement('ul'));
    responseJson.orders.forEach(order => {
        let item = workItems.appendChild(document.createElement('li'));
        let itemDetails = item.appendChild(document.createElement('pre'));
        itemDetails.innerHTML=`Order Id:\t${order.orderId}\n` +
            `Order Status: \t${order.orderState}\n` +
            `Customer Id:\t${order.customerId}\n`;
            let button = item.appendChild(document.createElement('button'));
            button.innerHTML = "Ship Order";
            let urlPath;
            order.links.forEach(link => {
                if(link.rel.includes("ship")){
                    urlPath = link.href;
                }
            })
            itemDetails.appendChild(button);
            button.addEventListener('click', () => {
                console.log("link for each running")
                order.links.forEach(link => {
                    if(link.rel.includes("ship")){
                        putRequest(link.href);
                    }
                });
                
            })
    })
    
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
    xhr.send(null);
}

let deleteRequest = (urlPath, callback) => {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", urlPath, true);
    xhr.onload = function () {
        if(xhr.status == "200") {
            window.alert("Successfully deleted");
            callback;
        }else{
            window.alert("Unable to delete");
        }
    }
    xhr.send(null);
}

let putRequest = (urlPath, callback) => {
    let xhr = new XMLHttpRequest();
    xhr.open("PUT", urlPath, true);
    xhr.onload = function() {
        if(xhr.status == "202" || xhr.status == "200"){
            window.alert("Successfully Updated");
            callback;
        } else {
            window.alert("Could not update");
        }
    }
    xhr.send(null);
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

/**********************************************************************
 * +
 * Not an ideal set up but saving html for building the context controls
 */
let findVendorControls = `<label for="searchId">Enter Vendor ID</label>
<input type="number" name="searchId" id="searchId">
<button id="setVendorBySearchId">Set Vendor</button>
<button id="searchVendorList">Search Vendors</button>`;

let setupFindControls = () => {
    $('setVendorBySearchId').addEventListener('click', () =>{
        clearAllNodes($('vendorDetails'));
        getRequest(`${baseUri}service/vendorservice/vendor/${$('searchId').value}`,displayVendorContext);
    })
    $('searchVendorList').addEventListener('click', () => {
        getRequest(`${baseUri}service/vendorservice/vendor`, displayAllVendors);
    })
} 

let manageVendorControls = `<button id="deleteVendor">Delete Vendor</button>
<button id="modifyVendor">Modify Vendor</button>
<button id="refreshVendor">RefreshVendor</button>
<button id="manageProducts">Manage Products</button>
<button id="manageOrders">Manage Orders</button>
<button id="addProduct">Add Product</button>`;


/********************************************************************
 * Initial page setup
 * These are the function to run on load of the page
 */
// Clears and resets the application
$('clearVendorContext').addEventListener('click', () => {
    clearAllNodes($('vendorDetails'), "Select a vendor to begin.");
    clearAllNodes($('workspace'));
    clearAllNodes($('contextControls'), findVendorControls);
    setupFindControls();

} )
setupFindControls();