/*
 Navicat Premium Data Transfer

 Source Server         : db-pc02
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.110.138:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 21/04/2021 00:05:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tab_good
-- ----------------------------
DROP TABLE IF EXISTS `tab_good`;
CREATE TABLE `tab_good`  (
  `good_id` int(11) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `price` double(10, 2) NOT NULL,
  `repertory` int(11) NOT NULL,
  PRIMARY KEY (`good_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_good
-- ----------------------------
INSERT INTO `tab_good` VALUES (1, 'Effective Java Programming Language Guide', 100.00, 100);

-- ----------------------------
-- Table structure for tab_order
-- ----------------------------
DROP TABLE IF EXISTS `tab_order`;
CREATE TABLE `tab_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `good_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_order
-- ----------------------------

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密码',
  `balance` decimal(11, 0) NOT NULL COMMENT '余额',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES (1, 'root', 'root', 1000);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Restore data
-- ----------------------------
UPDATE tab_user SET balance = 1000;
TRUNCATE tab_order;
UPDATE tab_good SET repertory = 100;