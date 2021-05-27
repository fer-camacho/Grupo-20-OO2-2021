USE `Grupo-20-BDD-OO2-2021`;

LOCK TABLES `user_role` WRITE;
insert into user_role VALUES (1,'ROLE_ADMIN');
insert into user_role VALUES (2,'ROLE_AUDIT');
UNLOCK TABLES;

LOCK TABLES `persona` WRITE;
insert into `persona` VALUES(1, 'Perez', 'Juan', 35785268, 'DNI');
insert into `persona` VALUES(2, 'Lopez', 'Alberto', 38456324, 'DNI');
insert into `persona` VALUES(3, 'Rodriguez', 'Barbara', 33659874, 'DNI');
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES(1, 'oo2.grupo20@gmail.com', true, '$2a$10$fsULUPafGxUEmV.RvE4PUun.Tv42TzLsXcfOWgPq2JQlOa/G1cQMm', 'admin', 1, 1);
INSERT INTO `user` VALUES(2, 'oo2@gmail.com', true, '$2a$10$7VcFzaibGjvfy4VtHYF8J.MjfBwByNlUO6AlbStQ1pDf.JlVaTTT6', 'audit', 1, 2);
UNLOCK TABLES;

LOCK TABLES `lugar` WRITE;
INSERT INTO `lugar` VALUES (1, '1852', 'Burzaco');
INSERT INTO `lugar` VALUES (2, '1836', 'Adrogue');
INSERT INTO `lugar` VALUES (3, '1828', 'Adrogue');
INSERT INTO `lugar` VALUES (4, '1886', 'Lomas');
INSERT INTO `lugar` VALUES (5, '1825', 'Banfield');
UNLOCK TABLES;