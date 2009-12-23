SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `iBet` ;
CREATE SCHEMA IF NOT EXISTS `iBet` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `iBet`;

-- -----------------------------------------------------
-- Table `iBet`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`users` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`users` (
  `username` VARCHAR(250) NOT NULL ,
  `nombre` VARCHAR(100) NOT NULL ,
  `apellido` VARCHAR(100) NOT NULL ,
  `fechaNacimiento` DATE NULL ,
  `sexo` VARCHAR(1) NOT NULL ,
  `correo` VARCHAR(100) UNIQUE NOT NULL ,
  `telefono` VARCHAR(100) NULL ,
  `pais` VARCHAR(100) NULL ,
  `ciudad` VARCHAR(100) NULL ,
  `codigoPostal` INT NULL ,
  `estado` VARCHAR(100) NULL ,
  `calle` VARCHAR(100) NULL ,
  `password` VARCHAR(250) NOT NULL ,
  `enabled` TINYINT(1) NOT NULL ,
  `confirmado` TINYINT(1) NOT NULL ,
  `avatar` VARCHAR(250) NULL ,
  PRIMARY KEY (`username`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`authorities` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`authorities` (
  `authority` VARCHAR(50) NOT NULL ,
  `username` VARCHAR(50) NOT NULL ,
  INDEX `fk_authorities_users1` (`username` ASC) ,
  CONSTRAINT `fk_authorities_users1`
    FOREIGN KEY (`username` )
    REFERENCES `iBet`.`users` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`MEDIO_PAGO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`MEDIO_PAGO` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`MEDIO_PAGO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) UNIQUE NOT NULL ,
  `activo` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`USUARIO_MEDIO_PAGO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`USUARIO_MEDIO_PAGO` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`USUARIO_MEDIO_PAGO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(250) NOT NULL ,
  `idMedioPago` INT NOT NULL ,
  `activo` TINYINT(1) NOT NULL ,
  `fechaInicio` DATE NOT NULL ,
  `fechaFin` DATE NULL ,
  `montoMaximo` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`, `username`, `idMedioPago`) ,
  INDEX `fk_USUARIO_has_MEDIO_PAGO_USUARIO1` (`username` ASC) ,
  INDEX `fk_USUARIO_has_MEDIO_PAGO_MEDIO_PAGO1` (`idMedioPago` ASC) ,
  CONSTRAINT `fk_USUARIO_has_MEDIO_PAGO_USUARIO1`
    FOREIGN KEY (`username` )
    REFERENCES `iBet`.`users` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_MEDIO_PAGO_MEDIO_PAGO1`
    FOREIGN KEY (`idMedioPago` )
    REFERENCES `iBet`.`MEDIO_PAGO` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`POLITICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`POLITICA` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`POLITICA` (
  `id` INT NOT NULL ,
  `montoMaximo` DOUBLE NOT NULL ,
  `finalizarAntes` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`CATEGORIA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`CATEGORIA` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`CATEGORIA` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(100) NOT NULL ,
  `empate` TINYINT(1) NOT NULL ,
  `logicaAutomatica` TINYINT(1) NOT NULL ,
  `habilitada` TINYINT(1) NOT NULL ,
  `nombreLogica` VARCHAR(100) NULL DEFAULT NULL ,
  `idCategoria` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_CATEGORIA_CATEGORIA1` (`idCategoria` ASC) ,
  CONSTRAINT `fk_CATEGORIA_CATEGORIA1`
    FOREIGN KEY (`idCategoria` )
    REFERENCES `iBet`.`CATEGORIA` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`EVENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`EVENTO` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`EVENTO` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `fecha` DATE NOT NULL ,
  `hora` TIME NOT NULL ,
  `fechaMaxima` DATE NOT NULL ,
  `horaMaxima` TIME NOT NULL ,
  `resultado` VARCHAR(200) NOT NULL ,
  `estatus` TINYINT(1) NOT NULL ,
  `finalizado` TINYINT(1) NOT NULL ,
  `imagenEvento` VARCHAR(200) NULL DEFAULT NULL ,
  `idCategoria` INT NOT NULL ,
  `idPolitica` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_EVENTO_POLITICA1` (`idPolitica` ASC) ,
  INDEX `fk_EVENTO_CATEGORIA1` (`idCategoria` ASC) ,
  CONSTRAINT `fk_EVENTO_POLITICA1`
    FOREIGN KEY (`idPolitica` )
    REFERENCES `iBet`.`POLITICA` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EVENTO_CATEGORIA1`
    FOREIGN KEY (`idCategoria` )
    REFERENCES `iBet`.`CATEGORIA` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`PARTICIPANTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`PARTICIPANTE` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`PARTICIPANTE` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) UNIQUE NOT NULL ,
  `descripcion` VARCHAR(45) NULL ,
  `idCategoria` INT NOT NULL ,
  PRIMARY KEY (`id`)  ,
  INDEX `fk_EVENTO_PARTICIPANTE1` (`idCategoria` ASC) ,
   CONSTRAINT `fk_EVENTO_PARTICIPANTE1`
    FOREIGN KEY (`idCategoria` )
    REFERENCES `iBet`.`CATEGORIA` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`TABLERO_GANANCIA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`TABLERO_GANANCIA` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`TABLERO_GANANCIA` (
  `idEvento` INT NOT NULL ,
  `idParticipante` INT NOT NULL ,
  `gano` TINYINT(1) NULL ,
  `empato` TINYINT(1) NULL ,
  `propocionGano` DOUBLE NOT NULL ,
  `proporcionEmpate` DOUBLE NULL ,
  PRIMARY KEY (`idEvento`, `idParticipante`) ,
  INDEX `fk_EVENTO_has_PARTICIPANTE_EVENTO1` (`idEvento` ASC) ,
  INDEX `fk_EVENTO_has_PARTICIPANTE_PARTICIPANTE1` (`idParticipante` ASC) ,
  CONSTRAINT `fk_EVENTO_has_PARTICIPANTE_EVENTO1`
    FOREIGN KEY (`idEvento` )
    REFERENCES `iBet`.`EVENTO` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EVENTO_has_PARTICIPANTE_PARTICIPANTE1`
    FOREIGN KEY (`idParticipante` )
    REFERENCES `iBet`.`PARTICIPANTE` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iBet`.`APUESTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iBet`.`APUESTA` ;

CREATE  TABLE IF NOT EXISTS `iBet`.`APUESTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(250) NOT NULL ,
  `idMedioPago` INT NOT NULL ,
  `fecha` DATE NOT NULL ,
  `monto` DOUBLE NOT NULL ,
  `ganador` BOOLEAN NULL ,
  `gano` TINYINT(1) NULL ,
  `empato` TINYINT(1) NULL ,
  `idEvento` INT NOT NULL ,
  `idParticipante` INT NOT NULL ,
  PRIMARY KEY (`id`, `username`, `idMedioPago`) ,
  INDEX `fk_USUARIO_has_MEDIO_PAGO_USUARIO2` (`username` ASC) ,
  INDEX `fk_USUARIO_has_MEDIO_PAGO_MEDIO_PAGO2` (`idMedioPago` ASC) ,
  INDEX `fk_APUESTA_EVENTO_PARTICIPANTE1` (`idEvento` ASC, `idParticipante` ASC) ,
  CONSTRAINT `fk_USUARIO_has_MEDIO_PAGO_USUARIO2`
    FOREIGN KEY (`username` )
    REFERENCES `iBet`.`users` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_MEDIO_PAGO_MEDIO_PAGO2`
    FOREIGN KEY (`idMedioPago` )
    REFERENCES `iBet`.`MEDIO_PAGO` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_APUESTA_EVENTO_PARTICIPANTE1`
    FOREIGN KEY (`idEvento` , `idParticipante` )
    REFERENCES `iBet`.`TABLERO_GANANCIA` (`idEvento` , `idParticipante` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `iBet`.`users`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `users` (`username`, `nombre`, `apellido`, `fechaNacimiento`, `sexo`, `correo`, `telefono`, `pais`, `ciudad`, `codigoPostal`, `estado`, `calle`, `password`, `enabled`, `confirmado`, `avatar`) VALUES ('admin', 'raul', 'barcia', '1990-01-16', 'M', 'raulbarciap@gmail.com', null, null, null, null, null, null, '827ccb0eea8a706c4c34a16891f84e7b', 1, 1, null);
INSERT INTO `users` (`username`, `nombre`, `apellido`, `fechaNacimiento`, `sexo`, `correo`, `telefono`, `pais`, `ciudad`, `codigoPostal`, `estado`, `calle`, `password`, `enabled`, `confirmado`, `avatar`) VALUES ('maya', 'maria', 'uribe', '1987-01-01', 'F', 'mayita.uribe@gmail.com', '0412 951-3436', 'VENEZUELA', 'caracas', 1010, 'MIRANDA', 'cafetal', '827ccb0eea8a706c4c34a16891f84e7b', 1, 1, null);
INSERT INTO `users` (`username`, `nombre`, `apellido`, `fechaNacimiento`, `sexo`, `correo`, `telefono`, `pais`, `ciudad`, `codigoPostal`, `estado`, `calle`, `password`, `enabled`, `confirmado`, `avatar`) VALUES ('johnny', 'jonathan', 'trujillo', '1987-08-26', 'M', 'trujillo.jonathan@gmail.com', '0412 737-4205', 'VENEZUELA', 'caracas', 1080, 'MIRANDA', 'el placer', '827ccb0eea8a706c4c34a16891f84e7b', 1, 1, null);
INSERT INTO `users` (`username`, `nombre`, `apellido`, `fechaNacimiento`, `sexo`, `correo`, `telefono`, `pais`, `ciudad`, `codigoPostal`, `estado`, `calle`, `password`, `enabled`, `confirmado`, `avatar`) VALUES ('gerardo', 'gerardo', 'barcia', '1986-01-09', 'M', 'gerardobarciap@gmail.com', '0412 704-9825', 'VENEZUELA', 'caracas', 1070, 'MIRANDA', 'la urbina', '827ccb0eea8a706c4c34a16891f84e7b', 1, 1, null);
COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`authorities`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `authorities` (`authority`, `username`) VALUES ('ROLE_ADMIN', 'admin');
INSERT INTO `authorities` (`authority`, `username`) VALUES ('ROLE_USER', 'maya');
INSERT INTO `authorities` (`authority`, `username`) VALUES ('ROLE_USER', 'johnny');
INSERT INTO `authorities` (`authority`, `username`) VALUES ('ROLE_USER', 'gerardo');
COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`MEDIO_PAGO`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `MEDIO_PAGO` (`id`, `nombre`, `activo`) VALUES (1, 'paypal', 1);
INSERT INTO `MEDIO_PAGO` (`id`, `nombre`, `activo`) VALUES (2, 'mastercard', 1);
INSERT INTO `MEDIO_PAGO` (`id`, `nombre`, `activo`) VALUES (3, 'visa', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`USUARIO_MEDIO_PAGO`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `USUARIO_MEDIO_PAGO` (`id`, `username`, `idMedioPago`, `activo`, `fechaInicio`, `fechaFin`, `montoMaximo`) VALUES (1, 'maya', 1, 1, '2009-02-01', null, 2000);
INSERT INTO `USUARIO_MEDIO_PAGO` (`id`, `username`, `idMedioPago`, `activo`, `fechaInicio`, `fechaFin`, `montoMaximo`) VALUES (2, 'johnny', 2, 1, '2009-06-20', null, 2500);
INSERT INTO `USUARIO_MEDIO_PAGO` (`id`, `username`, `idMedioPago`, `activo`, `fechaInicio`, `fechaFin`, `montoMaximo`) VALUES (3, 'gerardo', 3, 1, '2009-10-18', null, 2200);
INSERT INTO `USUARIO_MEDIO_PAGO` (`id`, `username`, `idMedioPago`, `activo`, `fechaInicio`, `fechaFin`, `montoMaximo`) VALUES (4, 'maya', 2, 1, '2009-10-26', null, 500);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`POLITICA`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `POLITICA` (`id`, `montoMaximo`, `finalizarAntes`) VALUES (1, 5000, 1);
INSERT INTO `POLITICA` (`id`, `montoMaximo`, `finalizarAntes`) VALUES (2, 7000, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`CATEGORIA`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (1, 'Futbol',       1, 0,  1, null, null);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (2, 'Liga BBVA',    1, 0,  1, null, 1);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (3, 'LVF Apertura', 1, 0,  1, null, 1);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (4, 'Beisbol',      0, 0,  1, null, null);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (5, 'LVBP',         0, 0,  1, null, 4);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (6, 'Tenis',        0, 0,  1, null, null);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (7, 'ATP Finals',   0, 0,  1, null, 6);
INSERT INTO `CATEGORIA` (`id`, `nombre`, `empate`, `logicaAutomatica`,`habilitada`, `nombreLogica`, `idCategoria`) VALUES (8, 'Hyundai Cup',  0, 0,  1, null, 6);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`EVENTO`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (1, 'Sporting Gijon - Villarreal', '2009-11-28', '08:00:00', '2009-11-28', '06:00:00', 'Ganador: Villarreal', 1, 1, null, 2, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (2, 'CF Valencia - RCD Mallorca', '2009-11-28', '08:30:00', '2009-11-28', '06:30:00', 'Ganador: CF Valencia', 1, 1, null, 2, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (3, 'FC Sevilla - Malaga', '2009-11-27', '22:00:00', '2009-11-27', '20:00:00', 'Empate', 1, 1, null, 2, 1);

INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (4, 'Anzoategui - Llaneros', '2009-11-29', '21:30:00', '2009-11-29', '19:30:00', '', 1, 0, null, 3, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (5, 'Carabobo FC - CD Lara', '2009-11-29', '21:30:00', '2009-11-29', '19:30:00', '', 1, 0, null, 3, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (6, 'Dep. Tachira - Trujillanos', '2009-11-29', '21:30:00', '2009-11-29', '19:30:00', '', 1, 0, null, 3, 1);

INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (7, 'Aguilas del Zulia - Tiburones de La Guaira', '2009-11-28', '00:00:00', '2009-11-28', '18:00:00', 'Ganador: Tiburones de La Guairas', 1, 1, null, 5, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (8, 'Bravos de Margarita - Caribes de Anzoategui', '2009-11-29', '00:00:00', '2009-11-29', '18:00:00', '', 1, 0, null, 5, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (9, 'Leones del Caracas - Navegantes del Magallanes', '2009-11-29', '00:00:00', '2009-11-29', '18:00:00', '', 1, 0, null, 5, 1);

INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (10, 'Rafael Nadal - Novak Djokovic', '2009-11-27', '15:15:00', '2009-11-27', '13:15:00', 'Ganador: Novak Djokovic', 1, 1, null, 7, 1);

INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (11, 'Lleyton Hewitt - Victor Hanescu', '2010-01-01', '23:00:00', '2010-01-01', '21:00:00', '', 1, 0, null, 8, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (12, 'Samantha Stosur - Sorana Cirstea', '2010-01-01', '23:00:00', '2010-01-01', '21:00:00', '', 1, 0, null, 8, 1);

INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (13, 'Navegantes del Magallanes - Bravos de Margarita', '2009-11-25', '20:00:00', '2009-11-25', '18:00:00', 'Ganador: Navegantes del Magallanes', 1, 1, null, 5, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (14, 'Leones del Caracas - Caribes de Anzoategui', '2009-11-25', '20:00:00', '2009-11-25', '18:00:00', 'Ganador: Leones del Caracas', 1, 1, null, 5, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (15, 'Tiburones de La Guaira - Cardenales de Lara', '2009-11-25', '20:00:00', '2009-11-25', '18:00:00', 'Ganador: Tiburones de La Guaira', 1, 1, null, 5, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (16, 'Tigres de Aragua - Aguilas del Zulia', '2009-11-25', '20:00:00', '2009-11-25', '18:00:00', 'Ganador: Tigres de Aragua', 1, 1, null, 5, 1);

INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (17, 'Athletic Club - FC Barcelona', '2010-01-15', '15:00:00', '2010-01-15', '13:00:00', '', 1, 0, null, 2, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (18, 'RC Deportivo - At. de Madrid', '2010-01-20', '17:00:00', '2010-01-20', '15:00:00', '', 1, 0, null, 2, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (19, 'Real Madrid CF - Real Racing Club', '2010-01-21', '20:00:00', '2010-01-21', '18:00:00', '', 1, 0, null, 2, 1);
INSERT INTO `EVENTO` (`id`, `nombre`, `fecha`, `hora`, `fechaMaxima`, `horaMaxima`, `resultado`, `estatus`, `finalizado`, `imagenEvento`, `idCategoria`, `idPolitica`) VALUES (20, 'RCD Mallorca - UD Almeria', '2010-01-22', '17:00:00', '2010-01-22', '19:00:00', '', 1, 0, null, 2, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`PARTICIPANTE`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (1, 'Sporting Gijon', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (2, 'Villarreal', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (3, 'CF Valencia', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (4, 'RCD Mallorca', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (5, 'FC Sevilla', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (6, 'Malaga', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (7, 'Anzoategui', '', 3);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (8, 'Llaneros', '', 3);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (9, 'Carabobo FC', '', 3);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (10, 'CD Lara', '', 3);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (11, 'Dep. Tachira', '', 3);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (12, 'Trujillanos', '', 3);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (13, 'Aguilas del Zulia', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (14, 'Tiburones de La Guaira', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (15, 'Bravos de Margarita', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (16, 'Caribes de Anzoategui', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (17, 'Leones del Caracas', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (18, 'Navegantes del Magallanes', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (19, 'Rafael Nadal', '', 6);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (20, 'Novak Djokovic', '', 6);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (21, 'Lleyton Hewitt', '', 6);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (22, 'Victor Hanescu', '', 6);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (23, 'Samantha Stosur', '', 6);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (24, 'Sorana Cirstea', '', 6);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (25, 'Cardenales de Lara', '', 4);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (26, 'Tigres de Aragua', '', 5);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (27, 'Athletic Club', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (28, 'FC Barcelona', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (29, 'RC Deportivo', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (30, 'At. de Madrid', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (31, 'Real Madrid CF', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (32, 'Real Racing Club', '', 2);
INSERT INTO `PARTICIPANTE` (`id`, `nombre`, `descripcion`, `idCategoria`) VALUES (33, 'UD Almeria', '', 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`TABLERO_GANANCIA`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (1, 1, 0, 0, 2.75, 3.40);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (1, 2, 1, 0, 2.35, 3.40);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (2, 3, 1, 0, 1.45, 4.20);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (2, 4, 0, 0, 6.45, 4.20);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (3, 5, 0, 1, 1.25, 5.50);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (3, 6, 0, 1, 10.0, 5.50);

INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (4, 7, null, null, 1.40, 4.00);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (4, 8, null, null, 6.50, 4.00);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (5, 9, null, null, 2.75, 3.25);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (5, 10, null, null, 2.25, 3.25);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (6, 11, null, null, 1.70, 3.35);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (6, 12, null, null, 4.45, 3.35);

INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (7, 13, 0, 0, 2.02, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (7, 14, 1, 0, 1.70, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (8, 15, null, null, 1.80, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (8, 16, null, null, 1.90, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (9, 17, null, null, 2.02, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (9, 18, null, null, 1.70, null);

INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (10, 19, 0, 0, 2.69, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (10, 20, 1, 0, 1.41, null);

INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (11, 21, null, null, 1.09, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (11, 22, null, null, 6.10, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (12, 23, null, null, 1.15, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (12, 24, null, null, 4.73, null);

INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (13, 18, 1, 0, 6.70, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (13, 15, 0, 0, 3.30, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (14, 17, 1, 0, 7.50, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (14, 16, 0, 0, 2.50, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (15, 14, 1, 0, 3.50, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (15, 25, 0, 0, 2.50, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (16, 26, 1, 0, 2.00, null);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (16, 13, 0, 0, 1.30, null);

INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (17, 27, null, null, 3.70, 7.10);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (17, 28, null, null, 2.40, 7.10);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (18, 29, null, null, 6.30, 1.00);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (18, 30, null, null, 2.00, 1.00);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (19, 31, null, null, 3.50, 3.35);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (19, 32, null, null, 1.25, 3.35);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (20, 4, null, null, 3.00, 2.25);
INSERT INTO `TABLERO_GANANCIA` (`idEvento`, `idParticipante`, `gano`, `empato`, `propocionGano`, `proporcionEmpate`) VALUES (20, 33, null, null, 2.30, 2.25);

COMMIT;

-- -----------------------------------------------------
-- Data for table `iBet`.`APUESTA`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `iBet`;
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (1, 'maya', 1, '2009-11-10', 500, 0, 1, 0, 1, 1);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (2, 'maya', 1, '2009-11-11', 100, null, 1, 0, 4, 7);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (3, 'maya', 1, '2009-11-12', 150, null, 1, 0, 9, 17);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (4, 'maya', 2, '2009-11-20', 50, null, 1, 0, 10, 20);

INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (5, 'gerardo', 3, '2009-11-10', 200, 1, 1, 0, 1, 2);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (6, 'gerardo', 3, '2009-11-11', 100, 1, 0, 1, 2, 3);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (7, 'gerardo', 3, '2009-11-20', 100, null, 1, 0, 9, 17);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (8, 'gerardo', 3, '2009-11-20', 250, null, 1, 0, 11, 22);

INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (9, 'johnny', 2, '2009-11-10', 400, 0, 0, 1, 1, 1);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (10, 'johnny', 2, '2009-11-11', 100, 1, 1, 0, 2, 3);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (11, 'johnny', 2, '2009-11-20', 250, null, 1, 0, 10, 19);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (12, 'johnny', 2, '2009-11-26', 150, null, 1, 0, 12, 23);

INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (13, 'maya', 1, '2009-10-18', 100, 1, 1, 0, 14, 17);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (14, 'maya', 1, '2009-10-19', 50, 0, 1, 0, 15, 25);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (15, 'maya', 1, '2009-10-20', 150, 1, 1, 0, 16, 26);

INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (18, 'gerardo', 3, '2009-10-29', 50, 1, 1, 0, 14, 17);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (19, 'gerardo', 3, '2009-11-20', 150, 1, 1, 0, 15, 14);

INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (22, 'johnny', 2, '2009-10-18', 100, 1, 1, 0, 13, 18);
INSERT INTO `APUESTA` (`id`, `username`, `idMedioPago`, `fecha`, `monto`, `ganador`, `gano`, `empato`, `idEvento`, `idParticipante`) VALUES (23, 'johnny', 2, '2009-10-29', 50, 0, 1, 0, 15, 25);

COMMIT;
