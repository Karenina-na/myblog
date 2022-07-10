CREATE DATABASE Myblog;
use Myblog;

CREATE TABLE article(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
date VARCHAR(20) NOT NULL,
title VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
messages VARCHAR(9999) NOT NULL
);

CREATE TABLE tag(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
tag VARCHAR(255) NOT NULL
);

CREATE TABLE artmaptag(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
a_id INT NOT NULL,
t_id INT NOT NULL,
FOREIGN KEY(a_id) REFERENCES article(id),
FOREIGN KEY(t_id) REFERENCES tag(id)
);

CREATE TABLE user(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE aboutme(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
author VARCHAR(255) NOT NULL,
introduce VARCHAR(255) NOT NULL,
notice VARCHAR(255) NOT NULL
);

CREATE TABLE source(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
type VARCHAR(30) NOT NULL,
name VARCHAR(255) NOT NULL,
date VARCHAR(255) NOT NULL
);

insert into tag values(null,'笔记'),(null,'日常'),(null,'技术'),(null,'后端')
,(null,'前端'),(null,'c与c++'),(null,'java'),(null,'python'),(null,'数据库'),(null,'Go');

insert into aboutme values(null,'Augenstern','介绍','公告');