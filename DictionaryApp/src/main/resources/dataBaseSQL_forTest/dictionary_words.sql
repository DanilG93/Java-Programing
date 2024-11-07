-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: dictionary
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `words`
--

DROP TABLE IF EXISTS `words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `words` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `example` varchar(200) DEFAULT NULL,
  `input_date` date NOT NULL,
  `term` varchar(40) NOT NULL,
  `translation` varchar(80) NOT NULL,
  `user_id` bigint NOT NULL,
  `language_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKedm154523nvo2ahafmo74c9xb` (`user_id`),
  KEY `FKjsgprkeo59pgfrw2peuyrmqux` (`language_id`),
  CONSTRAINT `FKedm154523nvo2ahafmo74c9xb` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjsgprkeo59pgfrw2peuyrmqux` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `words`
--

LOCK TABLES `words` WRITE;
/*!40000 ALTER TABLE `words` DISABLE KEYS */;
INSERT INTO `words` VALUES (1,'234234324234','2024-01-22','32424234234','234324324234',1,1),(2,'Jährlich nehmen wir über 3 000 ausländische Studierende auf.','2024-10-27','noun','student',1,3),(3,'I have been to countries like Canada and Russia.\r\nIch habe Länder wie Kanada und Russland besucht.','2024-10-27','wie','like',1,3),(4,'My tasks in marketing also involve public relations.\r\nMeine Aufgaben im Marketing umfassen auch Öffentlichkeitsarbeit.','2024-10-13','public relations','Öffentlichkeitsarbeit',1,3),(5,'di una o più autorità competenti interessate, prestare assistenza alle autorità per trovare un accordo conformemente alla','2023-01-06','assistenza','assistance',1,4),(6,'Students receive a diploma after four years of study.\r\nLes étudiants reçoivent un diplôme au bout de quatre années d\'études.','2024-10-29','études','study',1,4),(7,'En esta ocasión, dejé el hospital en contra de la opinión del médico que me atendía.','2024-10-27','médico tratante','attending physician',1,2),(8,'The doctor will see patients with urgent problems first.\r\nEl doctor atenderá primero a los pacientes con problemas urgentes.','2024-10-27','doctor','doctor',1,2),(9,'The company set up an action plan to improve sales.\r\nL\'entreprise a mis en place un plan d\'action pour améliorer les ventes.','2024-10-27','mettre en place','set up',1,1);
/*!40000 ALTER TABLE `words` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-03 19:23:20
