DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `userNameCn` varchar(100) NOT NULL COMMENT '中文名',
  `userNameEn` varchar(100) NOT NULL COMMENT '英文名',
  `enabled` CHAR(1) NOT NULL DEFAULT '1' COMMENT '是否可用\n1：可用\n0：停用',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` DATETIME NULL COMMENT '创建时间',
  `update_date` DATETIME NULL COMMENT '更新时间',
  `del_flag` CHAR(1) NULL DEFAULT 0 COMMENT '删除标记\n1：删除\n0：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_user` VALUES ('1', 'Admin', '123', '超级管理员','administrator','1', '超级管理员',"","", '0');