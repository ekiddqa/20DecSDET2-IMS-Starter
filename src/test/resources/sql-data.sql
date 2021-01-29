INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `price`) VALUES ('jordan', 50);
INSERT INTO `items`(`name`, `price`) VALUES ('smith', 77.77);
INSERT INTO `orders`(`fk_customer_id`) VALUES (`1`);
INSERT INTO `order_item`(`fk_order_id`,`fk_item_id`) VALUES(`1`,`2`);