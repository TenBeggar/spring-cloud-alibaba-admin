DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20) NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11) NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint       NOT NULL COMMENT '主键',
    `username`    varchar(255) NOT NULL COMMENT '账号',
    `password`    varchar(255) COMMENT '密码',
    `nickname`    varchar(255) COMMENT '昵称',
    `sex`         enum('M','W','U') NOT NULL DEFAULT 'U' COMMENT '性别',
    `age`         int COMMENT '年龄',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_user` bigint COMMENT '创建人',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `update_user` bigint COMMENT '修改人',
    `version`     int                   DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `user_username_unique`(`username` ASC)
) COMMENT '用户表';
