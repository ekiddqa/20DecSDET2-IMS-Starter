DROP TABLE `order_item`;
DROP TABLE `orders`;
DROP TABLE `items`;
DROP TABLE `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `price` double,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT(11) NOT NULL,
    `value` double,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `customers`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `order_item` (
    `fk_order_id` INT(11) NOT NULL,
    `fk_item_id` INT(11) NOT NULL,
    FOREIGN KEY (`fk_order_id`) REFERENCES `orders`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_item_id`) REFERENCES `items`(`id`) ON DELETE CASCADE
);
