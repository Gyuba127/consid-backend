create table category (id integer not null, category_name varchar(255), primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table library_item (id integer not null, author varchar(255) not null, borrow_date datetime(6), borrower varchar(255), is_borrowable bit not null, pages integer, run_time_minutes integer, title varchar(255) not null, type varchar(255) not null, category_id integer not null, primary key (id)) engine=InnoDB;
alter table category add constraint UK_lroeo5fvfdeg4hpicn4lw7x9b unique (category_name);
alter table library_item add constraint FKckp7mwuhf562bbixg7dm3eyo9 foreign key (category_id) references category (id);
