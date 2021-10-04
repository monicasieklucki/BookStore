# Bookstore
## About

The BookStore application is a simple representation of an online bookstore. Our project uses a Heroku Postgresql database. There are a few different types of classes used in our architecture

* We have models that represent the different types of data used by our application.(Customers, Vendors, Products, Orders) The Order model also includes some business logic to validate the different order state changes. 
* The DAL package contains the Data Access classes that manage creating, updating, deleting and querying data from the database. 
* The three services classes are intended to be the interface we will interact with in the future. These classes act as a facade and previde simple methods for ineteracting with the project combining but the DAO and models.

## Requirements:
For the database driver, we are using postgresql-42.2.24.

## Running The Program
* At this time, the different services can be validated with the classes in the test package. These run through simple steps we might expect to see when exposing our API in future projects. 
