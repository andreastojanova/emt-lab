
create table authors(
    id bigserial primary key ,
    name varchar(255) not null ,
    surname varchar(255) not null ,
    country_id bigint references countries(id),
    created_at timestamp not null,
    updated_at timestamp not null

);

