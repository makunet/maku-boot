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
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识  0：正常   1：已删除',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户管理';

CREATE TABLE sys_org
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    pid         bigint COMMENT '上级ID',
    name        varchar(50) COMMENT '机构名称',
    sort        int COMMENT '排序',
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
    remark      varchar(100) COMMENT '备注',
    data_scope  tinyint COMMENT '数据范围  0：全部数据  1：本机构及子机构数据  2：本机构数据  3：本人数据  4：自定义数据',
    org_id      bigint COMMENT '机构ID',
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
    pid         bigint COMMENT '上级ID，一级菜单为0',
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
    remark       varchar(255) COMMENT '备注',
    sort         int COMMENT '排序',
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
    create_time  datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT ='登录日志';



INSERT INTO sys_user (id, username, password, real_name, avatar, gender, email, mobile, status, org_id, super_admin, version, deleted, creator, create_time, updater, update_time) VALUES (10000, 'admin', '{bcrypt}$2a$10$mW/yJPHjyueQ1g26WNBz0uxVPa0GQdJO1fFZmqdkqgMTGnyszlXxu', 'admin', 'https://cdn.maku.net/images/avatar.png', 0, 'babamu@126.com', '13612345678', 1, null, 1, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, 0, '系统设置', NULL, NULL, 0, 0, 'icon-setting', 1, 0, 0, 10000, now(), 10000, now());
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
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (12, 0, '权限管理', '', '', 0, 0, 'icon-safetycertificate', 0, 0, 0, 10000, now(), 10000, now());
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
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (33, 0, '应用管理', '', '', 0, 0, 'icon-appstore', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (34, 1, '附件管理', 'sys/attachment/index', NULL, 0, 0, 'icon-folder-fill', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (35, 34, '查看', '', 'sys:attachment:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (36, 34, '上传', '', 'sys:attachment:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (37, 34, '删除', '', 'sys:attachment:delete', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (38, 0, '日志管理', '', '', 0, 0, 'icon-filedone', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (39, 38, '登录日志', 'sys/log/login', 'sys:log:login', 0, 0, 'icon-solution', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (40, 28, '导入', '', 'sys:user:import', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (41, 28, '导出', '', 'sys:user:export', 1, 0, '', 6, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (42, 1, '参数管理', 'sys/params/index', 'sys:params:all', 0, 0, 'icon-filedone', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (43, 1, '接口文档', '{{apiUrl}}/doc.html', null, 0, 1, 'icon-file-text-fill', 10, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, 'post_status', '状态', '岗位管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (2, 'user_gender', '性别', '用户管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (3, 'user_status', '状态', '用户管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (4, 'role_data_scope', '数据范围', '角色管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (5, 'enable_disable', '状态', '功能状态：启用 | 禁用 ', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (6, 'success_fail', '状态', '操作状态：成功 | 失败', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (7, 'login_operation', '操作信息', '登录管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (8, 'params_type', '系统参数', '参数管理', 0, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, 1, '停用', '0', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (2, 1, '正常', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (3, 2, '男', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (4, 2, '女', '1', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (5, 2, '未知', '2', '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (6, 3, '正常', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (7, 3, '停用', '0', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (8, 4, '全部数据', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (9, 4, '本机构及子机构数据', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (10, 4, '本机构数据', '2', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (11, 4, '本人数据', '3', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (12, 4, '自定义数据', '4', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (13, 5, '禁用', '0', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (14, 5, '启用', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (15, 6, '失败', '0', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (16, 6, '成功', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (17, 7, '登录成功', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (18, 7, '退出成功', '1', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (19, 7, '验证码错误', '2', '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (20, 7, '账号密码错误', '3', '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (21, 8, '否', '0', '', 1, 0, 0, 10000,now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (22, 8, '是', '1', '', 0, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_params (param_name, param_type, param_key, param_value, remark, version, deleted, creator, create_time, updater, update_time) VALUES ('用户登录-验证码开关', 1, 'LOGIN_CAPTCHA', 'false', '是否开启验证码（true：开启，false：关闭）', 0, 0, 10000, now(), 10000, now());
