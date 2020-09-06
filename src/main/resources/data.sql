-- -----------------------------------------------------
-- Table `Course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Course` ;

CREATE TABLE IF NOT EXISTS `Course` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Code` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Student` ;

CREATE TABLE IF NOT EXISTS `Student` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `idCourse` INT NOT NULL,
  `Rut` INT NOT NULL,
  `Dv` VARCHAR(1) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_Student_Course`
    FOREIGN KEY (`idCourse`)
    REFERENCES `Course` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

