/*
Navicat MySQL Data Transfer

Source Server         : jdxLocal_192.168.196.132
Source Server Version : 50505
Source Host           : 192.168.196.132:3306
Source Database       : jdxTest

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-05-28 15:47:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for table_auto_id
-- ----------------------------
DROP TABLE IF EXISTS `table_auto_id`;
CREATE TABLE `table_auto_id` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(200) NOT NULL,
  `table_name` varchar(200) NOT NULL,
  `now_auto_id` bigint(20) NOT NULL,
  `add_count` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_project_table` (`project_name`,`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
