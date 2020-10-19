--liquibase formatted sql
--changeset tianjl:tianjingle-20200924-1

use scaffold;

--liquibase formatted sql
--changeset tianjl:tianjingle-20200924-2
--创建工程的表--
create table project_info(
id int not null primary key auto_increment COMMENT '主键',
project_id int not null COMMENT '项目id，scaffold的项目id',
project_name varchar(128) comment '项目名称',
health_url varchar(128)  COMMENT '服务监控检测地址',
dump_url varchar(128)  COMMENT 'java应用堆内存监控地址',
info_url varchar(128) COMMENT '项目基本信息',
http_trance_url varchar(128)  COMMENT '项目名称',
properties_url varchar(128)  COMMENT '项目配置信息',
create_time DateTime COMMENT '创建时间',
other varchar(1024)  COMMENT '其他信息'
);


--liquibase formatted sql
--changeset tianjl:tianjingle-20200924-3
--httptrace信息--
create table http_trace(
id varchar (64) not null primary key comment '主键',
project_id int not null COMMENT '项目id，scaffold的项目id',
time_token int comment '时间标志',
request_info varchar(512) comment '请求信息',
reponse_info varchar(512) comment '返回信息',
stata int comment '状态',
create_time DateTime comment '创建的时间',
other varchar(1028) comment '预留字段'
);



--liquibase fromatted sql
--changeset tianjl:tianjingle-20200924-5
--内存信息--
create table system_info(
id varchar (64) not null primary key comment '主键',
project_id int not null COMMENT '项目id，scaffold的项目id',
jvm_memory_max varchar(128),
jvm_threads_states varchar(128),
jvm_gc_pause varchar(128),
jvm_gc_memory_promoted varchar(128),
jvm_memory_used varchar(128),
jvm_gc_max_data_size varchar(128),
jvm_memory_committed varchar(128),
system_cpu_count varchar(128),
logback_events varchar(128),
tomcat_global_sent varchar(128),
jvm_buffer_memory_used varchar(128),
tomcat_sessions_created varchar(128),
jvm_threads_daemon varchar(128),
system_cpu_usage varchar(128),
jvm_gc_memory_allocated varchar(128),
tomcat_global_request_max varchar(128),
tomcat_global_request varchar(128),
tomcat_sessions_expired varchar(128),
jvm_threads_live varchar(128),
jvm_threads_peak varchar(128),
tomcat_global_received varchar(128),
process_uptime DateTime,
tomcat_sessions_rejected varchar(128),
process_cpu_usage varchar(128),
tomcat_threads_config_max varchar(128),
jvm_classes_loaded varchar(128),
jvm_classes_unloaded varchar(128),
tomcat_global_error varchar(128),
tomcat_sessions_active_current varchar(128),
http_server_requests varchar(128),
tomcat_sessions_alive_max varchar(128),
jvm_gc_live_data_size varchar(128),
tomcat_threads_current varchar(128),
jvm_buffer_count varchar(128),
jvm_buffer_total_capacity varchar(128),
tomcat_sessions_active_max varchar(128),
tomcat_threads_busy varchar(128),
process_start_time DateTime,
create_time DateTime comment '创建的时间'
);



