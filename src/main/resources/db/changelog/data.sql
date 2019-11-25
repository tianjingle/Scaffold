--liquibase formatted sql
--changeset tianjl:tianjingle-20191116-10
INSERT INTO `tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('1', 'gitUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('2', 'jenkinsUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('3', 'apolloUrl', '127.0.0.1:8009/','admin','tianjingle');

--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-1
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('1', '资源管理', '0', '资源管理', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('2', '菜单管理', '1', '用户管理', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('3', '角色管理', '3', '角色管理', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('4', '用户管理', '4', '用户管理', 'Y');

--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-2
INSERT INTO `menu` (`id`, `menu_name`, `content`, `flag`) VALUES ('1', '权限管理', '权限管理', 'Y');

--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-3
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('1', '1', '1');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('2', '1', '2');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('3', '1', '3');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('4', '1', '4');

--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-4
INSERT INTO `role` (`id`, `role_name`, `menu_id`, `content`, `flag`) VALUES ('1', '系统管理员', '1', '系统管理员', 'Y');

--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-5
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES ('1', '1', '1');

--liquibase formatted sql
--changest tianjl:tianjingle-20191122-6
INSERT INTO `user` (`id`, `login_id`, `user_name`, `user_password`, `user_emil`, `ro_id`) VALUES ('1', '15652466911', 'scaffold', 'admin', 'scaffold@qq.com', '1');

