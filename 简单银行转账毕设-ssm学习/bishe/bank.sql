/*
Navicat MySQL Data Transfer

Source Server         : localhost-db
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2019-05-09 14:45:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `aid` int(255) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', '123456');

-- ----------------------------
-- Table structure for banks
-- ----------------------------
DROP TABLE IF EXISTS `banks`;
CREATE TABLE `banks` (
  `bank_id` int(255) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `bank_name` (`bank_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banks
-- ----------------------------
INSERT INTO `banks` VALUES ('25', '农业银行');
INSERT INTO `banks` VALUES ('2', '工商银行');
INSERT INTO `banks` VALUES ('1', '建设银行');

-- ----------------------------
-- Table structure for cards
-- ----------------------------
DROP TABLE IF EXISTS `cards`;
CREATE TABLE `cards` (
  `card_id` int(255) NOT NULL AUTO_INCREMENT,
  `card_num` int(255) DEFAULT NULL,
  `u_name` varchar(255) DEFAULT NULL COMMENT '卡片上的用户名',
  `bank_id` int(255) DEFAULT NULL COMMENT '外键，存储银行卡类型',
  `money` decimal(20,2) DEFAULT '0.00',
  `pay_pwd` varchar(255) DEFAULT NULL COMMENT '支付密码',
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cards
-- ----------------------------
INSERT INTO `cards` VALUES ('1', '1', '张三', '2', '5.00', '1');
INSERT INTO `cards` VALUES ('2', '2', '张三', '3', '35.00', '1');
INSERT INTO `cards` VALUES ('3', '3', '李斯', '5', '5.00', '123456');
INSERT INTO `cards` VALUES ('8', '8', 'tt', '1', '23.00', 'tt');
INSERT INTO `cards` VALUES ('9', '9', 'a', '1', '6.00', 'bb');
INSERT INTO `cards` VALUES ('10', '10', '33aa', '2', '43.00', '32');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `log_id` int(255) NOT NULL AUTO_INCREMENT,
  `uid` int(255) DEFAULT NULL,
  `aid` int(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  `time` varchar(255) DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('10', '1', null, '卡号为 1,存款12', '2019-05-08 13:19:50');
INSERT INTO `logs` VALUES ('11', '1', null, '卡号为 1的账户向 卡号为  3转账2元', '2019-05-08 13:29:29');
INSERT INTO `logs` VALUES ('12', '3', null, '卡号为 3的账户 收到来自卡号为  1的转账，金额 2元', '2019-05-08 13:29:29');
INSERT INTO `logs` VALUES ('13', '1', null, '从卡号为 10的银行卡取款23元', '2019-05-08 13:29:52');
INSERT INTO `logs` VALUES ('14', '1', null, '从卡号为 1的银行卡取款5元', '2019-05-08 13:30:04');
INSERT INTO `logs` VALUES ('16', '1', null, '开卡成功，卡号为101', '2019-05-08 13:41:36');
INSERT INTO `logs` VALUES ('17', '1', null, '开卡成功，卡号为12', '2019-05-08 13:42:49');
INSERT INTO `logs` VALUES ('18', '1', null, '开卡成功，卡号为13', '2019-05-08 13:43:45');
INSERT INTO `logs` VALUES ('19', null, '0', '管理员admin登陆后台', '2019-05-08 15:40:23');
INSERT INTO `logs` VALUES ('20', null, '1', '管理员admin登陆后台', '2019-05-08 15:54:49');
INSERT INTO `logs` VALUES ('21', null, '1', '管理员admin添加安抚类型', '2019-05-08 17:16:52');
INSERT INTO `logs` VALUES ('61', null, '1', '管理员admin登陆后台', '2019-05-08 20:54:24');
INSERT INTO `logs` VALUES ('62', null, '1', '管理员admin成功修改密码', '2019-05-08 21:46:34');
INSERT INTO `logs` VALUES ('63', null, '1', '管理员admin删除用户ces', '2019-05-08 23:52:11');
INSERT INTO `logs` VALUES ('64', null, '1', '管理员admin添加用户: bestPanda', '2019-05-09 0:17:41');
INSERT INTO `logs` VALUES ('65', null, '1', '管理员admin添加用户: asdf', '2019-05-09 0:22:50');
INSERT INTO `logs` VALUES ('66', null, '1', '管理员admin删除用户asdf', '2019-05-09 0:22:55');
INSERT INTO `logs` VALUES ('67', null, '1', '管理员admin添加卡号为：14的银行卡', '2019-05-09 10:04:54');
INSERT INTO `logs` VALUES ('68', null, '1', '管理员admin添加卡号为：15的银行卡', '2019-05-09 10:09:38');
INSERT INTO `logs` VALUES ('69', null, '1', '管理员admin添加卡号为：14的银行卡', '2019-05-09 10:40:24');
INSERT INTO `logs` VALUES ('70', null, null, '管理员删除属于zhangsan账户的、卡号为：12的银行卡', '2019-05-09 10:50:43');
INSERT INTO `logs` VALUES ('71', null, '1', '管理员admin删除属于zhangsan账户的、卡号为：12的银行卡', '2019-05-09 10:52:09');
INSERT INTO `logs` VALUES ('72', null, '1', '管理员admin删除属于zhangsan账户的、卡号为：11的银行卡', '2019-05-09 10:52:20');
INSERT INTO `logs` VALUES ('73', '1', null, '用户zhangsan成功登陆。', '2019-05-09 14:06:26');
INSERT INTO `logs` VALUES ('74', '1', null, '向卡号为 9的银行卡存款6元', '2019-05-09 14:07:06');
INSERT INTO `logs` VALUES ('75', '1', null, '用户zhangsan成功登陆。', '2019-05-09 14:20:32');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'zhangsan', '12345', '123@qq.com');
INSERT INTO `users` VALUES ('3', 'lisi', '12', '12@q');
INSERT INTO `users` VALUES ('6', 'bestPanda', '12', '223@qq');

-- ----------------------------
-- Table structure for user_card
-- ----------------------------
DROP TABLE IF EXISTS `user_card`;
CREATE TABLE `user_card` (
  `user_card_id` int(255) NOT NULL AUTO_INCREMENT,
  `uid` int(255) DEFAULT NULL,
  `card_id` int(255) DEFAULT NULL COMMENT '存储用户有哪些银行卡',
  PRIMARY KEY (`user_card_id`),
  UNIQUE KEY `card_id` (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_card
-- ----------------------------
INSERT INTO `user_card` VALUES ('1', '1', '2');
INSERT INTO `user_card` VALUES ('2', '1', '1');
INSERT INTO `user_card` VALUES ('3', '3', '3');
INSERT INTO `user_card` VALUES ('4', '1', '8');
INSERT INTO `user_card` VALUES ('5', '1', '9');
INSERT INTO `user_card` VALUES ('6', '1', '10');
