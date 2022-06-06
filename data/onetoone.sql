create table serial_number(
id serial primary key,
number varchar(255)
);

create table motor(
id serial primary key,
names varchar(255),
serialnumber int references serial_number(id) unique
);