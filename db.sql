CREATE DATABASE `shard0`CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `shard0`;


CREATE TABLE `user_info` (
                             `user_id` BIGINT NOT NULL,
                             `user_name` VARCHAR(45) DEFAULT NULL,
                             `account` VARCHAR(45) NOT NULL,
                             `password` VARCHAR(45) DEFAULT NULL,
                             PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order0` (
                            `order_id` INT(11) NOT NULL,
                            `user_id` INT(11) NOT NULL,
                            PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `t_order1` (
                            `order_id` INT(11) NOT NULL,
                            `user_id` INT(11) NOT NULL,
                            PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_item0` (
                                 `item_id` INT NOT NULL,
                                 `order_id` INT NOT NULL,
                                 `user_id` INT NOT NULL,
                                 PRIMARY KEY (`item_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order_item1` (
                                 `item_id` INT NOT NULL,
                                 `order_id` INT NOT NULL,
                                 `user_id` INT NOT NULL,
                                 PRIMARY KEY (`item_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


# 错误 ：Caused by: java.sql.SQLException: XAER_RMERR: Fatal error occurred in the transaction branch - check your data for consistency
# 原因 ： mysql没有开启用户XA事物权限 。
GRANT XA_RECOVER_ADMIN ON *.* TO root@'%' ;



CREATE DATABASE `shard1`CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `shard1`;


CREATE TABLE `user_info` (
                             `user_id` BIGINT NOT NULL,
                             `user_name` VARCHAR(45) DEFAULT NULL,
                             `account` VARCHAR(45) NOT NULL,
                             `password` VARCHAR(45) DEFAULT NULL,
                             PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order0` (
                            `order_id` INT(11) NOT NULL,
                            `user_id` INT(11) NOT NULL,
                            PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `t_order1` (
                            `order_id` INT(11) NOT NULL,
                            `user_id` INT(11) NOT NULL,
                            PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_order_item0` (
                                 `item_id` INT NOT NULL,
                                 `order_id` INT NOT NULL,
                                 `user_id` INT NOT NULL,
                                 PRIMARY KEY (`item_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order_item1` (
                                 `item_id` INT NOT NULL,
                                 `order_id` INT NOT NULL,
                                 `user_id` INT NOT NULL,
                                 PRIMARY KEY (`item_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




# 错误 ：Caused by: java.sql.SQLException: XAER_RMERR: Fatal error occurred in the transaction branch - check your data for consistency
# 原因 ： mysql没有开启用户XA事物权限 。
GRANT XA_RECOVER_ADMIN ON *.* TO root@'%' ;


