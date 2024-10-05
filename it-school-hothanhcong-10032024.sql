/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 8.0.32 : Database - it-school-hothanhcong
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`it-school-hothanhcong` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `it-school-hothanhcong`;

/*Table structure for table `subject_collection_parent_tbl` */

DROP TABLE IF EXISTS `subject_collection_parent_tbl`;

CREATE TABLE `subject_collection_parent_tbl` (
  `subject_collection_parent_id` int NOT NULL AUTO_INCREMENT,
  `subject_collection_parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `subject_collection_parent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `delete_flag` bit(1) DEFAULT b'0',
  PRIMARY KEY (`lesson_collection_parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson_collection_parent_tbl` */

insert  into `lesson_collection_parent_tbl`(`lesson_collection_parent_id`,`lesson_collection_parent_code`,`lesson_collection_parent_name`,`prefix`,`created_date`,`updated_date`,`delete_flag`) values 
(1,'A01','Toán','/toan','2024-10-01 16:40:37.000000','2024-10-01 16:40:39.000000','\0'),
(2,'A02','Văn','/van','2024-10-02 14:33:05.000000','2024-10-02 14:33:09.000000','\0');

/*Table structure for table `lesson_collection_tbl` */

DROP TABLE IF EXISTS `lesson_collection_tbl`;

CREATE TABLE `lesson_collection_tbl` (
  `lesson_collection_id` int NOT NULL AUTO_INCREMENT,
  `lesson_collection_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lesson_collection_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `lesson_collection_parent_id` int DEFAULT NULL,
  `delete_flag` bit(1) DEFAULT b'0',
  PRIMARY KEY (`lesson_collection_id`),
  KEY `FK4lctmyvsktcj7hj3r5ly2fh4u` (`lesson_collection_parent_id`),
  CONSTRAINT `FK4lctmyvsktcj7hj3r5ly2fh4u` FOREIGN KEY (`lesson_collection_parent_id`) REFERENCES `lesson_collection_parent_tbl` (`lesson_collection_parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson_collection_tbl` */

insert  into `lesson_collection_tbl`(`lesson_collection_id`,`lesson_collection_code`,`lesson_collection_name`,`prefix`,`created_date`,`updated_date`,`lesson_collection_parent_id`,`delete_flag`) values 
(1,'B01','Giải tích','/giaitich','2024-10-01 16:41:55','2024-10-01 16:41:57.000000',1,'\0'),
(2,'B02','Thơ','/tho','2024-10-02 14:34:41','2024-10-02 14:34:43.000000',2,'\0'),
(3,'B02','Văn','/van','2024-10-02 14:35:01','2024-10-02 14:35:03.000000',2,'\0');

/*Table structure for table `lesson_tbl` */

DROP TABLE IF EXISTS `lesson_tbl`;

CREATE TABLE `lesson_tbl` (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `delete_flag` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `lesson_code` varchar(255) DEFAULT NULL,
  `lesson_name` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson_tbl` */

/*Table structure for table `schedule_tbl` */

DROP TABLE IF EXISTS `schedule_tbl`;

CREATE TABLE `schedule_tbl` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `medicine_list` varchar(255) DEFAULT NULL,
  `morning_hour` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `night_hour` varchar(255) DEFAULT NULL,
  `noon_hour` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `schedule_tbl` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
