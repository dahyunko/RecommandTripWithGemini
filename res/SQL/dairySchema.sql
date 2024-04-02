create database if not exists `enjoyTripDB`;
use `enjoyTripDB`;

drop table if exists `members`;

create table `members` (
  `user_id` varchar(50) primary key not null,
  `user_name` varchar(30) not null,
  `user_password` varchar(50) not null,
  `email_id` varchar(100) not null,
  `email_domain` varchar(100) not null
);

drop table if exists `diary`;

create table `diary` (
  `diary_no` INT NOT NULL AUTO_INCREMENT primary key,
  `user_id`VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `color` VARCHAR(20) NOT NULL,
  `image`  VARCHAR(255) NOT NULL,
  `content` varchar(2000) not null,
  `hit` INT NOT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES members(user_id)
);

commit;

select *
from diary;

select *
from members;