create table tb_record(
  id int unsigned not null auto_increment comment '主键',
  ip varchar(50) comment 'ip',
  path varchar(200) comment 'path',
  create_time TIMESTAMP comment 'time',
  primary key(id)
);