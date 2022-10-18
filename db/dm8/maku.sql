CREATE TABLE sys_user
(
    id          bigint IDENTITY NOT NULL,
    username    varchar(50) NOT NULL,
    password    varchar(100),
    real_name   varchar(50),
    avatar      varchar(200),
    gender      int,
    email       varchar(100),
    mobile      varchar(20),
    org_id      bigint,
    super_admin int,
    status      int,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_user IS '用户管理';
COMMENT ON COLUMN sys_user.id IS 'id';
COMMENT ON COLUMN sys_user.username IS '用户名';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.real_name IS '姓名';
COMMENT ON COLUMN sys_user.avatar IS '头像';
COMMENT ON COLUMN sys_user.gender IS '性别   0：男   1：女   2：未知';
COMMENT ON COLUMN sys_user.email IS '邮箱';
COMMENT ON COLUMN sys_user.mobile IS '手机号';
COMMENT ON COLUMN sys_user.org_id IS '机构ID';
COMMENT ON COLUMN sys_user.super_admin IS '超级管理员   0：否   1：是';
COMMENT ON COLUMN sys_user.status IS '状态  0：停用   1：正常';
COMMENT ON COLUMN sys_user.version IS '版本号';
COMMENT ON COLUMN sys_user.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_user.creator IS '创建者';
COMMENT ON COLUMN sys_user.create_time IS '创建时间';
COMMENT ON COLUMN sys_user.updater IS '更新者';
COMMENT ON COLUMN sys_user.update_time IS '更新时间';


CREATE TABLE sys_org
(
    id          bigint IDENTITY NOT NULL,
    pid         bigint,
    name        varchar(50),
    sort        int,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_org IS '机构管理';
COMMENT ON COLUMN sys_org.id IS 'id';
COMMENT ON COLUMN sys_org.pid IS '上级ID';
COMMENT ON COLUMN sys_org.name IS '机构名称';
COMMENT ON COLUMN sys_org.sort IS '排序';
COMMENT ON COLUMN sys_org.version IS '版本号';
COMMENT ON COLUMN sys_org.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_org.creator IS '创建者';
COMMENT ON COLUMN sys_org.create_time IS '创建时间';
COMMENT ON COLUMN sys_org.updater IS '更新者';
COMMENT ON COLUMN sys_org.update_time IS '更新时间';


create table sys_role
(
    id          bigint IDENTITY NOT NULL,
    name        varchar(50),
    remark      varchar(100),
    data_scope  int,
    org_id      bigint,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_role IS '角色管理';
COMMENT ON COLUMN sys_role.id IS 'id';
COMMENT ON COLUMN sys_role.name IS '角色名称';
COMMENT ON COLUMN sys_role.remark IS '备注';
COMMENT ON COLUMN sys_role.data_scope IS '数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据';
COMMENT ON COLUMN sys_role.org_id IS '机构ID';
COMMENT ON COLUMN sys_role.version IS '版本号';
COMMENT ON COLUMN sys_role.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_role.creator IS '创建者';
COMMENT ON COLUMN sys_role.create_time IS '创建时间';
COMMENT ON COLUMN sys_role.updater IS '更新者';
COMMENT ON COLUMN sys_role.update_time IS '更新时间';


create table sys_user_role
(
    id          bigint IDENTITY NOT NULL,
    role_id     bigint,
    user_id     bigint,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_user_role IS '用户角色关系';
COMMENT ON COLUMN sys_user_role.id IS 'id';
COMMENT ON COLUMN sys_user_role.role_id IS '角色ID';
COMMENT ON COLUMN sys_user_role.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_role.version IS '版本号';
COMMENT ON COLUMN sys_user_role.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_user_role.creator IS '创建者';
COMMENT ON COLUMN sys_user_role.create_time IS '创建时间';
COMMENT ON COLUMN sys_user_role.updater IS '更新者';
COMMENT ON COLUMN sys_user_role.update_time IS '更新时间';

CREATE TABLE sys_post
(
    id          bigint IDENTITY NOT NULL,
    post_code   varchar(100),
    post_name   varchar(100),
    sort        int,
    status      int,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_post IS '岗位管理';
COMMENT ON COLUMN sys_post.id IS 'id';
COMMENT ON COLUMN sys_post.post_code IS '岗位编码';
COMMENT ON COLUMN sys_post.post_name IS '岗位名称';
COMMENT ON COLUMN sys_post.sort IS '排序';
COMMENT ON COLUMN sys_post.status IS '状态  0：停用   1：正常';
COMMENT ON COLUMN sys_post.version IS '版本号';
COMMENT ON COLUMN sys_post.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_post.creator IS '创建者';
COMMENT ON COLUMN sys_post.create_time IS '创建时间';
COMMENT ON COLUMN sys_post.updater IS '更新者';
COMMENT ON COLUMN sys_post.update_time IS '更新时间';


CREATE TABLE sys_user_post
(
    id          bigint IDENTITY NOT NULL,
    user_id     bigint,
    post_id     bigint,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_user_post IS '用户岗位关系';
COMMENT ON COLUMN sys_user_post.id IS 'id';
COMMENT ON COLUMN sys_user_post.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_post.post_id IS '岗位ID';
COMMENT ON COLUMN sys_user_post.version IS '版本号';
COMMENT ON COLUMN sys_user_post.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_user_post.creator IS '创建者';
COMMENT ON COLUMN sys_user_post.create_time IS '创建时间';
COMMENT ON COLUMN sys_user_post.updater IS '更新者';
COMMENT ON COLUMN sys_user_post.update_time IS '更新时间';


create table sys_menu
(
    id          bigint IDENTITY NOT NULL,
    pid         bigint NOT NULL,
    name        varchar(200),
    url         varchar(200),
    authority   varchar(500),
    type        int,
    open_style  int,
    icon        varchar(50),
    sort        int,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_menu IS '菜单管理';
COMMENT ON COLUMN sys_menu.id IS 'id';
COMMENT ON COLUMN sys_menu.pid IS '上级ID，一级菜单为0';
COMMENT ON COLUMN sys_menu.name IS '菜单名称';
COMMENT ON COLUMN sys_menu.url IS '菜单URL';
COMMENT ON COLUMN sys_menu.authority IS '授权标识';
COMMENT ON COLUMN sys_menu.type IS '类型   0：菜单   1：按钮   2：接口';
COMMENT ON COLUMN sys_menu.open_style IS '打开方式   0：内部   1：外部';
COMMENT ON COLUMN sys_menu.icon IS '菜单图标';
COMMENT ON COLUMN sys_menu.sort IS '排序';
COMMENT ON COLUMN sys_menu.version IS '版本号';
COMMENT ON COLUMN sys_menu.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_menu.creator IS '创建者';
COMMENT ON COLUMN sys_menu.create_time IS '创建时间';
COMMENT ON COLUMN sys_menu.updater IS '更新者';
COMMENT ON COLUMN sys_menu.update_time IS '更新时间';


create table sys_role_menu
(
    id          bigint IDENTITY NOT NULL,
    role_id     bigint,
    menu_id     bigint,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_role_menu IS '角色菜单关系';
COMMENT ON COLUMN sys_role_menu.id IS 'id';
COMMENT ON COLUMN sys_role_menu.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_menu.menu_id IS '菜单ID';
COMMENT ON COLUMN sys_role_menu.version IS '版本号';
COMMENT ON COLUMN sys_role_menu.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_role_menu.creator IS '创建者';
COMMENT ON COLUMN sys_role_menu.create_time IS '创建时间';
COMMENT ON COLUMN sys_role_menu.updater IS '更新者';
COMMENT ON COLUMN sys_role_menu.update_time IS '更新时间';


create table sys_role_data_scope
(
    id          bigint IDENTITY NOT NULL,
    role_id     bigint,
    org_id      bigint,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

CREATE INDEX idx_role_id on sys_role_data_scope(role_id);

COMMENT ON TABLE sys_role_data_scope IS '角色数据权限';
COMMENT ON COLUMN sys_role_data_scope.id IS 'id';
COMMENT ON COLUMN sys_role_data_scope.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_data_scope.org_id IS '机构ID';
COMMENT ON COLUMN sys_role_data_scope.version IS '版本号';
COMMENT ON COLUMN sys_role_data_scope.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_role_data_scope.creator IS '创建者';
COMMENT ON COLUMN sys_role_data_scope.create_time IS '创建时间';
COMMENT ON COLUMN sys_role_data_scope.updater IS '更新者';
COMMENT ON COLUMN sys_role_data_scope.update_time IS '更新时间';

create table sys_dict_type
(
    id          bigint IDENTITY NOT NULL,
    dict_type   varchar(100),
    dict_name   varchar(255),
    remark      varchar(255),
    sort        int,
    version     int,
    deleted     int,
    creator     bigint,
    create_time datetime,
    updater     bigint,
    update_time datetime,
    primary key (id)
);

COMMENT ON TABLE sys_dict_type IS '字典类型';
COMMENT ON COLUMN sys_dict_type.id IS 'id';
COMMENT ON COLUMN sys_dict_type.dict_type IS '字典类型';
COMMENT ON COLUMN sys_dict_type.dict_name IS '字典名称';
COMMENT ON COLUMN sys_dict_type.remark IS '备注';
COMMENT ON COLUMN sys_dict_type.sort IS '排序';
COMMENT ON COLUMN sys_dict_type.version IS '版本号';
COMMENT ON COLUMN sys_dict_type.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_dict_type.creator IS '创建者';
COMMENT ON COLUMN sys_dict_type.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_type.updater IS '更新者';
COMMENT ON COLUMN sys_dict_type.update_time IS '更新时间';

create table sys_dict_data
(
    id           bigint IDENTITY NOT NULL,
    dict_type_id bigint,
    dict_label   varchar(255),
    dict_value   varchar(255),
    remark       varchar(255),
    sort         int,
    version      int,
    deleted      int,
    creator      bigint,
    create_time  datetime,
    updater      bigint,
    update_time  datetime,
    primary key (id)
);

COMMENT ON TABLE sys_dict_data IS '字典数据';
COMMENT ON COLUMN sys_dict_data.id IS 'id';
COMMENT ON COLUMN sys_dict_data.dict_type_id IS '字典类型ID';
COMMENT ON COLUMN sys_dict_data.dict_label IS '字典标签';
COMMENT ON COLUMN sys_dict_data.dict_value IS '字典值';
COMMENT ON COLUMN sys_dict_data.remark IS '备注';
COMMENT ON COLUMN sys_dict_data.sort IS '排序';
COMMENT ON COLUMN sys_dict_data.version IS '版本号';
COMMENT ON COLUMN sys_dict_data.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_dict_data.creator IS '创建者';
COMMENT ON COLUMN sys_dict_data.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_data.updater IS '更新者';
COMMENT ON COLUMN sys_dict_data.update_time IS '更新时间';

create table sys_attachment
(
    id           bigint IDENTITY NOT NULL,
    name         varchar(255) NOT NULL,
    url          varchar(255) NOT NULL,
    size         bigint,
    platform     varchar(50),
    version      int,
    deleted      int,
    creator      bigint,
    create_time  datetime,
    updater      bigint,
    update_time  datetime,
    primary key (id)
);

COMMENT ON TABLE sys_attachment IS '附件管理';
COMMENT ON COLUMN sys_attachment.id IS 'id';
COMMENT ON COLUMN sys_attachment.name IS '附件名称';
COMMENT ON COLUMN sys_attachment.url IS '附件地址';
COMMENT ON COLUMN sys_attachment.size IS '附件大小';
COMMENT ON COLUMN sys_attachment.platform IS '存储平台';
COMMENT ON COLUMN sys_attachment.version IS '版本号';
COMMENT ON COLUMN sys_attachment.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN sys_attachment.creator IS '创建者';
COMMENT ON COLUMN sys_attachment.create_time IS '创建时间';
COMMENT ON COLUMN sys_attachment.updater IS '更新者';
COMMENT ON COLUMN sys_attachment.update_time IS '更新时间';

create table sys_log_login
(
    id           bigint IDENTITY NOT NULL,
    username     varchar(50),
    ip           varchar(32),
    address      varchar(32),
    user_agent   varchar(500),
    status       int,
    operation    int,
    create_time  datetime,
    primary key (id)
);

COMMENT ON TABLE sys_log_login IS '登录日志';
COMMENT ON COLUMN sys_log_login.id IS 'id';
COMMENT ON COLUMN sys_log_login.username IS '用户名';
COMMENT ON COLUMN sys_log_login.ip IS '登录IP';
COMMENT ON COLUMN sys_log_login.address IS '登录地点';
COMMENT ON COLUMN sys_log_login.user_agent IS 'User Agent';
COMMENT ON COLUMN sys_log_login.status IS '登录状态  0：失败   1：成功';
COMMENT ON COLUMN sys_log_login.operation IS '操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误';
COMMENT ON COLUMN sys_log_login.create_time IS '创建时间';



SET IDENTITY_INSERT sys_user ON;
INSERT INTO sys_user (id, username, password, real_name, avatar, gender, email, mobile, status, org_id, super_admin, version, deleted, creator, create_time, updater, update_time) VALUES (10000, 'admin', '{bcrypt}$2a$10$mW/yJPHjyueQ1g26WNBz0uxVPa0GQdJO1fFZmqdkqgMTGnyszlXxu', 'admin', 'https://cdn.maku.net/images/avatar.png', 0, 'babamu@126.com', '13612345678', 1, null, 1, 0, 0, 10000, now(), 10000, now());

SET IDENTITY_INSERT sys_menu ON;
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

SET IDENTITY_INSERT sys_dict_type ON;
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, 'post_status', '状态', '岗位管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (2, 'user_gender', '性别', '用户管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (3, 'user_status', '状态', '用户管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (4, 'role_data_scope', '数据范围', '角色管理', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (5, 'enable_disable', '状态', '功能状态：启用 | 禁用 ', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (6, 'success_fail', '状态', '操作状态：成功 | 失败', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_type (id, dict_type, dict_name, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (7, 'login_operation', '操作信息', '登录管理', 0, 0, 0, 10000, now(), 10000, now());

SET IDENTITY_INSERT sys_dict_data ON;
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, 1, '停用', '0', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (2, 1, '正常', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (3, 2, '男', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (4, 2, '女', '1', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (5, 2, '未知', '2', '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (6, 3, '正常', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (7, 3, '停用', '0', '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (8, 4, '全部数据', '0', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (9, 4, '本部门及子部门数据', '1', '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (id, dict_type_id, dict_label, dict_value, remark, sort, version, deleted, creator, create_time, updater, update_time) VALUES (10, 4, '本部门数据', '2', '', 0, 0, 0, 10000, now(), 10000, now());
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

commit;