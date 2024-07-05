create table profiles(

    id bigint not null auto_increment,
    name varchar(100) not null,
    active tinyint(1) not null,

    primary key(id)

);