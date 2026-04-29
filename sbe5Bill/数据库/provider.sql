-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bills
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `providertable`
--

DROP TABLE IF EXISTS `providertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `providertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `supplier` varchar(50) NOT NULL DEFAULT '' COMMENT 'name',
  `linkman` varchar(50) NOT NULL DEFAULT '' COMMENT 'linkman',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT 'phone',
  `fax` varchar(50) NOT NULL DEFAULT '' COMMENT 'fax',
  `create_date` datetime DEFAULT '1000-01-01 00:00:00' COMMENT 'createDate',
  `address` varchar(50) DEFAULT '' COMMENT 'address',
  `describe1` varchar(200) DEFAULT '' COMMENT 'describe1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=656 DEFAULT CHARSET=utf8 COMMENT='`Provider`';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providertable`
--

LOCK TABLES `providertable` WRITE;
/*!40000 ALTER TABLE `providertable` DISABLE KEYS */;
INSERT INTO `providertable` VALUES (1,'苹果','周世杰','15381591729','15381591729','2021-12-15 07:57:10','阿斯顿',''),(2,'华为','张三','13380206767','13380206767','2021-12-15 07:57:31','',''),(3,'小米','李四','110','110','2021-12-15 07:58:07','',''),(4,'联想','王五','119','119','2021-12-15 07:58:22','',''),(5,'罗技','洪七','120','120','2021-12-15 07:58:53','',''),(6,'苹果','周八','118611','118611','2021-12-15 07:59:14','阿斯顿','阿斯顿'),(21,'华为','周世杰','15381591729','15381591729','1000-01-01 00:00:00','阿斯顿','阿斯顿'),(68,'华为','周世杰','15381591729','15381591729','2021-12-17 20:56:28','阿斯顿','大苏打'),(654,'华为','周世杰','15381591729','15381591729','1000-01-01 00:00:00','阿斯顿','大苏打');
/*!40000 ALTER TABLE `providertable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-17 21:45:37
