/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.22 : Database - cloud_factory
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud_factory` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cloud_factory`;

/*Table structure for table `bidding` */

DROP TABLE IF EXISTS `bidding`;

CREATE TABLE `bidding` (
  `bid_id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int unsigned DEFAULT NULL,
  `price` float unsigned DEFAULT NULL,
  `factory_id` int unsigned DEFAULT NULL,
  `state` int unsigned NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bid_id`),
  KEY `order_id` (`order_id`),
  KEY `factory_id` (`factory_id`),
  CONSTRAINT `bidding_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `bidding_ibfk_2` FOREIGN KEY (`factory_id`) REFERENCES `factory` (`factory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bidding` */

/*Table structure for table `capacity` */

DROP TABLE IF EXISTS `capacity`;

CREATE TABLE `capacity` (
  `capacity_id` int unsigned NOT NULL AUTO_INCREMENT,
  `equipment_id` int unsigned DEFAULT NULL,
  `product_id` int unsigned DEFAULT NULL,
  `capacity` int unsigned DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`capacity_id`),
  KEY `equipment_id` (`equipment_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `capacity_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  CONSTRAINT `capacity_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `capacity` */

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `equipment_id` int unsigned NOT NULL AUTO_INCREMENT,
  `equipment_name` varchar(15) NOT NULL,
  `equipment_state` int unsigned NOT NULL,
  `equipment_profile` varchar(30) DEFAULT NULL,
  `factory_Id` int unsigned DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `equipment_category_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `equipment_category_id` (`equipment_category_id`),
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`equipment_category_id`) REFERENCES `equipment_category` (`equipment_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipment` */

/*Table structure for table `equipment_category` */

DROP TABLE IF EXISTS `equipment_category`;

CREATE TABLE `equipment_category` (
  `equipment_category_id` int unsigned NOT NULL AUTO_INCREMENT,
  `equipment_category_name` varchar(15) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`equipment_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipment_category` */

/*Table structure for table `factory` */

DROP TABLE IF EXISTS `factory`;

CREATE TABLE `factory` (
  `factory_id` int unsigned NOT NULL AUTO_INCREMENT,
  `factory_name` varchar(15) NOT NULL,
  `factory_state` int unsigned NOT NULL,
  `factory_profile` varchar(30) DEFAULT NULL,
  `user_id` int unsigned DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`factory_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `factory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `factory` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(15) NOT NULL,
  `purchase_quantity` int unsigned NOT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `bid_deadline` datetime DEFAULT NULL,
  `consignee` varchar(10) NOT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `user_id` int unsigned DEFAULT NULL,
  `product_id` int unsigned DEFAULT NULL,
  `state` int unsigned NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(15) NOT NULL,
  `product_code` varchar(15) NOT NULL,
  `product_description` varchar(30) DEFAULT NULL,
  `product_category_id` int unsigned DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `product_category_id` (`product_category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `product_category_id` int unsigned NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(15) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_category` */

/*Table structure for table `production_scheduling` */

DROP TABLE IF EXISTS `production_scheduling`;

CREATE TABLE `production_scheduling` (
  `production_scheduling_id` int unsigned NOT NULL AUTO_INCREMENT,
  `equipment_id` int unsigned DEFAULT NULL,
  `factory_id` int unsigned DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`production_scheduling_id`),
  KEY `equipment_id` (`equipment_id`),
  KEY `factory_id` (`factory_id`),
  CONSTRAINT `production_scheduling_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  CONSTRAINT `production_scheduling_ibfk_2` FOREIGN KEY (`factory_id`) REFERENCES `factory` (`factory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `production_scheduling` */

/*Table structure for table `rent` */

DROP TABLE IF EXISTS `rent`;

CREATE TABLE `rent` (
  `rent_id` int unsigned NOT NULL AUTO_INCREMENT,
  `equipment_id` int unsigned DEFAULT NULL,
  `factory_id` int unsigned DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `duration` int unsigned NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`rent_id`),
  KEY `equipment_id` (`equipment_id`),
  KEY `factory_id` (`factory_id`),
  CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`factory_id`) REFERENCES `factory` (`factory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rent` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role` int unsigned DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`password`,`account`,`role`,`phone_number`,`create_date`,`modify_date`) values (1,'张三','77661332','77661332656',3,'124124343','2021-07-02 00:00:00',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
