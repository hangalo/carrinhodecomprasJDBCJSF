-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema carrocompras
--

CREATE DATABASE IF NOT EXISTS carrocompras;
USE carrocompras;

--
-- Definition of table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoria`
--

/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`id_categoria`,`descricao`) VALUES 
 (1,'verduras'),
 (2,'Frutas');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;


--
-- Definition of table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`,`nome`) VALUES 
 (1,'Joaquim Hangalo'),
 (2,'Maria Hangalo'),
 (3,'Maria da Conceição Sapalo'),
 (4,'Rosa Hangalo');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL AUTO_INCREMENT,
  `data_factura` date DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `valor_total` double DEFAULT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `fk_factura_cliente1_idx` (`id_cliente`),
  CONSTRAINT `fk_factura_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `factura`
--

/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` (`id_factura`,`data_factura`,`id_cliente`,`valor_total`) VALUES 
 (1,NULL,1,NULL),
 (2,NULL,1,NULL),
 (3,'2018-04-13',4,47),
 (4,'2018-04-13',2,25),
 (5,'2018-04-13',4,29),
 (6,'2018-04-13',4,0),
 (7,'2018-04-13',4,0),
 (8,'2018-04-13',4,0),
 (9,'2018-04-13',2,30),
 (10,'2018-04-13',4,30),
 (11,'2018-04-13',1,59),
 (12,'2018-04-13',1,107),
 (13,'2018-04-14',1,56);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;


--
-- Definition of table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `preco_compra` double DEFAULT NULL,
  `id_factura` int(11) NOT NULL,
  PRIMARY KEY (`id_item`),
  KEY `fk_item_produto1_idx` (`id_produto`),
  KEY `fk_item_factura1_idx` (`id_factura`),
  CONSTRAINT `fk_item_factura1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`id_item`,`id_produto`,`quantidade`,`preco_compra`,`id_factura`) VALUES 
 (1,1,4,NULL,0),
 (2,3,1,NULL,0),
 (3,4,3,NULL,0),
 (4,1,2,8,0),
 (5,3,1,NULL,0),
 (6,3,2,4,0),
 (7,5,3,6,0),
 (8,4,1,8,0),
 (9,3,6,4,0),
 (10,1,2,8,0),
 (11,2,1,5,0),
 (12,2,2,5,0),
 (13,3,1,4,0),
 (14,3,2,4,2),
 (15,4,1,8,2),
 (16,3,4,4,2),
 (17,4,2,8,2),
 (18,3,2,4,2),
 (19,4,2,8,2),
 (20,4,4,8,2),
 (21,3,2,4,2),
 (22,3,3,4,3),
 (23,4,2,8,3),
 (24,5,1,6,3),
 (25,2,1,5,3),
 (26,1,1,8,3),
 (27,1,1,8,4),
 (28,2,1,5,4),
 (29,5,2,6,4),
 (30,1,1,8,5),
 (31,2,3,5,5),
 (32,5,1,6,5),
 (33,5,5,6,9),
 (34,2,2,5,10),
 (35,5,2,6,10),
 (36,1,1,8,10),
 (37,1,3,8,11),
 (38,2,1,5,11),
 (39,5,1,6,11),
 (40,3,2,4,11),
 (41,4,2,8,11),
 (42,4,2,8,12),
 (43,3,1,4,12),
 (44,1,2,8,12),
 (45,2,7,5,12),
 (46,5,6,6,12),
 (47,2,6,5,13),
 (48,5,1,6,13),
 (49,1,1,8,13),
 (50,4,1,8,13),
 (51,3,1,4,13);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


--
-- Definition of table `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `id_produto` int(11) NOT NULL,
  `nome_produto` varchar(45) DEFAULT NULL,
  `preco_produto` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `fk_categoria_produto_idx` (`categoria`),
  CONSTRAINT `fk_categoria_produto` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto`
--

/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` (`id_produto`,`nome_produto`,`preco_produto`,`quantidade`,`categoria`) VALUES 
 (1,'Banana',8,10,2),
 (2,'Manga',5,20,2),
 (3,'Couves',4,30,1),
 (4,'Repolho',8,40,1),
 (5,'Tomante',6,12,2);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
