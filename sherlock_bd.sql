-- -----------------------------------------------------
-- schema sherlock
-- -----------------------------------------------------
drop schema if exists sherlock;
create schema if not exists `sherlock`;
use `sherlock` ;

-- -----------------------------------------------------
-- table `sherlock`.`client`
-- -----------------------------------------------------
create table if not exists `sherlock`.`client` (
  `id` int not null primary key,
  `address` varchar(45) null,
  `name` varchar(45) null,
  `phone_number` varchar(45) null,
  `mail` varchar(45) null);


-- -----------------------------------------------------
-- table `sherlock`.`locker`
-- -----------------------------------------------------
create table if not exists `sherlock`.`locker` (
  `id` int not null primary key,
  `address` varchar(45) null,
  `latitude` double null,
  `longitude` double null);

-- -----------------------------------------------------
-- table `sherlock`.`drawer`
-- -----------------------------------------------------
create table if not exists `sherlock`.`drawer` (
  `id` int not null primary key,
  `locker_id` int not null,
  `dimension` varchar(45) null,
  `available` tinyint null,
  foreign key (`locker_id`) references `sherlock`.`locker` (`id`));

-- -----------------------------------------------------
-- table `sherlock`.`product`
-- -----------------------------------------------------
create table if not exists `sherlock`.`product` (
  `id` int not null primary key,
  `client_id` int not null,
  `drawer_id` int not null,
  `drawer_locker_id` int not null,
  `name` varchar(100) null,
  `quantity` float null,
  `weight` float null,
  `dimension` varchar(45) null,
  `estimated_date` varchar(45) null,
  `address` varchar(45) null,
  `available` tinyint null,
  `pass_code` varchar(300) null,
  foreign key (`client_id`) references `sherlock`.`client` (`id`),
  foreign key (`drawer_id`) references `sherlock`.`drawer` (`id`),
  foreign key (`drawer_locker_id`) references `sherlock`.`drawer` (`locker_id`));
  
  select * from drawer;