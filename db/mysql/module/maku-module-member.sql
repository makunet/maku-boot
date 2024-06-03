CREATE TABLE member_user
(
    id               bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    nick_name        varchar(100) NOT NULL COMMENT '昵称',
    mobile           varchar(20) NOT NULL COMMENT '手机号',
    avatar           varchar(200) COMMENT '头像',
    birthday         date COMMENT '出生日期',
    gender           tinyint COMMENT '性别   0：男   1：女   2：未知',
    openid           varchar(200) COMMENT '第三方平台，唯一标识',
    last_login_ip    varchar(100) COMMENT '最后登录IP',
    last_login_time  datetime COMMENT '最后登录时间',
    tenant_id        bigint COMMENT '租户ID',
    remark           varchar(500) COMMENT '备注',
    status           tinyint COMMENT '状态  0：禁用   1：启用',
    version          int COMMENT '版本号',
    deleted          tinyint COMMENT '删除标识  0：正常   1：已删除',
    create_time      datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='会员管理';