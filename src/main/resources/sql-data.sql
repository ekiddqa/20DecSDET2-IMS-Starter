INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`items`(`name`, `price`) VALUES ('jordan', 50);
INSERT INTO `ims`.`items`(`name`, `price`) VALUES ('smith', 77.77);
INSERT INTO `ims`.`orders`(`fk_customer_id`) VALUES (`1`);
INSERT INTO `ims`.`order_item`(`fk_order_id`,`fk_item_id`) VALUES (`1`,`2`);
