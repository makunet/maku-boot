SET IDENTITY_INSERT sys_menu ON;
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1201, 1, '定时任务', 'quartz/schedule/index', NULL, 0, 0, 'icon-reloadtime', 0, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1202, 1201, '查看', '', 'schedule:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1203, 1201, '新增', '', 'schedule:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1204, 1201, '修改', '', 'schedule:update,schedule:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1205, 1201, '删除', '', 'schedule:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1206, 1201, '立即运行', '', 'schedule:run', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1207, 1201, '日志', '', 'schedule:log', 1, 0, '', 4, 0, 0, 10000, now(), 10000, now());

SET IDENTITY_INSERT sys_dict_type ON;
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1201, 'schedule_group', '任务组名', '定时任务', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1201, '默认', 'default', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1201, '系统', 'system', '', 1, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1202, 'schedule_status', '状态', '定时任务', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1202, '暂停', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1202, '正常', '1', '', 1, 0, 0, 10000, now(), 10000, now());


DROP TABLE IF EXISTS schedule_job;
DROP TABLE IF EXISTS schedule_job_log;

CREATE TABLE schedule_job (
    id              bigint IDENTITY NOT NULL,
    job_name        varchar(200),
    job_group       varchar(100),
    bean_name       varchar(200),
    method          varchar(100),
    params          varchar(2000),
    cron_expression varchar(100),
    status          int,
    concurrent      int,
    remark          varchar(255),
    version         int,
    deleted         int,
    creator         bigint,
    create_time     datetime,
    updater         bigint,
    update_time     datetime,
    PRIMARY KEY (id)
);


COMMENT ON TABLE schedule_job IS '定时任务';
COMMENT ON COLUMN schedule_job.id IS 'id';
COMMENT ON COLUMN schedule_job.job_name IS '名称';
COMMENT ON COLUMN schedule_job.job_group IS '分组';
COMMENT ON COLUMN schedule_job.bean_name IS 'spring bean名称';
COMMENT ON COLUMN schedule_job.method IS '执行方法';
COMMENT ON COLUMN schedule_job.params IS '参数';
COMMENT ON COLUMN schedule_job.cron_expression IS 'cron表达式';
COMMENT ON COLUMN schedule_job.status IS '状态  0：暂停  1：正常';
COMMENT ON COLUMN schedule_job.concurrent IS '是否并发  0：禁止  1：允许';
COMMENT ON COLUMN schedule_job.remark IS '备注';
COMMENT ON COLUMN schedule_job.version IS '版本号';
COMMENT ON COLUMN schedule_job.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN schedule_job.creator IS '创建者';
COMMENT ON COLUMN schedule_job.create_time IS '创建时间';
COMMENT ON COLUMN schedule_job.updater IS '更新者';
COMMENT ON COLUMN schedule_job.update_time IS '更新时间';


CREATE TABLE schedule_job_log (
    id            bigint IDENTITY NOT NULL,
    job_id        bigint NOT NULL,
    job_name      varchar(200),
    job_group     varchar(100),
    bean_name     varchar(200),
    method        varchar(100),
    params        varchar(2000),
    status        int,
    error         varchar(2000),
    times         bigint NOT NULL,
    create_time   datetime,
    PRIMARY KEY (id)
);

COMMENT ON TABLE schedule_job_log IS '定时任务日志';
COMMENT ON COLUMN schedule_job_log.id IS 'id';
COMMENT ON COLUMN schedule_job_log.job_id IS '任务id';
COMMENT ON COLUMN schedule_job_log.job_name IS '任务名称';
COMMENT ON COLUMN schedule_job_log.job_group IS '任务组名';
COMMENT ON COLUMN schedule_job_log.bean_name IS 'spring bean名称';
COMMENT ON COLUMN schedule_job_log.method IS '执行方法';
COMMENT ON COLUMN schedule_job_log.params IS '参数';
COMMENT ON COLUMN schedule_job_log.status IS '任务状态    0：失败    1：成功';
COMMENT ON COLUMN schedule_job_log.error IS '异常信息';
COMMENT ON COLUMN schedule_job_log.times IS '耗时(单位：毫秒)';
COMMENT ON COLUMN schedule_job_log.create_time IS '创建时间';


