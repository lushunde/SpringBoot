

    CREATE TABLE `User` (
                            `user_id` bigint NOT NULL AUTO_INCREMENT,
                            `user_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
    `sex` bit(1) DEFAULT b'0' COMMENT '1-男 2-女',
    `age` int DEFAULT NULL,
    `address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
