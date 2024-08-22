-- 表结构
CREATE TABLE iot_device (
  id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  code varchar(255) NOT NULL COMMENT '编码',
  name varchar(255) NOT NULL COMMENT '名称',
  type int NOT NULL COMMENT '设备类型，1.手持设备，2.柜体，3传感设备',
  uid varchar(255) NOT NULL COMMENT '唯一标识码',
  secret varchar(255)  DEFAULT NULL COMMENT '设备密钥',
  app_version varchar(255) DEFAULT NULL COMMENT 'App版本号',
  battery_percent varchar(10)  DEFAULT NULL COMMENT '电池电量百分比',
  temperature varchar(10)  DEFAULT NULL COMMENT '温度',
  status tinyint NOT NULL DEFAULT '1' COMMENT '状态，0禁用，1启用',
  running_status int NOT NULL DEFAULT '0' COMMENT '运行状态，0.离线状态 1.在线状态 2.正常待机 3.用户使用中 4.OTA升级中',
  protocol_type varchar(20)  NOT NULL DEFAULT 'MQTT' COMMENT '协议类型',
  up_time datetime DEFAULT NULL COMMENT '上线时间',
  down_time datetime DEFAULT NULL COMMENT '下线时间',
  tenant_id bigint DEFAULT NULL COMMENT '租户ID',
  creator bigint DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  updater bigint DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  version int DEFAULT NULL COMMENT '版本号',
  deleted tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT='设备表';

CREATE TABLE iot_device_event_log (
  id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  device_id bigint NOT NULL COMMENT '设备id',
  event_type tinyint NOT NULL COMMENT '事件类型',
  event_uid varchar(50) DEFAULT NULL COMMENT '事件标识id',
  event_payload varchar(1000) DEFAULT NULL COMMENT '事件数据',
  event_time datetime DEFAULT NULL COMMENT '事件时间',
  tenant_id bigint DEFAULT NULL COMMENT '租户ID',
  version int DEFAULT NULL COMMENT '版本号',
  deleted tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  creator bigint DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  updater bigint DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT='设备事件日志';

CREATE TABLE iot_device_service_log (
  id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  device_id bigint NOT NULL COMMENT '设备id',
  service_type tinyint NOT NULL COMMENT '服务类型',
  service_uid varchar(50) DEFAULT NULL COMMENT '服务标识id',
  service_payload varchar(1000) DEFAULT NULL COMMENT '服务数据',
  service_time datetime DEFAULT NULL COMMENT '服务时间',
  tenant_id bigint DEFAULT NULL COMMENT '租户ID',
  version int DEFAULT NULL COMMENT '版本号',
  deleted tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  creator bigint DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  updater bigint DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT='设备服务日志';


INSERT INTO iot_device (id, code, name, type, uid, secret, app_version, battery_percent, temperature, status, running_status, protocol_type, up_time, down_time, tenant_id, creator, create_time, updater, update_time, version, deleted) VALUES (1, 'test-tcp', 'testTCP', 1, 'test12345678', '123456', NULL, NULL, NULL, 1, 1, 'TCP', now(), NULL, NULL, 10000, now(), 10000, now(), 0, 0);

-- 菜单，权限
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ( NULL, '物联网平台', NULL, NULL, 0, 0, 'icon-printer-fill', 6, 0, 0, 10000,now(), 10000, now());

SET @pid = @@identity;
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @pid) , '设备列表', 'iot/device/index', NULL, 0, 0, 'icon-menu', 0, 0, 0, 10000, now(), 10000, now());

SET @menuId = @@identity;
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '查看', '', 'iot:device:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '新增', '', 'iot:device:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '修改', '', 'iot:device:update,iot:device:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '删除', '', 'iot:device:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '下发指令', '', 'iot:device:send', 1, 0, '', 4, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '上报数据', '', 'iot:device:report', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '设备事件日志', '', 'iot:device_event_log:page', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (@menuId, '设备服务日志', '', 'iot:device_service_log:page', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());

-- 字典数据
INSERT INTO sys_dict_type (dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUE('device_type', '设备类型', '设备类型', 0, 10000, 0, 0, 10000, now(), 10000, now() );
SET @typeId = @@identity;
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '手持设备', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '柜体', '2', 'primary', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '传感设备', '3', 'primary', '', 2, 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES('device_running_status', '设备运行状态', '设备运行状态：离线|在线|待机|使用中|OTA升级中', 0, 10000, 0, 0, 10000, now(), 10000, now() );
SET @typeId = @@identity;
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '离线状态', '0', 'danger', NULL, 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '在线状态', '1', 'success', NULL, 1, 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES('device_command', '设备指令', '设备服务具备的功能', 0, 10000, 0, 0, 10000, now(), 10000, now() );
SET @typeId = @@identity;
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '远程锁定', 'LOCK', NULL, NULL, 0, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '远程解锁', 'UNLOCK', NULL, NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '登录', 'SIGN_ON', NULL, NULL, 2, NULL, 0, 1, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '登出', 'SIGN_OFF', NULL, NULL, 3, NULL, 0, 1, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'OTA升级', 'OTA_UPGRADE', NULL, NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES('device_protocol_type', '设备协议类型', '设备协议类型', 0, 10000, 0, 0, 10000, now(), 10000, now() );
SET @typeId = @@identity;
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'MQTT', 'MQTT', NULL, NULL, 0, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'TCP', 'TCP', NULL, NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'UDP', 'UDP', NULL, NULL, 2, NULL, 0, 1, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'BLE', 'BLE', NULL, NULL, 3, NULL, 0, 1, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'CoAP', 'CoAP', NULL, NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'LwM2M', 'LwM2M', NULL, NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'Modbus', 'Modbus', NULL, NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());


INSERT INTO sys_dict_type (dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES('device_property', '设备属性', '设备通用属性：运行状态|APP版本|电池电量百分比|温度', 0, 10000, 0, 0, 10000, now(), 10000, now() );
SET @typeId = @@identity;
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '运行状态', 'RUNNING_STATUS', NULL, NULL, 0, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'APP版本', 'APP_VERSION', NULL, NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '电池电量百分比', 'BATTERY_PERCENT', NULL, NULL, 2, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '温度', 'TEMPERATURE', NULL, NULL, 3, NULL, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES('device_event_type', '事件类型', '事件日志类型', 0, 10000, 0, 0, 10000, now(), 10000, now() );
SET @typeId = @@identity;
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '下线', 'OFFLINE', 'danger', NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '上线', 'ONLINE', 'primary', NULL, 2, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '登录', 'SIGN_ON', 'primary', NULL, 3, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '退出登录', 'SIGN_OFF', 'danger', NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'OTA升级', 'OTA_UPGRADE', 'primary', NULL, 5, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '设备远程锁定', 'LOCK', 'primary', NULL, 6, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '设备远程解锁', 'UNLOCK', 'primary', NULL,7, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), 'APP版本信息', 'APP_VERSION_REPORT', 'primary', NULL, 8, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '电池电量', 'BATTERY_PERCENT_REPORT', 'primary', NULL, 9, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data  (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @typeId), '温度', 'TEMPERATURE_REPORT', 'primary', NULL, 0, NULL, 10, 0, 10000, now(), 10000, now());
