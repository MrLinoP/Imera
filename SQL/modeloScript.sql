-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Pabellon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Pabellon` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Pabellon` (
  `idPabellon` VARCHAR(1) NOT NULL,
  `numeroPisos` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPabellon`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Piso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Piso` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Piso` (
  `idPiso` INT NOT NULL,
  `idPabellon` VARCHAR(1) NOT NULL,
  `numeroAulas` INT NOT NULL,
  PRIMARY KEY (`idPiso`, `idPabellon`),
  INDEX `fk_Piso_Pabellón_idx` (`idPabellon` ASC) VISIBLE,
  CONSTRAINT `fk_Piso_Pabellón`
    FOREIGN KEY (`idPabellon`)
    REFERENCES `mydb`.`Pabellon` (`idPabellon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Aula` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Aula` (
  `idAula` INT NOT NULL,
  `idPiso` INT NOT NULL,
  `idPabellon` VARCHAR(1) NOT NULL,
  `aforo` INT NOT NULL,
  `numeroEnchufes` INT NOT NULL,
  `tieneHorarios` TINYINT NOT NULL,
  PRIMARY KEY (`idAula`, `idPiso`, `idPabellon`),
  INDEX `fk_Aula_Piso1_idx` (`idPiso` ASC, `idPabellon` ASC) VISIBLE,
  CONSTRAINT `fk_Aula_Piso1`
    FOREIGN KEY (`idPiso` , `idPabellon`)
    REFERENCES `mydb`.`Piso` (`idPiso` , `idPabellon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Hora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Hora` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Hora` (
  `idHora` INT NOT NULL,
  `horaIni` INT NOT NULL,
  `horaFin` INT NOT NULL,
  PRIMARY KEY (`idHora`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Horario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Horario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Horario` (
  `idPabellon` VARCHAR(1) NOT NULL,
  `idPiso` INT NOT NULL,
  `idAula` INT NOT NULL,
  `idHora` INT NOT NULL,
  `idDia` DATE NOT NULL,
  `estado` ENUM('DISPONIBLE', 'AULA_LIBRE', 'AULA_LIBRE_SEMESTRAL', 'AULA_RESERVADA', 'AULA_CLASE') NOT NULL DEFAULT 'DISPONIBLE',
  INDEX `fk_RegistroHorarios_Aula1_idx` (`idAula` ASC, `idPiso` ASC, `idPabellon` ASC) VISIBLE,
  INDEX `fk_RegistroHorarios_Hora1_idx` (`idHora` ASC) VISIBLE,
  PRIMARY KEY (`idPiso`, `idAula`, `idHora`, `idDia`, `idPabellon`),
  CONSTRAINT `fk_RegistroHorarios_Aula1`
    FOREIGN KEY (`idAula` , `idPiso` , `idPabellon`)
    REFERENCES `mydb`.`Aula` (`idAula` , `idPiso` , `idPabellon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroHorarios_Hora1`
    FOREIGN KEY (`idHora`)
    REFERENCES `mydb`.`Hora` (`idHora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Persona` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `codigoPucp` INT NOT NULL,
  `correoPucp` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidoP` VARCHAR(45) NOT NULL,
  `apellidoM` VARCHAR(45) NOT NULL,
  `turno` ENUM('MAÑANA', 'TARDE') NULL,
  `activo` CHAR(1) NOT NULL,
  `contrasenha` VARCHAR(45) NOT NULL,
  `rol` ENUM('INTENDENTE', 'AUXILIAR') NOT NULL,
  `idPabellon` VARCHAR(1) NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE INDEX `codigoPucp_UNIQUE` (`codigoPucp` ASC) VISIBLE,
  UNIQUE INDEX `correoPucp_UNIQUE` (`correoPucp` ASC) VISIBLE,
  INDEX `fk_Persona_Pabellon1_idx` (`idPabellon` ASC) VISIBLE,
  CONSTRAINT `fk_Persona_Pabellon1`
    FOREIGN KEY (`idPabellon`)
    REFERENCES `mydb`.`Pabellon` (`idPabellon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CambioDeEstado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`CambioDeEstado` ;

CREATE TABLE IF NOT EXISTS `mydb`.`CambioDeEstado` (
  `idCambioDeEstado` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NOT NULL,
  `idAula` INT NOT NULL,
  `idPiso` INT NOT NULL,
  `idPabellon` VARCHAR(1) NOT NULL,
  `idHora` INT NOT NULL,
  `idDia` DATE NOT NULL,
  `fechaCambio` DATETIME NOT NULL,
  `estadoInicial` ENUM('DISPONIBLE', 'AULA_LIBRE', 'AULA_LIBRE_SEMESTRAL', 'AULA_RESERVADA', 'AULA_CLASE') NOT NULL,
  `nuevoEstado` ENUM('DISPONIBLE', 'AULA_LIBRE', 'AULA_LIBRE_SEMESTRAL', 'AULA_RESERVADA', 'AULA_CLASE') NOT NULL,
  PRIMARY KEY (`idCambioDeEstado`),
  INDEX `fk_CambiosDes1_idx` (`idAula` ASC, `idPiso` ASC, `idPabellon` ASC, `idHora` ASC, `idDia` ASC) VISIBLE,
  INDEX `fk_CambioDeEstado_Persona1_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_CEstadoo_Rs1`
    FOREIGN KEY (`idAula` , `idPiso` , `idPabellon` , `idHora` , `idDia`)
    REFERENCES `mydb`.`Horario` (`idAula` , `idPiso` , `idPabellon` , `idHora` , `idDia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CambioDeEstado_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `mydb`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
