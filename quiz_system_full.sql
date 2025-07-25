-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz_system
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int NOT NULL,
  `question` text NOT NULL,
  `option_a` varchar(255) DEFAULT NULL,
  `option_b` varchar(255) DEFAULT NULL,
  `option_c` varchar(255) DEFAULT NULL,
  `option_d` varchar(255) DEFAULT NULL,
  `correct_option` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,1,'What is the capital of France?','Berlin','Madrid','Paris','Rome','C'),(3,2,'What is the size of an int data type in Java?','2 bytes','4 bytes','8 bytes','Depends on the system','B'),(4,1,'What is the chemical symbol for Gold?','Ag','Go','Au ','Gd','C'),(5,1,'What is the capital city of Australia?','Sydney','Melbourne','Canberra','Perth','C'),(6,1,'Which planet is known as the “Red Planet”?','Venus','Mars','Jupiter','Saturn','B'),(7,1,'Who wrote the play Romeo and Juliet?','Charles Dickens','William Shakespeare','Mark Twain','Jane Austen','B'),(8,1,'Which is the largest ocean on Earth?','Atlantic Ocean','Pacific Ocean','Indian Ocean','Arctic Ocean','B'),(9,1,'What is the national currency of Japan?','Yen','Dollar','Euro','Peso','A'),(10,1,'Who is known as the “Father of Computers”?','Albert Einstein','Isaac Newton','Charles Babbage','Thomas Edison','C'),(11,1,'Which is the longest river in the world?','Amazon River','Nile River','Yangtze River','Ganges River','B'),(12,1,'In which country were the Olympic Games first held?','Italy','Greece','France','Egypt','B'),(13,1,'Which continent is known as the “Dark Continent”?','Asia','Africa','Europe','Australia','B'),(14,1,'Who painted the famous artwork Mona Lisa?','Pablo Picasso','Vincent van Gogh','Leonardo da Vinci','Michelangelo','C'),(15,2,'Which of the following is used to run a Java program?','javac','java','javadoc','jar','B'),(16,2,'Which of these is not a Java keyword?','static','class','print','final','C'),(17,2,'What is the default value of an int variable in Java','1','0','null','undefined','B'),(18,2,'Which of the following is used to compile a Java program?','javac','java','jvm','jre','A'),(19,2,'Which method is the entry point of a Java program?','start()','main()','run()','execute()','B'),(20,2,'Which of these is a valid data type in Java?','number','int','real','floatnum','B'),(21,2,'Which of these is used to handle exceptions in Java?','catch','throw','try','All of the above','D'),(22,2,'Which of the following is not an access modifier in Java?','public','private','protected','package','D'),(23,2,'What does JVM stand for?','Java Virtual Machine','Java Variable Method','Java Verified Mode','Java Version Manager','A'),(24,2,'Which of these is used to create an object in Java?','this','new','alloc','create','B'),(25,2,'Which of the following is not a primitive data type in Java?','int','boolean','String','char','C'),(26,2,'Which of these keywords is used to inherit a class in Java?','implement','extends','inherits','instanceof','B'),(27,2,'Which package is automatically imported in every Java program?','java.net','java.util','java.lang','java.io','C'),(28,2,'Which of these loops is guaranteed to execute at least once?','for loop','while loop','do-while loop','foreach loop','C'),(29,2,'Which operator is used for string concatenation in Java?','+','&','.','*','A');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (1,'General Knowledge'),(2,'Java Basics');
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results` (
  `result_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `quiz_id` int NOT NULL,
  `score` int NOT NULL,
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`result_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `results_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,3,1,1,'2025-07-13 16:39:14'),(2,3,1,0,'2025-07-13 16:40:47'),(3,3,1,0,'2025-07-13 16:41:33'),(4,3,1,1,'2025-07-13 16:48:55'),(5,3,1,1,'2025-07-13 16:49:41'),(6,3,1,1,'2025-07-13 16:56:07'),(7,3,2,1,'2025-07-13 17:04:15'),(8,3,2,0,'2025-07-13 17:31:00'),(9,3,1,2,'2025-07-16 08:09:00'),(10,3,1,2,'2025-07-16 09:21:55'),(11,3,2,0,'2025-07-16 09:22:17'),(12,3,1,2,'2025-07-25 15:14:37'),(13,3,1,4,'2025-07-25 15:43:07'),(14,3,1,3,'2025-07-25 15:44:23'),(15,3,1,5,'2025-07-25 15:45:01'),(16,3,2,0,'2025-07-25 15:45:58'),(17,4,2,2,'2025-07-25 17:46:25');
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'admin','admin123'),(4,'sumeet','sumeet123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-25 23:47:06
