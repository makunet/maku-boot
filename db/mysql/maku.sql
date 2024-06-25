CREATE TABLE sys_user
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    username    varchar(50) NOT NULL COMMENT '用户名',
    password    varchar(100) COMMENT '密码',
    real_name   varchar(50) COMMENT '姓名',
    avatar      varchar(200) COMMENT '头像',
    gender      tinyint COMMENT '性别   0：男   1：女   2：未知',
    email       varchar(100) COMMENT '邮箱',
    mobile      varchar(20) COMMENT '手机号',
    org_id      bigint COMMENT '机构ID',
    super_admin tinyint COMMENT '超级管理员   0：否   1：是',
    status      tinyint COMMENT '状态  0：停用   1：正常',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户管理';

CREATE TABLE sys_user_token
(
    id                    bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id               bigint COMMENT '用户ID',
    access_token          varchar(32) COMMENT 'accessToken',
    access_token_expire   datetime COMMENT 'accessToken 过期时间',
    refresh_token         varchar(32) COMMENT 'refreshToken',
    refresh_token_expire  datetime COMMENT 'refreshToken 过期时间',
    tenant_id             bigint COMMENT '租户ID',
    create_time           datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户Token';

CREATE TABLE sys_third_login
(
    id                    bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    open_type             varchar(50) COMMENT '开放平台类型',
    open_id               varchar(100) COMMENT '开放平台，唯一标识',
    username              varchar(100) COMMENT '昵称',
    user_id               bigint COMMENT '用户ID',
    tenant_id             bigint COMMENT '租户ID',
    version               int COMMENT '版本号',
    deleted               tinyint COMMENT '删除标识  0：正常   1：已删除',
    create_time           datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='第三方登录';

CREATE TABLE sys_third_login_config
(
    id                    bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    open_type             varchar(50) COMMENT '开放平台类型',
    client_id             varchar(200) COMMENT 'ClientID',
    client_secret         varchar(200) COMMENT 'ClientSecret',
    redirect_uri          varchar(200) COMMENT 'RedirectUri',
    agent_id              varchar(200) COMMENT 'AgentID',
    tenant_id             bigint COMMENT '租户ID',
    version               int COMMENT '版本号',
    deleted               tinyint COMMENT '删除标识  0：正常   1：已删除',
    create_time           datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='第三方登录配置';

CREATE TABLE sys_org
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    pid         bigint COMMENT '上级ID',
    name        varchar(50) COMMENT '机构名称',
    sort        int COMMENT '排序',
    leader_id   bigint COMMENT '负责人ID',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_pid (pid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='机构管理';

create table sys_role
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    name        varchar(50) COMMENT '角色名称',
    role_code   varchar(50) COMMENT '角色编码',
    remark      varchar(100) COMMENT '备注',
    data_scope  tinyint COMMENT '数据范围  0：全部数据  1：本机构及子机构数据  2：本机构数据  3：本人数据  4：自定义数据',
    org_id      bigint COMMENT '机构ID',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_org_id (org_id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='角色管理';

create table sys_user_role
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id     bigint COMMENT '角色ID',
    user_id     bigint COMMENT '用户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_role_id (role_id),
    key idx_user_id (user_id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='用户角色关系';

CREATE TABLE sys_post
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    post_code   varchar(100) COMMENT '岗位编码',
    post_name   varchar(100) COMMENT '岗位名称',
    sort        int COMMENT '排序',
    status      tinyint COMMENT '状态  0：停用   1：正常',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='岗位管理';

CREATE TABLE sys_user_post
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id     bigint COMMENT '用户ID',
    post_id     bigint COMMENT '岗位ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_user_id (user_id),
    key idx_post_id (post_id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='用户岗位关系';

create table sys_menu
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    pid         bigint COMMENT '上级ID',
    name        varchar(200) COMMENT '菜单名称',
    url         varchar(200) COMMENT '菜单URL',
    authority   varchar(500) COMMENT '授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)',
    type        tinyint COMMENT '类型   0：菜单   1：按钮   2：接口',
    open_style  tinyint COMMENT '打开方式   0：内部   1：外部',
    icon        varchar(50) COMMENT '菜单图标',
    sort        int COMMENT '排序',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_pid (pid)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='菜单管理';

create table sys_role_menu
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id     bigint COMMENT '角色ID',
    menu_id     bigint COMMENT '菜单ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_role_id (role_id),
    key idx_menu_id (menu_id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='角色菜单关系';

create table sys_role_data_scope
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id     bigint COMMENT '角色ID',
    org_id      bigint COMMENT '机构ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id),
    key idx_role_id (role_id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='角色数据权限';

create table sys_dict_type
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    dict_type   varchar(100) NOT NULL COMMENT '字典类型',
    dict_name   varchar(255) NOT NULL COMMENT '字典名称',
    dict_source tinyint default 0 COMMENT '来源  0：字典数据  1：动态SQL',
    dict_sql    varchar(500) COMMENT '动态SQL',
    remark      varchar(255) COMMENT '备注',
    sort        int COMMENT '排序',
    pid         bigint COMMENT '上级节点',
    has_child   tinyint default 0 COMMENT '是否有子节点',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='字典类型';

create table sys_dict_data
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    dict_type_id bigint NOT NULL COMMENT '字典类型ID',
    dict_label   varchar(255) NOT NULL COMMENT '字典标签',
    dict_value   varchar(255) COMMENT '字典值',
    label_class  varchar(100) COMMENT '标签样式',
    remark       varchar(255) COMMENT '备注',
    sort         int COMMENT '排序',
    tenant_id    bigint COMMENT '租户ID',
    version      int COMMENT '版本号',
    deleted      tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator      bigint COMMENT '创建者',
    create_time  datetime COMMENT '创建时间',
    updater      bigint COMMENT '更新者',
    update_time  datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='字典数据';

create table sys_attachment
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    name         varchar(255) NOT NULL COMMENT '附件名称',
    url          varchar(255) NOT NULL COMMENT '附件地址',
    size         bigint COMMENT '附件大小',
    platform     varchar(50) COMMENT '存储平台',
    tenant_id    bigint COMMENT '租户ID',
    version      int COMMENT '版本号',
    deleted      tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator      bigint COMMENT '创建者',
    create_time  datetime COMMENT '创建时间',
    updater      bigint COMMENT '更新者',
    update_time  datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='附件管理';

create table sys_params
(
    id            bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    param_name    varchar(100) COMMENT '参数名称',
    param_type    tinyint NOT NULL COMMENT '系统参数   0：否   1：是',
    param_key     varchar(100) COMMENT '参数键',
    param_value   varchar(2000) COMMENT '参数值',
    remark        varchar(200) COMMENT '备注',
    tenant_id     bigint COMMENT '租户ID',
    version       int COMMENT '版本号',
    deleted       tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator       bigint COMMENT '创建者',
    create_time   datetime COMMENT '创建时间',
    updater       bigint COMMENT '更新者',
    update_time   datetime COMMENT '更新时间',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='参数管理';

create table sys_log_login
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    username     varchar(50) COMMENT '用户名',
    ip           varchar(32) COMMENT '登录IP',
    address      varchar(32) COMMENT '登录地点',
    user_agent   varchar(500) COMMENT 'User Agent',
    status       tinyint COMMENT '登录状态  0：失败   1：成功',
    operation    tinyint unsigned COMMENT '操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误',
    tenant_id    bigint COMMENT '租户ID',
    create_time  datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='登录日志';

create table sys_log_operate
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    module       varchar(100) COMMENT '模块名',
    name         varchar(100) COMMENT '操作名',
    req_uri      varchar(200) DEFAULT NULL COMMENT '请求URI',
    req_method   varchar(20) DEFAULT NULL COMMENT '请求方法',
    req_params   text COMMENT '请求参数',
    ip           varchar(32) COMMENT '操作IP',
    address      varchar(32) COMMENT '登录地点',
    user_agent   varchar(500) COMMENT 'User Agent',
    operate_type tinyint COMMENT '操作类型',
    duration     int NOT NULL COMMENT '执行时长',
    status       tinyint COMMENT '操作状态  0：失败   1：成功',
    user_id      bigint COMMENT '用户ID',
    real_name    varchar(50) COMMENT '操作人',
    result_msg   varchar(500) COMMENT '返回消息',
    tenant_id    bigint COMMENT '租户ID',
    create_time  datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='操作日志';


CREATE TABLE sys_sms_config
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    platform    tinyint COMMENT '平台类型  0：阿里云   1：腾讯云   2：七牛云   3：华为云',
    group_name  varchar(100)  COMMENT '分组名称，发送短信时，可指定分组',
    sign_name   varchar(100)  COMMENT '短信签名',
    template_id varchar(100) COMMENT '短信模板',
    app_id      varchar(100) COMMENT '短信应用ID，如：腾讯云等',
    sender_id   varchar(100) COMMENT '腾讯云国际短信、华为云等需要',
    url         varchar(200) COMMENT '接入地址，如：华为云',
    access_key  varchar(100) COMMENT 'AccessKey',
    secret_key  varchar(100) COMMENT 'SecretKey',
    status      tinyint COMMENT '状态  0：禁用   1：启用',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='短信配置';

CREATE TABLE sys_sms_log
(
    id             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    platform_id    bigint COMMENT '平台ID',
    platform       tinyint COMMENT '平台类型',
    mobile         varchar(20) NOT NULL COMMENT '手机号',
    params         varchar(200) COMMENT '参数',
    status         tinyint COMMENT '状态  0：失败   1：成功',
    error          varchar(2000) COMMENT '异常信息',
    create_time    datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='短信日志';


CREATE TABLE sys_mail_config
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    platform    tinyint COMMENT '平台类型  -1：本地   0：阿里云',
    group_name  varchar(100) COMMENT '分组名称，发送邮件时，可指定分组',
    mail_host   varchar(100) COMMENT 'SMTP服务器',
    mail_port   int COMMENT 'SMTP端口',
    mail_from   varchar(100) COMMENT '发件人邮箱',
    mail_pass   varchar(100) COMMENT '发件人密码',
    region_id   varchar(100) COMMENT 'regionId',
    endpoint    varchar(100) COMMENT '阿里云 endpoint',
    access_key  varchar(100) COMMENT 'AccessKey',
    secret_key  varchar(100) COMMENT 'SecretKey',
    status      tinyint COMMENT '状态  0：禁用   1：启用',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='邮件平台';

CREATE TABLE sys_mail_log
(
    id             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    platform_id    bigint COMMENT '平台ID',
    platform       tinyint COMMENT '平台类型',
    mail_from      varchar(100) COMMENT '发件人邮箱',
    mail_tos       varchar(1000) COMMENT '接受人邮箱',
    subject        varchar(200) COMMENT '邮件主题',
    content        text COMMENT '邮件内容',
    status         tinyint COMMENT '状态  0：失败   1：成功',
    error          varchar(2000) COMMENT '异常信息',
    create_time    datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='邮件日志';

INSERT INTO sys_user (id, username, password, real_name, avatar, gender, email, mobile, status, org_id, super_admin, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (10000, 'admin', 'dc1fd00e3eeeb940ff46f457bf97d66ba7fcc36e0b20802383de142860e76ae6', 'admin', 'https://cdn.maku.net/images/avatar.png', 0, 'babamu@126.com', '13612345678', 1, null, 1, 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, NULL, '系统设置', NULL, NULL, 0, 0, 'icon-setting', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (2, 1, '菜单管理', 'sys/menu/index', NULL, 0, 0, 'icon-menu', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (3, 2, '查看', '', 'sys:menu:list', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (4, 2, '新增', '', 'sys:menu:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (5, 2, '修改', '', 'sys:menu:update,sys:menu:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (6, 2, '删除', '', 'sys:menu:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (7, 1, '数据字典', 'sys/dict/type', '', 0, 0, 'icon-insertrowabove', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (8, 7, '查询', '', 'sys:dict:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (9, 7, '新增', '', 'sys:dict:save', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (10, 7, '修改', '', 'sys:dict:update,sys:dict:info', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (11, 7, '删除', '', 'sys:dict:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (12, NULL, '权限管理', '', '', 0, 0, 'icon-safetycertificate', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (13, 12, '岗位管理', 'sys/post/index', '', 0, 0, 'icon-solution', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (14, 13, '查询', '', 'sys:post:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (15, 13, '新增', '', 'sys:post:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (16, 13, '修改', '', 'sys:post:update,sys:post:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (17, 13, '删除', '', 'sys:post:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (18, 12, '机构管理', 'sys/org/index', '', 0, 0, 'icon-cluster', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (19, 18, '查询', '', 'sys:org:list', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (20, 18, '新增', '', 'sys:org:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (21, 18, '修改', '', 'sys:org:update,sys:org:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (22, 18, '删除', '', 'sys:org:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (23, 12, '角色管理', 'sys/role/index', '', 0, 0, 'icon-team', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (24, 23, '查询', '', 'sys:role:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (25, 23, '新增', '', 'sys:role:save,sys:role:menu,sys:org:list', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (26, 23, '修改', '', 'sys:role:update,sys:role:info,sys:role:menu,sys:org:list,sys:user:page', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (27, 23, '删除', '', 'sys:role:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (28, 12, '用户管理', 'sys/user/index', '', 0, 0, 'icon-user', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (29, 28, '查询', '', 'sys:user:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (30, 28, '新增', '', 'sys:user:save,sys:role:list', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (31, 28, '修改', '', 'sys:user:update,sys:user:info,sys:role:list', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (32, 28, '删除', '', 'sys:user:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (33, NULL, '应用管理', '', '', 0, 0, 'icon-appstore', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (34, NULL, '日志管理', '', '', 0, 0, 'icon-filedone', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (35, 34, '登录日志', 'sys/log/login', 'sys:log:login', 0, 0, 'icon-solution', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (36, 28, '导入', '', 'sys:user:import', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (37, 28, '导出', '', 'sys:user:export', 1, 0, '', 6, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (38, 1, '参数管理', 'sys/params/index', 'sys:params:all', 0, 0, 'icon-filedone', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (39, 1, '接口文档', '{{apiUrl}}/doc.html', null, 0, 1, 'icon-file-text-fill', 10, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (40, 34, '操作日志', 'sys/log/operate', 'sys:operate:all', 0, 0, 'icon-file-text', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (41, 1, '系统配置', 'sys/config/index', null, 0, 0, 'icon-safetycertificate', 4, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (42, 41, '短信配置', '', 'sys:sms:config', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (43, 41, '邮件配置', '', 'sys:mail:config', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (44, 41, '第三方登录', '', 'sys:third:config', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (45, NULL, '基础工具', '', '', 0, 0, 'icon-wrench-fill', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (46, 45, '短信发送', 'sys/tool/sms/index', 'sys:sms:log', 0, 0, 'icon-message', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (47, 45, '邮件发送', 'sys/tool/mail/index', 'sys:mail:log', 0, 0, 'icon-mail', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (48, 45, '附件管理', 'sys/attachment/index', NULL, 0, 0, 'icon-folder-fill', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (49, 48, '查看', '', 'sys:attachment:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (50, 48, '上传', '', 'sys:attachment:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (51, 48, '删除', '', 'sys:attachment:delete', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (52, NULL, '企业版', 'https://maku.net/price', NULL, 0, 1, 'icon-safetycertificate', 10, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1, 'post_status', '状态', '岗位管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (2, 'user_gender', '性别', '用户管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (3, 'user_status', '状态', '用户管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (4, 'role_data_scope', '数据范围', '角色管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (5, 'enable_disable', '状态', '功能状态：启用 | 禁用 ', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (6, 'success_fail', '状态', '操作状态：成功 | 失败', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (7, 'login_operation', '操作信息', '登录管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (8, 'params_type', '系统参数', '参数管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (9, 'user_super_admin', '用户是否是超管','用户是否是超管', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (10, 'log_operate_type', '操作类型', '操作日志', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (11, 'sms_platform', '短信平台类型', '短信管理', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (12, 'mail_platform', '邮件平台类型', '邮件管理', 0, 10000, 0, 0, 10000, now(), 10000, now());


INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1, 1, '停用', '0', 'danger', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (2, 1, '正常', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (3, 2, '男', '0', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (4, 2, '女', '1', 'success', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (5, 2, '未知', '2', 'warning', '', 2, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (6, 3, '正常', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (7, 3, '停用', '0', 'danger', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (8, 4, '全部数据', '0', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (9, 4, '本机构及子机构数据', '1', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (10, 4, '本机构数据', '2', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (11, 4, '本人数据', '3', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (12, 4, '自定义数据', '4', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (13, 5, '禁用', '0', 'danger', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (14, 5, '启用', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (15, 6, '失败', '0', 'danger', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (16, 6, '成功', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (17, 7, '登录成功', '0', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (18, 7, '退出成功', '1', 'warning', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (19, 7, '验证码错误', '2', 'danger', '', 2, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (20, 7, '账号密码错误', '3', 'danger', '', 3, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (21, 8, '否', '0', 'primary', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (22, 8, '是', '1', 'danger', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (23, 9, '是', '1', 'danger', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (24, 9, '否', '0', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (25, 10, '其它', '0', 'info', '', 10, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (26, 10, '查询', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (27, 10, '新增', '2', 'success', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (28, 10, '修改', '3', 'warning', '', 2, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (29, 10, '删除', '4', 'danger', '', 3, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (30, 10, '导出', '5', 'info', '', 4, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (31, 10, '导入', '6', 'info', '', 5, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (32, 11, '阿里云', '0', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (33, 11, '腾讯云', '1', '', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (34, 11, '七牛云', '2', '', '', 2, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (35, 11, '华为云', '3', '', '', 3, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (36, 12, '本地', '-1', '', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (37, 12, '阿里云', '0', '', '', 1, 10000, 0, 0, 10000, now(), 10000, now());


INSERT INTO sys_params (param_name, param_type, param_key, param_value, remark, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ('用户登录-验证码开关', 1, 'LOGIN_CAPTCHA', 'false', '是否开启验证码（true：开启，false：关闭）', 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_third_login_config (open_type, client_id, client_secret, redirect_uri, agent_id, tenant_id, version, deleted, create_time) VALUES ('feishu', 'cli_a541d3aa03f8500b', '5Chz39zvEhZtxSVZz3vLjfQHdkvavQaH', 'http://localhost:8080/sys/third/callback/feishu', '', 10000, 0, 0, now());

INSERT INTO sys_mail_config (platform, group_name, mail_host, mail_port, mail_from, mail_pass, region_id, endpoint, access_key, secret_key, status, version, deleted, creator, create_time, updater, update_time) VALUES (-1, 'test', NULL, NULL, 'baba_tv@163.com', 'TZNVURLYVBNJUNBB', '', '', NULL, NULL, 1, 1, 0, 10000, now(), 10000, now());