-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: navigator
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `driver_license`
--

DROP TABLE IF EXISTS `driver_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver_license` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9118064167417251157 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_license`
--

LOCK TABLES `driver_license` WRITE;
/*!40000 ALTER TABLE `driver_license` DISABLE KEYS */;
INSERT INTO `driver_license` VALUES (1,2020944240),(2,583807854);
/*!40000 ALTER TABLE `driver_license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `coordinates` point DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9200719548470370559 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Sacramento',_binary '\0\0\0\0\0\0\0C­i\ÞqJC@\É\å?¤_^@'),(2,'Salt Lake City',_binary '\0\0\0\0\0\0\0þe÷\äaaD@/\Ý$ù[@'),(3,'Austin',_binary '\0\0\0\0\0\0\0¿}8gD>@jMóŽoX@'),(4,'Washington DC',_binary '\0\0\0\0\0\0\02w-!tC@<NÑ‘\\BS@'),(5,'Miami',_binary '\0\0\0\0\0\0\0û\\m\Åþ\Â9@\Ü×sFT@'),(6,'Charleston',_binary '\0\0\0\0\0\0\0¬Zdc@@St$—ûS@');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persons` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `driver_license_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Drivers_Driver_License1_idx` (`driver_license_id`),
  CONSTRAINT `fk_Drivers_Driver_License1` FOREIGN KEY (`driver_license_id`) REFERENCES `driver_license` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9138331956678738019 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'Charlie',1),(2,'Jack',2);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` bigint NOT NULL,
  `content` varchar(280) DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Reviews_Locations1_idx` (`location_id`),
  CONSTRAINT `fk_Reviews_Locations1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `location_a_id` bigint NOT NULL,
  `location_b_id` bigint NOT NULL,
  `duration` int DEFAULT NULL,
  `distance` int DEFAULT NULL,
  `transportation_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `location_a_idx` (`location_a_id`),
  KEY `location_b_idx` (`location_b_id`),
  KEY `fk_Routes_Transportation` (`transportation_id`),
  CONSTRAINT `fk_Routes_Transportation` FOREIGN KEY (`transportation_id`) REFERENCES `transportation` (`id`),
  CONSTRAINT `location_a` FOREIGN KEY (`location_a_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `location_b` FOREIGN KEY (`location_b_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9178470202071418019 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,1,2,7,856,1),(2,1,3,12,2358,1),(3,2,3,2,1728,4),(4,4,5,4,1491,2),(5,2,4,9,2964,3),(6,3,5,10,1792,1),(7,5,6,5,780,4),(8,4,6,1,730,2),(9,2,1,7,856,1),(10,3,1,12,2358,1),(11,3,2,2,1728,4),(12,5,4,4,1491,2),(13,4,2,9,2964,3),(14,5,3,10,1792,1),(15,6,5,5,780,4),(16,6,4,1,730,2);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes_has_persons`
--

DROP TABLE IF EXISTS `routes_has_persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes_has_persons` (
  `route_id` bigint NOT NULL,
  `person_id` bigint NOT NULL,
  PRIMARY KEY (`route_id`,`person_id`),
  KEY `fk_Routes_has_Persons_Persons1_idx` (`person_id`),
  KEY `fk_Routes_has_Persons_Routes1_idx` (`route_id`),
  CONSTRAINT `fk_Routes_has_Persons_Persons1` FOREIGN KEY (`person_id`) REFERENCES `persons` (`id`),
  CONSTRAINT `fk_Routes_has_Persons_Routes1` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes_has_persons`
--

LOCK TABLES `routes_has_persons` WRITE;
/*!40000 ALTER TABLE `routes_has_persons` DISABLE KEYS */;
/*!40000 ALTER TABLE `routes_has_persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportation`
--

DROP TABLE IF EXISTS `transportation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transportation` (
  `id` bigint NOT NULL,
  `number` int DEFAULT NULL,
  `cost` int DEFAULT NULL,
  `driver_id` bigint NOT NULL,
  `transportation_type_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Transportation_Persons1_idx` (`driver_id`),
  KEY `fk_Transportation_Transportation_Type1_idx` (`transportation_type_id`),
  CONSTRAINT `fk_Transportation_Persons1` FOREIGN KEY (`driver_id`) REFERENCES `persons` (`id`),
  CONSTRAINT `fk_Transportation_Transportation_Type1` FOREIGN KEY (`transportation_type_id`) REFERENCES `transportation_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportation`
--

LOCK TABLES `transportation` WRITE;
/*!40000 ALTER TABLE `transportation` DISABLE KEYS */;
INSERT INTO `transportation` VALUES (1,101,100,1,1),(2,101,100,2,2),(3,101,100,1,3),(4,101,100,2,4),(5,101,100,1,5);
/*!40000 ALTER TABLE `transportation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportation_type`
--

DROP TABLE IF EXISTS `transportation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transportation_type` (
  `id` bigint NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportation_type`
--

LOCK TABLES `transportation_type` WRITE;
/*!40000 ALTER TABLE `transportation_type` DISABLE KEYS */;
INSERT INTO `transportation_type` VALUES (1,'Bus'),(2,'Plane'),(3,'Boat'),(4,'Taxi'),(5,'Train');
/*!40000 ALTER TABLE `transportation_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-28 11:30:40
