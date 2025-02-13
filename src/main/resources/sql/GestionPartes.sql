-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestionpartes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestionpartes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestionpartes`;


-- -----------------------------------------------------
-- Table `gestionpartes`.`grupos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`grupos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`grupos` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `nombre_grupo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_grupo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;


-- -----------------------------------------------------
-- Table `gestionpartes`.`alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`alumnos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`alumnos` (
  `id_alum` INT NOT NULL AUTO_INCREMENT,
  `id_grupo` INT NOT NULL,
  `nombre_alum` VARCHAR(255) NULL DEFAULT NULL,
  `numero_expediente` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_alum`),
  INDEX `FKoif6noujgnb1q4hfucdj3by8q` (`id_grupo` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`alumnos`
  ADD CONSTRAINT `FKoif6noujgnb1q4hfucdj3by8q`
  FOREIGN KEY (`id_grupo`)
  REFERENCES `gestionpartes`.`grupos` (`id_grupo`);


-- -----------------------------------------------------
-- Table `gestionpartes`.`profesores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`profesores`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`profesores` (
  `id_profesor` INT NOT NULL AUTO_INCREMENT,
  `contrasena` VARCHAR(64) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `numero_asignado` VARCHAR(4) NULL DEFAULT NULL,
  `tipo` ENUM('JEFE_DE_ESTUDIOS', 'PROFESOR') NULL DEFAULT NULL,
  PRIMARY KEY (`id_profesor`),
  UNIQUE INDEX `UK_p6ltb4s5eu3ymeanq6rdw944v` (`numero_asignado` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;


-- -----------------------------------------------------
-- Table `gestionpartes`.`puntos_partes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`puntos_partes`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`puntos_partes` (
  `id_puntos` INT NOT NULL AUTO_INCREMENT,
  `color` ENUM('VERDE','NARANJA', 'ROJO'),
  `puntos` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_puntos`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;


-- -----------------------------------------------------
-- Table `gestionpartes`.`horas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`horas`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`horas` (
  `id_hora` INT NOT NULL AUTO_INCREMENT,
  `hora` VARCHAR(13) NULL DEFAULT NULL,
  PRIMARY KEY (`id_hora`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;


-- -----------------------------------------------------
-- Table `gestionpartes`.`partes_incidencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`partes_incidencia`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`partes_incidencia` (
  `id_parte` INT NOT NULL AUTO_INCREMENT,
  `id_alum` INT NULL DEFAULT NULL,
  `id_puntos` INT NULL DEFAULT NULL,
  `id_profesor` INT NULL DEFAULT NULL,
  `id_hora` INT NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `sancion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_parte`),
  INDEX `FKqrx661g5lij25bl2plx6cb2pl` (`id_alum` ASC),
  INDEX `FKniytl2x2lvm632ic1904a1bhb` (`id_profesor` ASC),
  INDEX `FKpuntos` (`id_puntos` ASC)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKniytl2x2lvm632ic1904a1bhb`
  FOREIGN KEY (`id_profesor`)
  REFERENCES `gestionpartes`.`profesores` (`id_profesor`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKqrx661g5lij25bl2plx6cb2pl`
  FOREIGN KEY (`id_alum`)
  REFERENCES `gestionpartes`.`alumnos` (`id_alum`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKpuntos`
  FOREIGN KEY (`id_puntos`)
  REFERENCES `gestionpartes`.`puntos_partes` (`id_puntos`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKhora`
  FOREIGN KEY (`id_hora`)
  REFERENCES `gestionpartes`.`horas` (`id_hora`);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- Inserción de profesores en la tabla 'profesores'
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Juan Perez', 'jefe_de_estudios', 1001, 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Alberto Perez', 'profesor', 1002, 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Maria Lopez', 'profesor', 1003, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Carlos Sanchez', 'profesor', 1004, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Laura Gomez', 'profesor', 1005, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Fernando Ruiz', 'profesor', 1006, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');

-- Inserción de grupos en la tabla 'grupos'
INSERT INTO grupos (nombre_grupo) VALUES('1º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('1º ESO B');
INSERT INTO grupos (nombre_grupo) VALUES('2º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('2º ESO B');
INSERT INTO grupos (nombre_grupo) VALUES('3º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('3º ESO B');
INSERT INTO grupos (nombre_grupo) VALUES('4º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('4º ESO B');

-- Inserción de alumnos en la tabla 'alumnos'
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(1, 'Alejandro García', 1001);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(1, 'María Fernández', 1002);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(2, 'Juan López', 1003);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(2, 'Laura Martínez', 1004);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(3, 'Pablo Sánchez', 1005);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(3, 'Sara González', 1006);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(4, 'David Rodríguez', 1007);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(4, 'Lucía Pérez', 1008);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(5, 'Daniel Gómez', 1009);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(5, 'Elena Díaz', 1010);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(6, 'Javier Ruiz', 1011);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(6, 'Marta Morales', 1012);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(7, 'Carlos Álvarez', 1013);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(7, 'Ana Ortega', 1014);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(8, 'Luis Navarro', 1015);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(8, 'Carmen Ramírez', 1016);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(1, 'Alberto Torres', 1017);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(2, 'Paula Ibáñez', 1018);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(3, 'Miguel Romero', 1019);
INSERT INTO alumnos (id_grupo, nombre_alum, numero_expediente) VALUES(4, 'Isabel Hernández', 1020);

-- Inserción de puntos en la tabla 'puntos_partes'
INSERT INTO puntos_partes (color, puntos) VALUES('VERDE', 1);
INSERT INTO puntos_partes (color, puntos) VALUES('NARANJA', 6);
INSERT INTO puntos_partes (color, puntos) VALUES('ROJO', 12);

-- Inserción de horas en la tabla 'horas'
INSERT INTO horas (hora) VALUES('08:30 - 09:20');
INSERT INTO horas (hora) VALUES('09:25 - 10:15');
INSERT INTO horas (hora) VALUES('10:20 - 11:10');
INSERT INTO horas (hora) VALUES('11:40 - 12:30');
INSERT INTO horas (hora) VALUES('12:35 - 13:25');
INSERT INTO horas (hora) VALUES('13:30 - 14:20');
INSERT INTO horas (hora) VALUES('16:00 - 16:50');
INSERT INTO horas (hora) VALUES('16:55 - 17:45');
INSERT INTO horas (hora) VALUES('17:50 - 18:40');
INSERT INTO horas (hora) VALUES('18:55 - 19:45');
INSERT INTO horas (hora) VALUES('19:50 - 20:40');
INSERT INTO horas (hora) VALUES('20:45 - 21:35');

-- Inserción de partes de incidencia en la tabla 'partes_incidencia'
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(1, 1, 'Incidente menor', '2024-01-01', 1, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(2, 2, 'Incidente moderado', '2024-01-02', 2, 'Suspensión', 2);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(3, 3, 'Incidente grave', '2024-01-03', 3, 'Expulsión', 3);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(4, 4, 'Incidente menor', '2024-01-04', 4, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(5, 5, 'Incidente moderado', '2024-01-05', 5, 'Suspensión', 2);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(6, 6, 'Incidente grave', '2024-01-06', 6, 'Expulsión', 3);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(7, 1, 'Incidente menor', '2024-01-07', 7, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(8, 2, 'Incidente moderado', '2024-01-08', 8, 'Suspensión', 2);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(9, 3, 'Incidente grave', '2024-01-09', 9, 'Expulsión', 3);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(10, 4, 'Incidente menor', '2024-01-10', 10, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(11, 5, 'Incidente moderado', '2024-01-11', 11, 'Suspensión', 2);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(12, 6, 'Incidente grave', '2024-01-12', 12, 'Expulsión', 3);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(13, 1, 'Incidente menor', '2024-01-13', 1, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(14, 2, 'Incidente moderado', '2024-01-14', 2, 'Suspensión', 2);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(15, 3, 'Incidente grave', '2024-01-15', 3, 'Expulsión', 3);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(16, 4, 'Incidente menor', '2024-01-16', 4, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(17, 5, 'Incidente moderado', '2024-01-17', 5, 'Suspensión', 2);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(18, 6, 'Incidente grave', '2024-01-18', 6, 'Expulsión', 3);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(19, 1, 'Incidente menor', '2024-01-19', 7, 'Advertencia', 1);
INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, id_hora, sancion, id_puntos) VALUES(20, 2, 'Incidente moderado', '2024-01-20', 8, 'Suspensión', 2);