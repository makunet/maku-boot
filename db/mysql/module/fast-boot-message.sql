INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, '短信服务', 'message/sms/index', NULL, 0, 0, 'icon-reloadtime', 0, 0, 0, 10000, now(), 10000, now());

SET @menuId = @@identity;
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '查看', '', 'schedule:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '新增', '', 'schedule:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '修改', '', 'schedule:update,schedule:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '删除', '', 'schedule:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '立即运行', '', 'schedule:run', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '日志', '', 'schedule:log', 1, 0, '', 4, 0, 0, 10000, now(), 10000, now());


CREATE TABLE sms_platform
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    platform    tinyint COMMENT '平台类型  0：阿里云   1：腾讯云',
    sign_name   varchar(50) NOT NULL COMMENT '短信签名',
    template_id varchar(50) NOT NULL COMMENT '短信模板',
    access_key  varchar(100) COMMENT '密码',
    secret_key  varchar(100) COMMENT '密码',
    status      tinyint COMMENT '状态  0：禁用   1：启用',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='短信平台';


CREATE TABLE sms_log
(
    id             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    platform_id    bigint COMMENT '平台ID',
    platform       tinyint COMMENT '平台类型',
    mobile         varchar(20) NOT NULL COMMENT '手机号',
    status         tinyint COMMENT '状态  0：失败   1：成功',
    params         varchar(200) COMMENT '参数',
    create_time    datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='短信日志';