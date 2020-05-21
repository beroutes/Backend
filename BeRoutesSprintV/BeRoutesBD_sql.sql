-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-05-2020 a las 03:11:56
-- Versión del servidor: 10.1.40-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `beroutes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `country_name` varchar(50) NOT NULL,
  `region` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `travel_route_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favorite`
--

CREATE TABLE `favorite` (
  `id` int(11) NOT NULL,
  `like` tinyint(1) NOT NULL,
  `not_like` tinyint(1) NOT NULL,
  `travel_route_id` int(11) NOT NULL,
  `user_profile_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `following`
--

CREATE TABLE `following` (
  `id` int(11) NOT NULL,
  `accepted` tinyint(1) DEFAULT NULL,
  `user_follower` int(11) NOT NULL,
  `user_followed` int(11) NOT NULL,
  `follow` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `travel_route_id` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `title_location` varchar(50) NOT NULL,
  `description_location` text NOT NULL,
  `x_coordinate` double DEFAULT NULL,
  `y_coordinate` double DEFAULT NULL,
  `qr_activation` tinyint(1) DEFAULT NULL,
  `qr_id` int(11) DEFAULT NULL,
  `qr_description` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `photo`
--

CREATE TABLE `photo` (
  `id` int(11) NOT NULL,
  `title_photo` varchar(50) NOT NULL,
  `description_photo` text,
  `photo_main` tinyint(1) NOT NULL,
  `photo_map` tinyint(1) NOT NULL,
  `photo_location` tinyint(1) NOT NULL,
  `photo_profile` tinyint(1) NOT NULL,
  `url_photo` varchar(300) NOT NULL,
  `code_photo` int(11) NOT NULL,
  `travel_route_id` int(11) NOT NULL,
  `image_route` blob,
  `image_route_content_type` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `qr`
--

CREATE TABLE `qr` (
  `id` int(11) NOT NULL,
  `qr_description` varchar(500) NOT NULL,
  `data_1` double DEFAULT NULL,
  `data_2` double DEFAULT NULL,
  `data_3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `travel_route`
--

CREATE TABLE `travel_route` (
  `id` int(11) NOT NULL,
  `title_route` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  `continent` enum('ASIA','AFRICA','EUROPE','AUSTRALIA','AMERICANORTH','AMERICASOUTH','ANTARCTICA') NOT NULL,
  `days` int(11) NOT NULL,
  `weeks` int(11) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `season` enum('SPRING','SUMMER','AUTUMN','WINTER') DEFAULT NULL,
  `budget` double NOT NULL,
  `category` enum('CHEAP','LUXURY','LONELY','FRIENDS','ROMANTIC','KIDS','SPORT','RELAXATION','ART','FOOD','NATURE','CITY') NOT NULL,
  `category_two` enum('CHEAP','LUXURY','LONELY','FRIENDS','ROMANTIC','KIDS','SPORT','RELAXATION','ART','FOOD','NATURE','CITY') NOT NULL,
  `value_average` double NOT NULL,
  `description_route_summary` text NOT NULL,
  `description_route` text,
  `steps` int(11) NOT NULL,
  `summary_map` varchar(300) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_profile_id` int(11) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `travel_route`
--

INSERT INTO `travel_route` (`id`, `title_route`, `destination`, `continent`, `days`, `weeks`, `location_id`, `season`, `budget`, `category`, `category_two`, `value_average`, `description_route_summary`, `description_route`, `steps`, `summary_map`, `created_at`, `updated_at`, `user_profile_id`, `country_id`) VALUES
(1, 'San Francisco. With my best friend', 'San Francisco. USA', 'ASIA', 0, 0, NULL, 'AUTUMN', 1000, 'CHEAP', 'CHEAP', 0, 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti.', 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\r\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis, sollicitudin nisl aptent facilisi gravida\r\n\r\nRouts markers:\r\n\r\n1- Nevsehir\r\n2- Goreme\r\n3- Ahipur\r\n4- Agios Nicolao\r\n5- Naxos\r\n6- Amorgos\r\n7-Rethinno\r\n', 0, '', '2020-04-13 00:00:00', NULL, NULL, 0),
(2, 'Cappadocia bella.', 'Cappadocia.Turkey', 'ASIA', 0, 0, NULL, 'SPRING', 1500, 'CHEAP', 'CHEAP', 0, 'ac blandit netus ultricies arcu aliquet inceptos eget sollicitudin', 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\r\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis, sollicitudin nisl aptent facilisi gravida\r\n\r\nRouts markers:\r\n\r\n1- Nevsehir\r\n2- Goreme\r\n3- Ahipur\r\n4- Agios Nicolao\r\n5- Naxos\r\n6- Amorgos\r\n7-Rethinno\r\n', 0, '', '2020-04-03 00:00:00', '2020-04-27 00:00:00', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_profile`
--

CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` int(11) DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `biography` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `follower` int(11) NOT NULL,
  `followed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valuation`
--

CREATE TABLE `valuation` (
  `id` int(11) NOT NULL,
  `comment` text NOT NULL,
  `rating` int(11) NOT NULL,
  `travel_route_id` int(11) NOT NULL,
  `user_profile_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `travel_route_id` (`travel_route_id`),
  ADD KEY `user_profile_id` (`user_profile_id`);

--
-- Indices de la tabla `following`
--
ALTER TABLE `following`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_profile_id` (`user_followed`),
  ADD KEY `follower_id` (`user_follower`);

--
-- Indices de la tabla `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD KEY `qr_id` (`qr_id`),
  ADD KEY `photo_id` (`photo_id`),
  ADD KEY `travel_route_id` (`travel_route_id`);

--
-- Indices de la tabla `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `travel_route_id` (`travel_route_id`);

--
-- Indices de la tabla `qr`
--
ALTER TABLE `qr`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `travel_route`
--
ALTER TABLE `travel_route`
  ADD PRIMARY KEY (`id`),
  ADD KEY `location_id` (`location_id`),
  ADD KEY `user_profile_id` (`user_profile_id`),
  ADD KEY `country_id` (`country_id`);

--
-- Indices de la tabla `user_profile`
--
ALTER TABLE `user_profile`
  ADD PRIMARY KEY (`id`),
  ADD KEY `photo_id` (`photo_id`);

--
-- Indices de la tabla `valuation`
--
ALTER TABLE `valuation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `travel_route_id` (`travel_route_id`),
  ADD KEY `user_profile_id` (`user_profile_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `favorite`
--
ALTER TABLE `favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `following`
--
ALTER TABLE `following`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `photo`
--
ALTER TABLE `photo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `qr`
--
ALTER TABLE `qr`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `travel_route`
--
ALTER TABLE `travel_route`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user_profile`
--
ALTER TABLE `user_profile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `valuation`
--
ALTER TABLE `valuation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`travel_route_id`) REFERENCES `travel_route` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `following`
--
ALTER TABLE `following`
  ADD CONSTRAINT `following_ibfk_1` FOREIGN KEY (`user_followed`) REFERENCES `user_profile` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `following_ibfk_2` FOREIGN KEY (`user_follower`) REFERENCES `user_profile` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`qr_id`) REFERENCES `qr` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `location_ibfk_3` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `location_ibfk_5` FOREIGN KEY (`travel_route_id`) REFERENCES `travel_route` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`travel_route_id`) REFERENCES `travel_route` (`id`);

--
-- Filtros para la tabla `travel_route`
--
ALTER TABLE `travel_route`
  ADD CONSTRAINT `travel_route_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `travel_route_ibfk_3` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `travel_route_ibfk_4` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `user_profile`
--
ALTER TABLE `user_profile`
  ADD CONSTRAINT `user_profile_ibfk_1` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `valuation`
--
ALTER TABLE `valuation`
  ADD CONSTRAINT `valuation_ibfk_1` FOREIGN KEY (`travel_route_id`) REFERENCES `travel_route` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `valuation_ibfk_2` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
