INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (33, '代码生成器', '{{apiUrl}}/maku-generator/index.html', '', 0, 0, 'icon-rocket', 2, 0, 0, 10000, now(), 10000, now());

CREATE TABLE gen_datasource
(
    id          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    db_type     varchar(200) COMMENT '数据库类型',
    conn_name   varchar(200) NOT NULL COMMENT '连接名',
    conn_url    varchar(500) COMMENT 'URL',
    username    varchar(200) COMMENT '用户名',
    password    varchar(200) COMMENT '密码',
    create_time datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='数据源管理';

CREATE TABLE gen_field_type
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    column_type  varchar(200) COMMENT '字段类型',
    attr_type    varchar(200) COMMENT '属性类型',
    package_name varchar(200) COMMENT '属性包名',
    create_time  datetime COMMENT '创建时间',
    primary key (id),
    unique key (column_type)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='字段类型管理';

CREATE TABLE gen_base_class
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    package_name varchar(200) COMMENT '基类包名',
    code         varchar(200) COMMENT '基类编码',
    fields       varchar(500) COMMENT '基类字段，多个用英文逗号分隔',
    remark       varchar(200) COMMENT '备注',
    create_time  datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='基类管理';

CREATE TABLE gen_table
(
    id             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    table_name     varchar(200) COMMENT '表名',
    class_name     varchar(200) COMMENT '类名',
    table_comment  varchar(200) COMMENT '说明',
    author         varchar(200) COMMENT '作者',
    email          varchar(200) COMMENT '邮箱',
    package_name   varchar(200) COMMENT '项目包名',
    version        varchar(200) COMMENT '项目版本号',
    generator_type tinyint COMMENT '生成方式  0：zip压缩包   1：自定义目录',
    backend_path   varchar(500) COMMENT '后端生成路径',
    frontend_path  varchar(500) COMMENT '前端生成路径',
    module_name    varchar(200) COMMENT '模块名',
    function_name  varchar(200) COMMENT '功能名',
    form_layout    tinyint COMMENT '表单布局  1：一列   2：两列',
    table_type       tinyint COMMENT '表类型',
    sub_table        varchar(4000) COMMENT '子表数据',
    table_operation  varchar(200) COMMENT '生成功能',
    auth_level       tinyint COMMENT '权限级别  0:页面层级  1:按钮层级',
    open_type        tinyint COMMENT '新增编辑  0:弹窗  1:右侧栏',
    request_url      varchar(200) COMMENT '请求URL',
    authority        varchar(200) COMMENT '权限标识',
    tree_id          varchar(200) COMMENT '树形ID',
    tree_pid         varchar(200) COMMENT '树形父ID',
    tree_label       varchar(200) COMMENT '树展示列',
    left_title       varchar(200) COMMENT '左侧标题',
    left_from        tinyint COMMENT '数据来源',
    left_table_name     varchar(200) COMMENT '左侧树表名',
    left_url            varchar(200) COMMENT '接口地址',
    left_relation_field varchar(200) COMMENT '关联字段',
    datasource_id  bigint COMMENT '数据源ID',
    baseclass_id   bigint COMMENT '基类ID',
    create_time    datetime COMMENT '创建时间',
    primary key (id),
    unique key (table_name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='代码生成表';

CREATE TABLE gen_table_field
(
    id              bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    table_id        bigint COMMENT '表ID',
    field_name      varchar(200) COMMENT '字段名称',
    field_type      varchar(200) COMMENT '字段类型',
    field_comment   varchar(200) COMMENT '字段说明',
    attr_name       varchar(200) COMMENT '属性名',
    attr_type       varchar(200) COMMENT '属性类型',
    package_name    varchar(200) COMMENT '属性包名',
    sort            int COMMENT '排序',
    auto_fill       varchar(20) COMMENT '自动填充  DEFAULT、INSERT、UPDATE、INSERT_UPDATE',
    primary_pk      tinyint COMMENT '主键 0：否  1：是',
    base_field      tinyint COMMENT '基类字段 0：否  1：是',
    form_item       tinyint COMMENT '表单项 0：否  1：是',
    form_required   tinyint COMMENT '表单必填 0：否  1：是',
    form_type       varchar(200) COMMENT '表单类型',
    form_dict       varchar(200) COMMENT '表单字典类型',
    form_validator  varchar(200) COMMENT '表单效验',
    grid_item       tinyint COMMENT '列表项 0：否  1：是',
    grid_sort       tinyint COMMENT '列表排序 0：否  1：是',
    query_item      tinyint COMMENT '查询项 0：否  1：是',
    query_type      varchar(200) COMMENT '查询方式',
    query_form_type varchar(200) COMMENT '查询表单类型',
    create_time     datetime COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='代码生成表字段';

CREATE TABLE gen_project_modify
(
    id                     bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    project_name           varchar(100) COMMENT '项目名',
    project_code           varchar(100) COMMENT '项目标识',
    project_package        varchar(100) COMMENT '项目包名',
    project_path           varchar(200) COMMENT '项目路径',
    modify_project_name    varchar(100) COMMENT '变更项目名',
    modify_project_code    varchar(100) COMMENT '变更标识',
    modify_project_package varchar(100) COMMENT '变更包名',
    exclusions             varchar(200) COMMENT '排除文件',
    modify_suffix          varchar(200) COMMENT '变更文件',
    modify_tmp_path        varchar(100) COMMENT '变更临时路径',
    create_time            datetime COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='项目名变更';


-- 用于测试代码生成器的表结构 --
CREATE TABLE gen_test_member
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    name        varchar(50) COMMENT '姓名',
    gender      tinyint COMMENT '性别',
    age         int COMMENT '年龄',
    tenant_id   bigint COMMENT '租户ID',
    create_time datetime COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='单表测试';

CREATE TABLE gen_test_tree
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    parent_id   bigint COMMENT '上级ID',
    tree_name   varchar(100) COMMENT '名称',
    tenant_id   bigint COMMENT '租户ID',
    create_time datetime COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='树表测试';

CREATE TABLE gen_test_product
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    name        varchar(100) COMMENT '名称',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='产品测试';

CREATE TABLE gen_test_product_info
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    images      varchar(2000) COMMENT '图片',
    intro       varchar(5000) COMMENT '介绍',
    product_id  bigint COMMENT '产品ID',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='产品信息';

CREATE TABLE gen_test_product_param
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    param_name  varchar(200) COMMENT '参数名称',
    param_value varchar(200) COMMENT '参数值',
    product_id  bigint COMMENT '产品ID',
    tenant_id   bigint COMMENT '租户ID',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='产品参数';

CREATE TABLE gen_test_goods_category
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    name        varchar(100) COMMENT '名称',
    pid         bigint COMMENT '上级ID',
    tenant_id   bigint COMMENT '租户ID',
    deleted     tinyint COMMENT '删除标识',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='商品分类';

CREATE TABLE gen_test_goods
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    name        varchar(100) COMMENT '名称',
    intro       varchar(5000) COMMENT '介绍',
    category_id bigint COMMENT '分类ID',
    tenant_id   bigint COMMENT '租户ID',
    deleted     tinyint COMMENT '删除标识',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='商品管理';


INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('datetime', 'LocalDateTime', 'java.time.LocalDateTime', now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('date', 'LocalDateTime', 'java.time.LocalDateTime', now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('tinyint', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('smallint', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('mediumint', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('int', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('integer', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('bigint', 'Long', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('float', 'Float', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('double', 'Double', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('decimal', 'BigDecimal', 'java.math.BigDecimal', now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('bit', 'Boolean', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('char', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('varchar', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('tinytext', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('text', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('mediumtext', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('longtext', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('timestamp', 'LocalDateTime', 'java.time.LocalDateTime', now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('NUMBER', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('BINARY_INTEGER', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('BINARY_FLOAT', 'Float', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('BINARY_DOUBLE', 'Double', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('VARCHAR2', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('NVARCHAR', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('NVARCHAR2', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('CLOB', 'String', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('int8', 'Long', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('int4', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('int2', 'Integer', NULL, now());
INSERT INTO gen_field_type (column_type, attr_type, package_name, create_time) VALUES ('numeric', 'BigDecimal', 'java.math.BigDecimal', now());

INSERT INTO gen_base_class (package_name, code, fields, remark, create_time) VALUES ('net.maku.framework.mybatis.entity', 'BaseEntity', 'id,creator,create_time,updater,update_time,version,deleted', '使用该基类，则需要表里有这些字段', now());

INSERT INTO gen_project_modify (project_name, project_code, project_package, project_path, modify_project_name, modify_project_code, modify_project_package, exclusions, modify_suffix, create_time) VALUES ('maku-boot', 'maku', 'net.maku', 'D:/makunet/maku-boot', 'baba-boot', 'baba', 'com.baba', '.git,.idea,target,logs', 'java,xml,yml,txt', now());
INSERT INTO gen_project_modify (project_name, project_code, project_package, project_path, modify_project_name, modify_project_code, modify_project_package, exclusions, modify_suffix, create_time) VALUES ('maku-cloud', 'maku', 'net.maku', 'D:/makunet/maku-cloud', 'baba-cloud', 'baba', 'com.baba', '.git,.idea,target,logs', 'java,xml,yml,txt', now());


INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (20, 'gen_test_member', 'GenTestMember', '单表测试', '阿沐', 'babamu@126.com', 'net.maku', '', 1, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'member', 1, 0, NULL, '[\"query\",\"insert\",\"update\",\"delete\",\"export\",\"import\"]', 0, 0, '/test/member', 'test:member', NULL, NULL, NULL, 0, NULL, now(), NULL, NULL, NULL, NULL, NULL);
INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (21, 'gen_test_tree', 'GenTestTree', '树表测试', '阿沐', 'babamu@126.com', 'net.maku', '', 1, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'tree', 1, 1, NULL, '[\"query\",\"insert\",\"update\",\"delete\",\"export\"]', 0, 0, '/test/tree', 'test:tree', 'id', 'parent_id', 'tree_name', 0, NULL, now(), NULL, NULL, NULL, NULL, NULL);
INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (22, 'gen_test_goods', 'GenTestGoods', '商品管理', '阿沐', 'babamu@126.com', 'net.maku', '', 1, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'goods', 1, 2, '[]', '[\"query\",\"insert\",\"update\",\"delete\"]', 0, 0, '/test/goods', 'test:goods', NULL, NULL, NULL, 0, NULL, now(), 0, 'gen_test_goods_category', '/test/category/list', 'category_id', '分类列表');
INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (23, 'gen_test_goods_category', 'GenTestGoodsCategory', '商品分类', '阿沐', 'babamu@126.com', 'net.maku', '', 1, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'category', 1, 1, '[]', '[\"query\",\"insert\",\"update\",\"delete\"]', 0, 0, '/test/category', 'test:category', 'id', 'pid', 'name', 0, NULL, now(), NULL, NULL, NULL, NULL, NULL);
INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (24, 'gen_test_product_info', 'GenTestProductInfo', '产品信息', '阿沐', 'babamu@126.com', 'net.maku', '', 0, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'info', 1, 0, NULL, '[\"query\",\"insert\",\"update\",\"delete\",\"export\"]', 0, 0, '/test/info', 'test:info', NULL, NULL, NULL, 0, NULL, now(), NULL, NULL, NULL, NULL, NULL);
INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (25, 'gen_test_product_param', 'GenTestProductParam', '产品参数', '阿沐', 'babamu@126.com', 'net.maku', '', 0, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'param', 1, 0, NULL, '[\"query\",\"insert\",\"update\",\"delete\",\"export\"]', 0, 0, '/test/param', 'test:param', NULL, NULL, NULL, 0, NULL, now(), NULL, NULL, NULL, NULL, NULL);
INSERT INTO gen_table (id, table_name, class_name, table_comment, author, email, package_name, version, generator_type, backend_path, frontend_path, module_name, function_name, form_layout, table_type, sub_table, table_operation, auth_level, open_type, request_url, authority, tree_id, tree_pid, tree_label, datasource_id, baseclass_id, create_time, left_from, left_table_name, left_url, left_relation_field, left_title) VALUES (26, 'gen_test_product', 'GenTestProduct', '产品测试', '阿沐', 'babamu@126.com', 'net.maku', '', 1, '/Users/maku/makunet/maku-boot/maku-boot-new', '/Users/maku/makunet/maku-admin', 'test', 'product', 1, 0, '[{\"tableName\":\"gen_test_product_info\",\"foreignKey\":\"product_id\",\"tableTitle\":\"产品信息\",\"mainRelation\":1,\"sort\":0},{\"tableName\":\"gen_test_product_param\",\"foreignKey\":\"product_id\",\"tableTitle\":\"产品参数\",\"mainRelation\":2,\"sort\":1}]', '[\"query\",\"insert\",\"update\",\"delete\"]', 0, 1, '/test/product', 'test:product', NULL, NULL, NULL, 0, NULL, now(), NULL, NULL, NULL, NULL, NULL);

INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (220, 20, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (221, 20, 'name', 'varchar', '姓名', 'name', 'String', NULL, 1, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (222, 20, 'gender', 'tinyint', '性别', 'gender', 'Integer', NULL, 2, 'DEFAULT', 0, 0, 1, 1, 'radio', 'user_gender', NULL, 1, 0, 1, '=', 'select', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (223, 20, 'age', 'int', '年龄', 'age', 'Integer', NULL, 3, 'DEFAULT', 0, 0, 1, 1, 'number', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (224, 20, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 4, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (225, 20, 'version', 'int', '版本号', 'version', 'Integer', NULL, 5, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (226, 20, 'deleted', 'tinyint', '删除标识', 'deleted', 'Integer', NULL, 6, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (227, 20, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 7, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (228, 21, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (229, 21, 'tree_name', 'varchar', '名称', 'treeName', 'String', NULL, 1, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 1, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (230, 21, 'parent_id', 'bigint', '上级ID', 'parentId', 'Long', NULL, 2, 'DEFAULT', 0, 0, 1, 0, 'treeselect', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (231, 21, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 3, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (232, 21, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 4, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 1, 0, 1, '=', 'date', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (233, 22, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (234, 22, 'name', 'varchar', '名称', 'name', 'String', NULL, 1, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 1, 'like', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (235, 22, 'intro', 'varchar', '介绍', 'intro', 'String', NULL, 2, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (236, 22, 'category_id', 'bigint', '分类ID', 'categoryId', 'Long', NULL, 3, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (237, 22, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 4, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (238, 22, 'deleted', 'tinyint', '删除标识', 'deleted', 'Integer', NULL, 5, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (239, 22, 'creator', 'bigint', '创建者', 'creator', 'Long', NULL, 6, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (240, 22, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 7, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (241, 22, 'updater', 'bigint', '更新者', 'updater', 'Long', NULL, 8, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (242, 22, 'update_time', 'datetime', '更新时间', 'updateTime', 'LocalDateTime', 'java.time.LocalDateTime', 9, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (243, 23, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (244, 23, 'name', 'varchar', '名称', 'name', 'String', NULL, 2, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 1, 'like', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (245, 23, 'pid', 'bigint', '上级ID', 'pid', 'Long', NULL, 1, 'DEFAULT', 0, 0, 1, 0, 'treeselect', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (246, 23, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 3, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (247, 23, 'deleted', 'tinyint', '删除标识', 'deleted', 'Integer', NULL, 4, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (248, 23, 'creator', 'bigint', '创建者', 'creator', 'Long', NULL, 5, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (249, 23, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 6, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 1, 0, 1, '=', 'datetime', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (250, 23, 'updater', 'bigint', '更新者', 'updater', 'Long', NULL, 7, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (251, 23, 'update_time', 'datetime', '更新时间', 'updateTime', 'LocalDateTime', 'java.time.LocalDateTime', 8, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (252, 24, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (253, 24, 'images', 'varchar', '图片', 'images', 'String', NULL, 1, 'DEFAULT', 0, 0, 1, 1, 'image', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (254, 24, 'intro', 'varchar', '介绍', 'intro', 'String', NULL, 2, 'DEFAULT', 0, 0, 1, 1, 'editor', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (255, 24, 'product_id', 'bigint', '产品ID', 'productId', 'Long', NULL, 3, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (256, 24, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 4, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (257, 24, 'version', 'int', '版本号', 'version', 'Integer', NULL, 5, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (258, 24, 'deleted', 'tinyint', '删除标识', 'deleted', 'Integer', NULL, 6, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (259, 24, 'creator', 'bigint', '创建者', 'creator', 'Long', NULL, 7, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (260, 24, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 8, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (261, 24, 'updater', 'bigint', '更新者', 'updater', 'Long', NULL, 9, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (262, 24, 'update_time', 'datetime', '更新时间', 'updateTime', 'LocalDateTime', 'java.time.LocalDateTime', 10, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (263, 25, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (264, 25, 'param_name', 'varchar', '参数名称', 'paramName', 'String', NULL, 1, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (265, 25, 'param_value', 'varchar', '参数值', 'paramValue', 'String', NULL, 2, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (266, 25, 'product_id', 'bigint', '产品ID', 'productId', 'Long', NULL, 3, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (267, 25, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 4, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (268, 25, 'version', 'int', '版本号', 'version', 'Integer', NULL, 5, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (269, 25, 'deleted', 'tinyint', '删除标识', 'deleted', 'Integer', NULL, 6, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (270, 25, 'creator', 'bigint', '创建者', 'creator', 'Long', NULL, 7, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (271, 25, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 8, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 1, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (272, 25, 'updater', 'bigint', '更新者', 'updater', 'Long', NULL, 9, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (273, 25, 'update_time', 'datetime', '更新时间', 'updateTime', 'LocalDateTime', 'java.time.LocalDateTime', 10, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (274, 26, 'id', 'bigint', 'ID', 'id', 'Long', NULL, 0, 'DEFAULT', 1, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (275, 26, 'name', 'varchar', '名称', 'name', 'String', NULL, 1, 'DEFAULT', 0, 0, 1, 1, 'input', NULL, NULL, 1, 0, 1, 'like', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (276, 26, 'tenant_id', 'bigint', '租户ID', 'tenantId', 'Long', NULL, 2, 'DEFAULT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (277, 26, 'version', 'int', '版本号', 'version', 'Integer', NULL, 3, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (278, 26, 'deleted', 'tinyint', '删除标识', 'deleted', 'Integer', NULL, 4, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (279, 26, 'creator', 'bigint', '创建者', 'creator', 'Long', NULL, 5, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (280, 26, 'create_time', 'datetime', '创建时间', 'createTime', 'LocalDateTime', 'java.time.LocalDateTime', 6, 'INSERT', 0, 0, 0, 0, 'input', NULL, NULL, 1, 0, 1, '=', 'datetime', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (281, 26, 'updater', 'bigint', '更新者', 'updater', 'Long', NULL, 7, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
INSERT INTO gen_table_field (id, table_id, field_name, field_type, field_comment, attr_name, attr_type, package_name, sort, auto_fill, primary_pk, base_field, form_item, form_required, form_type, form_dict, form_validator, grid_item, grid_sort, query_item, query_type, query_form_type, create_time) VALUES (282, 26, 'update_time', 'datetime', '更新时间', 'updateTime', 'LocalDateTime', 'java.time.LocalDateTime', 8, 'INSERT_UPDATE', 0, 0, 0, 0, 'input', NULL, NULL, 0, 0, 0, '=', 'input', now());
