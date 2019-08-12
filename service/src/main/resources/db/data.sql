insert into tb_product(id, name, cover_image, price) values (1, '测试商品-001', '/products/cover/001.png', 100);
insert into tb_product(id, name, cover_image, price) values (2, '测试商品-002', '/products/cover/002.png', 100);
insert into tb_product(id, name, cover_image, price) values (3, '测试商品-003', '/products/cover/003.png', 100);
insert into tb_product(id, name, cover_image, price) values (4, '测试商品-004', '/products/cover/004.png', 100);
insert into tb_product(id, name, cover_image, price) values (5, '测试商品-005', '/products/cover/005.png', 100);

insert into tb_user(id, nickname, avatar) values (1, 'zhangsan', '/users/avatar/zhangsan.png');
insert into tb_user(id, nickname, avatar) values (2, 'lisi', '/users/avatar/zhangsan.png');
insert into tb_user(id, nickname, avatar) values (3, 'wangwu', '/users/avatar/zhangsan.png');
insert into tb_user(id, nickname, avatar) values (4, 'zhaoliu', '/users/avatar/zhangsan.png');

insert into tb_product_comment (id, product_id, author_id, content, created) values (1,3,1, '非常不错的商品', CURRENT_TIMESTAMP());
insert into tb_product_comment (id, product_id, author_id, content, created) values (2,3,3, '非常不错的商品+1', CURRENT_TIMESTAMP());
insert into tb_product_comment (id, product_id, author_id, content, created) values (3,3,4, '哈哈，谁用谁知道', CURRENT_TIMESTAMP());