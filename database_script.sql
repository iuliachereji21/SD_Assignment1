CREATE DATABASE  IF NOT EXISTS `sd_assignment_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sd_assignment_1`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sd_assignment_1
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (50);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regular_user`
--

DROP TABLE IF EXISTS `regular_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regular_user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regular_user`
--

LOCK TABLES `regular_user` WRITE;
/*!40000 ALTER TABLE `regular_user` DISABLE KEYS */;
INSERT INTO `regular_user` VALUES ('1','iuliachereji@gmail.com','Iulia Chereji','0000'),('13','ana@yahoo.com','Ana Iepure','0000'),('2','iulia.chereji@yahoo.com','Iulia Chereji','0000'),('27','razvan@yahoo.com','Razvan','0000'),('28','aleb@yahoo.com','ale bucur','0000'),('4','luplucia@yahoo.com','Lucia Lup','0000'),('43','tudorb@yahoo.com','Tudor','1234');
/*!40000 ALTER TABLE `regular_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regular_user_vacation_package`
--

DROP TABLE IF EXISTS `regular_user_vacation_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regular_user_vacation_package` (
  `regular_user_id` varchar(255) NOT NULL,
  `vacation_package_id` varchar(255) NOT NULL,
  KEY `FK1vw4etc5bcnxp6u32v46dljjf` (`vacation_package_id`),
  KEY `FKacbaky9824xtwjdxtmkbrp1lh` (`regular_user_id`),
  CONSTRAINT `FK1vw4etc5bcnxp6u32v46dljjf` FOREIGN KEY (`vacation_package_id`) REFERENCES `vacation_package` (`id`),
  CONSTRAINT `FKacbaky9824xtwjdxtmkbrp1lh` FOREIGN KEY (`regular_user_id`) REFERENCES `regular_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regular_user_vacation_package`
--

LOCK TABLES `regular_user_vacation_package` WRITE;
/*!40000 ALTER TABLE `regular_user_vacation_package` DISABLE KEYS */;
INSERT INTO `regular_user_vacation_package` VALUES ('1','29'),('27','42'),('13','29'),('28','44'),('43','42'),('43','44'),('43','45'),('4','44'),('4','46');
/*!40000 ALTER TABLE `regular_user_vacation_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travelling_agency`
--

DROP TABLE IF EXISTS `travelling_agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travelling_agency` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelling_agency`
--

LOCK TABLES `travelling_agency` WRITE;
/*!40000 ALTER TABLE `travelling_agency` DISABLE KEYS */;
INSERT INTO `travelling_agency` VALUES ('15','veltravel@agency.com','veltravel','0000'),('3','dertour@agency.com','dertour','0000'),('7','alice@agency.com','alice holidays','0000');
/*!40000 ALTER TABLE `travelling_agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacation_destination`
--

DROP TABLE IF EXISTS `vacation_destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacation_destination` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `travelling_agency_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmk80xip69vpkvkg08hlw2ivwa` (`travelling_agency_id`),
  CONSTRAINT `FKmk80xip69vpkvkg08hlw2ivwa` FOREIGN KEY (`travelling_agency_id`) REFERENCES `travelling_agency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacation_destination`
--

LOCK TABLES `vacation_destination` WRITE;
/*!40000 ALTER TABLE `vacation_destination` DISABLE KEYS */;
INSERT INTO `vacation_destination` VALUES ('16','costinesti','15'),('17','italia','3'),('18','anglia','3'),('24','anglia 2','3'),('31','canada','7'),('33','africa','7'),('36','mamaia','15'),('41','bucuresti','15'),('8','maldive','7');
/*!40000 ALTER TABLE `vacation_destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacation_package`
--

DROP TABLE IF EXISTS `vacation_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacation_package` (
  `id` varchar(255) NOT NULL,
  `currentNrPeople` int DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `endDate` varchar(255) DEFAULT NULL,
  `maxNrPeople` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `startDate` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `travelling_agency_id` varchar(255) DEFAULT NULL,
  `vacation_destination_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnrqpqhmrayic040bd9dwy1dvy` (`travelling_agency_id`),
  KEY `FKlycyv4gekm9x3us3thut9uks9` (`vacation_destination_id`),
  CONSTRAINT `FKlycyv4gekm9x3us3thut9uks9` FOREIGN KEY (`vacation_destination_id`) REFERENCES `vacation_destination` (`id`),
  CONSTRAINT `FKnrqpqhmrayic040bd9dwy1dvy` FOREIGN KEY (`travelling_agency_id`) REFERENCES `travelling_agency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacation_package`
--

LOCK TABLES `vacation_package` WRITE;
/*!40000 ALTER TABLE `vacation_package` DISABLE KEYS */;
INSERT INTO `vacation_package` VALUES ('29',2,'summer','30.07.2022',2,'italia summer',200,'22.07.2022','BOOKED','3','17'),('34',0,'africa expedition','13.07.2022',2,'africa exploration',450,'13.06.2022','NOT_BOOKED','7','33'),('37',0,'more details','22.06.2022',2,'mamaia resort',200,'22.05.2022','NOT_BOOKED','15','36'),('42',2,'what will you see...','30.05.2022',2,'explore bucuresti',299.9,'22.05.2022','BOOKED','15','41'),('44',3,'3 people trip','10.07.2023',3,'maldive trip',200,'01.07.2023','BOOKED','7','8'),('45',1,'detailsss','20.12.2022',6,'canada ski',300,'12.12.2022','IN_PROGRESS','7','31'),('46',1,'hotel and sea','20.07.2022',2,'costinesti holiday',400,'12.07.2022','IN_PROGRESS','15','16');
/*!40000 ALTER TABLE `vacation_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sd_assignment_1'
--

--
-- Dumping routines for database 'sd_assignment_1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-11 18:47:46
