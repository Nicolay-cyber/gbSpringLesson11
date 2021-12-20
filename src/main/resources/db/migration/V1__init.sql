create table products
(
    id    integer not null
        constraint products_pk
            primary key autoincrement,
    title text    not null,
    cost  integer not null
);
create unique index products_id_uindex
    on products (id);

create unique index products_title_uindex
    on products (title);

create table users
(
    id integer not null
        constraint table_name_pk
            primary key autoincrement,
    username text not null,
    password text not null,
    email text not null
);

create unique index users_email_uindex
    on users (email);

create unique index users_id_uindex
    on users (id);


create table roles
(
    id integer not null
        constraint table_name_pk
            primary key autoincrement,
    name text not null
);

create unique index roles_id_uindex
    on roles (id);


create table users_roles
(
    user_id integer not null
        constraint users_roles_users_id_fk
            references users (id)
            on update cascade on delete cascade,
    role_id integer not null
        constraint users_roles_roles_id_fk
            references roles (id)
            on update cascade on delete cascade,
    constraint users_roles_pk
        primary key (user_id, role_id)
);


insert into roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN'), ('SUPER_ADMIN');

insert into users (username, password, email)
values
    ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');
INSERT INTO users (username, password, email)
VALUES ('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com');

INSERT INTO users (username, password, email)
VALUES ('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com');

INSERT INTO users (username, password, email)
VALUES ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

INSERT INTO users (username, password, email)
VALUES ('superadmin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'superadmin@gmail.com');


insert into users_roles (user_id, role_id)
values (1, 1);
insert into users_roles (user_id, role_id)
values (2, 1);
insert into users_roles (user_id, role_id)
values (3, 1);
insert into users_roles (user_id, role_id)
values (4, 2);
insert into users_roles (user_id, role_id)
values (5, 3);