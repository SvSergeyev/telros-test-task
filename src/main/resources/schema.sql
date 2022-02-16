create table if not exists person
(
    id bigserial primary key ,
    name varchar(20) ,
    lastname varchar(25) ,
    patronymic varchar(20) ,
    date_of_birth date ,
    email varchar(25) ,
    phone varchar(10) ,
    login varchar(20) not null ,
    password varchar not null
);
