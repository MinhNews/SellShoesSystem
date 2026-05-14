CREATE DATABASE  IF NOT EXISTS `quanlycuahanggiay` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlycuahanggiay`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlycuahanggiay
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `ID_HoaDon` int NOT NULL,
  `ID_Giay` int NOT NULL,
  `SoLuongMua` int NOT NULL,
  `DonGia` double NOT NULL,
  PRIMARY KEY (`ID_HoaDon`,`ID_Giay`),
  KEY `ID_Giay` (`ID_Giay`),
  CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`ID_HoaDon`) REFERENCES `hoadon` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`ID_Giay`) REFERENCES `giay` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (1,1,1,10000000),(2,1,1,100000),(3,1,1,100000),(4,1,1,100000),(5,1,1,100000),(6,3,1,2500000),(7,5,1,1800000);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giay`
--

DROP TABLE IF EXISTS `giay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giay` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TenGiay` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ThuongHieu` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Size` int NOT NULL,
  `MauSac` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GiaBan` double NOT NULL,
  `SoLuongTon` int NOT NULL DEFAULT '0',
  `TrangThai` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'Available',
  `ID_LoaiGiay` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Giay_LoaiGiay` (`ID_LoaiGiay`),
  CONSTRAINT `FK_Giay_LoaiGiay` FOREIGN KEY (`ID_LoaiGiay`) REFERENCES `loaigiay` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giay`
--