INSERT INTO schedule_job (job_name, job_group, bean_name, method, params, cron_expression, status, concurrent, remark, version, deleted, creator, create_time, updater, update_time) VALUES ('测试任务', 'system', 'testTask', 'run', '123', '0 * * * * ? *', 0, 0, '', 14, 0, 10000, now(), 10000, now());




-- ----------------------------------------------------------
--              以下为Quartz框架，自带的表结构
-- ----------------------------------------------------------

DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

create table QRTZ_JOB_DETAILS (
    sched_name           varchar(120)    not null,
    job_name             varchar(200)    not null,
    job_group            varchar(200)    not null,
    description          varchar(250)    null,
    job_class_name       varchar(250)    not null,
    is_durable           varchar(1)      not null,
    is_nonconcurrent     varchar(1)      not null,
    is_update_data       varchar(1)      not null,
    requests_recovery    varchar(1)      not null,
    job_data             blob            null,
    primary key (sched_name, job_name, job_group)
);

create table QRTZ_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    job_name             varchar(200)    not null,
    job_group            varchar(200)    not null,
    description          varchar(250)    null,
    next_fire_time       bigint      null,
    prev_fire_time       bigint      null,
    priority             int         null,
    trigger_state        varchar(16)     not null,
    trigger_type         varchar(8)      not null,
    start_time           bigint      not null,
    end_time             bigint      null,
    calendar_name        varchar(200)    null,
    misfire_instr        int     null,
    job_data             blob            null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group)
    references QRTZ_JOB_DETAILS(sched_name, job_name, job_group)
);

create table QRTZ_SIMPLE_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    repeat_count         bigint       not null,
    repeat_interval      bigint      not null,
    times_triggered      bigint     not null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
);

create table QRTZ_CRON_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    cron_expression      varchar(200)    not null,
    time_zone_id         varchar(80),
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
);

create table QRTZ_BLOB_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    blob_data            blob            null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
);

create table QRTZ_CALENDARS (
    sched_name           varchar(120)    not null,
    calendar_name        varchar(200)    not null,
    calendar             blob            not null,
    primary key (sched_name, calendar_name)
);

create table QRTZ_PAUSED_TRIGGER_GRPS (
    sched_name           varchar(120)    not null,
    trigger_group        varchar(200)    not null,
    primary key (sched_name, trigger_group)
);

create table QRTZ_FIRED_TRIGGERS (
    sched_name           varchar(120)    not null,
    entry_id             varchar(95)     not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    instance_name        varchar(200)    not null,
    fired_time           bigint      not null,
    sched_time           bigint      not null,
    priority             int         not null,
    state                varchar(16)     not null,
    job_name             varchar(200)    null,
    job_group            varchar(200)    null,
    is_nonconcurrent     varchar(1)      null,
    requests_recovery    varchar(1)      null,
    primary key (sched_name, entry_id)
);

create table QRTZ_SCHEDULER_STATE (
    sched_name           varchar(120)    not null,
    instance_name        varchar(200)    not null,
    last_checkin_time    bigint      not null,
    checkin_interval     bigint      not null,
    primary key (sched_name, instance_name)
);

create table QRTZ_LOCKS (
    sched_name           varchar(120)    not null,
    lock_name            varchar(40)     not null,
    primary key (sched_name, lock_name)
);

create table QRTZ_SIMPROP_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    str_prop_1           varchar(512)    null,
    str_prop_2           varchar(512)    null,
    str_prop_3           varchar(512)    null,
    int_prop_1           int             null,
    int_prop_2           int             null,
    long_prop_1          bigint          null,
    long_prop_2          bigint          null,
    dec_prop_1           numeric(13,4)   null,
    dec_prop_2           numeric(13,4)   null,
    bool_prop_1          varchar(1)      null,
    bool_prop_2          varchar(1)      null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
);
