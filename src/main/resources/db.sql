create database scaffold;
use scaffold;
--创建权限的表--
CREATE TABLE resource (
id int not null primary key auto_increment,
resourcename varchar(64)  COMMENT '资源名称',
resourceurl varchar(64) COMMENT '资源路径h5',
content varchar(128) COMMENT '资源描述',
flag varchar(64) COMMENT '其他标志'
)ENGINE=InnoDB;

CREATE TABLE tmenu (
id int not null primary key auto_increment,
mununame varchar(64) NOT NULL COMMENT '菜单名称',
content varchar(128) COMMENT '菜单描述',
flag varchar(64) COMMENT '标志'
)ENGINE=InnoDB;

CREATE TABLE menu_resource (
id int not null primary key auto_increment,
menusid int COMMENT '菜单id',
resourcesid int COMMENT '资源id',
CONSTRAINT m_tmenu FOREIGN KEY (menusid) REFERENCES tmenu (id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT m_resources FOREIGN KEY (resourcesid) REFERENCES resource (id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE role (
id int NOT NULL PRIMARY KEY auto_increment,
rolename varchar(64) COMMENT '角色名称',
menuid varchar(64) COMMENT '菜单id',
content varchar(128) COMMENT '内容描述',
flag varchar(64) COMMENT '标志'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE role_menu (
id int not null primary key auto_increment,
roid int COMMENT '角色id',
tmid int COMMENT '菜单id',
CONSTRAINT role_r_tm FOREIGN KEY (roid) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT role_tm1 FOREIGN KEY (tmid) REFERENCES tmenu (id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE user (
id int primary key not null auto_increment,
loginid varchar(128) NOT NULL COMMENT '用户登录号，手机号',
username varchar(64) not null COMMENT '用户名称',
userpassword varchar(64) not null COMMENT '用户密码',
useremil varchar(64) COMMENT '用户电子邮箱',
roid int not null COMMENT '用户权限标志',
CONSTRAINT user_role FOREIGN KEY (roid) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--创建工程的表--
create table project(
id int not null primary key auto_increment COMMENT '主键',
loginid varchar(128) not null COMMENT '登陆用户的手机号',
giturl varchar(128) not null COMMENT 'git项目url',
jenkinsurl varchar(128) not null COMMENT 'jenkins项目url',
apollourl varchar(128) not null COMMENT 'apollo项目url',
artifactid varchar(128) not null COMMENT '项目名称',
groupid varchar(128) not null COMMENT '基础包名',
version varchar(128) not null comment '版本号',
gitorg varchar(64) COMMENT 'git的组织',
apolloorg varchar(64) COMMENT 'apollo工程的组织',
createtime Date COMMENT '创建时间'
);
--创建工具项目的url--
create table toolprojecturl(
id int not null primary key auto_increment comment '主键',
urlname varchar(128) not null comment 'url的名称',
url varchar(128) not null comment 'url地址',
name varchar(128) comment 'name',
password varchar(128) comment 'password'
);
SELECT * FROM scaffold.toolprojecturl;
INSERT INTO `scaffold`.`toolprojecturl` (`id`, `urlname`, `url`,`name`,`password`) VALUES ('1', 'gitUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `scaffold`.`toolprojecturl` (`id`, `urlname`, `url`,`name`,`password`) VALUES ('2', 'jenkinsUrl', '127.0.0.1:8009/','admin','tianjingle');
INSERT INTO `scaffold`.`toolprojecturl` (`id`, `urlname`, `url`,`name`,`password`) VALUES ('3', 'apolloUrl', '127.0.0.1:8009/','admin','tianjingle');
