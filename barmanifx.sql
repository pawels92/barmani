-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema barmanifx
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema barmanifx
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema barmanifx
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema barmanifx
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `barmanifx` DEFAULT CHARACTER SET utf8 ;
USE `barmanifx` ;

-- -----------------------------------------------------
-- Table `barmanifx`.`klienci`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`klienci` (
  `ID_k` INT NOT NULL AUTO_INCREMENT,
  `Imie_k` VARCHAR(45) NOT NULL,
  `Nazwisko_k` VARCHAR(45) NOT NULL,
  `Telefon_k` VARCHAR(45) NOT NULL,
  `email_k` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_k`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`zamowienia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`zamowienia` (
  `ID_zam` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `godzina_w` TIME NOT NULL,
  `liczba_gosci` SMALLINT NOT NULL,
  `Bar` ENUM('Tak', 'Nie') NULL,
  `Dodatkowo` ENUM('Lemoniada', 'Fontanna', 'Nic') NOT NULL,
  `Adres` TEXT NOT NULL,
  `klienci_ID_k` INT NOT NULL,
  PRIMARY KEY (`ID_zam`, `klienci_ID_k`),
  INDEX `fk_zamowienia_klienci_idx` (`klienci_ID_k` ASC),
  CONSTRAINT `fk_zamowienia_klienci`
    FOREIGN KEY (`klienci_ID_k`)
    REFERENCES `barmanifx`.`klienci` (`ID_k`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`Zakupy100_150`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`Zakupy100_150` (
  `Produkt` VARCHAR(45) NOT NULL,
  `Jednostka` VARCHAR(45) NOT NULL,
  `Ilość` SMALLINT NOT NULL,
  `Cena` DOUBLE NOT NULL,
  `Suma` DOUBLE NULL,
  `Sklep` VARCHAR(45) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`Zakupy_copy1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`Zakupy_copy1` (
  `Produkt` VARCHAR(45) NOT NULL,
  `Jednostka` VARCHAR(45) NOT NULL,
  `Ilość` SMALLINT NOT NULL,
  `Cena/szt` DOUBLE NOT NULL,
  `Suma` DOUBLE NULL,
  `Sklep` VARCHAR(45) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`Zakupy150_200`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`Zakupy150_200` (
  `Produkt` VARCHAR(45) NOT NULL,
  `Jednostka` VARCHAR(45) NOT NULL,
  `Ilość` SMALLINT NOT NULL,
  `Cena` DOUBLE NOT NULL,
  `Suma` DOUBLE NULL,
  `Sklep` VARCHAR(45) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`Zakupy200_250`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`Zakupy200_250` (
  `Produkt` VARCHAR(45) NOT NULL,
  `Jednostka` VARCHAR(45) NOT NULL,
  `Ilość` SMALLINT NOT NULL,
  `Cena` DOUBLE NOT NULL,
  `Suma` DOUBLE NULL,
  `Sklep` VARCHAR(45) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`Zakupy300_350`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`Zakupy300_350` (
  `Produkt` VARCHAR(45) NOT NULL,
  `Jednostka` VARCHAR(45) NOT NULL,
  `Ilość` SMALLINT NOT NULL,
  `Cena` DOUBLE NOT NULL,
  `Suma` DOUBLE NULL,
  `Sklep` VARCHAR(45) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `barmanifx`.`Magazyn`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barmanifx`.`Magazyn` (
  `Produkt` VARCHAR(45) NOT NULL,
  `Jednostka` VARCHAR(45) NOT NULL,
  `Ilość` SMALLINT NOT NULL)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
