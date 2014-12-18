
CREATE TABLE `register_phone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(50) NOT NULL,
  `regist_time` datetime NOT NULL COMMENT '注册时间',
  `status` tinyint(2) DEFAULT 0 COMMENT '1:已注册 0:导入但没有注册',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `person_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` int(2) NOT NULL COMMENT '1:男,2女',
  `head_pic` varchar(20) DEFAULT NULL,
  `current_company` varchar(50) DEFAULT NULL COMMENT '当前所在公司',
  `current_position` varchar(50) DEFAULT NULL COMMENT '当前所在职位',
  `phone_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `person_connection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `master_phone_id` bigint(100) NOT NULL COMMENT '电话id,来自表register',
  `slaver_phone_id` bigint(100) NOT NULL COMMENT '电话id,来自表register',
  `create_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY master_slaver_index(`master_phone_id`,`slaver_phone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

