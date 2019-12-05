/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : park_user

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 15/11/2019 11:05:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` bigint(20) NOT NULL COMMENT '主键id',
  `AVATAR` varchar(255) DEFAULT NULL COMMENT '头像',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '密码',
  `SALT` varchar(32) DEFAULT NULL COMMENT 'md5密码盐',
  `NAME` varchar(32) DEFAULT NULL COMMENT '名字',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',
  `EMPLOYEE_NUMBER` varchar(32) DEFAULT NULL COMMENT '工号',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  `SEX` varchar(32) DEFAULT NULL COMMENT '性别(字典)',
  `EMAIL` varchar(32) DEFAULT NULL COMMENT '电子邮件',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '电话',
  `STATUS` int(4) DEFAULT NULL COMMENT '状态(字典)',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(20) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` bigint(20) DEFAULT NULL COMMENT '更新人',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `ENTRY_DATE` datetime DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `unique_email_1` (`EMAIL`) USING BTREE,
  UNIQUE KEY `unique_account_1` (`ACCOUNT`) USING BTREE COMMENT '账号唯一',
  UNIQUE KEY `unique_phone_1` (`PHONE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
