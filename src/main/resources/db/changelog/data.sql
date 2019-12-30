--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-1
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('1', '菜单管理', '/menuManager', 'menuManager', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('2', '资源管理', '/resourceManager', 'ResourceManager', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('3', '角色管理', '/roleManager', 'roleManager', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('4', '用户管理', '/userManager', 'UserManager', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('5', '机构管理', '/orginazation', 'Orginazation', 'Y');
INSERT INTO `resource` (`id`, `resource_name`, `resource_url`, `content`, `flag`) VALUES ('6', '我的项目', '/myProjectManager', 'MyProjectManager', 'Y');


--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-2
INSERT INTO `menu` (`id`, `menu_name`, `content`, `flag`) VALUES ('1', '基础管理', '权限管理', 'Y');
INSERT INTO `menu` (`id`, `menu_name`, `content`, `flag`) VALUES ('2', '开发平台', '项目开发', 'Y');
INSERT INTO `menu` (`id`, `menu_name`, `content`, `flag`) VALUES ('3', '项目机构', '项目机构', 'Y');


--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-3
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('1', '1', '1');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('2', '1', '2');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('3', '1', '3');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('4', '1', '4');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('5', '2', '6');
INSERT INTO `menu_resource` (`id`, `menu_id`, `resource_id`) VALUES ('6', '3', '5');


--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-4
INSERT INTO `role` (`id`, `role_name`, `menu_id`, `content`, `flag`) VALUES ('1', '系统管理员', '1', '系统管理员', 'Y');
INSERT INTO `role` (`id`, `role_name`, `menu_id`, `content`, `flag`) VALUES ('2', '高级管理员', '1', '高级管理员', 'Y');
INSERT INTO `role` (`id`, `role_name`, `menu_id`, `content`, `flag`) VALUES ('3', '开发者工程师', '1', '开发工程师', 'Y');

--liquibase formatted sql
--changeset tianjl:tianjingle-20191122-5
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES ('1', '1', '1');

--liquibase formatted sql
--changest tianjl:tianjingle-20191122-6
INSERT INTO `user` (`id`, `login_id`, `user_name`, `user_password`, `user_emil`, `ro_id`) VALUES ('1', '15652466911', 'scaffold', 'admin', 'scaffold@qq.com', '1');

