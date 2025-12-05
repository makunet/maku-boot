INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, '定时任务', 'quartz/schedule/index', NULL, 0, 0, 'icon-reloadtime', 0, 0, 0, 10000, now(), 10000, now());

SET @menuId = @@identity;
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '查看', '', 'schedule:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '新增', '', 'schedule:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '修改', '', 'schedule:update,schedule:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '删除', '', 'schedule:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '立即运行', '', 'schedule:run', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '日志', '', 'schedule:log', 1, 0, '', 4, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ('schedule_group', '任务组名', '定时任务', 0, 10000, 0, 0, 10000, now(), 10000, now());

SET @typeId = @@identity;
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '默认', 'default', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '系统', 'system', '', '', 1, 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ('schedule_status', '状态', '定时任务', 0, 10000, 0, 0, 10000, now(), 10000, now());

SET @typeId = @@identity;
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '暂停', '0', 'danger', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '正常', '1', 'primary', '', 1, 10000, 0, 0, 10000, now(), 10000, now());


DROP TABLE IF EXISTS schedule_job;
DROP TABLE IF EXISTS schedule_job_log;

CREATE TABLE schedule_job (
    id              bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    job_name        varchar(200) COMMENT '名称',
    job_group       varchar(100) COMMENT '分组',
    bean_name       varchar(200)  COMMENT 'spring bean名称',
    method          varchar(100) COMMENT '执行方法',
    params          varchar(2000) COMMENT '参数',
    cron_expression varchar(100) COMMENT 'cron表达式',
    status          tinyint unsigned COMMENT '状态  0：暂停  1：正常',
    concurrent      tinyint unsigned COMMENT '是否并发  0：禁止  1：允许',
    remark          varchar(255) COMMENT '备注',
    version         int COMMENT '版本号',
    deleted         tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator         bigint COMMENT '创建者',
    create_time     datetime COMMENT '创建时间',
    updater         bigint COMMENT '更新者',
    update_time     datetime COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='定时任务';

CREATE TABLE schedule_job_log (
    id            bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    job_id        bigint NOT NULL COMMENT '任务id',
    job_name      varchar(200) COMMENT '任务名称',
    job_group     varchar(100) COMMENT '任务组名',
    bean_name     varchar(200) COMMENT 'spring bean名称',
    method        varchar(100) COMMENT '执行方法',
    params        varchar(2000) COMMENT '参数',
    status        tinyint unsigned NOT NULL COMMENT '任务状态    0：失败    1：成功',
    error         varchar(2000) COMMENT '异常信息',
    times         bigint NOT NULL COMMENT '耗时(单位：毫秒)',
    create_time   datetime COMMENT '创建时间',
    PRIMARY KEY (id),
    key idx_job_id (job_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='定时任务日志';


INSERT INTO schedule_job (id, job_name, job_group, bean_name, method, params, cron_expression, status, concurrent, remark, version, deleted, creator, create_time, updater, update_time) VALUES (1, '测试任务', 'system', 'testTask', 'run', '123', '0 * * * * ? *', 0, 0, '', 14, 0, 10000, now(), 10000, now());
