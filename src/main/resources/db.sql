create database scaffold;
use scaffold;
--创建权限的表--
CREATE TABLE resource (
id int not null primary key auto_increment,
resource_name varchar(64)  COMMENT '资源名称',
resource_url varchar(64) COMMENT '资源路径h5',
content varchar(128) COMMENT '资源描述',
flag varchar(64) COMMENT '其他标志'
)ENGINE=InnoDB;

CREATE TABLE menu (
id int not null primary key auto_increment,
menu_name varchar(64) NOT NULL COMMENT '菜单名称',
content varchar(128) COMMENT '菜单描述',
flag varchar(64) COMMENT '标志'
)ENGINE=InnoDB;

CREATE TABLE menu_resource (
id int not null primary key auto_increment,
menu_id int COMMENT '菜单id',
resource_id int COMMENT '资源id',
CONSTRAINT m_tmenu FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT m_resources FOREIGN KEY (resource_id) REFERENCES resource (id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE role (
id int NOT NULL PRIMARY KEY auto_increment,
role_name varchar(64) COMMENT '角色名称',
menu_id varchar(64) COMMENT '菜单id',
content varchar(128) COMMENT '内容描述',
flag varchar(64) COMMENT '标志'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE role_menu (
id int not null primary key auto_increment,
role_id int COMMENT '角色id',
menu_id int COMMENT '菜单id',
CONSTRAINT role_r_tm FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT role_tm1 FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户信息表--
CREATE TABLE user (
id int primary key not null auto_increment,
login_id varchar(128) NOT NULL COMMENT '用户登录号，手机号',
user_name varchar(64) not null COMMENT '用户名称',
user_password varchar(64) not null COMMENT '用户密码',
user_emil varchar(64) COMMENT '用户电子邮箱',
ro_id int not null COMMENT '用户权限标志',
CONSTRAINT user_role FOREIGN KEY (ro_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--创建工程的表--
create table project(
id int not null primary key auto_increment COMMENT '主键',
login_id varchar(128) not null COMMENT '登陆用户的手机号',
git_url varchar(128) not null COMMENT 'git项目url',
jenkins_url varchar(128) not null COMMENT 'jenkins项目url',
apollo_url varchar(128) not null COMMENT 'apollo项目url',
artifact_id varchar(128) not null COMMENT '项目名称',
group_id varchar(128) not null COMMENT '基础包名',
version varchar(128) not null comment '版本号',
git_org varchar(64) COMMENT 'git的组织',
apollo_org varchar(64) COMMENT 'apollo工程的组织',
create_time Date COMMENT '创建时间'
);
--创建工具项目的url--
create table tool_project(
id int not null primary key auto_increment comment '主键',
name varchar(128) not null comment 'url的名称',
url varchar(128) not null comment 'url地址',
user_name varchar(128) comment '用户名称',
user_password varchar(128) comment '用户密码'
);
SELECT * FROM scaffold.tool_project;
INSERT INTO `scaffold`.`tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('1', 'gitUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `scaffold`.`tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('2', 'jenkinsUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `scaffold`.`tool_project` (`id`, `name`, `url`,`user_name`,`user_password`) VALUES ('3', 'apolloUrl', '127.0.0.1:8009/','admin','tianjingle');
