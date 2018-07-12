CREATE DATABASE `treetory_test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

CREATE TABLE `treetory_test`.`test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `col1` INT NOT NULL,
  `col2` VARCHAR(45) NULL,
  `col3` LONGTEXT NULL,
  `col4` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  
insert into treetory_test.test (col1, col2, col3, col4) values (1, 'test', '자 이제부터 게시판을 만들어보면 됩니다.', now());