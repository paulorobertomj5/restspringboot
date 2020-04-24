CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  	`client_id` bigint(20),
    CONSTRAINT fk_client FOREIGN KEY (`client_id`) REFERENCES client(`id`),
  	`product_id` bigint(20),
    CONSTRAINT fk_product FOREIGN KEY (`product_id`) REFERENCES product(`id`),
  `qtd` varchar(80) NOT NULL,
  `price` decimal(13, 2) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;