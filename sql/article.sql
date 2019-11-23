-- auto-generated definition
create table article
(
    id          bigint auto_increment comment '文章id'
        primary key,
    title       char(20)                             not null comment '文章标题',
    description char(100)                            not null comment '文章描述',
    content     varchar(255)                         not null comment '文章内容',
    create_time datetime   default CURRENT_TIMESTAMP not null comment '创造时间',
    update_time datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag tinyint(1) default 0                 not null comment '删除标志'
);