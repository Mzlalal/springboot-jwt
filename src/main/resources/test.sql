create table jwt_user (
  id number(9) unique,
  username varchar(100),
  password varchar(100)
)

comment on column JWT_USER.id
  is '流水';
comment on column JWT_USER.username
  is '用户名';
comment on column JWT_USER.password
  is '密码';

insert into jwt_user values (1,'admin','admin');
insert into jwt_user values (2,'root','root');
insert into jwt_user values (3,'yyy','yyy');

COMMIT;