-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.24 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.5.0.5332
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para banco
CREATE DATABASE IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `banco`;

-- Volcando estructura para tabla banco.cuentas
CREATE TABLE IF NOT EXISTS `cuentas` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `saldo` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla banco.cuentas: ~3 rows (aproximadamente)
DELETE FROM `cuentas`;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` (`id`, `descripcion`, `saldo`) VALUES
	(1, 'cuenta1', 100),
	(2, 'cuenta2', 150),
	(3, 'cuenta3', 50),
	(4, 'cuenta4', 200),
	(5, 'cuenta5', 350);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;

-- Volcando estructura para tabla banco.movs
CREATE TABLE IF NOT EXISTS `movs` (
  `id` int(11) NOT NULL,
  `idCta` int(11) NOT NULL,
  `fecha` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `importe` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla banco.movs: ~0 rows (aproximadamente)
DELETE FROM `movs`;
/*!40000 ALTER TABLE `movs` DISABLE KEYS */;
INSERT INTO `movs` (`id`, `idCta`, `fecha`, `descripcion`, `importe`) VALUES
	(1, 2, '2019-01-03', 'compra de material', -50),
	(2, 3, '2019-01-03', 'ingreso en cuenta', 120),
	(3, 1, '2019-01-04', 'factura telefono', -55),
	(4, 5, '2019-01-04', 'cobro cliente 23F', 250),
	(5, 2, '2019-01-05', 'Ingreso cajero', 120),
	(6, 3, '2019-01-05', 'Nomina A231', -70);
/*!40000 ALTER TABLE `movs` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