LOCK TABLES `giay` WRITE;
/*!40000 ALTER TABLE `giay` DISABLE KEYS */;
INSERT INTO `giay` VALUES (1,'Domixi','Mixi',43,'Red',100000,26,'Available',1),(2,'DomixiVip','Mixi',43,'Red',1000000,30,'Available',1),(3,'Nike Air Force 1','Nike',42,'Trắng',2500000,49,'Available',1),(4,'Adidas Stan Smith','Adidas',41,'Trắng Xanh',2200000,35,'Available',1),(5,'Vans Old Skool','Vans',40,'Đen Trắng',1800000,99,'Available',1),(6,'Nike ZoomX Vaporfly','Nike',43,'Hồng',5500000,10,'Available',2),(7,'Adidas Ultraboost 22','Adidas',42,'Đen',3500000,20,'Available',2),(8,'Asics Gel-Kayano 29','Asics',41,'Xanh Navy',3200000,15,'Available',2),(9,'Clarks Tilden Cap','Clarks',40,'Nâu',2800000,25,'Available',3),(10,'Cole Haan Washington','Cole Haan',42,'Đen',4500000,12,'Available',3),(11,'Pedro Penny Loafer','Pedro',41,'Đen',2100000,40,'Available',4),(12,'Aldo Kioniel','Aldo',42,'Nâu Sáng',1900000,30,'Available',4),(13,'Reebok Nano X2','Reebok',43,'Trắng Đen',2400000,22,'Available',5),(14,'Puma Deviate Nitro','Puma',41,'Cam',2900000,18,'Available',5),(15,'Sandal Biti\'s Hunter','Bitis',40,'Đen',650000,150,'Available',6),(16,'Sandal Teva Hurricane','Teva',42,'Xám',1200000,45,'Available',6),(17,'Timberland Premium 6-Inch','Timberland',43,'Vàng Lúa Mì',4200000,20,'Available',7),(18,'Dr. Martens 1460','Dr. Martens',41,'Đen',3800000,15,'Available',7),(19,'Nike Air Jordan 1 Retro','Nike',42,'Đỏ Đen',4500000,5,'Available',1),(20,'New Balance Fresh Foam','New Balance',44,'Xám',2700000,12,'Available',2),(21,'Nike Air Force 1','Nike',42,'Trắng',2500000,50,'Available',1),(22,'Adidas Stan Smith','Adidas',41,'Trắng Xanh',2200000,35,'Available',1),(23,'Vans Old Skool','Vans',40,'Đen Trắng',1800000,100,'Available',1),(24,'Nike ZoomX Vaporfly','Nike',43,'Hồng',5500000,10,'Available',2),(25,'Adidas Ultraboost 22','Adidas',42,'Đen',3500000,20,'Available',2),(26,'Asics Gel-Kayano 29','Asics',41,'Xanh Navy',3200000,15,'Available',2),(27,'Clarks Tilden Cap','Clarks',40,'Nâu',2800000,25,'Available',3),(28,'Cole Haan Washington','Cole Haan',42,'Đen',4500000,12,'Available',3),(29,'Pedro Penny Loafer','Pedro',41,'Đen',2100000,40,'Available',4),(30,'Aldo Kioniel','Aldo',42,'Nâu Sáng',1900000,30,'Available',4),(31,'Reebok Nano X2','Reebok',43,'Trắng Đen',2400000,22,'Available',5),(32,'Puma Deviate Nitro','Puma',41,'Cam',2900000,18,'Available',5),(33,'Sandal Biti\'s Hunter','Bitis',40,'Đen',650000,150,'Available',6),(34,'Sandal Teva Hurricane','Teva',42,'Xám',1200000,45,'Available',6),(35,'Timberland Premium 6-Inch','Timberland',43,'Vàng Lúa Mì',4200000,20,'Available',7),(36,'Dr. Martens 1460','Dr. Martens',41,'Đen',3800000,15,'Available',7),(37,'Nike Air Jordan 1 Retro','Nike',42,'Đỏ Đen',4500000,5,'Available',1),(38,'New Balance Fresh Foam','New Balance',44,'Xám',2700000,12,'Available',2);
/*!40000 ALTER TABLE `giay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_NhanVien` int DEFAULT NULL,
  `ID_KhachHang` int DEFAULT NULL,
  `NgayLap` datetime DEFAULT CURRENT_TIMESTAMP,
  `TongTien` double DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID_NhanVien` (`ID_NhanVien`),
  KEY `ID_KhachHang` (`ID_KhachHang`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`ID_NhanVien`) REFERENCES `nhanvien` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`ID_KhachHang`) REFERENCES `khachhang` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,NULL,'2026-05-12 00:52:58',10000000),(2,1,1,'2026-05-13 22:54:03',100000),(3,1,1,'2026-05-13 22:55:08',99000),(4,1,1,'2026-05-13 22:55:48',100000),(5,1,1,'2026-05-13 22:56:19',99000),(6,1,2,'2026-05-13 23:09:32',2350000),(7,1,3,'2026-05-13 23:10:03',1800000);
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiemTichLuy` int DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SoDienThoai` (`SoDienThoai`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Nguyễn Huy Hùng','0999999999',0),(2,'Nguyễn Văn An','0901234567',23),(3,'Trần Thị Bình','0912345678',68),(4,'Lê Hoàng Cường','0923456789',0),(5,'Phạm Tường Vy','0934567890',300),(6,'Hoàng Trọng Đạt','0945678901',120),(7,'Vũ Thùy Linh','0956789012',450),(8,'Đặng Quốc Huy','0967890123',0),(9,'Bùi Bích Ngọc','0978901234',85),(10,'Đỗ Tấn Phát','0989012345',210),(11,'Ngô Ngọc Trâm','0990123456',5);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaigiay`
--

DROP TABLE IF EXISTS `loaigiay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaigiay` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TenLoai` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaigiay`
--

LOCK TABLES `loaigiay` WRITE;
/*!40000 ALTER TABLE `loaigiay` DISABLE KEYS */;
INSERT INTO `loaigiay` VALUES (1,'Sport'),(2,'Sneaker'),(3,'Giày Chạy Bộ (Running)'),(4,'Giày Tây (Oxford/Derby)'),(5,'Giày Lười (Loafer)'),(6,'Giày Thể Thao (Training)'),(7,'Sandal'),(8,'Giày Boot');
/*!40000 ALTER TABLE `loaigiay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `HoTen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DienThoai` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Quyen` int DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'admin','123456','Quản Trị Viên',NULL,0),(2,'nhanvien01','123456','Nguyễn Phú Minh Thái','0123456789',1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-13 23:15:41
