/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : legendcarry

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2019-03-08 17:31:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL default '',
  `level` int(11) NOT NULL default '0',
  `grade` int(11) NOT NULL default '0',
  `time` int(11) NOT NULL default '0',
  `maxgrade` int(11) NOT NULL default '0',
  `blood` int(11) NOT NULL default '0',
  PRIMARY KEY  (`username`,`password`,`level`,`grade`,`time`,`maxgrade`,`blood`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('111', '1111', '0', '950', '745', '950', '0');
INSERT INTO `user` VALUES ('1111', '11111', '0', '450', '1588', '950', '0');
INSERT INTO `user` VALUES ('11111111', '11111111', '0', '0', '0', '0', '0');
INSERT INTO `user` VALUES ('syj', '0124', '2', '1150', '38179', '12460', '50');
