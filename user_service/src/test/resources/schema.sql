alter table if exists review drop constraint if exists FKj8m0asijw8lfl4jxhcps8tf4y;

drop table if exists review cascade;

drop table if exists "user" cascade;

create table review (
    created_date timestamp(6),
    id bigserial not null,
    last_modified_date timestamp(6),
    restaurant_id varchar(20) not null,
    user_id bigint,
    rate varchar(50) not null check (rate in ('ONE','TWO','THREE','FOUR','FIVE')),
    comment varchar(400) not null,
    primary key (id)
);

create table "user" (
    latitude float(53) not null,
    longitude float(53) not null,
    created_date timestamp(6),
    id bigserial not null,
    last_modified_date timestamp(6),
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    primary key (id)
);

alter table if exists review add constraint FKj8m0asijw8lfl4jxhcps8tf4y foreign key (user_id) references "user";