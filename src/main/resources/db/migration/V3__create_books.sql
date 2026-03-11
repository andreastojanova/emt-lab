create table books(
     id bigserial primary key ,
     name varchar(255) not null ,
     category varchar(255) not null ,
     author_id bigint references authors(id),
     available_copies integer,
     state varchar(50),
     created_at timestamp not null,
     updated_at timestamp not null

);