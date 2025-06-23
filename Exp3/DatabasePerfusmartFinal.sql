-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_venta
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Current Database: `db_venta`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_venta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `db_venta`;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `id_venta` bigint(20) NOT NULL AUTO_INCREMENT,
  `producto` varchar(100) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `rut` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_venta`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,'Lancôme – La Vie Est Belle Eau de Parfum 100 ml',50000,'21861088-0'),(2,'Chanel – Bleu de Chanel Parfum 100 ml',85000,'20433122-1'),(3,'Paco Rabanne – Invictus Victory Elixir 100 ml',69000,'18900234-4'),(4,'Dolce & Gabbana – Light Blue Eau Intense 100 ml',72000,'21700123-K'),(5,'Carolina Herrera – 212 VIP Black 100 ml',64000,'21123456-7'),(6,'Dior – Sauvage Eau de Toilette 100 ml',89000,'20111222-2'),(7,'Carolina Herrera – Good Girl Eau de Parfum 80 ml',76000,'22233444-5'),(8,'YSL – Libre Eau de Parfum 90 ml',78000,'20987654-3'),(9,'Lancôme – Idôle Eau de Parfum 75 ml',71000,'19876543-2'),(10,'Creed – Aventus 100 ml',150000,'19345678-9');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `db_producto`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_producto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `db_producto`;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'La Vie Est Belle Eau de Parfum 100 ml',50000,'Lancôme','De mujer',266),(2,'Bleu de Chanel Eau de Parfum 100 ml',85000,'Chanel','De hombre',150),(3,'Light Blue Eau Intense 100 ml',72000,'Dolce & Gabbana','De mujer',180),(4,'Invictus Victory Elixir 100 ml',69000,'Paco Rabanne','De hombre',120),(5,'Libre Eau de Parfum 90 ml',78000,'Yves Saint Laurent','De mujer',210),(6,'Sauvage Eau de Toilette 100 ml',89000,'Dior','De hombre',250),(7,'Good Girl Eau de Parfum 80 ml',76000,'Carolina Herrera','De mujer',195),(8,'212 VIP Black 100 ml',64000,'Carolina Herrera','De hombre',170),(9,'Idôle Eau de Parfum 75 ml',71000,'Lancôme','De mujer',160),(10,'Aventus 100 ml',150000,'Creed','De hombre',90);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `db_cliente`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_cliente` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `db_cliente`;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `contrasenna` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `metodo_pago` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Camila','Sánchez','C@m1l@2025','camila.sanchez@example.com','+56912345678','Av. Siempre Viva 742, Santiago','Tarjeta débito'),(2,'José','Pérez','J0s3_2025!','jose.perez@example.com','+56987654321','Calle Los Olmos 1234, Valparaíso','Tarjeta crédito'),(3,'Fernanda','Gómez','F3rn@g0m!','fernanda.gomez@example.com','+56945678912','Pasaje Las Lilas 45, Viña del Mar','Transferencia bancaria'),(4,'Ricardo','Moya','R1c4rd0!','ricardo.moya@example.com','+56933445566','Camino El Alba 3000, La Florida','Efectivo'),(5,'Isidora','Navarrete','Is1Dora@23','isidora.navarrete@example.com','+56955667788','Callejón Estrella 678, Talca','Tarjeta débito'),(6,'Matías','Castillo','M4t14s!pass','matias.castillo@example.com','+56922334455','Av. Prat 1590, Temuco','Tarjeta crédito'),(7,'Valentina','Salinas','Val3n!2025','valentina.salinas@example.com','+56966778899','Villa El Sol 203, Antofagasta','Paypal'),(8,'Sebastián','Rojas','S3bR0j@s','sebastian.rojas@example.com','+56911223344','Ruta 5 Sur Km 23, Chillán','Transferencia bancaria'),(9,'Daniela','Figueroa','Dan!F2025','daniela.figueroa@example.com','+56977889900','Pasaje Los Robles 321, Rancagua','Tarjeta débito'),(10,'Cristóbal','Muñoz','Cr1sMu2025!','cristobal.munoz@example.com','+56999887766','Calle Nueva 101, Puerto Montt','Efectivo');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `db_logistica`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_logistica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `db_logistica`;

--
-- Table structure for table `logistica`
--

DROP TABLE IF EXISTS `logistica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logistica` (
  `id_envio` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `producto` varchar(255) DEFAULT NULL,
  `fecha_envio` varchar(255) DEFAULT NULL,
  `fecha_entrega` varchar(255) DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_envio`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logistica`
--

LOCK TABLES `logistica` WRITE;
/*!40000 ALTER TABLE `logistica` DISABLE KEYS */;
INSERT INTO `logistica` VALUES (1,201,45000,2,0.4,'Perfume Dior Sauvage','2025-06-01','2025-06-03','Santiago','Valparaíso'),(2,202,39000,1,0.3,'Perfume Carolina Herrera 212','2025-06-02','2025-06-04','Santiago','La Serena'),(3,203,52000,3,0.5,'Perfume Paco Rabanne Invictus','2025-06-03','2025-06-05','Valdivia','Temuco'),(4,204,61000,1,0.6,'Perfume Chanel Bleu','2025-06-04','2025-06-06','Concepción','Osorno'),(5,205,28000,4,0.2,'Perfume Adidas Dynamic Pulse','2025-06-05','2025-06-07','Santiago','Rancagua'),(6,206,85000,1,0.7,'Perfume Jean Paul Gaultier Le Male','2025-06-06','2025-06-09','Antofagasta','Copiapó'),(7,207,32000,2,0.3,'Perfume Benetton Colors Blue','2025-06-07','2025-06-10','Puerto Montt','Castro'),(8,208,18000,5,0.2,'Perfume Natura Kaiak','2025-06-08','2025-06-09','Iquique','Arica'),(9,209,70000,1,0.6,'Perfume Versace Eros','2025-06-09','2025-06-11','Santiago','Talca'),(10,210,64000,2,0.5,'Perfume Hugo Boss Bottled','2025-06-10','2025-06-13','Coquimbo','Valparaíso');
/*!40000 ALTER TABLE `logistica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `db_usuario`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_usuario` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `db_usuario`;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `rut` varchar(15) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `contrasenna` varchar(100) DEFAULT NULL,
  `rol` varchar(50) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('18900234-4','Carlos','Sanchez','c4r1itos!','Empleado','carlos.sanchez@gmail.com','+569 99887766'),('19345678-9','Tomas','Castro','tcastro99','Administrador','t.castro@gmail.com','+569 89998877'),('19876543-2','Valentina','Gomez','valevale','Empleado','valen.gomez@gmail.com','+569 81112233'),('20111222-2','Javier','Morales','javmor2024','Empleado','j.morales@gmail.com','+569 90112233'),('20433122-1','Maria','Lopez','maria123','Administrador','maria.lopez@gmail.com','+569 91234567'),('20987654-3','Diego','Rojas','rojaspass','Empleado','diego.rojas@gmail.com','+569 82233445'),('21123456-7','Lucia','Fernandez','lucia_pw','Administrador','luciaf@gmail.com','+569 93322110'),('21700123-K','Ana','Diaz','ana4567','Empleado','ana.diaz@gmail.com','+569 87654321'),('21861087-0','Pedro','Perez','passw0rd','Empleado','pedropere@gmail.com','+569 83022268'),('21861088-0','Bastian','Lopez','passw00rd','Administrador','bastianlopez@gmail.com','+569 83022282'),('22233444-5','Sofia','Martinez','sofi@123','Empleado','sofia.mtz@gmail.com','+569 80090011');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-18 19:21:34

