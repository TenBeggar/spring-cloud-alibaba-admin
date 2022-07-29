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

DROP TABLE IF EXISTS `produce`;
CREATE TABLE `produce`
(
    `id`          bigint(20) NOT NULL COMMENT '主键',
    `record_time` datetime COMMENT '时间',
    `department`  varchar(128) COMMENT '生产单位',
    `area`        varchar(128) COMMENT '工作面',
    `unit`        varchar(8) COMMENT '单位',
    `plan_coal`   int(11) COMMENT '计划产量',
    `real_coal`   int(11) COMMENT '实际产量',
    PRIMARY KEY (`id`)
) COMMENT '产量表';