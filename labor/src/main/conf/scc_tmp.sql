-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: scc
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `counter` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `lastAccessDate` datetime DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobilephone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `pictureName` varchar(255) DEFAULT NULL,
  `pictureSource` longblob,
  `regNum` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK459C5729F14969B2` (`department_id`),
  KEY `FK459C5729EA73B8C1` (`org_id`),
  KEY `FK459C5729BAEF4CEF` (`created_by_id`),
  KEY `FK459C572928D39560` (`deleted_by_id`),
  KEY `FK459C572958E83E11` (`modified_by_he_org_id`),
  KEY `FK459C572962DCA410` (`modified_by_id`),
  CONSTRAINT `FK459C57293A950EF3` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK459C5729A8795764` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK459C5729E2826614` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK459C572928D39560` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK459C572958E83E11` FOREIGN KEY (`modified_by_he_org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK459C572962DCA410` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK459C5729BAEF4CEF` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK459C5729EA73B8C1` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK459C5729F14969B2` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'2013-08-09 00:00:00',NULL,'2014-04-15 11:05:35',1,1,NULL,'',NULL,NULL,'2014-04-15 11:05:35',NULL,NULL,'12dea96fec20593566ab75692c9949596833adc9',NULL,NULL,NULL,NULL,'user','123',2,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_aud`
--

DROP TABLE IF EXISTS `app_user_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `counter` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `lastAccessDate` datetime DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobilephone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pictureName` varchar(255) DEFAULT NULL,
  `pictureSource` longblob,
  `regNum` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKE406C87ADF74E053` (`REV`),
  CONSTRAINT `FKE406C87ADF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_aud`
--

LOCK TABLES `app_user_aud` WRITE;
/*!40000 ALTER TABLE `app_user_aud` DISABLE KEYS */;
INSERT INTO `app_user_aud` VALUES (1,1,1,'2013-08-09 00:00:00',NULL,'2014-04-15 11:05:35',1,1,NULL,'',NULL,NULL,'2014-04-15 11:05:35',NULL,NULL,'12dea96fec20593566ab75692c9949596833adc9',NULL,NULL,NULL,NULL,'user','123',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `app_user_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK328E4352EA73B8C1` (`org_id`),
  KEY `FK328E4352BAEF4CEF` (`created_by_id`),
  KEY `FK328E435228D39560` (`deleted_by_id`),
  KEY `FK328E435258E83E11` (`modified_by_he_org_id`),
  KEY `FK328E435262DCA410` (`modified_by_id`),
  CONSTRAINT `FK328E435262DCA410` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK328E435228D39560` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK328E435258E83E11` FOREIGN KEY (`modified_by_he_org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK328E4352BAEF4CEF` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK328E4352EA73B8C1` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_aud`
--

DROP TABLE IF EXISTS `department_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKF0647823DF74E053` (`REV`),
  CONSTRAINT `FKF0647823DF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_aud`
--

LOCK TABLES `department_aud` WRITE;
/*!40000 ALTER TABLE `department_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reg_num` varchar(255) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `web` varchar(255) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4644ED33BAEF4CEF` (`created_by_id`),
  KEY `FK4644ED3328D39560` (`deleted_by_id`),
  KEY `FK4644ED3358E83E11` (`modified_by_he_org_id`),
  KEY `FK4644ED3362DCA410` (`modified_by_id`),
  CONSTRAINT `FK4644ED3362DCA410` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK4644ED3328D39560` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK4644ED3358E83E11` FOREIGN KEY (`modified_by_he_org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK4644ED33BAEF4CEF` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_aud`
--

DROP TABLE IF EXISTS `organization_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reg_num` varchar(255) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `web` varchar(255) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK93014984DF74E053` (`REV`),
  CONSTRAINT `FK93014984DF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_aud`
--

LOCK TABLES `organization_aud` WRITE;
/*!40000 ALTER TABLE `organization_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission_function` varchar(255) DEFAULT NULL,
  `permission_value` bit(1) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE125C5CFBAEF4CEF` (`created_by_id`),
  KEY `FKE125C5CF28D39560` (`deleted_by_id`),
  KEY `FKE125C5CF58E83E11` (`modified_by_he_org_id`),
  KEY `FKE125C5CF62DCA410` (`modified_by_id`),
  CONSTRAINT `FKE125C5CF62DCA410` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FKE125C5CF28D39560` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FKE125C5CF58E83E11` FOREIGN KEY (`modified_by_he_org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FKE125C5CFBAEF4CEF` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_aud`
--

DROP TABLE IF EXISTS `permission_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission_function` varchar(255) DEFAULT NULL,
  `permission_value` bit(1) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKAA4C7420DF74E053` (`REV`),
  CONSTRAINT `FKAA4C7420DF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_aud`
--

LOCK TABLES `permission_aud` WRITE;
/*!40000 ALTER TABLE `permission_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revinfo` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revinfo`
--

LOCK TABLES `revinfo` WRITE;
/*!40000 ALTER TABLE `revinfo` DISABLE KEYS */;
INSERT INTO `revinfo` VALUES (1,1397531135802);
/*!40000 ALTER TABLE `revinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_action` bit(1) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `role_label` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  `parent_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK358076BAEF4CEF` (`created_by_id`),
  KEY `FK358076A7CC569D` (`parent_role_id`),
  KEY `FK35807628D39560` (`deleted_by_id`),
  KEY `FK35807658E83E11` (`modified_by_he_org_id`),
  KEY `FK35807662DCA410` (`modified_by_id`),
  CONSTRAINT `FK3580763A950EF3` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK358076A8795764` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK358076E2826614` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK35807628D39560` FOREIGN KEY (`deleted_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK35807658E83E11` FOREIGN KEY (`modified_by_he_org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK35807662DCA410` FOREIGN KEY (`modified_by_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK358076A7CC569D` FOREIGN KEY (`parent_role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK358076BAEF4CEF` FOREIGN KEY (`created_by_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'','admin','ROLE_ADMIN',1,1,NULL,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_aud`
--

DROP TABLE IF EXISTS `role_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_action` bit(1) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `role_label` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `deleted_by_id` bigint(20) DEFAULT NULL,
  `modified_by_id` bigint(20) DEFAULT NULL,
  `modified_by_he_org_id` bigint(20) DEFAULT NULL,
  `parent_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKF0208347DF74E053` (`REV`),
  CONSTRAINT `FKF0208347DF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_aud`
--

LOCK TABLES `role_aud` WRITE;
/*!40000 ALTER TABLE `role_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK143BF46A98A7F132` (`role_id`),
  KEY `FK143BF46A3DD2B512` (`user_id`),
  CONSTRAINT `FK143BF46A184DB336` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK143BF46ABD787716` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK143BF46A3DD2B512` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK143BF46A98A7F132` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_aud`
--

DROP TABLE IF EXISTS `user_role_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_aud` (
  `REV` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`user_id`,`role_id`),
  KEY `FKF323DD3BDF74E053` (`REV`),
  CONSTRAINT `FKF323DD3BDF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_aud`
--

LOCK TABLES `user_role_aud` WRITE;
/*!40000 ALTER TABLE `user_role_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role_aud` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-15 11:08:35
