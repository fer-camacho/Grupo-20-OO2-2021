USE `Grupo-20-BDD-OO2-2021`;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES(1, 'admin', true, 'admin', 123, '$2a$10$fsULUPafGxUEmV.RvE4PUun.Tv42TzLsXcfOWgPq2JQlOa/G1cQMm', 'dni', 'admin');

INSERT INTO `user` VALUES(2, 'audit', true, 'audit', 321, '$2a$10$7VcFzaibGjvfy4VtHYF8J.MjfBwByNlUO6AlbStQ1pDf.JlVaTTT6', 'dni', 'audit');
UNLOCK TABLES;

LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES (1,'ROLE_ADMIN',1);
INSERT INTO `user_role` VALUES (2,'ROLE_AUDIT',2);
UNLOCK TABLES;

--Grupo-20-OO2-2021 

--Grupo-20-BDD-OO2-2021