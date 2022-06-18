/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : ssm_crud

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 18/06/2022 17:21:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`dept_id`) USING BTREE,
  UNIQUE KEY `uq_dept_name` (`dept_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
BEGIN;
INSERT INTO `tb_dept` (`dept_id`, `dept_name`) VALUES (4, '人事部');
INSERT INTO `tb_dept` (`dept_id`, `dept_name`) VALUES (1, '开发部');
INSERT INTO `tb_dept` (`dept_id`, `dept_name`) VALUES (2, '测试部');
INSERT INTO `tb_dept` (`dept_id`, `dept_name`) VALUES (3, '设计部');
COMMIT;

-- ----------------------------
-- Table structure for tb_emp
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp`;
CREATE TABLE `tb_emp` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `emp_name` varchar(15) NOT NULL COMMENT '员工姓名',
  `gender` int(4) DEFAULT NULL COMMENT '员工性别, 1男2女',
  `email` varchar(255) DEFAULT NULL COMMENT '员工邮箱',
  `d_id` int(11) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`emp_id`) USING BTREE,
  KEY `fk_emp_dept` (`d_id`) USING BTREE,
  KEY `uq_emp_name` (`emp_name`) USING BTREE,
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `tb_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_emp
-- ----------------------------
BEGIN;
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (1, '简简', 1, 'jwt1399@gmail.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (2, '柴高岩', 1, 'gaoyan@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (3, '陈露', 2, 'chenlu@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (4, '褚宸皓', 1, 'chenhao@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (5, '冯金平', 1, 'jinpin@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (6, '高奇泽', 1, 'qize@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (7, '宫敏', 2, 'gongmin@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (8, '郭旭', 1, 'guoxu@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (9, '郝思远', 1, 'siyuan@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (10, '冯阳阳', 2, 'yangyang@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (11, '家彦明', 1, 'yanming@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (12, '焦晨帆', 2, 'chenfan@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (13, '李兴栋', 1, 'xindong@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (14, '李英', 2, 'liying@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (15, '李源', 1, 'liyuan@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (16, '刘丹', 2, 'liudan@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (17, '牛旭东', 1, 'xudong@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (18, '牛泽鹏', 1, 'zepeng@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (19, '裴怡博', 1, 'yibo@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (20, '王静玲', 2, 'jingling@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (21, '师伟', 1, 'shiwei@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (22, '石宇飞', 1, 'yufei@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (23, '孙亚龙', 1, 'yalong@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (24, '王攀榕', 2, 'panrong@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (25, '王洋', 1, 'wangyang@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (26, '王振龙', 1, 'zhenlong@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (27, '魏超群', 1, 'chaoqun@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (28, '闫青', 2, 'yanqing@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (29, '杨华', 2, 'yanghua@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (30, '张勇', 1, 'zhangyong@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (31, '杨凯', 2, 'yangkai@163.com', 4);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (32, '姚丹娜', 2, 'danna@163.com', 1);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (33, '由国婧', 2, 'guojing@163.com', 2);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (34, '张强文', 1, 'qiangwen@163.com', 3);
INSERT INTO `tb_emp` (`emp_id`, `emp_name`, `gender`, `email`, `d_id`) VALUES (67, '张三', 1, 'zhangsan@qq.com', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
