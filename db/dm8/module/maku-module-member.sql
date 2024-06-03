CREATE TABLE member_user
(
    id               bigint IDENTITY NOT NULL,
    nick_name        varchar(100) NOT NULL,
    mobile           varchar(20) NOT NULL,
    avatar           varchar(200),
    birthday         date,
    gender           int,
    openid           varchar(200),
    last_login_ip    varchar(100),
    last_login_time  datetime,
    remark           varchar(500),
    status           int,
    tenant_id        bigint,
    version          int,
    deleted          int,
    create_time      datetime,
    primary key (id)
);

COMMENT ON TABLE member_user IS '会员管理';
COMMENT ON COLUMN member_user.id IS 'id';
COMMENT ON COLUMN member_user.nick_name IS '昵称';
COMMENT ON COLUMN member_user.mobile IS '手机号';
COMMENT ON COLUMN member_user.avatar IS '头像';
COMMENT ON COLUMN member_user.birthday IS '出生日期';
COMMENT ON COLUMN member_user.gender IS '性别   0：男   1：女   2：未知';
COMMENT ON COLUMN member_user.openid IS '第三方平台，唯一标识';
COMMENT ON COLUMN member_user.last_login_ip IS '最后登录IP';
COMMENT ON COLUMN member_user.last_login_time IS '最后登录时间';
COMMENT ON COLUMN member_user.remark IS '备注';
COMMENT ON COLUMN member_user.status IS '状态  0：禁用   1：启用';
COMMENT ON COLUMN member_user.version IS '版本号';
COMMENT ON COLUMN member_user.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN member_user.create_time IS '创建时间';