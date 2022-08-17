INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, '定时任务', 'quartz/schedule/index', NULL, 0, 0, 'icon-reloadtime', 0, 0, 0, 10000, now(), 10000, now());

SET @menuId = @@identity;
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '查看', '', 'schedule:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '新增', '', 'schedule:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '修改', '', 'schedule:update,schedule:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '删除', '', 'schedule:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '立即运行', '', 'schedule:run', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '日志', '', 'schedule:log', 1, 0, '', 4, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES ('schedule_group', '任务组名', '定时任务', 0, 0, 0, 10000, now(), 10000, now());

SET @typeId = @@identity;
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '默认', 'default', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '系统', 'system', '', 1, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES ('schedule_status', '状态', '定时任务', 0, 0, 0, 10000, now(), 10000, now());

SET @typeId = @@identity;
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '暂停', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '正常', '1', '', 1, 0, 0, 10000, now(), 10000, now());


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
    sched_name           varchar(120)    not null            comment '调度名称',
    job_name             varchar(200)    not null            comment '任务名称',
    job_group            varchar(200)    not null            comment '任务组名',
    description          varchar(250)    null                comment '相关介绍',
    job_class_name       varchar(250)    not null            comment '执行任务类名称',
    is_durable           varchar(1)      not null            comment '是否持久化',
    is_nonconcurrent     varchar(1)      not null            comment '是否并发',
    is_update_data       varchar(1)      not null            comment '是否更新数据',
    requests_recovery    varchar(1)      not null            comment '是否接受恢复执行',
    job_data             blob            null                comment '存放持久化job对象',
    primary key (sched_name, job_name, job_group)
) ENGINE=InnoDB COMMENT = '任务详细信息表';

create table QRTZ_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment '触发器的名字',
    trigger_group        varchar(200)    not null            comment '触发器所属组的名字',
    job_name             varchar(200)    not null            comment 'qrtz_job_details表job_name的外键',
    job_group            varchar(200)    not null            comment 'qrtz_job_details表job_group的外键',
    description          varchar(250)    null                comment '相关介绍',
    next_fire_time       bigint(13)      null                comment '上一次触发时间（毫秒）',
    prev_fire_time       bigint(13)      null                comment '下一次触发时间（默认为-1表示不触发）',
    priority             integer         null                comment '优先级',
    trigger_state        varchar(16)     not null            comment '触发器状态',
    trigger_type         varchar(8)      not null            comment '触发器的类型',
    start_time           bigint(13)      not null            comment '开始时间',
    end_time             bigint(13)      null                comment '结束时间',
    calendar_name        varchar(200)    null                comment '日程表名称',
    misfire_instr        smallint(2)     null                comment '补偿执行的策略',
    job_data             blob            null                comment '存放持久化job对象',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group)
    references QRTZ_JOB_DETAILS(sched_name, job_name, job_group)
) ENGINE=InnoDB COMMENT = '触发器详细信息表';

create table QRTZ_SIMPLE_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    repeat_count         bigint(7)       not null            comment '重复的次数统计',
    repeat_interval      bigint(12)      not null            comment '重复的间隔时间',
    times_triggered      bigint(10)      not null            comment '已经触发的次数',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) ENGINE=InnoDB COMMENT = '简单触发器的信息表';

create table QRTZ_CRON_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    cron_expression      varchar(200)    not null            comment 'cron表达式',
    time_zone_id         varchar(80)                         comment '时区',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) ENGINE=InnoDB COMMENT = 'Cron类型的触发器表';

create table QRTZ_BLOB_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    blob_data            blob            null                comment '存放持久化Trigger对象',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) ENGINE=InnoDB COMMENT = 'Blob类型的触发器表';

create table QRTZ_CALENDARS (
    sched_name           varchar(120)    not null            comment '调度名称',
    calendar_name        varchar(200)    not null            comment '日历名称',
    calendar             blob            not null            comment '存放持久化calendar对象',
    primary key (sched_name, calendar_name)
) ENGINE=InnoDB COMMENT = '日历信息表';

create table QRTZ_PAUSED_TRIGGER_GRPS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    primary key (sched_name, trigger_group)
) ENGINE=InnoDB COMMENT = '暂停的触发器表';

create table QRTZ_FIRED_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    entry_id             varchar(95)     not null            comment '调度器实例id',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    instance_name        varchar(200)    not null            comment '调度器实例名',
    fired_time           bigint(13)      not null            comment '触发的时间',
    sched_time           bigint(13)      not null            comment '定时器制定的时间',
    priority             integer         not null            comment '优先级',
    state                varchar(16)     not null            comment '状态',
    job_name             varchar(200)    null                comment '任务名称',
    job_group            varchar(200)    null                comment '任务组名',
    is_nonconcurrent     varchar(1)      null                comment '是否并发',
    requests_recovery    varchar(1)      null                comment '是否接受恢复执行',
    primary key (sched_name, entry_id)
) ENGINE=InnoDB COMMENT = '已触发的触发器表';

create table QRTZ_SCHEDULER_STATE (
    sched_name           varchar(120)    not null            comment '调度名称',
    instance_name        varchar(200)    not null            comment '实例名称',
    last_checkin_time    bigint(13)      not null            comment '上次检查时间',
    checkin_interval     bigint(13)      not null            comment '检查间隔时间',
    primary key (sched_name, instance_name)
) ENGINE=InnoDB COMMENT = '调度器状态表';

create table QRTZ_LOCKS (
    sched_name           varchar(120)    not null            comment '调度名称',
    lock_name            varchar(40)     not null            comment '悲观锁名称',
    primary key (sched_name, lock_name)
) ENGINE=InnoDB COMMENT = '存储的悲观锁信息表';

create table QRTZ_SIMPROP_TRIGGERS (
    sched_name           varchar(120)    not null            comment '调度名称',
    trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
    trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
    str_prop_1           varchar(512)    null                comment 'String类型的trigger的第一个参数',
    str_prop_2           varchar(512)    null                comment 'String类型的trigger的第二个参数',
    str_prop_3           varchar(512)    null                comment 'String类型的trigger的第三个参数',
    int_prop_1           int             null                comment 'int类型的trigger的第一个参数',
    int_prop_2           int             null                comment 'int类型的trigger的第二个参数',
    long_prop_1          bigint          null                comment 'long类型的trigger的第一个参数',
    long_prop_2          bigint          null                comment 'long类型的trigger的第二个参数',
    dec_prop_1           numeric(13,4)   null                comment 'decimal类型的trigger的第一个参数',
    dec_prop_2           numeric(13,4)   null                comment 'decimal类型的trigger的第二个参数',
    bool_prop_1          varchar(1)      null                comment 'Boolean类型的trigger的第一个参数',
    bool_prop_2          varchar(1)      null                comment 'Boolean类型的trigger的第二个参数',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
    references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) ENGINE=InnoDB COMMENT = '同步机制的行锁表';