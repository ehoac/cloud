drop table if exists tb_product;
drop table if exists tb_product_comment;
drop table if exists tb_user;

create table tb_product(
  id int unsigned not null auto_increment comment '主键',
  name varchar(100) comment '商品名称',
  cover_image varchar(100) comment '商品封面图片',
  price int not null default 0 comment '商品价格（分）',
  primary key(id)
)