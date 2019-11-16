--liquibase formatted sql
--changeset tianjl:tianjingle-20191116-10
INSERT INTO `tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('1', 'gitUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('2', 'jenkinsUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('3', 'apolloUrl', '127.0.0.1:8009/','admin','tianjingle');
