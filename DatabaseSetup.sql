set transaction read write; 

 /*set up customer */
drop table if exists Customer Cascade;
create table Customer  (
  customerId serial primary key,
  lastName varchar(64),
  firstName varchar(64),
  billingAddress int references Address,
  shippingAddress int references Address
);
insert into Customer values (
  default,
  'Panchot',
  'Shane',
  null,
  null
);

/*set up address*/
drop table if exists Address CASCADE;
create table Address (
  addressId serial primary key, 
  street varchar(32),
  unit varchar(12),
  city varchar(32),
  state char(2),
  zip varchar(5)
);
insert into Address values (
  default,
  '555 W Cornelia Ave', 
  'Apt 501',
  'Chicago',
  'IL',
  '60657'
);

/*set up vendor */
drop table if exists Vendor cascade;
create table Vendor (
  vendorId serial primary key, 
  vendorName varchar(128)
);
insert into Vendor values (
  default,
  'Ace Books'
);
insert into Vendor values (
  default, 
  'Logos Bookstore'
);

/*seet up product*/
drop table if exists Product cascade;
create table Product (
  productid serial primary key,
  title varchar not null,
  price float
);
insert into Product values (
  default,
  'Harry Potter and the Sorcer''s Stone',
  24.99
);
insert into Product values (
  default,
  'Desperate Characters',
  9.99
);
insert into product values (
  default,
  'Don Quixote',
  14.99
);

/*set up vendor line*/
drop table if exists vendorLine cascade;
create table vendorLine (
  vendorId serial references Vendor not null,
  productId serial references Product not null,
  quantity int default 0
);
insert into vendorLine values (1,1,15);
insert into vendorLine values (2,2,6);
insert into vendorLine values (2,3,11);

/*set up order*/
drop table if exists orders cascade;
create table orders (
	orderId serial primary key,
	paymentRec boolean default false,
	orderState varchar default 'Open'
);
insert into orders values(default, default, default);

/*set up order line*/
drop table if exists orderLine cascade;
create table orderLine (
	orderID serial references orders not null,
	productId serial references product not null,
	quantity int
);

commit;

select v.vendorName, p.title, p.price, vl.quantity
from vendorLine vl
join vendor v on vl.vendorId = v.vendorId
join product p on vl.productId = p.id;

