-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema delivery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema delivery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `delivery` DEFAULT CHARACTER SET utf8 ;
USE `delivery` ;

-- -----------------------------------------------------
-- Table `delivery`.`party`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`party` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(200) NULL DEFAULT NULL COMMENT '',
  `description` VARCHAR(100) NULL COMMENT '',
  `image_url` VARCHAR(200) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `delivery`.`order_status_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order_status_type` (
  `id` INT NOT NULL COMMENT '',
  `title` VARCHAR(100) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `code` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `order_status_type_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ordr_order_status_type1_idx` (`order_status_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ordr_order_status_type1`
    FOREIGN KEY (`order_status_type_id`)
    REFERENCES `delivery`.`order_status_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `delivery`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `lft` INT(11) NOT NULL COMMENT '',
  `rgt` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `delivery`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `product_category_id` INT(11) NOT NULL COMMENT '',
  `description` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_product_product_category1_idx` (`product_category_id` ASC)  COMMENT '',
  CONSTRAINT `fk_product_product_category1`
    FOREIGN KEY (`product_category_id`)
    REFERENCES `delivery`.`product_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `delivery`.`order_item_status_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order_item_status_type` (
  `id` INT NOT NULL COMMENT '',
  `title` VARCHAR(100) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `ordr_id` INT(11) NOT NULL COMMENT '',
  `quantity` INT NULL COMMENT '',
  `product_id` INT(11) NOT NULL COMMENT '',
  `status_type_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_order_item_order1_idx` (`ordr_id` ASC)  COMMENT '',
  INDEX `fk_ordr_item_product1_idx` (`product_id` ASC)  COMMENT '',
  INDEX `fk_ordr_item_status_type1_idx` (`status_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_order_item_order1`
    FOREIGN KEY (`ordr_id`)
    REFERENCES `delivery`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordr_item_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `delivery`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordr_item_status_type1`
    FOREIGN KEY (`status_type_id`)
    REFERENCES `delivery`.`order_item_status_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '		';


-- -----------------------------------------------------
-- Table `delivery`.`product_party_role_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product_party_role_type` (
  `id` INT NOT NULL COMMENT '',
  `title` VARCHAR(100) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`product_party`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product_party` (
  `product_id` INT(11) NOT NULL COMMENT '',
  `party_id` INT(11) NOT NULL COMMENT '',
  `product_party_role_type_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`product_id`, `party_id`)  COMMENT '',
  INDEX `fk_product_party_party1_idx` (`party_id` ASC)  COMMENT '',
  INDEX `fk_product_party_product_party_role_type1_idx` (`product_party_role_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_product_party_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `delivery`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_party_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `delivery`.`party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_party_product_party_role_type1`
    FOREIGN KEY (`product_party_role_type_id`)
    REFERENCES `delivery`.`product_party_role_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`order_party_role_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order_party_role_type` (
  `id` INT NOT NULL COMMENT '',
  `title` VARCHAR(100) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`order_party`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`order_party` (
  `ordr_id` INT(11) NOT NULL COMMENT '',
  `party_id` INT(11) NOT NULL COMMENT '',
  `order_party_role_type_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`ordr_id`, `party_id`)  COMMENT '',
  INDEX `fk_ordr_party_party1_idx` (`party_id` ASC)  COMMENT '',
  INDEX `fk_order_party_order_party_role_type1_idx` (`order_party_role_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ordr_party_ordr1`
    FOREIGN KEY (`ordr_id`)
    REFERENCES `delivery`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordr_party_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `delivery`.`party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_party_order_party_role_type1`
    FOREIGN KEY (`order_party_role_type_id`)
    REFERENCES `delivery`.`order_party_role_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`price_component_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`price_component_type` (
  `id` INT NOT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`product_price_component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`product_price_component` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `product_id` INT(11) NOT NULL COMMENT '',
  `price_component_type_id` INT NOT NULL COMMENT '',
  `value` DOUBLE NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_product_price_component_product1_idx` (`product_id` ASC)  COMMENT '',
  INDEX `fk_product_price_component_price_component_type1_idx` (`price_component_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_product_price_component_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `delivery`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_price_component_price_component_type1`
    FOREIGN KEY (`price_component_type_id`)
    REFERENCES `delivery`.`price_component_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`comm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`comm` (
  `id` INT NOT NULL COMMENT '',
  `email` VARCHAR(254) NULL COMMENT '',
  `mobile` VARCHAR(20) NULL COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`party_comm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`party_comm` (
  `party_id` INT(11) NOT NULL COMMENT '',
  `comm_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`party_id`, `comm_id`)  COMMENT '',
  INDEX `fk_party_comm_comm1_idx` (`comm_id` ASC)  COMMENT '',
  CONSTRAINT `fk_party_comm_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `delivery`.`party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_comm_comm1`
    FOREIGN KEY (`comm_id`)
    REFERENCES `delivery`.`comm` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`route` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`shipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`shipment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `route_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_shipment_route1_idx` (`route_id` ASC)  COMMENT '',
  CONSTRAINT `fk_shipment_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `delivery`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`address` (
  `id` INT NOT NULL COMMENT '',
  `address_string` VARCHAR(200) NULL COMMENT '',
  `latitude` FLOAT NULL COMMENT '',
  `longitude` FLOAT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`site` (
  `id` INT NOT NULL COMMENT '',
  `address_id` INT NOT NULL COMMENT '',
  `comm_id` INT NOT NULL COMMENT '',
  `name` VARCHAR(100) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_site_address1_idx` (`address_id` ASC)  COMMENT '',
  INDEX `fk_site_comm1_idx` (`comm_id` ASC)  COMMENT '',
  CONSTRAINT `fk_site_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `delivery`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_site_comm1`
    FOREIGN KEY (`comm_id`)
    REFERENCES `delivery`.`comm` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`route_segment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`route_segment` (
  `id` INT NOT NULL COMMENT '',
  `route_id` INT NOT NULL COMMENT '',
  `site_one_id` INT NULL COMMENT '',
  `site_two_id` INT NULL COMMENT '',
  `estimated_arrival` DATETIME NULL COMMENT '',
  `actual_arrival` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_route_segment_route1_idx` (`route_id` ASC)  COMMENT '',
  INDEX `fk_route_segment_site_one_idx` (`site_one_id` ASC)  COMMENT '',
  INDEX `fk_route_segment_site_two_idx` (`site_two_id` ASC)  COMMENT '',
  CONSTRAINT `fk_route_segment_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `delivery`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_segment_site_one`
    FOREIGN KEY (`site_one_id`)
    REFERENCES `delivery`.`site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_segment_site_two`
    FOREIGN KEY (`site_two_id`)
    REFERENCES `delivery`.`site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`shipment_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`shipment_order` (
  `shipment_id` INT NOT NULL COMMENT '',
  `ordr_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`shipment_id`, `ordr_id`)  COMMENT '',
  INDEX `fk_shipment_order_ordr1_idx` (`ordr_id` ASC)  COMMENT '',
  CONSTRAINT `fk_shipment_order_shipment1`
    FOREIGN KEY (`shipment_id`)
    REFERENCES `delivery`.`shipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shipment_order_ordr1`
    FOREIGN KEY (`ordr_id`)
    REFERENCES `delivery`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`party_site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`party_site` (
  `party_id` INT(11) NOT NULL COMMENT '',
  `site_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`party_id`, `site_id`)  COMMENT '',
  INDEX `fk_party_site_site1_idx` (`site_id` ASC)  COMMENT '',
  CONSTRAINT `fk_party_site_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `delivery`.`party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_site_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `delivery`.`site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`party_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`party_address` (
  `party_id` INT(11) NOT NULL COMMENT '',
  `address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`party_id`, `address_id`)  COMMENT '',
  INDEX `fk_party_address_address1_idx` (`address_id` ASC)  COMMENT '',
  CONSTRAINT `fk_party_address_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `delivery`.`party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_address_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `delivery`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`party_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`party_role` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `party_id` INT(11) NOT NULL COMMENT '',
  `role` VARCHAR(100) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_party_role_party1_idx` (`party_id` ASC)  COMMENT '',
  CONSTRAINT `fk_party_role_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `delivery`.`party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `delivery` ;

-- -----------------------------------------------------
-- procedure ins_prod_cat
-- -----------------------------------------------------

DELIMITER $$
USE `delivery`$$
CREATE PROCEDURE `ins_prod_cat` 
(
	IN category_name text,
    IN parent_category_id int
)
BEGIN
	declare prev_lft int DEFAULT 0;
    
	SELECT lft INTO prev_lft FROM product_category
	WHERE id = parent_category_id;
    
    IF (prev_lft > 0) THEN
		UPDATE product_category SET 
		rgt = rgt + 2 WHERE
		rgt > prev_lft;
		
		UPDATE product_category SET 
		lft = lft + 2 WHERE
		lft > prev_lft;
    END IF;
    
	INSERT INTO product_category(name, lft, rgt) 
    VALUES(category_name, prev_lft + 1, prev_lft + 2);
END
$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure del_prod_cat
-- -----------------------------------------------------

DELIMITER $$
USE `delivery`$$
CREATE PROCEDURE `del_prod_cat` 
(
	IN category_id text
)
BEGIN
	DECLARE product_category_lft int;
    DECLARE product_category_rgt int;
    DECLARE product_category_width int;
    
	SELECT lft,rgt,rgt - lft INTO 
		product_category_lft,product_category_rgt,product_category_width
    FROM product_category
    WHERE id = category_id;
    
    -- delete node 
    DELETE FROM product_category
    WHERE id = category_id;
    
    -- delete children 
    DELETE FROM product_category
    WHERE lft BETWEEN product_category_lft AND product_category_rgt;
    
    -- update siblings and sibling-children to the right
    UPDATE product_category SET rgt = rgt - product_category_width 
    WHERE rgt > product_category_rgt;
	UPDATE product_category SET lft = lft - product_category_width 
    WHERE lft > product_category_rgt;
    
END
$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_sub_cat
-- -----------------------------------------------------

DELIMITER $$
USE `delivery`$$
CREATE PROCEDURE `get_sub_cat` 
(
	IN cat_id INT
)
BEGIN
	DECLARE l INT;
    DECLARE r INT;
    
SELECT 
    lft, rgt
INTO l , r FROM
    product_category
WHERE
    id = cat_id;

SELECT child.*
FROM product_category AS child LEFT JOIN product_category AS ancestor 
ON ancestor.lft BETWEEN l+1 AND r-1 AND
    child.lft BETWEEN ancestor.lft+1 AND ancestor.rgt-1
WHERE child.lft BETWEEN l+1 AND r-1 AND ancestor.id IS NULL;
END
$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
