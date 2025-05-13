-- scheduler.`user` definition

CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '작성자 식별자',
                        `user_email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '작성자 이메일',
                        `user_password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '작성자 비밀번호',
                        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- scheduler.schedule definition

CREATE TABLE `schedule` (
                            `schedule_id` bigint NOT NULL AUTO_INCREMENT COMMENT '스케줄 식별자',
                            `schedule_title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '스케줄 제목',
                            `schedule_content` text COLLATE utf8mb4_general_ci NOT NULL,
                            `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `user_id` bigint NOT NULL,
                            PRIMARY KEY (`schedule_id`),
                            KEY `user_id` (`user_id`),
                            CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
