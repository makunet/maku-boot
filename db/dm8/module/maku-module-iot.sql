CREATE TABLE iot_device (
  id bigint IDENTITY NOT NULL,
  code varchar(255),
  name varchar(255),
  type int,
  uid varchar(255),
  secret varchar(255),
  app_version varchar(255),
  battery_percent varchar(10),
  temperature varchar(10),
  status int,
  running_status int DEFAULT 0,
  protocol_type varchar(20),
  up_time datetime,
  down_time datetime,
  tenant_id bigint,
  version int,
  deleted int,
  creator bigint,
  create_time datetime,
  updater bigint,
  update_time datetime,
  PRIMARY KEY (id)
);

COMMENT ON TABLE iot_device IS '设备表';
COMMENT ON COLUMN iot_device.id IS 'id';
COMMENT ON COLUMN iot_device.code IS '编码';
COMMENT ON COLUMN iot_device.name IS '名称';
COMMENT ON COLUMN iot_device.type IS '设备类型，1.手持设备，2.柜体，3传感设备';
COMMENT ON COLUMN iot_device.uid IS '唯一标识码';
COMMENT ON COLUMN iot_device.secret IS '设备密钥';
COMMENT ON COLUMN iot_device.app_version IS 'App版本号';
COMMENT ON COLUMN iot_device.battery_percent IS '电池电量百分比';
COMMENT ON COLUMN iot_device.status IS '状态，0禁用，1启用';
COMMENT ON COLUMN iot_device.running_status IS '运行状态，0.离线状态 1.在线状态 2.正常待机 3.用户使用中 4.OTA升级中';
COMMENT ON COLUMN iot_device.protocol_type IS '协议类型';
COMMENT ON COLUMN iot_device.up_time IS '上线时间';
COMMENT ON COLUMN iot_device.down_time IS '下线时间';
COMMENT ON COLUMN iot_device.tenant_id IS '租户ID';
COMMENT ON COLUMN iot_device.version IS '版本号';
COMMENT ON COLUMN iot_device.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN iot_device.creator IS '创建者';
COMMENT ON COLUMN iot_device.create_time IS '创建时间';
COMMENT ON COLUMN iot_device.updater IS '更新者';
COMMENT ON COLUMN iot_device.update_time IS '更新时间';


CREATE TABLE iot_device_event_log (
  id bigint IDENTITY NOT NULL,
  device_id bigint,
  event_type int,
  event_uid varchar(50),
  event_payload varchar(1000),
  event_time datetime,
  tenant_id bigint,
  version int,
  deleted int,
  creator bigint,
  create_time datetime,
  updater bigint,
  update_time datetime,
  PRIMARY KEY (id)
);

COMMENT ON TABLE iot_device_event_log IS '设备事件日志';
COMMENT ON COLUMN iot_device_event_log.id IS 'id';
COMMENT ON COLUMN iot_device_event_log.device_id IS '设备id';
COMMENT ON COLUMN iot_device_event_log.event_type IS '事件类型';
COMMENT ON COLUMN iot_device_event_log.event_uid IS '事件标识id';
COMMENT ON COLUMN iot_device_event_log.event_payload IS '事件数据';
COMMENT ON COLUMN iot_device_event_log.event_time IS '事件时间';
COMMENT ON COLUMN iot_device_event_log.tenant_id IS '租户ID';
COMMENT ON COLUMN iot_device_event_log.version IS '版本号';
COMMENT ON COLUMN iot_device_event_log.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN iot_device_event_log.creator IS '创建者';
COMMENT ON COLUMN iot_device_event_log.create_time IS '创建时间';
COMMENT ON COLUMN iot_device_event_log.updater IS '更新者';
COMMENT ON COLUMN iot_device_event_log.update_time IS '更新时间';


CREATE TABLE iot_device_service_log (
  id bigint IDENTITY NOT NULL,
  device_id bigint,
  service_type int,
  service_uid varchar(50),
  service_payload varchar(1000),
  service_time datetime,
  tenant_id bigint,
  version int,
  deleted int,
  creator bigint,
  create_time datetime,
  updater bigint,
  update_time datetime,
  PRIMARY KEY (id)
);

COMMENT ON TABLE iot_device_service_log IS '设备服务日志';
COMMENT ON COLUMN iot_device_service_log.id IS 'id';
COMMENT ON COLUMN iot_device_service_log.device_id IS '设备id';
COMMENT ON COLUMN iot_device_service_log.service_type IS '服务类型';
COMMENT ON COLUMN iot_device_service_log.service_uid IS '服务标识id';
COMMENT ON COLUMN iot_device_service_log.service_payload IS '服务数据';
COMMENT ON COLUMN iot_device_service_log.service_time IS '服务时间';
COMMENT ON COLUMN iot_device_service_log.tenant_id IS '租户ID';
COMMENT ON COLUMN iot_device_service_log.version IS '版本号';
COMMENT ON COLUMN iot_device_service_log.deleted IS '删除标识  0：正常   1：已删除';
COMMENT ON COLUMN iot_device_service_log.creator IS '创建者';
COMMENT ON COLUMN iot_device_service_log.create_time IS '创建时间';
COMMENT ON COLUMN iot_device_service_log.updater IS '更新者';

