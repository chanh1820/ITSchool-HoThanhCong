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

/*Table structure for table `lesson_collection_parent_tbl` */

DROP TABLE IF EXISTS `lesson_collection_parent_tbl`;

CREATE TABLE `lesson_collection_parent_tbl` (
  `lesson_collection_parent_id` int NOT NULL AUTO_INCREMENT,
  `lesson_collection_parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lesson_collection_parent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
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
  `lesson_code` varchar(255) DEFAULT NULL,
  `lesson_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pdf_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `collection_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `collection_parent_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` bit(1) DEFAULT b'0',
  PRIMARY KEY (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson_tbl` */

insert  into `lesson_tbl`(`lesson_id`,`lesson_code`,`lesson_name`,`description`,`image_url`,`pdf_url`,`slug`,`collection_prefix`,`collection_parent_prefix`,`created_date`,`updated_date`,`delete_flag`) values 
(1,'','Giải tích 1','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','giai-tich-sdjhasdsd76','/giaitich','/toan','2024-10-03 15:15:59','2024-10-03 15:15:55','\0'),
(2,'','Giải tích 1','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','giai-tich-asdasdsdf76','/giaitich','/toan','2024-10-03 22:22:18','2024-10-03 22:22:18','\0'),
(3,'','Giải tích 1','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','giai-tich-sdjhfasdasd','/giaitich','/toan','2024-10-03 22:22:18','2024-10-03 22:22:18','\0'),
(4,'','Thơ trữ tình 1','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','tho-tru-tinh-asdu3nna','/tho','/van','2024-10-03 22:34:09','2024-10-03 22:34:09','\0'),
(5,'','Thơ trữ tình 2','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','tho-tru-tinh-asdu3nns','/tho','/van','2024-10-03 22:34:19','2024-10-03 22:34:19','\0'),
(6,'','Văn xuôi','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','van-xuoi-ad34ua','/van','/van','2024-10-03 22:34:34','2024-10-03 22:34:34','\0'),
(7,'','Văn nước ngoài','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','van-nuoc-ngoai-34hdd','/van','/van','2024-10-03 22:37:27','2024-10-03 22:37:27','\0'),
(8,'','Văn nước ngoài 2','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','van-nuoc-ngoai-ddasd','/van','/van','2024-10-03 22:37:29','2024-10-03 22:37:29','\0'),
(9,'','Văn nước ngoài 3','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','van-nuoc-ngoai-sdv22','/van','/van','2024-10-03 22:37:32','2024-10-03 22:37:32','\0'),
(10,'','Văn nước ngoài 4','','z5883284308197_33533f9e21c520e00c6edcdaf91edbc0.jpg','Chẩn Đoán.pdf','van-nuoc-ngoai-svks8','/van','/van','2024-10-03 22:37:34','2024-10-03 22:37:34','\0');

/*Table structure for table `question_tbl` */

DROP TABLE IF EXISTS `question_tbl`;

CREATE TABLE `question_tbl` (
  `question_id` bigint NOT NULL AUTO_INCREMENT,
  `answer_a` varchar(255) DEFAULT NULL,
  `answer_b` varchar(255) DEFAULT NULL,
  `answer_c` varchar(255) DEFAULT NULL,
  `answer_d` varchar(255) DEFAULT NULL,
  `random_id` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `test_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `question_tbl` */

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

/*Table structure for table `subject_collection_parent_tbl` */

DROP TABLE IF EXISTS `subject_collection_parent_tbl`;

CREATE TABLE `subject_collection_parent_tbl` (
  `subject_collection_parent_id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `delete_flag` bit(1) DEFAULT NULL,
  `subject_collection_parent_code` varchar(255) DEFAULT NULL,
  `subject_collection_parent_name` varchar(255) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`subject_collection_parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `subject_collection_parent_tbl` */

insert  into `subject_collection_parent_tbl`(`subject_collection_parent_id`,`created_date`,`delete_flag`,`subject_collection_parent_code`,`subject_collection_parent_name`,`prefix`,`updated_date`) values 
(1,'2024-10-05 16:03:39.000000','\0','A01','Toán','/toan','2024-10-05 16:03:32.000000'),
(2,'2024-10-05 16:03:41.000000','\0','A02','Văn','/van','2024-10-05 16:03:33.000000');

/*Table structure for table `subject_collection_tbl` */

DROP TABLE IF EXISTS `subject_collection_tbl`;

CREATE TABLE `subject_collection_tbl` (
  `subject_collection_id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `delete_flag` bit(1) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `subject_collection_code` varchar(255) DEFAULT NULL,
  `subject_collection_name` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `subject_collection_parent_id` int DEFAULT NULL,
  PRIMARY KEY (`subject_collection_id`),
  KEY `FK7t9rbfsxsbmxf6oc024ianbqv` (`subject_collection_parent_id`),
  CONSTRAINT `FK7t9rbfsxsbmxf6oc024ianbqv` FOREIGN KEY (`subject_collection_parent_id`) REFERENCES `subject_collection_parent_tbl` (`subject_collection_parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `subject_collection_tbl` */

insert  into `subject_collection_tbl`(`subject_collection_id`,`created_date`,`delete_flag`,`prefix`,`subject_collection_code`,`subject_collection_name`,`updated_date`,`subject_collection_parent_id`) values 
(1,'2024-10-05 16:05:22.000000','\0','/giai-tich','B01','Giải tích','2024-10-05 16:04:44.000000',1),
(2,'2024-10-05 16:05:24.000000','\0','/tho','B02','Thơ','2024-10-05 16:05:18.000000',2),
(3,'2024-10-05 16:05:25.000000','\0','/van','B03','Văn','2024-10-05 16:05:20.000000',2);

/*Table structure for table `test_tbl` */

DROP TABLE IF EXISTS `test_tbl`;

CREATE TABLE `test_tbl` (
  `test_id` int NOT NULL AUTO_INCREMENT,
  `collection_parent_prefix` varchar(255) DEFAULT NULL,
  `collection_prefix` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `delete_flag` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pdf_url` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `test_code` varchar(255) DEFAULT NULL,
  `test_name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `public_flag` bit(1) DEFAULT NULL,
  `pdf_file` varchar(255) DEFAULT NULL,
  `thumbnail_file` varchar(255) DEFAULT NULL,
  `number_choose_test` int DEFAULT NULL,
  `number_write_test` int DEFAULT NULL,
  `json_list_item_question` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `max_point` int DEFAULT NULL,
  `minute_time` int DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `test_tbl` */

insert  into `test_tbl`(`test_id`,`collection_parent_prefix`,`collection_prefix`,`created_date`,`delete_flag`,`description`,`pdf_url`,`slug`,`test_code`,`test_name`,`image_url`,`updated_date`,`author`,`public_flag`,`pdf_file`,`thumbnail_file`,`number_choose_test`,`number_write_test`,`json_list_item_question`,`max_point`,`minute_time`) values 
(1,'/toan','/giai-tich','2024-10-06 16:03:28.158830','\0','asdasd',NULL,'toan-cong-c8a98478-722a-492e-a6b1-504ede0dc910','asdasd','toán cộng',NULL,'2024-10-06 16:03:28.158830','',NULL,'noiquy.pdf','13.png',10,5,NULL,NULL,NULL),
(2,'/toan','/giai-tich','2024-10-06 16:48:47.251133','\0','adasd',NULL,'asdasd','asdasd','asdasd',NULL,'2024-10-06 16:48:47.251133','',NULL,'Bản trình bày không có tiêu đề.pptx.pdf','15.png',100,1,NULL,NULL,NULL),
(3,'/toan','/giai-tich','2024-10-07 07:59:16.840297','\0','sdasd',NULL,'toan-cong-8209ca14-ff20-4e3c-9e2f-e127b517af8a','asdasd','toán cộng',NULL,'2024-10-07 07:59:16.840297','',NULL,'Chẩn Đoán.pdf','Chẩn Đoán (1).jpg',100,3,NULL,NULL,NULL),
(4,'/toan','/giai-tich','2024-10-07 07:59:26.116009','\0','sdasd',NULL,'toan-cong-949bf9e6-07f3-4e3b-8789-040d8ceb8d28','asdasd','toán cộng',NULL,'2024-10-07 07:59:26.116009','',NULL,'Chẩn Đoán.pdf','Chẩn Đoán (1).jpg',100,3,NULL,NULL,NULL),
(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'{\"1\":{\"type\":\"1\",\"value\":\"A\"},\"2\":{\"type\":\"1\",\"value\":\"B\"},\"3\":{\"type\":\"1\",\"value\":\"A\"},\"4\":{\"type\":\"1\",\"value\":\"A\"},\"5\":{\"type\":\"1\",\"value\":\"B\"},\"6\":{\"type\":\"1\",\"value\":\"C\"},\"7\":{\"type\":\"1\",\"value\":\"B\"},\"8\":{\"type\":\"1\",\"value\":\"B\"},\"9\":{\"type\":\"1\",\"value\":\"D\"},\"10\":{\"type\":\"1\",\"value\":\"D\"}}',NULL,NULL);

/*Table structure for table `user_tbl` */

DROP TABLE IF EXISTS `user_tbl`;

CREATE TABLE `user_tbl` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `class_room` varchar(20) DEFAULT NULL,
  `day_of_birth` varchar(20) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_r0s0txs2bqvs26pnfuye8nwyt` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_tbl` */

insert  into `user_tbl`(`user_id`,`class_room`,`day_of_birth`,`full_name`,`gender`,`password`,`user_name`) values 
(1,NULL,NULL,'123456',NULL,'$2a$10$J67WaRbD1VpywgerOSOKe.r.Vl.pxyeBw8sAzzJK7xKEEDHdjTKPG','chanh');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
