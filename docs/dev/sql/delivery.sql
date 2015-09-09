
-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `line_one` VARCHAR(45) NULL DEFAULT NULL,
  `line_two` VARCHAR(45) NULL DEFAULT NULL,
  `locality` VARCHAR(45) NULL DEFAULT NULL,
  `lat` FLOAT(10,6) NULL DEFAULT NULL,
  `lng` FLOAT(10,6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `token` VARCHAR(45) NULL DEFAULT NULL,
  `mail_verified` TINYINT(1) NULL DEFAULT 0,
  `phone_verified` TINYINT(1) NULL DEFAULT 0,
  `address_verified` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `customer_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer_address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `address_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_address_address1_idx` (`address_id` ASC),
  INDEX `fk_customer_address_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_customer_address_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_address_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orderr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orderr` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NOT NULL,
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `status` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `lft` INT(11) NOT NULL,
  `rgt` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `product_category_id` INT(11) NOT NULL,
  `base_price` FLOAT NULL,
  `description` VARCHAR(45) NULL,
  `dish_calories` VARCHAR(45) NULL,
  `dish_vegetarian` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_product_category1_idx` (`product_category_id` ASC),
  CONSTRAINT `fk_product_product_category1`
    FOREIGN KEY (`product_category_id`)
    REFERENCES `product_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_item_order1_idx` (`order_id` ASC),
  INDEX `fk_order_item_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_order_item_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orderr` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '		';


-- -----------------------------------------------------
-- Table `supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supplier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `address_id` INT(11) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `website` VARCHAR(45) NULL,
  `rest_cuisines` VARCHAR(100) NULL,
  `rest_price_range` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_supplier_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_supplier_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `product_supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product_supplier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `supplier_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_supplier_supplier1_idx` (`supplier_id` ASC),
  INDEX `fk_product_supplier_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_product_supplier_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_supplier_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shipment` (
  `ship_id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  PRIMARY KEY (`ship_id`),
  INDEX `fk_shipment_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_shipment_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orderr` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- procedure insert_product_category
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `insert_product_category` 
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
-- procedure delete_product_category
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `delete_product_category` 
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

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_sub_cat`(
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

END$$
DELIMITER ;

