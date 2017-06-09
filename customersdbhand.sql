
CREATE USER 'customerjava'@'localhost' IDENTIFIED BY 'customerjava';

CREATE DATABASE customersdb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON customersdb.* TO 'customerjava'@'localhost';

use customersdb;

CREATE TABLE `customers` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `customers` (`id`, `name`, `phone`, `year`) VALUES
(2, 'Ocelot', '+34666666569', 1998),
(4, 'Wolf', '+34696684569', 1999),
(5, 'ninja', '678898963', 1997),
(6, 'pakitoupdateado', '6969663212', 1888),
(7, 'juan', '644022969', 1987);
