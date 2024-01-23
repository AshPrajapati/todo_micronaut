--liquibase formatted sql

--changeset example-user:todo

CREATE TABLE todo (
	id INTEGER auto_increment NOT NULL,
	`date` DATE NOT NULL,
	todo_text varchar(100) NOT NULL,
	category varchar(100) NOT NULL,
	CONSTRAINT todo_PK PRIMARY KEY (id)
)