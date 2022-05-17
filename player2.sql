-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2022 a las 22:59:42
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `player2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `follow`
--

CREATE TABLE `follow` (
  `id` int(11) NOT NULL,
  `idusuariofollower` int(11) NOT NULL,
  `idusuariofollowed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `follow`
--

INSERT INTO `follow` (`id`, `idusuariofollower`, `idusuariofollowed`) VALUES
(163, 1, 2),
(156, 1, 6),
(128, 2, 1),
(71, 2, 9),
(161, 2, 11),
(127, 6, 1),
(162, 6, 2),
(70, 6, 9),
(154, 8, 6),
(160, 9, 11),
(155, 11, 6),
(72, 11, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generos`
--

CREATE TABLE `generos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `generos`
--

INSERT INTO `generos` (`id`, `nombre`) VALUES
(1, 'Acción'),
(2, 'Aventura'),
(3, 'Carreras'),
(4, 'Estrategia'),
(5, 'Simulación'),
(6, 'Deporte'),
(7, 'Lucha'),
(8, 'Rol'),
(9, 'Puzzle'),
(10, 'Plataformas'),
(11, 'Shooter');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juego_usuario`
--

CREATE TABLE `juego_usuario` (
  `id` int(11) NOT NULL,
  `completado` tinyint(1) DEFAULT NULL,
  `horas` int(11) DEFAULT NULL,
  `idvideojuego` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `puntuacion` int(11) DEFAULT NULL,
  `fecha` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `juego_usuario`
--

INSERT INTO `juego_usuario` (`id`, `completado`, `horas`, `idvideojuego`, `idusuario`, `puntuacion`, `fecha`) VALUES
(8, 0, 40, 1, 2, 7, 1650227228697),
(14, 0, 40, 1, 6, 8, 1649612994140),
(27, 0, 0, 15, 6, 6, 1649686746703),
(28, 1, 0, 13, 6, 5, 1649519686856),
(29, 0, 0, 5, 6, 5, 0),
(31, 0, 18, 16, 9, 8, 1650209903156),
(35, 0, 0, 4, 6, 5, 0),
(37, 0, 35, 8, 9, 5, 1649713796486),
(39, 1, 0, 6, 2, 5, 1649520179190),
(41, 0, 12, 7, 2, 9, 1650227001926),
(43, 0, 0, 6, 1, 7, 1649974917915),
(44, 1, 40, 15, 2, 8, 1650227023215),
(47, 0, 0, 7, 6, 9, 1649759306179),
(48, 0, 0, 4, 2, 5, 1649881534653),
(49, 1, 0, 4, 11, 5, 1649591568861),
(50, 0, 13, 7, 11, 9, 1649714070637),
(51, 0, 18, 6, 11, 10, 1649714142308),
(54, 0, 0, 12, 6, 10, 1649613127352),
(57, 0, 7, 8, 2, 2, 1649679537830),
(58, 0, 0, 5, 9, 4, 1650209783735),
(59, 0, 0, 15, 9, 10, 1649705597625),
(60, 1, 0, 13, 9, 5, 1650208993928),
(62, 0, 0, 9, 6, 5, 1649759322451),
(64, 0, 0, 11, 6, 5, 1649762619756),
(65, 0, 0, 10, 6, 5, 1649762672622),
(66, 0, 0, 14, 2, 5, 1649789042644),
(67, 0, 0, 12, 2, 5, 1649789119057),
(73, 0, 0, 22, 1, 10, 1650199948810),
(74, 0, 7, 22, 2, 6, 1650225545165),
(83, 0, 0, 16, 19, 5, 1650483846065),
(84, 0, 0, 17, 19, 5, 1650483861096),
(85, 0, 0, 6, 9, 5, 1650826449529),
(86, 0, 18, 1, 9, 8, 1650826750914);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL,
  `fecha` bigint(20) NOT NULL,
  `contenido` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `idautor` int(11) NOT NULL,
  `iddestinatario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fecha`, `contenido`, `idautor`, `iddestinatario`) VALUES
(1, 1651003767917, 'hola', 1, 2),
(2, 1651003914984, 'cómo estás=', 1, 2),
(3, 1651003919863, 'k', 1, 2),
(4, 1651004242419, 'hola admin', 2, 1),
(5, 1651004256026, 'estoy bien, gracias\n', 2, 1),
(6, 1651004273599, 'genial', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE `pistas` (
  `id` int(11) NOT NULL,
  `titulo` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `contenido` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `fecha` bigint(20) NOT NULL,
  `idvideojuego` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `pistas`
--

INSERT INTO `pistas` (`id`, `titulo`, `contenido`, `fecha`, `idvideojuego`, `idusuario`) VALUES
(3, 'Pista 1', '¡NO ABRAS LA PUERTA DEL PASILLO!', 1648925822408, 4, 9),
(4, 'Mal juego', 'A cada entrega peor que la anterior', 1648926804904, 4, 6),
(23, 'Pista 3', 'Pista 3', 1649887654251, 7, 1),
(25, 'Pista 4', 'Pista 4', 1650227258270, 16, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `titulo` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `contenido` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `idusuario` int(11) NOT NULL,
  `idvideojuego` int(11) NOT NULL,
  `fecha` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `reviews`
--

INSERT INTO `reviews` (`id`, `titulo`, `contenido`, `idusuario`, `idvideojuego`, `fecha`) VALUES
(4, 'No da miedo', 'Lo único que da miedo de este juego es su precio', 2, 4, 1648927527663),
(7, 'Muy raro', 'No entendí nada', 2, 6, 1649095561034),
(8, 'Muy bueno', 'Todo un clásico', 9, 16, 1649190489500),
(9, 'Nueva review', 'Los chicos de GearBox, los autores de Borderlands, atacan de nuevo con Tiny Tina\'s Wonderlands, un videojuego que parece estéticamente heredero de la saga shooter pero en un entorno de fantasía de lo más llamativo y con el mismo empeño por el sentido del humor y la acción desenfrenada en primera persona, donde disparar, saquear, da espadazos y lanzar hechizos para derrotar al Señor de los Dragones abriéndose paso por mazmorras llenas de botín y enemigos.', 9, 6, 1649190544112),
(10, 'Me gustó', 'Buen juego', 6, 12, 1649444080187),
(11, 'No está mal', 'Interesante', 2, 15, 1649675394365),
(14, 'Gran juego', 'Joya de principios de los 2000', 2, 22, 1650225566611);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `password` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `rol` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `avatar` varchar(100) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `banner` varchar(100) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `descripcion` varchar(1000) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `color` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `password`, `rol`, `avatar`, `banner`, `descripcion`, `color`, `activo`) VALUES
(1, 'admin', '$2a$12$Xa2I6G51cmJrx/hAvBcOu..Cnk7nXQ.T92tc9eVNjcCGyMo/A/GnW', 'ROLE_ADMIN', 'https://cdn.imgbin.com/21/5/11/imgbin-pixel-art-doge-run-jump-doge-j6b6EFR23GC28HWbueQrEPpWc.jpg', 'https://image.shutterstock.com/image-vector/vector-pixel-background-evening-sky-260nw-1649940421.jpg', 'Hola me llamo admin', 'LightSalmon', 1),
(2, 'rober23', '$2a$10$Br/RzrOeW7pK/eh/BgO1XuiXcmROACFSRnhnD4Jy.9jsdq/PUVrP2', 'ROLE_USER', 'https://www.kindpng.com/picc/m/233-2336731_avatar-the-last-airbender-pixel-art-png-download.png', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkFGCGXwYsQ5_DAVuK8iO-CgTnGhk-QYEm4A&usqp=CAU', 'Hola me llamo rober23 y me gusta Avatar', 'LightSalmon', 1),
(6, 'ramon', '$2a$10$DzsNw583Adg.lrM20Pf7POYKFcAzlFhCStg2CmJ23Mgkw3hH7IpP2', 'ROLE_USER', 'https://simg.nicepng.com/png/small/157-1570528_i-need-some-gfx-super-mario-bros-mario.png', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkFGCGXwYsQ5_DAVuK8iO-CgTnGhk-QYEm4A&usqp=CAU', NULL, 'lightsteelblue', 1),
(8, 'sandro', '$2a$10$Br/RzrOeW7pK/eh/BgO1XuiXcmROACFSRnhnD4Jy.9jsdq/PUVrP2', 'ROLE_USER', 'https://pbs.twimg.com/profile_images/1435683902887235597/6-jVIold_400x400.jpg', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkFGCGXwYsQ5_DAVuK8iO-CgTnGhk-QYEm4A&usqp=CAU', NULL, NULL, 1),
(9, 'isidro', '$2a$10$xoJERJXj4sh3sy6n9hZ3puwOsNI5OB4NIuse83/4INO8n6K.RYfV.', 'ROLE_USER', 'http://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/71912697f4d8761.png', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkFGCGXwYsQ5_DAVuK8iO-CgTnGhk-QYEm4A&usqp=CAU', '\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or', 'DarkSeaGreen', 1),
(11, 'lora', '$2a$10$F7WIHoskqyGJqobB.m33RuQjV1DrXPCUx8Kpm8MIxENKJLS8RRZB.', 'ROLE_USER', 'https://raw.githubusercontent.com/wahidmagdy/pixel-banner/HEAD/demo.gif', 'https://raw.githubusercontent.com/wahidmagdy/pixel-banner/HEAD/demo.gif', 'Hola soy Lora', 'DarkSeaGreen', 1),
(19, 'pablo', '$2a$10$f1s0owb./BN9dmkj7k7Xwe7QwM3rCEXQfUu2RjNwkK7gkQREz0/7.', 'ROLE_USER', 'https://cdn.pixabay.com/photo/2016/03/31/19/10/avatar-1294767_960_720.png', 'https://wallpapercave.com/wp/2ZBotFo.jpg', 'Hola soy nuevo aquí', 'LightSalmon', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegos`
--

CREATE TABLE `videojuegos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fecha` bigint(20) NOT NULL,
  `descripcion` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `imagen` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `videojuegos`
--

INSERT INTO `videojuegos` (`id`, `nombre`, `fecha`, `descripcion`, `imagen`) VALUES
(1, 'Elden Ring', 1640995200000, 'Elden Ring es el nuevo videojuego de FromSoftware, creadores de Dark Souls, Sekiro o Bloodborne. Se trata del nuevo título de acción y rol para un jugador ideado por Hidetaka Miyazaki, que en esta ocasión, estrenará un mundo abierto más grande y ambicioso. Su argumento y mitología están firmados por George R.R. Martin, autor de Canción de hielo y fuego.', 'https://esports.as.com/2021/06/10/bonus/videojuegos/Imagen-Elden-Ring_1472562782_690256_1440x810.png'),
(4, '405 Horror Escape Room', 1647820800000, '¿Eres fan del morbo y el misterio? ¿Tienes ojo para los rompecabezas? Luego explora el oscuro misterio del apartamento 405 en este juego de suspenso al estilo de una sala de escape. Juega como John, un escritor de artículos recientemente sin trabajo en el Londres de 1960, que está tratando de encontrar su última historia hasta que comienza a escuchar ruidos en el piso de abajo...', 'https://i.ytimg.com/vi/1ObGsS2XCHM/mqdefault.jpg'),
(5, 'Adome', 1615334400000, 'Adome es un juego de acción y aventura para un solo jugador que combina la rapidez y agilidad del parkour, acción frenética con una narrativa profunda y emotiva.  El juego nos transporta a una bella y espectacular urbe futurista automatizada, cubierta por una cúpula y poblada únicamente por robots, llamada Ciudad de Luz. En ella serás IB1, un robot de aspecto humanoide, cuya funcionalidad es instruir y proteger a las semillas, niños que están a su cargo.  El núcleo jugable de Adome se basa en la satisfactoria sensación de ritmo, fluidez y control total combinado con un desarrollo cuidado y variado.', 'https://i0.wp.com/www.gamingcypher.com/wp-content/uploads/2018/08/Adome-Gaming-Cypher-800x450.png?resize=525%2C295&ssl=1'),
(6, 'Tiny Tina\'s Wonderlands', 1616630400000, 'Los chicos de GearBox, los autores de Borderlands, atacan de nuevo con Tiny Tina\'s Wonderlands, un videojuego que parece estéticamente heredero de la saga shooter pero en un entorno de fantasía de lo más llamativo y con el mismo empeño por el sentido del humor y la acción desenfrenada en primera persona, donde disparar, saquear, da espadazos y lanzar hechizos para derrotar al Señor de los Dragones abriéndose paso por mazmorras llenas de botín y enemigos.', 'https://sm.ign.com/ign_es/gallery/t/tiny-tinas/tiny-tinas-wonderland-summer-game-fest-screenshots_6sgu.jpg'),
(7, 'Kirby y la tierra olvidada', 1616630400000, 'Kirby y la tierra olvidada es un videojuego de acción y plataformas en 3D para Nintendo Switch que permite a los jugadores moverse libremente por distintas zonas mientras usan las conocidas habilidades de copia de Kirby. La aventura lleva a la popular bola rosada de la compañía japonesa hasta un mundo misterioso repleto de estructuras abandonadas de una antigua civilización.  Kirby y la tierra olvidada da a la bolsa rosa de Nintendo la transmorfosis, una habilidad que le permite absorbe objetos y transformarse en ellos. Por ejemplo, puede convertirse en coche y moverse a toda pastilla, transformarse en dispensador para atacar con latas de zumo e incluso adoptar la forma de un cono perforante. Además, Kirby también peude hacer evolucionar sus talentos de copia en la armería de Waddle Dee, ubisca en la homónima ciudad. Ahora bien, ¿será suficiente para que Kirbye rescate a los Waddle Dees y devuelva la paz a este mundo?', 'https://consejosjuegospro.com/wp-content/uploads/2022/01/Kirby-and-the-Forgotten-Land-Fecha-de-lanzamiento-Todo.jpg'),
(8, 'GhostWire: Tokyo', 1648166400000, 'Kirby y la tierra olvidada es un videojuego de acción y plataformas en 3D para Nintendo Switch que permite a los jugadores moverse libremente por distintas zonas mientras usan las conocidas habilidades de copia de Kirby. La aventura lleva a la popular bola rosada de la compañía japonesa hasta un mundo misterioso repleto de estructuras abandonadas de una antigua civilización.  Kirby y la tierra olvidada da a la bolsa rosa de Nintendo la transmorfosis, una habilidad que le permite absorbe objetos y transformarse en ellos. Por ejemplo, puede convertirse en coche y moverse a toda pastilla, transformarse en dispensador para atacar con latas de zumo e incluso adoptar la forma de un cono perforante. Además, Kirby también peude hacer evolucionar sus talentos de copia en la armería de Waddle Dee, ubisca en la homónima ciudad. Ahora bien, ¿será suficiente para que Kirbye rescate a los Waddle Dees y devuelva la paz a este mundo?', 'https://cdn2.unrealengine.com/egs-ghostwiretokyo-tangogameworks-g1c-00-1920x1080-22f38791fe9b.jpg?h=720&resize=1&w=1280'),
(9, 'Tunic', 1647388800000, 'Tunic es un videojuego de acción y aventuras al más puro estilo Zelda protagonizado por un pequeño zorro en un gran mundo que debemos recorrer en fórmulas que mezclan los rompecabezas y las plataformas. Esta apuesta indie para PC y consolas propone al jugador de Tunic explorar un desierto repleto de ruinas espeluznantes, así como luchar contra unas criaturas terribles presentes en esta tierra desde tiempos muy antiguos. El juego contiene combates desafiantes y una exploración intrincada en que no hay pocos secretos, así como mazmorras y jefes finales desafiantes. Es un título que hace uso de muchos elementos zelderos, pero que también se apoya en una dificultad que recoge elementos de los juegos al estilo Souls. Todo dentro de una producción independiente que goza de un aspecto visual encantador, con variedad de tonos y escenarios.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdOvTAuQ3hvNVV1RsRigHUr59KedbBshAvxOCdcusmKEP0VIqKsxYhmkkgZgsngi4-WIQ&usqp=CAU'),
(10, 'Grand Theft Auto V', 1379376000000, 'GTA 5 es la quinta entrega de la exitosa saga de videojuegos sandbox desarrollada por Rockstar Games, Grand Theft Auto, que llega con su quinta entrega ahora a Xbox Series y PS5. Con millones de ventas a sus espaldas en sus plataformas anteriores y uno de los mundos abiertos más ricos hasta la fecha, Grand Theft Auto V transporta al jugador a Los Santos, una extensa y soleada metrópolis en declive que lucha por mantenerse a flote en una era de incertidumbre económica y realities baratos que referencia de una forma paródica a la Los Ángeles de nuestro tiempo.  En este contexto tan crítico y sarcástico de la sociedad actual, tres criminales muy diferentes barajan sus probabilidades de éxito: Franklin, un estafador callejero en busca de mucho dinero; Michael, profesional ex convicto con un retiro muy diferente al que imaginaba; y por último Trevor, un maníaco violento cuya motivación pasa por encontrar drogas baratas que meterse y propinar nuevos atracos.', 'https://i.blogs.es/2c9c70/gta-20v-20portada-20grande/450_1000.jpg'),
(11, 'Harry Potter: Hogwarts Legacy', 1663804800000, 'Harry Potter: Hogwarts Legacy es un RPG de acción en mundo abierto desarrollado por Avalanche Software protagonizado por un estudiante del Colegio Hogwarts de Magia y Hechicería a comienzos del siglo XIX. El videojuego busca llevar a los usuarios a vivir un misterio totalmente inédito respecto a la saga de libros y películas en la que nuestro personaje tiene plena libertad de movimiento por la escuela y sus alrededores, la mejor forma de satisfacer a los aficionados a la obra de J.K. Rowling.  Se trata de un videojuego de acción y aventuras desarrollado por los responsables de Disney Infinity y que nos transporta al universo de Harry Potter con su aventura más ambiciosa, en una hermosa ambientación dentro del colegio de Hogwarts. Hablamos de un RPG de mundo abierto ambientado en el universo de los libros de Harry Potter que se sitúa en el sigo XIX. Encarnaremos a un estudiante que tiene la clave de un antiguo secreto que amenaza con destrozar el mundo mágico. Nuestro personaje ha recibido una aceptación tardía Hogwarts y descubriremos que posee una habilidad inusual para percibir y dominar la Magia Antigua. Podremos decidir si protegemos el secreto o caemos en las garras de la magia más siniestra.  En el videojuego, podremos visitar lugares familiares, y también algunos nuevos, mientras exploramos y descubrimos bestias fantásticas, personalizamos el personaje y realizamos las actividades típicas de la serie, como elaborar pociones, aprender hechizos y mejoramos como magos.', 'https://www.hogwartslegacy.com/images/share.jpg'),
(12, 'Lost Ark', 1644537600000, 'Tras triunfar en Corea del Sur, Lost Ark llega a Occidente cargado de los contenidos y mejoras que este RPG de acción gratis ha recibido a lo largo de los años. Con un estilo de combate que recuerda a Diablo, pero con el diseño y mecánicas propias de un MMORPG, esta aventura de rol en línea nos transporta al mundo de fantasía de Arkesia, donde debemos encontrar siete artefactos de gran poder para luchar contra los demonios.  Desarrollado por Smilegate, esta aventura de acción y rol online free to play nos permite encarnar a cinco clases de personaje con sus respectivas especializaciones, entre las que encontramos pistoleros, paladines y hechiceras con una amplia variedad de ataques y poderes únicos. Durante estas intensas batallas, los jugadores deben usar las esquivas en el momento oportuno para escapar de la muerte a la par que lanzan todo su poder de ataque.  Con montones de misiones y coleccionables, Lost Ark es un videojuego inmenso que incluye las tradicionales mazmorras para grupos (que puedes jugar en solitario), misiones de historia y hasta un barco para que explores su gigantesco mundo junto a tu tripulación. Por el camino vas a encontrar poderosos jefes finales y batallas multitudinarias, que son una de las claves del éxito de este MMORPG coreano.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRz-29N3IGvF4bm493V2guIe5lSK5AGD-HLLst-G8ZsoZJEyvG22pioHq0ihN7LKxho3do&usqp=CAU'),
(13, 'NBA 2K13', 1360540800000, 'a culminación de la franquicia NBA de 2K no solo es un juego donde toda la estrategia y atractivo del baloncesto están perfectamente reflejados con un control intuitivo, eficaz y que deja en evidencia, sin ir más lejos, a sus contrapartidas futboleras, sino que es un auténtico joyón para fans de la liga norteamericana: hay un modo en el que se puede encarnar a decenas de leyendas de la liga en sus partidos y momentos históricos, y replicar los encuentros que los hicieron grandes. Un monumento al deporte bien entendido. Por eso no hay nadie dándole pataditas a una pelota como un perrete.', 'https://w7.pngwing.com/pngs/894/752/png-transparent-nba-2k13-nba-2k14-nba-2k17-nba-2k12-los-angeles-clippers-others-computer-wallpaper-sports-lebron-james.png'),
(14, 'Counter Strike', 973641600000, 'Dejémonos de tonterías: Counter-Strike (en pie desde 1999, pero Source es la mejor versión) sigue siendo la madre del cordero en términos de enfrentar a un par de equipos de tíos armados con armamento rudimentario y su puntería y velocidad. Ni alta tecnología ni complicados sistemas de clases: tú, tu rifle y unos mapas perfectos e inagotables casi diez años después. Y si recuerdas un cibercafé de hace un lustro y pico sabrás que no exageramos lo más mínimo.', 'http://media.steampowered.com/apps/csgo/blog/images/fb_image.png?v=6'),
(15, 'OVERWATCH', 1464048000000, 'Un juego de disparos online que no sólo hizo historia en su año de publicación, sino que nos trajo toda la revolución del \'Fortnite\' sólo uno después. Sin embargo, su mecánica, personajes, colores explosivos y universo son muchísimo mejores: hablamos de un fenómeno fascinante que, no podemos olvidarlo, ha hecho muchísimo por la representación inclusiva en los videojuegos. No importa que ganes o pierdas la partida: \'Overwatch\' siempre ofrece horas de diversión.Un juego de disparos online que no sólo hizo historia en su año de publicación, sino que nos trajo toda la revolución del \'Fortnite\' sólo uno después. Sin embargo, su mecánica, personajes, colores explosivos y universo son muchísimo mejores: hablamos de un fenómeno fascinante que, no podemos olvidarlo, ha hecho muchísimo por la representación inclusiva en los videojuegos. No importa que ganes o pierdas la partida: \'Overwatch\' siempre ofrece horas de diversión.', 'https://1000marcas.net/wp-content/uploads/2020/03/Overwatch-Fuente.jpg'),
(16, 'METROID PRIME', 1037577600000, 'Un poco más allá hablamos de Super Metroid como un juego esencial para entender las posibilidades de los títulos de aventura y exploración en 2D. Este es el salto de la serie a las 3D, y... lo mismo. Todo lo contrario a un shooter de matar árabes con los que te enfrascas cuando tu novia sale a una despedida de soltera: entornos abiertos, llenos de secretos por descubrir, elegante, atmosférico, con una historia que se va desvelando con una narrativa impecable... y excelentemente diseñado y ejecutado. Las tres entregas de Prime, por cierto, están recopiladas para Wii en Metroid Prime Trilogy. Ya me tardas.', 'https://uvejuegos.com/img/caratulas/263/0_metroid_prime_gc_palmasoft_eur.jpg'),
(17, 'THE WITNESS', 1453766400000, 'Videojuego de autor donde los haya, The Witness es una pieza de orfebrería cien por cien Jonathan Blow. También es, en muchos sentidos, el juego de puzles que acaba con todos los juegos de puzles, o que lleva el género hasta cotas impensables hasta el momento. Quizá esa característica lo haga un tanto impermeable para el jugador casual, pero todo el que acepte sus reglas del juego está a punto de descubrir una propuesta tan radical que se permite funcionar a base de epifanías.', 'https://cdn.akamai.steamstatic.com/steam/apps/210970/capsule_616x353.jpg?t=1598383120'),
(22, 'Metal Gear Solid 3', 1197417600000, 'Descripción de Metal Gear Solid 3', 'http://www.retroplace.com/pics/ps2/packshots/78353--metal-gear-solid-3-subsistence.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuego_genero`
--

CREATE TABLE `videojuego_genero` (
  `id` int(11) NOT NULL,
  `idvideojuego` int(11) NOT NULL,
  `idgenero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `videojuego_genero`
--

INSERT INTO `videojuego_genero` (`id`, `idvideojuego`, `idgenero`) VALUES
(5, 4, 9),
(6, 5, 1),
(7, 5, 2),
(38, 6, 8),
(9, 7, 1),
(10, 7, 10),
(11, 8, 1),
(12, 8, 2),
(13, 9, 1),
(14, 9, 2),
(15, 10, 1),
(16, 10, 11),
(17, 11, 1),
(18, 11, 2),
(19, 11, 8),
(20, 12, 2),
(21, 12, 5),
(22, 12, 8),
(23, 13, 5),
(24, 13, 6),
(25, 14, 5),
(26, 14, 6),
(27, 15, 1),
(28, 15, 11),
(29, 16, 2),
(30, 16, 10),
(31, 17, 2),
(32, 17, 9),
(55, 22, 1),
(56, 22, 2),
(57, 22, 11);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idusuariofollower` (`idusuariofollower`,`idusuariofollowed`),
  ADD KEY `idusuariofollowed` (`idusuariofollowed`);

--
-- Indices de la tabla `generos`
--
ALTER TABLE `generos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `juego_usuario`
--
ALTER TABLE `juego_usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idvideojuego` (`idvideojuego`,`idusuario`),
  ADD KEY `idusuario` (`idusuario`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idautor` (`idautor`),
  ADD KEY `iddestinatario` (`iddestinatario`);

--
-- Indices de la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idvideojuego` (`idvideojuego`),
  ADD KEY `idusuario` (`idusuario`);

--
-- Indices de la tabla `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idusuario` (`idusuario`),
  ADD KEY `idvideojuego` (`idvideojuego`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `videojuego_genero`
--
ALTER TABLE `videojuego_genero`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idvideojuego` (`idvideojuego`,`idgenero`),
  ADD KEY `idgenero` (`idgenero`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

--
-- AUTO_INCREMENT de la tabla `generos`
--
ALTER TABLE `generos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `juego_usuario`
--
ALTER TABLE `juego_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pistas`
--
ALTER TABLE `pistas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `videojuego_genero`
--
ALTER TABLE `videojuego_genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`idusuariofollowed`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`idusuariofollower`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `juego_usuario`
--
ALTER TABLE `juego_usuario`
  ADD CONSTRAINT `juego_usuario_ibfk_1` FOREIGN KEY (`idvideojuego`) REFERENCES `videojuegos` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `juego_usuario_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`idautor`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `mensajes_ibfk_2` FOREIGN KEY (`iddestinatario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD CONSTRAINT `pistas_ibfk_1` FOREIGN KEY (`idvideojuego`) REFERENCES `videojuegos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pistas_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`idvideojuego`) REFERENCES `videojuegos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `videojuego_genero`
--
ALTER TABLE `videojuego_genero`
  ADD CONSTRAINT `videojuego_genero_ibfk_1` FOREIGN KEY (`idgenero`) REFERENCES `generos` (`id`),
  ADD CONSTRAINT `videojuego_genero_ibfk_2` FOREIGN KEY (`idvideojuego`) REFERENCES `videojuegos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
