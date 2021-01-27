drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `price` DEC(11,2) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_customer_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `ims`.`customers`(`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`order_item` (
    `fk_order_id` INT(11) NOT NULL,
    `fk_item_id` int(11) NOT NULL,
    FOREIGN KEY (`fk_order_id`) REFERENCES `ims`.`orders`(`id`),
    FOREIGN KEY (`fk_item_id`) REFERENCES `ims`.`items`(`id`)
);
