-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sparrowadmin
-- ------------------------------------------------------
-- Server version	5.7.14

-- -----------------------------------------------------
-- Schema petshow
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sparrowadmin`;
CREATE SCHEMA IF NOT EXISTS `sparrowadmin` DEFAULT CHARACTER SET UTF8;
USE `sparrowadmin`;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '用户',
  `title` varchar(300) DEFAULT NULL COMMENT '日志',
  `url` varchar(300) DEFAULT NULL COMMENT '地址',
  `params` varchar(1000) DEFAULT NULL COMMENT '参数',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `pid` varchar(50) NOT NULL COMMENT '父级菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
  `url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `deep` int(11) DEFAULT NULL COMMENT '深度',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('1101aca29a9dbe0a9e6eb03fe72abb0d','5d283766dbe1b250926904ea6339900d','角色管理',0,'/system/role/list/1','fa-users',2,2,'0102','role',NULL,NULL),('28521bb4e1e11d1784b4f212282e13f0','5d283766dbe1b250926904ea6339900d','系统监控',0,'/system/monitor/list','fa-eye',7,2,'0107',NULL,NULL,NULL),('2fdc10b0c62f1602e08acacbc7aa78ca','8bb1552c916f4ca7145d905b5eadf9b1','新增用户',0,NULL,NULL,1,3,'010101','addUser',NULL,NULL),('32c7724ad38e746fb52a6b882137e9cb','1101aca29a9dbe0a9e6eb03fe72abb0d','批量删除角色',0,NULL,NULL,5,3,'010205','deleteBatchRole',NULL,NULL),('3ba2695bb7f934d2ff36ba450a9d6a3d','47d8f55290f7de0582228aaf38089243','删除菜单',0,NULL,NULL,3,3,'010303','deleteMenu',NULL,NULL),('420d72eb74ade02390e9a2c131577031','47d8f55290f7de0582228aaf38089243','创建菜单',0,NULL,NULL,1,3,'010301','addMenu',NULL,NULL),('452154c770b1d29d1d7289db1f200c21','1101aca29a9dbe0a9e6eb03fe72abb0d','删除角色',0,NULL,NULL,3,3,'010203','deleteRole',NULL,NULL),('47d8f55290f7de0582228aaf38089243','5d283766dbe1b250926904ea6339900d','菜单管理',0,'/system/menu/list/1','fa-list',3,2,'0103','menu',NULL,NULL),('5d283766dbe1b250926904ea6339900d','0','系统管理',0,NULL,'fa fa-cogs',1,1,'01',NULL,NULL,NULL),('623704459748f4ca30690968074a3619','1101aca29a9dbe0a9e6eb03fe72abb0d','查看角色列表',0,NULL,NULL,0,3,'010200','listRole',NULL,NULL),('62f9244a76a3181acdbeb4f23f97b485','e1d14917b7d5d7acb5aba5bd38b68d36','查看菜单列表',0,NULL,NULL,0,3,'010300','listMenu',NULL,NULL),('6d2b8caf3c59cf9101fe86bdd0f12806','e1d14917b7d5d7acb5aba5bd38b68d36','查询系统配置',0,NULL,NULL,0,3,'010600','listSetting',NULL,NULL),('7174826bfcb81a907b4c6c7baf85fafa','8bb1552c916f4ca7145d905b5eadf9b1','查看用户列表',0,NULL,NULL,0,3,'010100','listUser',NULL,NULL),('8378178f752982e3f61641f19bde153d','47d8f55290f7de0582228aaf38089243','编辑菜单',0,NULL,NULL,2,3,'010302','editMenu',NULL,NULL),('89ee44bb6c3390ce278e9cb032404e6a','8bb1552c916f4ca7145d905b5eadf9b1','编辑用户',0,NULL,NULL,2,3,'010102','editUser',NULL,NULL),('8ad25d30078368b12f69fdaefac1456d','1101aca29a9dbe0a9e6eb03fe72abb0d','编辑角色',0,NULL,NULL,2,3,'010202','editRole',NULL,NULL),('8bb1552c916f4ca7145d905b5eadf9b1','5d283766dbe1b250926904ea6339900d','用户管理',0,'/system/user/list/1','fa-user-circle-o',1,2,'0101','user',NULL,NULL),('8cdf6177816ab9c7522711c2f7cbfc0d','28521bb4e1e11d1784b4f212282e13f0','监控列表',0,NULL,NULL,1,3,'010701','monitorList',NULL,NULL),('900c34599d28933db44de2fcc4a2db17','8bb1552c916f4ca7145d905b5eadf9b1','删除用户',0,NULL,NULL,3,3,'010103','deleteUser',NULL,NULL),('a3fbf503038050a8f64ccdca21115b1a','5d283766dbe1b250926904ea6339900d','业务日志',0,'/system/log/list/1','fa-info-circle',5,2,'0105','log',NULL,NULL),('a9caf91bd3ad7249787e5bfc13454f7b','1101aca29a9dbe0a9e6eb03fe72abb0d','角色授权',0,NULL,NULL,4,3,'010204','authRole',NULL,NULL),('b4f04dfcbfb6746ab19b661bb8784b44','a3fbf503038050a8f64ccdca21115b1a','查看日志列表',0,NULL,NULL,0,3,'010500','listLog',NULL,NULL),('ca8e1246414013e2a7a9a3b89f17ff20','47d8f55290f7de0582228aaf38089243','操作系统设置',0,NULL,NULL,1,3,'010601','doSetting',NULL,NULL),('e1d14917b7d5d7acb5aba5bd38b68d36','5d283766dbe1b250926904ea6339900d','系统配置',0,'/system/setting/page','fa-cog',6,2,'0106','setting',NULL,NULL),('f0c42cfe187698b3d0bb75c75780d0ed','1101aca29a9dbe0a9e6eb03fe72abb0d','新增角色',0,NULL,NULL,1,3,'010201','addRole',NULL,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `role_name` varchar(30) NOT NULL COMMENT '角色',
  `role_desc` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `role_status` tinyint(3) unsigned DEFAULT '0' COMMENT '状态,0:启用,1:禁用',
  `sort` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('8720b276fefb096ae6dbdf108197cf9c','guestGroup','访问者',0,0,'2019-09-18 23:38:01',NULL),('a67af9f88e424225585b37a193b561d5','SuperGroup','超级管理员',0,0,NULL,'2019-09-18 22:58:02');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `role_id` varchar(50) NOT NULL COMMENT '角色主键',
  `menu_id` varchar(50) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES ('2bb0962240701056de91a71ff7402824','a67af9f88e424225585b37a193b561d5','e1d14917b7d5d7acb5aba5bd38b68d36'),('3e825ab046b3e75158c2d2c877297d07','a67af9f88e424225585b37a193b561d5','1101aca29a9dbe0a9e6eb03fe72abb0d'),('3ecd4f53f90d7ce1dc73d7239767c85b','a67af9f88e424225585b37a193b561d5','62f9244a76a3181acdbeb4f23f97b485'),('4cbaaa03a479ed9f78eee45bf8eb0bbe','a67af9f88e424225585b37a193b561d5','ca8e1246414013e2a7a9a3b89f17ff20'),('531b0c20bbd0433756a0bccf3fcb4806','a67af9f88e424225585b37a193b561d5','b4f04dfcbfb6746ab19b661bb8784b44'),('5454312e8ad19b68f1ed29baf1a356ce','a67af9f88e424225585b37a193b561d5','452154c770b1d29d1d7289db1f200c21'),('561c78025c9dd6e350c015a606962dad','a67af9f88e424225585b37a193b561d5','8bb1552c916f4ca7145d905b5eadf9b1'),('5789094a9faad41a7c910376b6f0ef2f','a67af9f88e424225585b37a193b561d5','8378178f752982e3f61641f19bde153d'),('64c53ab3cf48447acb5ad1d14d62aef5','a67af9f88e424225585b37a193b561d5','32c7724ad38e746fb52a6b882137e9cb'),('6f723cfa7cbc7c5ae0e7a7c4a0a4555a','a67af9f88e424225585b37a193b561d5','47d8f55290f7de0582228aaf38089243'),('7ad5175c09a9742ddc93fe4fe44246bd','a67af9f88e424225585b37a193b561d5','900c34599d28933db44de2fcc4a2db17'),('7fa882de3fc998b1131d30a8b4d3b8a5','a67af9f88e424225585b37a193b561d5','5d283766dbe1b250926904ea6339900d'),('88eff4c00566205ea80e94c79578bf1c','a67af9f88e424225585b37a193b561d5','a9caf91bd3ad7249787e5bfc13454f7b'),('91e1cd662be7da9f6487526625d2e9c7','a67af9f88e424225585b37a193b561d5','623704459748f4ca30690968074a3619'),('9f5aedbb7779360e6b9d0f59dbf5888f','a67af9f88e424225585b37a193b561d5','8ad25d30078368b12f69fdaefac1456d'),('a67e2704feb4aa2f4523209d6bde92e3','a67af9f88e424225585b37a193b561d5','7174826bfcb81a907b4c6c7baf85fafa'),('b4fb887d788f0e3362190a5a33756697','a67af9f88e424225585b37a193b561d5','f0c42cfe187698b3d0bb75c75780d0ed'),('bb8787f9205e02204e516813f7e3db90','a67af9f88e424225585b37a193b561d5','28521bb4e1e11d1784b4f212282e13f0'),('c7faad74d494c238d561a3c63462a8ad','a67af9f88e424225585b37a193b561d5','89ee44bb6c3390ce278e9cb032404e6a'),('c9909a403ab0a01dda539846c043f90d','a67af9f88e424225585b37a193b561d5','3ba2695bb7f934d2ff36ba450a9d6a3d'),('d04a06d4f2f0d65a93124747cb239a97','a67af9f88e424225585b37a193b561d5','8cdf6177816ab9c7522711c2f7cbfc0d'),('d12cb2e6374a8bc47f8385dff40b2257','a67af9f88e424225585b37a193b561d5','420d72eb74ade02390e9a2c131577031'),('d861a5e1cee86baaac4aaa5d6d3efe90','a67af9f88e424225585b37a193b561d5','6d2b8caf3c59cf9101fe86bdd0f12806'),('e307fede7f9b022fd635a429383bbd33','a67af9f88e424225585b37a193b561d5','a3fbf503038050a8f64ccdca21115b1a'),('fe146bde70b771b89e01705760f0721d','a67af9f88e424225585b37a193b561d5','2fdc10b0c62f1602e08acacbc7aa78ca');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_setting`
--

DROP TABLE IF EXISTS `sys_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_setting` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `sys_name` varchar(50) NOT NULL COMMENT '名称',
  `sys_key` varchar(50) NOT NULL COMMENT 'KEY',
  `sys_value` varchar(300) DEFAULT NULL COMMENT '值',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `sys_desc` varchar(300) DEFAULT NULL COMMENT '说明',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_setting`
--

LOCK TABLES `sys_setting` WRITE;
/*!40000 ALTER TABLE `sys_setting` DISABLE KEYS */;
INSERT INTO `sys_setting` VALUES ('13d8835da6bbadbc7f1f7ede3d56f007','系统简称','systemSubName','后台管理系统',1,NULL,NULL,NULL),('1f39a764682b8cb04d26180a239e1f78','许可说明','bottomCopyright','Copyright © 2020 think.com. All rights reserved.',2,NULL,NULL,NULL),('6b2fa06c54efaf143ef466b4d48b023f','系统名称','systemName','SparrowAdmin',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_name` varchar(40) NOT NULL COMMENT '用户名',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `user_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '用户状态,0:启用,1:禁用',
  `user_desc` varchar(300) DEFAULT NULL COMMENT '描述',
  `avatar` varchar(300) DEFAULT '/app/img/photo1.png' COMMENT '头像',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('999999999999999999','visitor','934bca3db40bcd8e681e252173f69430',0,'DO NOT DELETE','/app/img/photo1.png','2019-09-18 23:39:40',NULL),('b51da60b6fa2843ad4068798c9b5f5f3','admin','038bdaf98f2037b31f1e75b5b4c9b26e',0,NULL,'/upload/2019-09-16/kt70fxz4tydl3ldhofbzmnuneo2uqua0.jpg','2019-09-05 12:40:29','2019-09-05 12:40:29');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('244cef2208269abd2c79a75bf12265fd','999999999999999999','8720b276fefb096ae6dbdf108197cf9c'),('d2453b4827a002da1b5b19321fe88955','b51da60b6fa2843ad4068798c9b5f5f3','a67af9f88e424225585b37a193b561d5');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-19 14:31:27
