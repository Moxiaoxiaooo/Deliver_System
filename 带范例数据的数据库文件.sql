-- Dump created by MySQL pump utility, version: 5.7.29, Win64 (x86_64)
-- Dump start time: Mon Apr 13 13:45:01 2020
-- Server version: 5.7.29

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET @@SESSION.SQL_LOG_BIN= 0;
SET @OLD_TIME_ZONE=@@TIME_ZONE;
SET TIME_ZONE='+00:00';
SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8mb4;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `deliver_system` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
CREATE TABLE `deliver_system`.`t_assistant_manager` (
`id` bigint(20) NOT NULL,
`assistant_manager_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`superior_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
CREATE TABLE `deliver_system`.`t_cook` (
`id` bigint(20) NOT NULL,
`cook_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`superior_id` bigint(20) DEFAULT NULL,
`contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`window_no` bigint(20) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
INSERT INTO `deliver_system`.`t_cook` VALUES (3,"张三",4,"131923100003",1),(13,"厨师2",4,"厨师2联系方式",1);
CREATE TABLE `deliver_system`.`t_manager` (
`id` bigint(20) NOT NULL,
`manager_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
CREATE TABLE `deliver_system`.`t_menu` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`meal_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`price` double DEFAULT NULL,
`cook_no` bigint(20) DEFAULT NULL,
`meal_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`amount` bigint(20) DEFAULT NULL,
`window_no` bigint(20) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
CREATE TABLE `deliver_system`.`t_normal_user` (
`id` bigint(20) NOT NULL,
`user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
CREATE TABLE `deliver_system`.`t_order` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`order_meal_no` bigint(20) DEFAULT NULL,
`order_meal_amount` int(11) DEFAULT NULL,
`order_person_id` bigint(20) DEFAULT NULL,
`order_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`send_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`status` int(1) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
INSERT INTO `deliver_system`.`t_assistant_manager` VALUES (4,"副经理1","副经理1的家",5);
CREATE TABLE `deliver_system`.`t_user` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`user_account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`user_password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
`permission_level` int(11) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_cook` ADD CONSTRAINT `t_cook_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`);
INSERT INTO `deliver_system`.`t_manager` VALUES (5,"总经理","总经理的家");
INSERT INTO `deliver_system`.`t_menu` VALUES (1,"暖男炒饭",12,3,"粤菜",149,1),(2,"叉烧饭！",10,3,"粤菜",47,1),(3,"麻婆豆腐",8,3,"川菜",139,1),(4,"剁椒鱼头",10,3,"湘菜",26,1),(5,"仰望星空派",15,3,"粤菜",100,1),(6,"阿普鲁派",15,3,"粤菜",997,1),(7,"阿帕茶",1,3,"川菜",100,1),(8,"辣椒雪糕",12,3,"湘菜",9998,1),(9,"煎饺",15,3,"川菜",0,1);
INSERT INTO `deliver_system`.`t_order` VALUES (1,4,1,1,"04-07 09:28","04-07 09:28",1),(2,2,1,1,"04-07 09:28","04-07 09:28",1),(3,2,1,1,"04-07 09:28","04-07 09:28",1),(4,3,1,1,"04-07 09:28","04-07 09:28",1),(5,4,1,12,"04-08 01:52","04-08 01:52",1),(6,9,1,1,"04-13 01:03","04-13 01:03",1),(7,8,1,1,"04-13 01:11","04-13 01:11",1),(8,4,1,1,"04-13 01:29","04-13 01:29",0),(9,4,1,1,"04-13 01:31","04-13 01:31",0),(10,2,1,1,"04-13 01:31","04-13 01:31",0),(11,1,1,1,"04-13 01:31","04-13 01:31",0),(12,8,1,1,"04-13 01:31","04-13 01:31",0),(13,6,1,1,"04-13 01:31","04-13 01:31",0),(14,6,1,1,"04-13 01:31","04-13 01:31",0),(15,3,1,1,"04-13 01:31","04-13 01:31",0),(16,6,1,1,"04-13 01:32","04-13 01:32",0);
INSERT INTO `deliver_system`.`t_normal_user` VALUES (1,"小明同学","西三101","131923100000"),(2,"李老师","西三201","131923100002"),(12,"小红","西三102","131923100001");
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_assistant_manager` ADD CONSTRAINT `t_assistant_manager_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`);
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_manager` ADD CONSTRAINT `t_manager_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`);
INSERT INTO `deliver_system`.`t_user` VALUES (1,"1001","1001",1),(2,"2001","2001",2),(3,"3001","3001",3),(4,"4001","4001",4),(5,"5001","5001",5),(12,"1002","1002",1),(13,"3002","3002",3);
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_menu` ADD KEY `cook_no` (`cook_no`);
ALTER TABLE `deliver_system`.`t_menu` ADD CONSTRAINT `t_menu_ibfk_1` FOREIGN KEY (`cook_no`) REFERENCES `t_cook` (`id`);
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_order` ADD KEY `order_person_id` (`order_person_id`);
ALTER TABLE `deliver_system`.`t_order` ADD KEY `t_order_nofk_2` (`order_meal_no`);
ALTER TABLE `deliver_system`.`t_order` ADD CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`order_person_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `deliver_system`.`t_order` ADD CONSTRAINT `t_order_nofk_2` FOREIGN KEY (`order_meal_no`) REFERENCES `t_menu` (`id`);
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_user` ADD UNIQUE KEY `user_account` (`user_account`);
USE `deliver_system`;
ALTER TABLE `deliver_system`.`t_normal_user` ADD CONSTRAINT `t_normal_user_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`);
SET TIME_ZONE=@OLD_TIME_ZONE;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET SQL_MODE=@OLD_SQL_MODE;
-- Dump end time: Mon Apr 13 13:45:03 2020
