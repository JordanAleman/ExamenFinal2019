-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `programacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `codigo` tinyint(4) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `fecha` varchar(10) NOT NULL,
  `estado` char(1) NOT NULL,
  `precio` int(11) NOT NULL,
  `nif` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`codigo`, `matricula`, `fecha`, `estado`, `precio`, `nif`) VALUES
(1, '2876FNZ', '2001-01-11', 'A', 3500, '45231980K'),
(2, '9091GNX', '2012-03-14', 'A', 3500, '43592175P'),
(3, '3756DKP', '2010-09-13', 'A', 3500, '42081483Z'),
(4, '3176KGF', '2015-12-09', 'A', 3500, '45231980K'),
(5, '9201BNZ', '2003-11-10', 'A', 3500, '45231980K'),
(6, '4358KLM', '2016-05-25', 'A', 3500, '43592175P'),
(7, '2876FNZ', '2012-02-17', 'A', 2300, '45231980K'),
(8, '2876CNF', '2005-10-12', 'A', 12500, '43592175P'),
(9, '8932BHB', '2001-12-04', 'A', 3500, '42081483Z'),
(10, '5309GCF', '2009-11-22', 'A', 7500, '44509185H');

--
--
--

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `codigo` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
