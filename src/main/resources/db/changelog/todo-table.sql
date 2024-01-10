--liquibase formatted sql

--changeset example-user:todo-table

CREATE TABLE todo (
	id INT auto_increment NOT NULL,
	`date` DATE NOT NULL,
	todo_text varchar(100) NOT NULL,
	CONSTRAINT todo_PK PRIMARY KEY (id)
)