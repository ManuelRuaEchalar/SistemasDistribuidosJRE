-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-08-2024 a las 22:10:59
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_productos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `laptop`
--

CREATE TABLE `laptop` (
  `id` int(11) NOT NULL,
  `producto_id` int(11) DEFAULT NULL,
  `memoriaRam` int(11) NOT NULL,
  `procesador` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `laptop`
--

INSERT INTO `laptop` (`id`, `producto_id`, `memoriaRam`, `procesador`) VALUES
(1, 2, 32, 'intel 7!'),
(3, 6, 16, 'Intel Core i7'),
(4, 9, 8, 'Apple M1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `marca` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `descuento` float DEFAULT NULL,
  `precioFinal` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `marca`, `modelo`, `tipo`, `descripcion`, `stock`, `precio`, `descuento`, `precioFinal`) VALUES
(1, 'ASUS TUF GAMING', 'ASUS', 'FX506L', 'laptop', 'ASJLKLJSFKDJKSFDL', 2, 7700, 1, 0),
(2, 'APPLE MAC', 'APPEL', 'MAC5', 'laptop', 'asdjlkfsdkljfsd', 2, 10000, 0, 10000),
(4, 'IPHONE 15 PRO MAX', 'APPLE', '15 PRO MAX', 'telefono', 'TELEFONO MAS POTENTE DEL MERCADO', 2, 12000, 0, 12000),
(5, 'LG SMART TV E615', 'LG', 'E615', 'televisor', 'LA MEJOR TV PARA EL HOGAR', 4, 3000, 0, 3000),
(6, 'Laptop Inspiron', 'Dell', 'Inspiron 15', 'Laptop', 'Laptop Dell con procesador Intel i7', 10, 700, 0.1, 630),
(7, 'Smartphone Galaxy', 'Samsung', 'Galaxy S21', 'Telefono', 'Smartphone Samsung con 8GB RAM y procesador Exynos', 15, 800, 0.15, 680),
(8, 'Televisor OLED', 'LG', 'OLED55CX', 'Televisor', 'Televisor LG 55 pulgadas con calidad 4K', 8, 1200, 0.05, 1140),
(9, 'Laptop MacBook', 'Apple', 'MacBook Air', 'Laptop', 'Laptop Apple con procesador M1', 5, 999, 0.1, 899),
(10, 'Smartphone iPhone', 'Apple', 'iPhone 13', 'Telefono', 'Smartphone Apple con 6GB RAM y procesador A15', 20, 999, 0.05, 949);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono`
--

CREATE TABLE `telefono` (
  `id` int(11) NOT NULL,
  `producto_id` int(11) DEFAULT NULL,
  `memoriaRam` int(11) NOT NULL,
  `procesador` varchar(255) NOT NULL,
  `versionAndroid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `telefono`
--

INSERT INTO `telefono` (`id`, `producto_id`, `memoriaRam`, `procesador`, `versionAndroid`) VALUES
(1, 4, 256, 'A18', 'IOS 18'),
(2, 7, 8, 'Exynos 2100', 'Android 11'),
(3, 10, 6, 'Apple A15', 'iOS 15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `televisor`
--

CREATE TABLE `televisor` (
  `id` int(11) NOT NULL,
  `producto_id` int(11) DEFAULT NULL,
  `tamañoPantalla` int(11) NOT NULL,
  `calidad` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `televisor`
--

INSERT INTO `televisor` (`id`, `producto_id`, `tamañoPantalla`, `calidad`) VALUES
(1, 5, 80, 'FULL HD'),
(2, 8, 55, '4K');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `laptop`
--
ALTER TABLE `laptop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- Indices de la tabla `televisor`
--
ALTER TABLE `televisor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `laptop`
--
ALTER TABLE `laptop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `telefono`
--
ALTER TABLE `telefono`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `televisor`
--
ALTER TABLE `televisor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `laptop_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD CONSTRAINT `telefono_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `televisor`
--
ALTER TABLE `televisor`
  ADD CONSTRAINT `televisor_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
