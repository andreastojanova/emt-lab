CREATE table countries(
   id bigserial primary key ,
   name varchar(255) not null,
   continent varchar(255) not null ,
   created_at timestamp not null,
   updated_at timestamp not null
);
