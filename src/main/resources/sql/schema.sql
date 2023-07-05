create table users (
  user_id      bigint not null,
  user_name_tx varchar(50) not null
);
CREATE UNIQUE INDEX users_pk ON users(user_id);
ALTER TABLE USERS ADD CONSTRAINT USERS_PK PRIMARY KEY (USER_ID);
CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1;

create table categories (
  cate_id bigint not null,
  cate_name_tx varchar(50) not null,
  cate_type_tx varchar(10) not null
);
create unique index categories_pk on categories(cate_id);
alter table categories add constraint categories_pk primary key(cate_id);
create sequence cate_seq start with 1 increment by 1;

create table items (
  item_id bigint not null,
  item_name_tx varchar(50) not null,
  cate_id bigint not null
);
create unique index items_pk on items(item_id);
alter table items add constraint items_pk primary key(item_id);
alter table items add constraint categories_fk foreign key(cate_id) references categories(cate_id);
create sequence item_seq start with 1 increment by 1;


create table documents (
  docu_id      bigint not null,
  docu_dt      date not null,
  acco_id      bigint not null,
  docu_type_tx varchar(10) not null,
  curr_cd      varchar(4),
  curr_rate_am decimal(10,6) default 1,
  paym_meth_tx varchar(15),
  cprt_id      bigint,
  invo_tx      varchar(50),
  crea_dt      timestamp,
  updt_dt      timestamp,
  vers_nm      int
);
CREATE UNIQUE INDEX documents_pk ON documents(docu_id);
ALTER TABLE documents ADD CONSTRAINT documents_pk PRIMARY KEY (docu_id);
ALTER TABLE documents ADD CONSTRAINT accounts_fk FOREIGN KEY (acco_id) REFERENCES users(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
CREATE SEQUENCE docu_seq start with 1 increment by 1;

create table document_items (
  ditm_id      bigint not null,
  ditm_dt      date not null,
  docu_id      bigint not null,
  crea_dt      timestamp,
  updt_dt      timestamp,
  vers_nm      int
);
CREATE UNIQUE INDEX document_items_pk ON document_items(ditm_id);
ALTER TABLE document_items ADD CONSTRAINT document_items_pk PRIMARY KEY(ditm_id);
alter table document_items add constraint document_fk foreign key(docu_id) references documents(docu_id);