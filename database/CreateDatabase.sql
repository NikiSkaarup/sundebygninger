-- MySQL Script generated by MySQL Workbench
-- 11/16/16 12:52:49
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sundebygninger
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sundebygninger` ;

-- -----------------------------------------------------
-- Schema sundebygninger
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sundebygninger` DEFAULT CHARACTER SET utf8 ;
USE `sundebygninger` ;

-- -----------------------------------------------------
-- Table `sundebygninger`.`Org`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Org` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Org` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `Phone` VARCHAR(16) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Role` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Role` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`User` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `FkOrgId` INT NULL,
  `FkRoleId` INT NOT NULL DEFAULT 1,
  `Phone` VARCHAR(16) NOT NULL DEFAULT '00000000',
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `UserToOrganizationId_idx` (`FkOrgId` ASC),
  INDEX `UserRoleIdToRoleId_idx` (`FkRoleId` ASC),
  CONSTRAINT `UserToOrganizationId`
    FOREIGN KEY (`FkOrgId`)
    REFERENCES `sundebygninger`.`Org` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserRoleIdToRoleId`
    FOREIGN KEY (`FkRoleId`)
    REFERENCES `sundebygninger`.`Role` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Building`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Building` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Building` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `FkOrgId` INT NOT NULL,
  `Name` VARCHAR(255) NOT NULL,
  `Address` VARCHAR(255) NOT NULL,
  `ConstructionYear` TIMESTAMP NOT NULL DEFAULT '1990-01-01',
  `CurrentUse` VARCHAR(128) NOT NULL,
  `Area` VARCHAR(512) NOT NULL,
  `PreviousUse` VARCHAR(128) NULL,
  `Submission` TIMESTAMP NOT NULL DEFAULT now(),
  PRIMARY KEY (`Id`),
  INDEX `BuildingToOrgId_idx` (`FkOrgId` ASC),
  CONSTRAINT `BuildingToOrgId`
    FOREIGN KEY (`FkOrgId`)
    REFERENCES `sundebygninger`.`Org` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Report` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Report` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `FkBuildingId` INT NOT NULL,
  `Submission` TIMESTAMP NOT NULL DEFAULT now(),
  `FkUserId` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `ReportToBuildingId_idx` (`FkBuildingId` ASC),
  INDEX `ReportUserIdToUserId_idx` (`FkUserId` ASC),
  CONSTRAINT `ReportToBuildingId`
    FOREIGN KEY (`FkBuildingId`)
    REFERENCES `sundebygninger`.`Building` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ReportUserIdToUserId`
    FOREIGN KEY (`FkUserId`)
    REFERENCES `sundebygninger`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`ServiceType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`ServiceType` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`ServiceType` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Request` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Request` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `FkBuildingId` INT NOT NULL,
  `Submission` TIMESTAMP NOT NULL DEFAULT now(),
  `FkReportId` INT NULL,
  `Description` VARCHAR(512) NULL,
  `FkServiceTypeId` INT NOT NULL,
  `FkUserId` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `RequestBuildingIdToBuildingId_idx` (`FkBuildingId` ASC),
  INDEX `RequestServiceTypeIdToServiceTypeId_idx` (`FkServiceTypeId` ASC),
  INDEX `RequestReportIdToReportId_idx` (`FkReportId` ASC),
  INDEX `RequestUserIdToUserId_idx` (`FkUserId` ASC),
  CONSTRAINT `RequestBuildingIdToBuildingId`
    FOREIGN KEY (`FkBuildingId`)
    REFERENCES `sundebygninger`.`Building` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RequestServiceTypeIdToServiceTypeId`
    FOREIGN KEY (`FkServiceTypeId`)
    REFERENCES `sundebygninger`.`ServiceType` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RequestReportIdToReportId`
    FOREIGN KEY (`FkReportId`)
    REFERENCES `sundebygninger`.`Report` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RequestUserIdToUserId`
    FOREIGN KEY (`FkUserId`)
    REFERENCES `sundebygninger`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`DamageType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`DamageType` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`DamageType` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Incident`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Incident` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Incident` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `FkBuildingId` INT NOT NULL,
  `FkDamageType` INT NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `IncidentBuildingIdToBuildingId_idx` (`FkBuildingId` ASC),
  INDEX `IncidentFkDamageTypeIdToDamageTypeId_idx` (`FkDamageType` ASC),
  CONSTRAINT `IncidentBuildingIdToBuildingId`
    FOREIGN KEY (`FkBuildingId`)
    REFERENCES `sundebygninger`.`Building` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `IncidentFkDamageTypeIdToDamageTypeId`
    FOREIGN KEY (`FkDamageType`)
    REFERENCES `sundebygninger`.`DamageType` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`FileType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`FileType` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`FileType` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL DEFAULT 'default',
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`File`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`File` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`File` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `FkBuildingId` INT NOT NULL,
  `Data` BLOB NOT NULL,
  `FkFileTypeId` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `DocumentToBuildingId_idx` (`FkBuildingId` ASC),
  INDEX `FileFkFileTypeIdToFileTypeId_idx` (`FkFileTypeId` ASC),
  CONSTRAINT `ImageBuildingIdToBuildingId0`
    FOREIGN KEY (`FkBuildingId`)
    REFERENCES `sundebygninger`.`Building` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FileFkFileTypeIdToFileTypeId`
    FOREIGN KEY (`FkFileTypeId`)
    REFERENCES `sundebygninger`.`FileType` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Room` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Room` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `FkReportId` INT NOT NULL,
  `Num` VARCHAR(45) NOT NULL,
  `Damage` TINYINT(1) NOT NULL,
  `When` TIMESTAMP NOT NULL,
  `Where` VARCHAR(255) NOT NULL,
  `WhatHappened` VARCHAR(255) NOT NULL,
  `WhatWasFixed` VARCHAR(45) NOT NULL,
  `MoistureScan` VARCHAR(128) NULL,
  `MoistureTarget` VARCHAR(128) NULL,
  PRIMARY KEY (`Id`),
  INDEX `RoomReportIdToReportId_idx` (`FkReportId` ASC),
  CONSTRAINT `RoomReportIdToReportId`
    FOREIGN KEY (`FkReportId`)
    REFERENCES `sundebygninger`.`Report` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`RoomToDamageType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`RoomToDamageType` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`RoomToDamageType` (
  `FkRoomId` INT NOT NULL,
  `FkDamageTypeId` INT NOT NULL,
  PRIMARY KEY (`FkRoomId`, `FkDamageTypeId`),
  INDEX `RtDTFkDamageTypeIdToDamageTypeId_idx` (`FkDamageTypeId` ASC),
  CONSTRAINT `RtDTFkRoomIdToRoomId`
    FOREIGN KEY (`FkRoomId`)
    REFERENCES `sundebygninger`.`Room` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RtDTFkDamageTypeIdToDamageTypeId`
    FOREIGN KEY (`FkDamageTypeId`)
    REFERENCES `sundebygninger`.`DamageType` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`CommentType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`CommentType` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`CommentType` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sundebygninger`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sundebygninger`.`Comment` ;

CREATE TABLE IF NOT EXISTS `sundebygninger`.`Comment` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `FkReportId` INT NOT NULL,
  `FkRoomId` INT NULL,
  `Comment` VARCHAR(512) NULL,
  `FkCommentTypeId` INT NOT NULL,
  `Path` VARCHAR(255) NULL,
  PRIMARY KEY (`Id`),
  INDEX `ACFkRoomIdToRoomId_idx` (`FkRoomId` ASC),
  INDEX `ACFkCommentTypeIdToCommentTypeId_idx` (`FkCommentTypeId` ASC),
  INDEX `ACFkReportIdToReportId_idx` (`FkReportId` ASC),
  CONSTRAINT `ACFkRoomIdToRoomId`
    FOREIGN KEY (`FkRoomId`)
    REFERENCES `sundebygninger`.`Room` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ACFkCommentTypeIdToCommentTypeId`
    FOREIGN KEY (`FkCommentTypeId`)
    REFERENCES `sundebygninger`.`CommentType` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ACFkReportIdToReportId`
    FOREIGN KEY (`FkReportId`)
    REFERENCES `sundebygninger`.`Report` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
