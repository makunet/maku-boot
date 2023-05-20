SET IDENTITY_INSERT sys_menu ON;
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1301, 33, '系统监控', '', '', 0, 0, 'icon-Report', 10, 0, 0, 10000, now(), 10000, now());

INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1302, 1301, '服务监控', 'monitor/server/index', 'monitor:server:all', 0, 0, 'icon-sever', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1304, 1301 '缓存监控', 'monitor/cache/index', 'monitor:cache:all', 0, 0, 'icon-fund-fill', 2, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (id, pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1305, 1301, '在线用户', 'monitor/user/index', 'monitor:user:all', 0, 0, 'icon-user', 3, 0, 0, 10000, now(), 10000, now());

commit;