# Bookstore
## Front End Demonstrations
We split up the the work in our group for the frontend with a few different examples of how our API could be consumed. Below is a link to each with some information on how to run each.
### Vendor Portal
* https://book-store-luc.herokuapp.com/frontend/vendorportal/index.html
* The vendor portal is intended to be used to manage the products for each vendor on the site and ship orders
* The vendor you want to work with must be set as the vendor context. This is done by entering the vendor id, selecting from the list of all vendors or creating a new vendor.
* The get request for the context vendor contains links to add products, manage existing products, modify the vendor name, and manage orders. Buttons are created based on these available actions.
* The button to delete each product are based on the links provided by the API.
* The button to ship an order are provided if appropriate based on the links provided with each order. After taking actions. You will have to reset the vendor context or press the correct action button at the top again to refresh the page and see the changes made. 
* You can use the clear button at the top to clear the context and start fresh

### Customer Portal 1
* https://book-store-luc.herokuapp.com/frontend/customerportal/index.html
* Similar set up to the vendor portal with only the most basic workflow actions to demonstrate this other element of the API.
* First set the vendor context. Currently the vendor Id must be known. Vendor ID 1 should work.
* After a customer is set, all products are returned. 
* For each product, there is a button to order that product. In this version of the frontend, only one product can be ordered at a time for simplicity.
* After the order is created, the page updates with the order details. Based on the HATEOAS links returned, there is a button to pay for that order.
* A window alert is displayed confirming the status of the update. 


## About

The BookStore application is a simple representation of an online bookstore. Our project uses a Heroku Postgresql database. There are a few different types of classes used in our architecture

* We have models that represent the different types of data used by our application.(Customers, Vendors, Products, Orders) The Order model also includes some business logic to validate the different order state changes. 
* The DAL package contains the Data Access classes that manage creating, updating, deleting and querying data from the database. 
* The three services classes are intended to be the interface we will interact with in the future. These classes act as a facade and previde simple methods for ineteracting with the project combining but the DAO and models.

## Requirements:
For the database driver, we are using postgresql-42.2.24.

## Running The Program
* At this time, the different services can be validated with the classes in the test package. These run through simple steps we might expect to see when exposing our API in future projects. 
