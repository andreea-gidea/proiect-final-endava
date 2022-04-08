insert into USERS (user_name, password) values ('MiohaiIonel','$2a$12$KvjUvS48/YT9.hI7bQsw6uK6e0c2kSPF7TubxucRfpDDsd/Mke8uu');
insert into USERS (user_name, password) values ('Francisc','$2a$12$3/uezhtqsnv/cApZTHK.t.DNG0ftDu3/DX8IB8.hXn5CYT8ASLK5m');
insert into USERS (user_name, password) values ('Adidascomp','$2a$12$3/uezhtqsnv/cApZTHK.t.DNG0ftDu3/DX8IB8.hXn5CYT8ASLK5m');
insert into USERS (user_name, password) values ('Moisescu','$2a$12$3/uezhtqsnv/cApZTHK.t.DNG0ftDu3/DX8IB8.hXn5CYT8ASLK5m');

insert into PRODUCTS ( product_description, product_name) values('incaltaminte dama, piele, siret alb','adidas');
insert into PRODUCTS ( product_description, product_name) values('incaltaminte dama, textil, siret negru','adidas');
insert into PRODUCTS ( product_description, product_name) values('incaltaminte dama, piele','pantof');
insert into PRODUCTS ( product_description, product_name) values('incaltaminte dama, textil, snur matase','sandale');


insert into authorities (name, user_id) values ('ROLE_ADMIN',1);
insert into authorities (name, user_id) values ('ROLE_INDIVIDUAL_CLIENT',2);
insert into authorities (name, user_id) values ('ROLE_COMPANY_CLIENT',3);
insert into authorities (name, user_id) values ('ROLE_SUPPLIER',4);