alter table users add active tinyint(1);
update users set active = 1;
alter table users modify active tinyint(1) not null;