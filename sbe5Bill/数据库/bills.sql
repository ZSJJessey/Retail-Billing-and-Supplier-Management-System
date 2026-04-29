/*
SQLyog Ultimate v8.32 
MySQL - 5.7.26 : Database - bills
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bills` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `bills`;

/*Table structure for table `bill_table` */

DROP TABLE IF EXISTS `billtable`;

CREATE TABLE `billtable` (
  `bill_id` int(11) NOT NULL,
  `goods_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `supplier` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bill_amount` float DEFAULT NULL,
  `is_payment` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `bill_table` */

insert  into `billtable`(`bill_id`,`goods_name`,`supplier`,`bill_amount`,`is_payment`,`create_time`) values (1,'手机','华为',4000,1,'2021-11-22 10:13:44'),(2,'笔记本','联想',8888,0,'2021-11-22 10:13:44'),(3,'鼠标','罗技',999,0,'2021-11-22 10:14:07'),(4,'手机','小米',1999,0,'2021-11-22 11:38:32'),(5,'笔记本','苹果',12999,1,'2021-11-22 11:38:32'),(6,'鼠标','卓威',599,0,'2021-11-22 11:38:32');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
