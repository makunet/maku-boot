INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1101, 33, '消息管理', '', '', 0, 0, 'icon-message', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1102, 1101, '短信日志', 'message/sms/log/index', 'sms:log', 0, 0, 'icon-detail', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1103, 1101, '短信平台', 'message/sms/platform/index', NULL, 0, 0, 'icon-whatsapp', 0, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1104, 1103, '查看', '', 'sms:platform:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1105, 1103, '新增', '', 'sms:platform:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1106, 1103, '修改', '', 'sms:platform:update,sms:platform:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1107, 1103, '删除', '', 'sms:platform:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1101, 'sms_platform', '平台类型', '短信管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1101, '阿里云', '0', '', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1101, '腾讯云', '1', '', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1101, '七牛云', '2', '', '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1101, '华为云', '3', '', '', 3, 0, 0, 10000, now(), 10000, now());

select setval('sys_menu_id_seq', (select max(id) from sys_menu));
select setval('sys_dict_type_id_seq', (select max(id) from sys_dict_type));
select setval('sys_dict_data_id_seq', (select max(id) from sys_dict_data));

DROP TABLE IF EXISTS sms_platform;
DROP TABLE IF EXISTS sms_log;

CREATE TABLE sms_platform
(
    id          bigserial NOT NULL,
    platform    int,
    sign_name   varchar(100) NOT NULL,
    template_id varchar(100) NOT NULL,
    app_id      varchar(100) NOT NULL,
    sender_id   varchar(100) NOT NULL,
    url         varchar(200) NOT NULL,
    access_key  varchar(100),
    secret_key  varchar(100),
    status      int,
    version     int,
    deleted     int,
    creator     int8,
    create_time timestamp,
    updater     int8,
    update_time timestamp,
    primary key (id)
);

COMMENT ON TABLE sms_platform IS '短信平台';
COMMENT ON COLUMN sms_platform.id IS 'id';
COMMENT ON COLUMN sms_platform.platform IS '平台类型  0：阿里云   1：腾讯云   2：七牛云   3：华为云';
COMMENT ON COLUMN sms_platform.sign_name IS '短信签名';
COMMENT ON COLUMN sms_platform.template_id IS '短信模板';
COMMENT ON COLUMN sms_platform.app_id IS '短信应用ID，如：腾讯云等';
COMMENT ON COLUMN sms_platform.sender_id IS '腾讯云国际短信、华为云等需要';
COMMENT ON COLUMN sms_platform.url IS '接入地址，如：华为云';
COMMENT ON COLUMN sms_platform.access_key IS 'AccessKey';
COMMENT ON COLUMN sms_platform.secret_key IS 'SecretKey';
COMMENT ON COLUMN sms_platform.status IS '状态  0：禁用   1：启用';
COMMENT ON COLUMN sms_platform.version IS '版本号';
COMMENT ON COLUMN sms_platform.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sms_platform.creator IS '创建者';
COMMENT ON COLUMN sms_platform.create_time IS '创建时间';
COMMENT ON COLUMN sms_platform.updater IS '更新者';
COMMENT ON COLUMN sms_platform.update_time IS '更新时间';

CREATE TABLE sms_log
(
    id             bigserial NOT NULL,
    platform_id    int8,
    platform       int,
    mobile         varchar(20) NOT NULL,
    params         varchar(200),
    status         int,
    error          varchar(2000),
    create_time    timestamp,
    primary key (id)
);

COMMENT ON TABLE sms_log IS '短信日志';
COMMENT ON COLUMN sms_log.id IS 'id';
COMMENT ON COLUMN sms_log.platform_id IS '平台ID';
COMMENT ON COLUMN sms_log.platform IS '平台类型';
COMMENT ON COLUMN sms_log.mobile IS '手机号';
COMMENT ON COLUMN sms_log.params IS '参数';
COMMENT ON COLUMN sms_log.status IS '状态  0：失败   1：成功';
COMMENT ON COLUMN sms_log.error IS '异常信息';
COMMENT ON COLUMN sms_log.create_time IS '创建时间';
