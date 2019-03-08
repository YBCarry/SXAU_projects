/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : db_lab

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 09/03/2019 00:57:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_gonggao
-- ----------------------------
DROP TABLE IF EXISTS `t_gonggao`;
CREATE TABLE `t_gonggao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deletestatus` int(11) NOT NULL,
  `gtitle` varchar(255) DEFAULT NULL,
  `neirong` text,
  `shijian` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gonggao
-- ----------------------------
BEGIN;
INSERT INTO `t_gonggao` VALUES (4, 0, '实验室开放时间', '<p>周一到周五：</p>\r\n\r\n<p>上午：8:00-12:00</p>\r\n\r\n<p>下午：2:00-6:00</p>\r\n\r\n<p>晚上：6:30-10:00</p>\r\n\r\n<p>周末老师自行安排。</p>\r\n', '2019-01-16 18:21:47');
INSERT INTO `t_gonggao` VALUES (5, 0, '实验室寒假安排', '<p>寒假实验室暂停使用，特殊情况由相关教师在留言中具体说明。</p>\r\n', '2019-01-16 18:25:26');
COMMIT;

-- ----------------------------
-- Table structure for t_lab
-- ----------------------------
DROP TABLE IF EXISTS `t_lab`;
CREATE TABLE `t_lab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deletestatus` int(11) NOT NULL,
  `dianhua` varchar(255) DEFAULT NULL,
  `fenlei` varchar(255) DEFAULT NULL,
  `fzr` varchar(255) DEFAULT NULL,
  `jianjie` text,
  `leixing` varchar(255) DEFAULT NULL,
  `mingchen` varchar(255) DEFAULT NULL,
  `renshu` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lab
-- ----------------------------
BEGIN;
INSERT INTO `t_lab` VALUES (1, 0, '10000000000', '教师实验室', '李丽老师', '<p>仅用于化学实验</p>\r\n', '化学实验室', '化学实验室一', 0);
INSERT INTO `t_lab` VALUES (2, 0, '10000000000', '教师实验室', '张丽老师', '<p>仅用于生物实验</p>\r\n', '生物实验室', '生物实验室一', 0);
INSERT INTO `t_lab` VALUES (4, 0, '10000000000', '教师实验室', '胡伟老师', '<p>教师用化学实验室</p>\r\n', '化学实验室', '化学实验室二', 0);
INSERT INTO `t_lab` VALUES (5, 0, '10000000000', '教师实验室', '吕艳阳老师', '<p>教师实验室</p>\r\n', '软件实验室', '软件实验室二', 0);
INSERT INTO `t_lab` VALUES (6, 0, '10000000000', '教师实验室', '吕艳阳老师', '<p>Java实验室</p>\r\n', '软件实验室', '软件实验室一', 0);
INSERT INTO `t_lab` VALUES (7, 0, '10000000000', '学生实验室', '杜老师', '<p>注意保护实验设备</p>\r\n', '物理实验室', '物理实验室一', 10);
INSERT INTO `t_lab` VALUES (8, 0, '10000000000', '学生实验室', '冯老师', '<p>教师实验室</p>\r\n<p>注意保护实验室环境</p>\r\n', '软件实验室', '软件实验室一', 100);
COMMIT;

-- ----------------------------
-- Table structure for t_lab2
-- ----------------------------
DROP TABLE IF EXISTS `t_lab2`;
CREATE TABLE `t_lab2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deletestatus` int(11) NOT NULL,
  `dianhua` varchar(255) DEFAULT NULL,
  `fenlei` varchar(255) DEFAULT NULL,
  `fzr` varchar(255) DEFAULT NULL,
  `jianjie` text,
  `leixing` varchar(255) DEFAULT NULL,
  `mingchen` varchar(255) DEFAULT NULL,
  `renshu` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lab2
-- ----------------------------
BEGIN;
INSERT INTO `t_lab2` VALUES (1, 0, '10000000000', '学生实验室', '杜老师', '<p>注意保护实验设备</p>\r\n', '物理实验室', '物理实验室一', 10);
INSERT INTO `t_lab2` VALUES (2, 0, '10000000000', '学生实验室', '冯老师', '<p>教师实验室</p>\r\n<p>注意保护实验室环境</p>\r\n', '软件实验室', '软件实验室一', 100);
COMMIT;