SET IDENTITY_INSERT iot_device ON;
INSERT INTO iot_device (id, code, name, type, uid, secret, app_version, battery_percent, temperature, status, running_status, protocol_type, up_time, down_time, tenant_id, creator, create_time, updater, update_time, version, deleted) VALUES (1, 'test-tcp', 'testTCP', 1, 'test12345678', '123456', NULL, NULL, NULL, 1, 1, 'TCP', now(), NULL, NULL, 10000, now(), 10000, now(), 0, 0);

SET IDENTITY_INSERT sys_menu ON;
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1601, NULL, '物联网平台', NULL, NULL, 0, 0, 'icon-printer-fill', 6, 0, 0, 10000,now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1602, 1601, '设备列表', 'iot/device/index', NULL, 0, 0, 'icon-menu', 0, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1603, 1602, '查看', '', 'iot:device:page', 1, 0, '', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1604, 1602, '新增', '', 'iot:device:save', 1, 0, '', 1, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1605, 1602, '修改', '', 'iot:device:update,iot:device:info', 1, 0, '', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1606, 1602, '删除', '', 'iot:device:delete', 1, 0, '', 3, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1607, 1602, '下发指令', '', 'iot:device:send', 1, 0, '', 4, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1608, 1602, '上报数据', '', 'iot:device:report', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1609, 1602, '设备事件日志', '', 'iot:device_event_log:page', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1610, 1602, '设备服务日志', '', 'iot:device_service_log:page', 1, 0, '', 5, 0, 0, 10000, now(), 10000, now());

SET IDENTITY_INSERT sys_dict_type ON;
INSERT INTO sys_dict_type (id, dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUE(1601, 'device_type', '设备类型', '设备类型', 0, 10000, 0, 0, 10000, now(), 10000, now() );
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1601, '手持设备', '1', 'primary', '', 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1601, '柜体', '2', 'primary', '', 1, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1601, '传感设备', '3', 'primary', '', 2, 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES(1602, 'device_running_status', '设备运行状态', '设备运行状态：离线|在线|待机|使用中|OTA升级中', 0, 10000, 0, 0, 10000, now(), 10000, now() );
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1602, '离线状态', '0', 'danger', NULL, 0, 10000, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1602, '在线状态', '1', 'success', NULL, 1, 10000, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES(1603, 'device_command', '设备指令', '设备服务具备的功能', 0, 10000, 0, 0, 10000, now(), 10000, now() );
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1603, '远程锁定', 'LOCK', NULL, NULL, 0, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1603, '远程解锁', 'UNLOCK', NULL, NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1603, '登录', 'SIGN_ON', NULL, NULL, 2, NULL, 0, 1, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1603, '登出', 'SIGN_OFF', NULL, NULL, 3, NULL, 0, 1, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1603, 'OTA升级', 'OTA_UPGRADE', NULL, NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES(1604, 'device_property', '设备属性', '设备通用属性：运行状态|APP版本|电池电量百分比|温度', 0, 10000, 0, 0, 10000, now(), 10000, now() );
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1604, '运行状态', 'RUNNING_STATUS', NULL, NULL, 0, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1604, 'APP版本', 'APP_VERSION', NULL, NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1604, '电池电量百分比', 'BATTERY_PERCENT', NULL, NULL, 2, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1604, '温度', 'TEMPERATURE', NULL, NULL, 3, NULL, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_dict_type (id, dict_type,dict_name,remark,sort,tenant_id,version,deleted,creator,create_time,updater,update_time )VALUES(1605, 'device_event_type', '事件类型', '事件日志类型', 0, 10000, 0, 0, 10000, now(), 10000, now() );
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '下线', 'OFFLINE', 'danger', NULL, 1, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '上线', 'ONLINE', 'primary', NULL, 2, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '登录', 'SIGN_ON', 'primary', NULL, 3, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '退出登录', 'SIGN_OFF', 'danger', NULL, 4, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, 'OTA升级', 'OTA_UPGRADE', 'primary', NULL, 5, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '设备远程锁定', 'LOCK', 'primary', NULL, 6, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '设备远程解锁', 'UNLOCK', 'primary', NULL,7, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, 'APP版本信息', 'APP_VERSION_REPORT', 'primary', NULL, 8, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '电池电量', 'BATTERY_PERCENT_REPORT', 'primary', NULL, 9, NULL, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_dict_data (dict_type_id, dict_label, dict_value, label_class, remark, sort, tenant_id, version, deleted, creator, create_time, updater, update_time) VALUES (1605, '温度', 'TEMPERATURE_REPORT', 'primary', NULL, 0, NULL, 10, 0, 10000, now(), 10000, now());
