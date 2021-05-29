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

LOCK TABLES `rodado` WRITE;
insert into `rodado` VALUES(1,'123', 'ford');
UNLOCK TABLES;

LOCK TABLES `permiso` WRITE;
insert into `permiso` values(1, sysdate(), 1);
insert into `permiso` values(2, sysdate(), 1);
insert into `permiso` values(3, sysdate(), 1);
insert into `permiso` VALUES(4, sysdate(), 1);
insert into `permiso` values(5, sysdate(), 1);
insert into `permiso` values(6, sysdate(), 1);
insert into `permiso` values(7, sysdate(), 1);
UNLOCK TABLES;

LOCK TABLES `permiso_diario` WRITE;
insert into `permiso_diario` VALUES('asado', 1);
insert into `permiso_diario` VALUES('birra', 2);
insert into `permiso_diario` VALUES('alcohol', 3);
UNLOCK TABLES;

LOCK TABLES `permiso_periodo` WRITE;
insert into `permiso_periodo` VALUES(15, 1, 4, 1);
insert into `permiso_periodo` VALUES(20, 0, 5, 1);
insert into `permiso_periodo` VALUES(10, 1, 6, 1);
insert into `permiso_periodo` VALUES(15, 1, 7, 1);
UNLOCK TABLES;

LOCK TABLES `lugar` WRITE;
insert into `lugar` (id_lugar, codigo_postal, lugar) VALUES (1, '1852', 'Burzaco');
insert into `lugar` (id_lugar, codigo_postal, lugar) VALUES(2, '1846', 'Adrogue');
insert into `lugar` (id_lugar, codigo_postal, lugar) VALUES(3, '1883', 'Temperley');
insert into `lugar` (id_lugar, codigo_postal, lugar) VALUES(4, '1832', 'Lomas');
UNLOCK TABLES;