-- ----------------------------
-- Table structure for t_liuyan
-- ----------------------------
DROP TABLE IF EXISTS `t_liuyan`;
CREATE TABLE `t_liuyan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deletestatus` int(11) NOT NULL,
  `huifu` text,
  `ltitle` varchar(255) DEFAULT NULL,
  `neirong` text,
  `shijian` varchar(255) DEFAULT NULL,
  `zhuangtai` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3B2747191433B443` (`userid`),
  CONSTRAINT `FK3B2747191433B443` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_liuyan
-- ----------------------------
BEGIN;
INSERT INTO `t_liuyan` VALUES (5, 1, NULL, '近期实验学习问题反馈', '<p>大家说说最近学习遇到的问题</p>\r\n', '2019-01-16 21:23:15', '未回复', 7);
INSERT INTO `t_liuyan` VALUES (6, 0, '<p>好的，近期安装</p>\r\n', '实验室环境反馈', '<p>建议实验室更新新版本的软件，方便学生操作使用。</p>\r\n', '2019-03-01 16:59:57', '已回复', 6);
INSERT INTO `t_liuyan` VALUES (7, 0, NULL, '建议反馈', '<p>建议为学生电脑部署自动录屏功能，以更好地记录学生实验课情况</p>\r\n', '2019-03-01 17:13:40', '未回复', 7);
COMMIT;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banji` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `dianhua` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `xingming` varchar(255) DEFAULT NULL,
  `xueyuan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
BEGIN;
INSERT INTO `t_student` VALUES (3, '软件1511', '2019-01-13 12:12:45', 0, '10000000000', '111111', 1, '20151612401', '白金', NULL);
INSERT INTO `t_student` VALUES (4, '软件1511', '2019-01-16 19:06:57', 0, '10000000000', '111111', 1, '20151612402', '马静茹', NULL);
INSERT INTO `t_student` VALUES (5, '软件1511', '2019-01-16 19:08:08', 0, '10000000000', '111111', 1, '20151612403', '赵莉', '');
INSERT INTO `t_student` VALUES (6, '软件1511', '2019-01-16 19:09:31', 0, '10000000000', '111111', 1, '20151612437', '段松启', '');
COMMIT;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banji` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `dianhua` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `xingming` varchar(255) DEFAULT NULL,
  `xueyuan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
BEGIN;
INSERT INTO `t_teacher` VALUES (7, NULL, '2019-01-16 19:14:48', 0, '10000000000', '111111', 2, 'teacher01', '吕艳阳', '软件学院');
INSERT INTO `t_teacher` VALUES (8, '', '2019-01-13 12:12:45', 0, '10000000000', '111111', 2, 'teacher02', '李丽', '文理学院');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banji` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `dianhua` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `xingming` varchar(255) DEFAULT NULL,
  `xueyuan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, NULL, NULL, 0, NULL, '123456', 0, 'ybcarry', '管理员', NULL);
INSERT INTO `t_user` VALUES (3, '软件1511', '2019-01-13 12:12:45', 0, '10000000000', '111111', 1, '20151612401', '白金', NULL);
INSERT INTO `t_user` VALUES (4, '软件1511', '2019-01-16 19:06:57', 0, '10000000000', '111111', 1, '20151612402', '马静茹', NULL);
INSERT INTO `t_user` VALUES (5, '软件1511', '2019-01-16 19:08:08', 0, '10000000000', '111111', 1, '20151612403', '赵莉', '');
INSERT INTO `t_user` VALUES (6, '软件1511', '2019-01-16 19:09:31', 0, '10000000000', '111111', 1, '20151612437', '段松启', '');
INSERT INTO `t_user` VALUES (7, NULL, '2019-01-16 19:14:48', 0, '10000000000', '111111', 2, 'teacher01', '吕艳阳老师', '软件学院');
INSERT INTO `t_user` VALUES (8, '', '2019-01-13 12:12:45', 0, '10000000000', '111111', 2, 'teacher02', '李丽老师', '文理学院');
INSERT INTO `t_user` VALUES (9, '软件1511', '2019-03-01 17:07:39', 0, '10000000000', '111111', 1, '20151612439', '方行', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_yuyue
-- ----------------------------
DROP TABLE IF EXISTS `t_yuyue`;
CREATE TABLE `t_yuyue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deletestatus` int(11) NOT NULL,
  `fankui` text,
  `j1` bigint(20) NOT NULL,
  `jieshu` varchar(255) DEFAULT NULL,
  `riqi` varchar(255) DEFAULT NULL,
  `s1` bigint(20) NOT NULL,
  `shenhe` varchar(255) DEFAULT NULL,
  `shijian` varchar(255) DEFAULT NULL,
  `shiyong` varchar(255) DEFAULT NULL,
  `shuoming` text,
  `sjd` varchar(255) DEFAULT NULL,
  `labid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9F8C6C826B7A7863` (`labid`),
  KEY `FK9F8C6C821433B443` (`userid`),
  CONSTRAINT `FK9F8C6C821433B443` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK9F8C6C826B7A7863` FOREIGN KEY (`labid`) REFERENCES `t_lab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_yuyue
-- ----------------------------
BEGIN;
INSERT INTO `t_yuyue` VALUES (5, 0, NULL, 1547870400000, '2019-01-19 12:00', NULL, 1547856000000, '审核中', '2019-01-16 20:13:02', '2019-01-19 08:00', '<p>课题研究</p>\r\n', NULL, 6, 7);
INSERT INTO `t_yuyue` VALUES (6, 0, '', 0, NULL, '2019-3-5', 0, '审核通过', '2019-03-01 16:58:17', NULL, '', '下午4点到5点半', 8, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
