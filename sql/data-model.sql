SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `delivery` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `delivery` ;

-- -----------------------------------------------------
-- Table `delivery`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT primary key,
  `last_name` VARCHAR(100) NULL,
  `first_name` VARCHAR(45) NULL,
  `birth_date` DATE NULL,
  comm_id int not null,
  PRIMARY KEY (`id`),
  FOREIGN KEY (comm_id)
  	references comm (id))
ENGINE = InnoDB;

CREATE TABLE if not exists 'delivery'.'userinfo' (
id int not null primary key,
username text,
token text,
foreign key (id)
	references person (id)
)

-- -----------------------------------------------------
-- Table `delivery`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`comm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`comm` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mobile` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  order_time datetime not null,
  expected_del_time date not null,
  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order_items` (
  id int not null primary key AUTO_INCREMENT,
  o_id INT null,
  per_id int not null,
  FOREIGN KEY (per_id)
    references person (id),
  foreign key (o_id)
  	references order ( id),
  pro_id int not null,
  foreign key (pro_id)
  	references product (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  pro_name varchar(50) null;
  site_id int not null,
  PRIMARY KEY (`id`),
  prod_cat id int not null,
  FOREIGN KEY (prod_cat)
  	references product_category (id),
  foreign key (site_id)
  	references site (id)
  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `parent_id` INT NULL,
  PRIMARY KEY (`id`),
  foreign key (parent_id)
    references product_category (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`site` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `location_id` INT NULL,
  `comm_id` INT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(location_id)
  	references location (id),
  FOREIGN KEY (comm_id)
  	references comm (id))

ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`restaurant` (
  `id` INT NOT NULL,
  name varchar(50),
  PRIMARY KEY (`id`),
  FOREIGN KEY (id)
  	references site (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`pharmacy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`pharmacy` (
	id int primary key not null,
	FOREIGN KEY (id)
  	references site (id)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`grocery_store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`grocery_store` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id)
  	references site (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`location` (
  `id` INT NOT NULL,
  `address` VARCHAR(200) NULL,
  `latitude` DECIMAL(10,0) NULL,
  `longitude` DECIMAL(10,0) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`person_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`person_location` (
  `id` INT NOT NULL,
  pid int not null,
  `location_id` INT not NULL,
  `location_type` varchar(40) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (pid)
  	references person (id),
  foreign key (location_id)
  	references location (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`shipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`shipment` (
  `ship_id` INT NOT NULL,
  PRIMARY KEY (`ship_id`),
  o_id int not null,
  foreign key (o_id)
  	references order (id),
  l_id int not null,
  foreign key (l_id)
  	references location (id),
  o_time DATE() not null,
  del_time DATE() not null,
  status int(1) DEFAULT 0
  )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
