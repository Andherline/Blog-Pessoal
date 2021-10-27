CREATE DATABASE de_blog;

USE de_blog;

CREATE TABLE `postagens` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`titulo` varchar(255) NOT NULL,
	`texto` varchar(1000) NOT NULL,
	`data` TIMESTAMP NOT NULL,
	`tema_id` bigint NOT NULL,
	`usuario_id` bigint NOT NULL,
	PRIMARY KEY (`id`)
);

USE de_blog;

CREATE TABLE `temas` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

USE de_blog;

CREATE TABLE `usuarios` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`usuario` varchar(255) NOT NULL,
	`senha` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `postagens` ADD CONSTRAINT `postagens_fk0` FOREIGN KEY (`tema_id`) REFERENCES `temas`(`id`);

ALTER TABLE `postagens` ADD CONSTRAINT `postagens_fk1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios`(`id`);




