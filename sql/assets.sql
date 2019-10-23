/*
 Navicat Premium Data Transfer

 Source Server         : 170
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 172.28.8.170:3306
 Source Schema         : assets

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 23/10/2019 16:19:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;



-- ----------------------------
-- Table structure for function_interface
-- ----------------------------
DROP TABLE IF EXISTS `function_interface`;
CREATE TABLE `function_interface` (
  `interface_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `function_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `menu_2` (`function_id`) USING BTREE,
  KEY `interface` (`interface_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `parent_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `permission` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL COMMENT '类型',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `is_lock` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES ('3b7a9043abc3d8b83eedbae627181343', '', NULL, '系统设置', 'cogs', NULL, 1, 1, 0);
INSERT INTO `menu` VALUES ('6e217ea49288a434161dbbd178e13e13', '3b7a9043abc3d8b83eedbae627181343', '/system/route', '路由管理', 'cogs', NULL, 1, 3, 0);
INSERT INTO `menu` VALUES ('82b104c4c019ea4eb93f224376c6a53d', '3b7a9043abc3d8b83eedbae627181343', '/system/user', '用户管理', 'cogs', NULL, 1, 5, 0);
INSERT INTO `menu` VALUES ('a593d0bf805aac20b18abf970c918a94', '3b7a9043abc3d8b83eedbae627181343', '/system/menu', '菜单管理', 'cogs', NULL, 1, 2, 0);
INSERT INTO `menu` VALUES ('cc4af1eba3c1386daf301236192f33d6', '3b7a9043abc3d8b83eedbae627181343', '/system/role', '角色管理', 'cogs', NULL, 1, 4, 0);
COMMIT;


-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `role_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `function_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `menu` (`function_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES ('0eedac4cabb81ef927efbf277e7332ed', '6e217ea49288a434161dbbd178e13e13', 49);
INSERT INTO `permission` VALUES ('0eedac4cabb81ef927efbf277e7332ed', '82b104c4c019ea4eb93f224376c6a53d', 50);
INSERT INTO `permission` VALUES ('0eedac4cabb81ef927efbf277e7332ed', 'a593d0bf805aac20b18abf970c918a94', 51);
INSERT INTO `permission` VALUES ('0eedac4cabb81ef927efbf277e7332ed', 'cc4af1eba3c1386daf301236192f33d6', 52);
INSERT INTO `permission` VALUES ('0eedac4cabb81ef927efbf277e7332ed', '3b7a9043abc3d8b83eedbae627181343', 58);

COMMIT;



-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('0eedac4cabb81ef927efbf277e7332ed', 'admin', 'admin', NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `role_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `is_add` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`user_id`) USING BTREE,
  KEY `role` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES ('0eedac4cabb81ef927efbf277e7332ed', 'b4af61cd50ad1f4975dcccdbc55a60b0', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `parent_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `permission` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `component` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `component_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cache` tinyint(1) DEFAULT NULL,
  `is_lock` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of route
-- ----------------------------
BEGIN;
INSERT INTO `route` VALUES ('2962d69fe92d1bb0885239462da7dc6b', NULL, 'layoutHeaderAside', 'System', '系统设置', NULL, 0, 'layoutHeaderAside', 'layout/header-aside/layout', 0, 0);
INSERT INTO `route` VALUES ('6bc77a97731f8ec8ac21a10332b07f18', '2962d69fe92d1bb0885239462da7dc6b', '/system/user', 'UserPage', '用户管理', NULL, 4, 'user', 'pages/sys/user/index', 0, 0);
INSERT INTO `route` VALUES ('7f8ce15cd6dbe69eed4cf3659ffebc8f', '2962d69fe92d1bb0885239462da7dc6b', '/system/role', 'RolePage', '角色管理', NULL, 3, 'role', 'pages/sys/role/index', 0, 0);
INSERT INTO `route` VALUES ('8e69733dd43ebb73cefd33e5d594793e', '2962d69fe92d1bb0885239462da7dc6b', '/system/interface', 'InterfacePage', '接口管理', NULL, 5, 'interface', 'pages/sys/interface/index', 0, 0);
INSERT INTO `route` VALUES ('b88901adde7e64a9ac617a71a8230d58', '2962d69fe92d1bb0885239462da7dc6b', '/system/route', 'RoutePage', '路由管理', NULL, 2, 'route', 'pages/sys/route/index', 0, 0);
INSERT INTO `route` VALUES ('c8e32d50bff734e6d516f7432388e861', '2962d69fe92d1bb0885239462da7dc6b', '/system/menu', 'MenuPage', '菜单管理', NULL, 1, 'menu', 'pages/sys/menu/index', 0, 0);
COMMIT;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `true_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `avatar_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `is_admin` tinyint(2) DEFAULT NULL COMMENT '是否是管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('b4af61cd50ad1f4975dcccdbc55a60b0', 'admin', '123456', '123@123.com', '12345678901', '管理员', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for user_col
-- ----------------------------
DROP TABLE IF EXISTS `user_col`;
CREATE TABLE `user_col` (
  `id` varchar(35) COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `col` longtext COLLATE utf8mb4_bin COMMENT '可见列',
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单名',
  `orders` longtext COLLATE utf8mb4_bin COMMENT '列顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user_col
-- ----------------------------
BEGIN;
INSERT INTO `user_col` VALUES ('8aaa1d126df78467016df7858c590000', 'b4af61cd50ad1f4975dcccdbc55a60b0', '', 'device', NULL);
COMMIT;


SET FOREIGN_KEY_CHECKS = 1;
