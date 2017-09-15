DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '机型型号',
  `types` varchar(100) NOT NULL COMMENT '类别',
  `brand` varchar(100) NOT NULL COMMENT '品牌',
  `seats_no` int NOT NULL COMMENT '座位数',
  `ranges` int NOT NULL COMMENT '续航里程',
  `ranges_unit` varchar(100) NOT NULL COMMENT '里程单位',
  `speeds` int NOT NULL COMMENT '速度',
  `speeds_unit` varchar(100) NOT NULL COMMENT '速度单位',
  `loads` double NOT NULL COMMENT '最大载重',
  `loads_unit` varchar(100) NOT NULL COMMENT '最大载重单位',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '照片',
  `short_desc` varchar(4000) DEFAULT NULL COMMENT '简述',
  `description` varchar(4000) DEFAULT NULL COMMENT '详细描述',
  `manufacture` varchar(255) DEFAULT NULL COMMENT '制造商',
  `produce_date` date DEFAULT NULL COMMENT '生产日期',
  `price` double NOT NULL COMMENT '参考价',
  `price_unit` varchar(100) NOT NULL COMMENT '参考价单位',
  `pre_pay` double NOT NULL COMMENT '预付价',
  `pre_pay_unit` varchar(100) NOT NULL COMMENT '预付价单位',
  `create_by` varchar(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `data_dictionary`
--

DROP TABLE IF EXISTS `data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_dictionary` (
  `ID` varchar(36) NOT NULL COMMENT 'ID',
  `CODE` varchar(100) NOT NULL COMMENT '对象值',
  `TYPE` varchar(100) NOT NULL COMMENT '数据字典类型',
  `NAME` varchar(500) DEFAULT NULL COMMENT '对象值描述',
  `NAME_NUMBER` int(11) DEFAULT NULL COMMENT '对象序号',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(36) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_BY` varchar(36) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `performing`
--

DROP TABLE IF EXISTS `photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photos` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '照片',
  `description` varchar(4000) DEFAULT NULL COMMENT '介绍',
  `main_id` varchar(36) NOT NULL COMMENT '主表ID',
  `main_type` varchar(100) NOT NULL COMMENT '主表类别',
  `create_by` varchar(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` varchar(36) NOT NULL COMMENT 'id主键',
  `account` varchar(36) NOT NULL COMMENT '登陆帐号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `pwd` varchar(50) DEFAULT NULL COMMENT '登陆密码',
  `name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(50) DEFAULT NULL COMMENT '移动电话',
  `type` varchar(100) DEFAULT NULL COMMENT '用户类别',
  `is_available` char(1) NOT NULL DEFAULT 'Y' COMMENT '是否可用',
  `login_count` int(11) DEFAULT NULL COMMENT '登录总次数',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(36) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(36) DEFAULT NULL COMMENT '修改人',
  `is_admin` char(1) NOT NULL DEFAULT 'N' COMMENT '是否为管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

truncate table `people` ;
INSERT INTO `people` VALUES ('A0001','admin','admin','C33367701511B4F6020EC61DED352059','admin','18888888888','BAND','Y',NULL,NULL,'N','2015-08-24 18:30:33','2015-08-24 18:30:33','A0001','A0001','Y'),('A1001','boris.hou@oracle.com','boris.hou@oracle.com','202CB962AC59075B964B07152D234B70','Boris','18600182885','BAND','Y',NULL,NULL,'N','2015-09-06 11:01:29','2015-09-06 11:01:29',NULL,NULL,'N');

commit;
--
-- Table structure for table `tbl_key_map`
--

DROP TABLE IF EXISTS `tbl_key_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_key_map` (
  `tbl_name` varchar(50) NOT NULL COMMENT '表名主键',
  `key_prefix` varchar(10) DEFAULT NULL COMMENT '主键前缀',
  `key_nbr` int(11) DEFAULT NULL COMMENT '当前主键序号',
  PRIMARY KEY (`tbl_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `subject` varchar(200) NOT NULL COMMENT '标题',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '照片',
  `status` varchar(100) NOT NULL COMMENT '状态',
  `types` varchar(100) NOT NULL COMMENT '类别',
  `on_top` varchar(1) NOT NULL COMMENT '置顶',
  `description` varchar(4000) DEFAULT NULL COMMENT '详细描述',
  `create_by` varchar(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

