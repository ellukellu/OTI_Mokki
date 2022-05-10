-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

alter table varaus modify varattu_pvm varchar(10);
alter table varaus modify vahvistus_pvm varchar(10);
alter table varaus modify varattu_alkupvm varchar(10);
alter table varaus modify varattu_loppupvm varchar(10);
DESCRIBE varaus;


-- -----------------------------------------------------
-- Schema vn
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `vn` ;

-- -----------------------------------------------------
-- Schema vn
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vn` DEFAULT CHARACTER SET latin1 ;
USE `vn` ;

-- -----------------------------------------------------
-- Table `vn`.`posti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`posti` ;

CREATE TABLE IF NOT EXISTS `vn`.`posti` (
  `postinro` CHAR(5) NOT NULL,
  `toimipaikka` VARCHAR(45) NULL,
  PRIMARY KEY (`postinro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vn`.`asiakas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`asiakas` ;

CREATE TABLE IF NOT EXISTS `vn`.`asiakas` (
  `asiakas_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `postinro` CHAR(5) NOT NULL,
  `etunimi` VARCHAR(20) NULL DEFAULT NULL,
  `sukunimi` VARCHAR(40) NULL DEFAULT NULL,
  `lahiosoite` VARCHAR(40) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `puhelinnro` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`asiakas_id`),
  INDEX `fk_as_posti1_idx` (`postinro` ASC),
  INDEX `asiakas_snimi_idx` (`sukunimi` ASC),
  INDEX `asiakas_enimi_idx` (`etunimi` ASC),
  CONSTRAINT `fk_asiakas_posti`
    FOREIGN KEY (`postinro`)
    REFERENCES `vn`.`posti` (`postinro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vn`.`alue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`alue` ;

CREATE TABLE IF NOT EXISTS `vn`.`alue` (
  `alue_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nimi` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`alue_id`),
  INDEX `alue_nimi_index` (`nimi` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vn`.`mokki`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`mokki` ;

CREATE TABLE IF NOT EXISTS `vn`.`mokki` (
  `mokki_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `alue_id` INT(11) UNSIGNED NOT NULL,
  `postinro` CHAR(5) NOT NULL,
  `mokkinimi` VARCHAR(45) NULL,
  `katuosoite` VARCHAR(45) NULL,
	`hinta` DOUBLE(8,2) NOT NULL,
  `kuvaus` VARCHAR(150) NULL,
  `henkilomaara` INT NULL,
  `varustelu` VARCHAR(100) NULL,
  PRIMARY KEY (`mokki_id`),
  INDEX `fk_mokki_alue_idx` (`alue_id` ASC),
  INDEX `fk_mokki_posti_idx` (`postinro` ASC),
  CONSTRAINT `fk_mokki_alue`
    FOREIGN KEY (`alue_id`)
    REFERENCES `vn`.`alue` (`alue_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mokki_posti`
    FOREIGN KEY (`postinro`)
    REFERENCES `vn`.`posti` (`postinro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vn`.`varaus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`varaus` ;

CREATE TABLE IF NOT EXISTS `vn`.`varaus` (
  `varaus_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `asiakas_id` INT(11) UNSIGNED NOT NULL,
  `mokki_mokki_id` INT UNSIGNED NOT NULL,
  `varattu_pvm` DATETIME NULL DEFAULT NULL,
  `vahvistus_pvm` DATETIME NULL DEFAULT NULL,
  `varattu_alkupvm` DATETIME NULL DEFAULT NULL,
  `varattu_loppupvm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`varaus_id`),
  INDEX `varaus_as_id_index` (`asiakas_id` ASC),
  INDEX `fk_var_mok_idx` (`mokki_mokki_id` ASC),
  CONSTRAINT `varaus_ibfk`
    FOREIGN KEY (`asiakas_id`)
    REFERENCES `vn`.`asiakas` (`asiakas_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_varaus_mokki`
    FOREIGN KEY (`mokki_mokki_id`)
    REFERENCES `vn`.`mokki` (`mokki_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vn`.`lasku`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`lasku` ;

CREATE TABLE IF NOT EXISTS `vn`.`lasku` (
  `lasku_id` INT(11) NOT NULL,
  `varaus_id` INT(11) UNSIGNED NOT NULL,
  `summa` DOUBLE(8,2) NOT NULL,
  `alv` DOUBLE(8,2) NOT NULL,
  PRIMARY KEY (`lasku_id`),
  CONSTRAINT `lasku_ibfk_1`
    FOREIGN KEY (`varaus_id`)
    REFERENCES `vn`.`varaus` (`varaus_id`)
    ON DELETE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vn`.`palvelu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`palvelu` ;

CREATE TABLE IF NOT EXISTS `vn`.`palvelu` (
  `palvelu_id` INT(11) UNSIGNED NOT NULL,
  `alue_id` INT(11) UNSIGNED NOT NULL,
  `nimi` VARCHAR(40) NULL DEFAULT NULL,
  `tyyppi` INT(11) NULL DEFAULT NULL,
  `kuvaus` VARCHAR(255) NULL DEFAULT NULL,
  `hinta` DOUBLE(8,2) NOT NULL,
  `alv` DOUBLE(8,2) NOT NULL,
  PRIMARY KEY (`palvelu_id`),
  INDEX `Palvelu_nimi_index` (`nimi` ASC),
  INDEX `palv_toimip_id_ind` (`alue_id` ASC),
  CONSTRAINT `palvelu_ibfk_1`
    FOREIGN KEY (`alue_id`)
    REFERENCES `vn`.`alue` (`alue_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vn`.`varauksen_palvelut`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vn`.`varauksen_palvelut` ;

CREATE TABLE IF NOT EXISTS `vn`.`varauksen_palvelut` (
  `varaus_id` INT(11) UNSIGNED NOT NULL,
  `palvelu_id` INT(11) UNSIGNED NOT NULL,
  `lkm` INT(11) NOT NULL,
  INDEX `vp_varaus_id_index` (`varaus_id` ASC),
  INDEX `vp_palvelu_id_index` (`palvelu_id` ASC),
  PRIMARY KEY (`palvelu_id`, `varaus_id`),
  CONSTRAINT `fk_varaus`
    FOREIGN KEY (`varaus_id`)
    REFERENCES `vn`.`varaus` (`varaus_id`)
    ON DELETE RESTRICT,
  CONSTRAINT `fk_palvelu`
    FOREIGN KEY (`palvelu_id`)
    REFERENCES `vn`.`palvelu` (`palvelu_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;