CREATE DATABASE Myblog;
use Myblog;

CREATE TABLE Article(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
date VARCHAR(20) NOT NULL,
title VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
messages VARCHAR(9999) NOT NULL
);

CREATE TABLE Tag(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
tag VARCHAR(255) NOT NULL
);

CREATE TABLE ArtMapTag(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
a_id INT NOT NULL,
t_id INT NOT NULL,
FOREIGN KEY(a_id) REFERENCES Article(id),
FOREIGN KEY(t_id) REFERENCES Tag(id)
);

CREATE TABLE User(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE AboutMe(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
author VARCHAR(255) NOT NULL,
introduce VARCHAR(255) NOT NULL,
notice VARCHAR(255) NOT NULL
);

insert into Tag values(null,'笔记'),(null,'日常'),(null,'技术'),(null,'后端')
,(null,'前端'),(null,'c与c++'),(null,'java'),(null,'python'),(null,'数据库');

insert into AboutMe values(null,'Augenstern','介绍','公告');