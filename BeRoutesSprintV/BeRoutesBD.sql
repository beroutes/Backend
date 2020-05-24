-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-05-2020 a las 13:23:04
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
  `country_name` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `country`
--

INSERT INTO `country` (`id`, `country_name`, `region`, `city`) VALUES
(1, 'Unites States', NULL, 'San Francisco'),
(2, 'Turkey', 'Cappadocia', NULL),
(3, 'Greece', NULL, 'Athens'),
(4, 'Morocco', 'Rif', NULL),
(5, 'Greenland', 'Tassiusaq', NULL),
(6, 'Italia', 'Toscana', 'Florencia'),
(7, 'Portugal', NULL, 'Lisboa'),
(8, 'Japon', NULL, 'Tokio'),
(9, 'Spain', 'Andalucia', 'Tarifa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favorite`
--

CREATE TABLE `favorite` (
  `id` int(11) NOT NULL,
  `jhi_like` tinyint(1) DEFAULT NULL,
  `not_like` tinyint(1) DEFAULT NULL,
  `travel_route_id` int(11) DEFAULT NULL,
  `user_profile_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `favorite`
--

INSERT INTO `favorite` (`id`, `jhi_like`, `not_like`, `travel_route_id`, `user_profile_id`) VALUES
(1, 1, 0, 1, 2),
(2, 1, 0, 2, 1),
(3, 1, NULL, 5, 2),
(4, 1, NULL, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `following`
--

CREATE TABLE `following` (
  `id` int(11) NOT NULL,
  `accepted` tinyint(1) DEFAULT NULL,
  `user_follower` int(11) DEFAULT NULL,
  `user_followed` int(11) DEFAULT NULL,
  `follow` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `travel_route_id` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `title_location` varchar(200) DEFAULT NULL,
  `description_location` text,
  `x_coordinate` double DEFAULT NULL,
  `y_coordinate` double DEFAULT NULL,
  `qr_activation` tinyint(1) DEFAULT NULL,
  `qr_id` int(11) DEFAULT NULL,
  `qr_description` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `location`
--

INSERT INTO `location` (`id`, `travel_route_id`, `position`, `title_location`, `description_location`, `x_coordinate`, `y_coordinate`, `qr_activation`, `qr_id`, `qr_description`, `created_at`, `updated_at`, `photo_id`) VALUES
(5, 1, 1, 'Union Square', 'Praesent lacinia volutpat nisl, a feugiat magna gravida eu. Integer ut condimentum lorem, eget fermentum', -122.4075169, 37.7879797, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 1, 2, 'Twin Peaks', 'Curabitur laoreet lacus vitae lectus luctus, id interdum libero ult', -122.44648, 37.75464, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 1, 3, 'Sausalito', 'itae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito', -122.4854694, 37.8590272, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 1, 4, 'ChinaTown', 'oreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus met', -122.4063757, 37.7943011, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 1, 5, 'Point Reyes', 'laoreet lacus vitae lectus luctus, id i', -117.323303, 33.248039, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 2, 1, 'Göreme', 'urabitur laoreet lacus vitae lectus luctus, id interdum libers', 34.8296234, 38.642089, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 2, 2, 'Uchisar', ' laoreet lacus vitae lectus luctus', 34.8046138, 38.6293826, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 2, 3, 'Ortahisar', 'bitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus met', 39.706678, 41.0013246, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 2, 4, 'Ürgup', 'r laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellu', 34.9589020075008, 38.5928197, NULL, NULL, NULL, NULL, NULL, NULL),
(14, 3, 1, 'Narsarsuaq', 'valle glacial de las mil flores', -45.425556, 61.160556, NULL, NULL, NULL, NULL, NULL, NULL),
(15, 4, 0, 'Rif ', NULL, -3.933333, 35.25, NULL, NULL, NULL, NULL, NULL, NULL),
(16, 5, 1, 'Athens', NULL, 23.7162, 37.9795, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `photo`
--

CREATE TABLE `photo` (
  `id` int(11) NOT NULL,
  `title_photo` varchar(100) DEFAULT NULL,
  `description_photo` text,
  `photo_main` tinyint(1) DEFAULT NULL,
  `photo_map` tinyint(1) DEFAULT NULL,
  `photo_location` tinyint(1) DEFAULT NULL,
  `photo_profile` tinyint(1) DEFAULT NULL,
  `url_photo` text,
  `code_photo` int(11) DEFAULT NULL,
  `travel_route_id` int(11) DEFAULT NULL,
  `image_route` blob,
  `image_route_content_type` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `qr`
--

CREATE TABLE `qr` (
  `id` int(11) NOT NULL,
  `qr_description` text,
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
  `title_route` varchar(100) DEFAULT NULL,
  `destination` varchar(100) DEFAULT NULL,
  `continent` enum('ASIA','AFRICA','EUROPE','AUSTRALIA','AMERICANORTH','AMERICASOUTH','ANTARCTICA') DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `weeks` double DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `season` enum('SPRING','SUMMER','AUTUMN','WINTER') DEFAULT NULL,
  `budget` double DEFAULT NULL,
  `category` enum('CHEAP','LUXURY','LONELY','FRIENDS','ROMANTIC','KIDS','SPORT','RELAXATION','ART','FOOD','NATURE','CITY') DEFAULT NULL,
  `category_two` enum('CHEAP','LUXURY','LONELY','FRIENDS','ROMANTIC','KIDS','SPORT','RELAXATION','ART','FOOD','NATURE','CITY') DEFAULT NULL,
  `value_average` double DEFAULT NULL,
  `description_route_summary` text,
  `description_route` text,
  `steps` int(11) DEFAULT NULL,
  `summary_map` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_profile_id` int(11) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `travel_route`
--

INSERT INTO `travel_route` (`id`, `title_route`, `destination`, `continent`, `days`, `weeks`, `location_id`, `season`, `budget`, `category`, `category_two`, `value_average`, `description_route_summary`, `description_route`, `steps`, `summary_map`, `created_at`, `updated_at`, `user_profile_id`, `country_id`) VALUES
(1, 'San Francisco. With my best friend', 'San Francisco. USA', 'AMERICANORTH', 5, 0.7, 5, 'AUTUMN', 1000, 'FRIENDS', 'CITY', 9.2, 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti.', 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\r\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis, sollicitudin nisl aptent facilisi gravida\r\n\r\nRouts markers:\r\n\r\nDía 1: Union Square, Fisherman\'s Wharf y Marina District; Día 2: Twin Peaks, Golden Gate Park, Alamo Square.;Día 3: Sausalito, Presidio Park y Castro; Día 4: Isla Alcatraz, Chinatown y Coit Tower; Día 5: Yerba Buena o Point Reyes.', 5, 'Día 1: Union Square, Fisherman\'s Wharf y Marina District; Día 2: Twin Peaks, Golden Gate Park, Alamo Square.;Día 3: Sausalito, Presidio Park y Castro; Día 4: Isla Alcatraz, Chinatown y Coit Tower; Día 5: Yerba Buena o Point Reyes.', '2020-04-13 00:00:00', NULL, 1, 1),
(2, 'Cappadocia bella.', 'Cappadocia.Turkey', 'ASIA', 14, 2, 10, 'SPRING', 1500, 'ROMANTIC', 'NATURE', 9.3, 'ac blandit netus ultricies arcu aliquet inceptos eget sollicitudin', 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\r\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis, sollicitudin nisl aptent facilisi gravida\r\n\r\nRouts markers:\r\n\r\n1- Nevsehir\r\n2- Goreme\r\n3- Ahipur\r\n4- Agios Nicolao\r\n5- Naxos\r\n6- Amorgos\r\n7-Rethinno\r\n', 4, '1:Museo al Aire Libre de Göreme.; 2:Castillo de Uchisar.; 3:Ortahisar.; 4:Ürgup.', '2020-04-03 00:00:00', '2020-04-27 00:00:00', 2, 2),
(3, 'Greenland gift to the senses', 'Greenland', 'AMERICANORTH', 15, 2.1, 14, 'SPRING', 2500, 'ROMANTIC', 'NATURE', 9.4, 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti.', 'Elementum cubilia parturient sociis. Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\\\\\\\\r\\\\\\\\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis', 9, '1 El valle glaciar de las Mil Flores; 2 Glaciar Kiattut; 3 Mercado de pescadores en Narsaq; 4 Glaciar Qaleraliq; 5 Lago Tasersuatsiaq; 6 Mirador al Inlandis; 7 Igaliku; 8 Glaciar Qooroq; 9 Narsarsuaq Museum', NULL, NULL, 2, 5),
(4, 'Bike trip in morocco', 'Morocco', 'AFRICA', 7, 1, 15, 'WINTER', 600, 'FRIENDS', 'SPORT', 9.1, 'Lorem Ipsun istin icole hasta que fisticolico de nimiluti.', 'Elementum cubilia parturient sociis. Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\\\\\\\\r\\\\\\\\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis', 6, '1 Chefchauen, pintada de azul.; 2 Subida al Yebel El Kelaa; 3 Camino de Alhucemas.; 4 Parque Nacional de Alhucemas; 5 Senderismo en el Rif; 6 Camino, Nador.', NULL, NULL, 1, 4),
(5, 'Greece the blue paradise', 'Greece', 'EUROPE', 10, 1.4, 16, 'WINTER', 1200, 'NATURE', 'RELAXATION', 9.7, 'Suspendisse mattis dolor sit amet orci gravida, eu fringilla libero varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed posuere malesuada sollicitudin. Sed ac elit quis velit lacinia condimentum.', '\r\nDuis dignissim mollis ex, nec dignissim tellus consequat id. Aliquam imperdiet quam sit amet vulputate vulputate. Nulla vitae sapien ex. Duis eleifend libero bibendum, sollicitudin\r\n\r\n1 Athenas\r\n2 Naxos\r\n3 Amorgos\r\n4 Santorini\r\n5 Creta\r\n', 5, '1 Athenas\r\n2 Naxos\r\n3 Amorgos\r\n4 Santorini\r\n5 Creta\r\n', NULL, NULL, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_profile`
--

CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `biography` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `follower` int(11) DEFAULT NULL,
  `followed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `user_profile`
--

INSERT INTO `user_profile` (`id`, `first_name`, `last_name`, `email`, `telephone`, `user_name`, `password`, `age`, `biography`, `created_at`, `updated_at`, `photo_id`, `follower`, `followed`) VALUES
(1, 'Anna', 'Times', 'annatimes@gmail.com', 555555555, 'annatravaller', '$2y$12$7UH.qnMEDlNycz8PUr/kCOJEgBgyHo9g0q7i84ZkpUbSGy0G/YLqG', 25, 'Estoy empezando a viajar, poco puedo contar', NULL, NULL, NULL, 0, 0),
(2, 'Erik', 'Olson', 'erikolson@gmail.com', 555555555, 'worldtraveller', '$2y$12$7UH.qnMEDlNycz8PUr/kCOJEgBgyHo9g0q7i84ZkpUbSGy0G/YLqG', 38, 'Volando voy volando vengo por el camino yo me entretengo', NULL, NULL, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valuation`
--

CREATE TABLE `valuation` (
  `id` int(11) NOT NULL,
  `comment` text,
  `rating` int(11) DEFAULT NULL,
  `travel_route_id` int(11) DEFAULT NULL,
  `user_profile_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `valuation`
--

INSERT INTO `valuation` (`id`, `comment`, `rating`, `travel_route_id`, `user_profile_id`) VALUES
(1, 'Me ha encantado esta ruta. Estoy deseando ir a conocer ese mágico lugar.', 9, 3, 1),
(2, 'Iré a conocerlo pero mejor en primavera me asusta el calor. Qué pasada de playas y mar azul!', 10, 5, 2);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `favorite`
--
ALTER TABLE `favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `following`
--
ALTER TABLE `following`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `user_profile`
--
ALTER TABLE `user_profile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `valuation`
--
ALTER TABLE `valuation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  ADD CONSTRAINT `travel_route_ibfk_4` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON UPDATE CASCADE;

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
