INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (33, '系统监控', '', '', 0, 0, 'icon-Report', 10, 0, 0, 10000, now(), 10000, now());

set @menuId = @@identity;
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '服务监控', 'monitor/server/index', 'monitor:server:all', 0, 0, 'icon-sever', 0, 0, 0, 10000, now(), 10000, now());
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES ((SELECT @menuId), '缓存监控', 'monitor/cache/index', 'monitor:cache:all', 0, 0, 'icon-fund-fill', 2, 0, 0, 10000, now(), 10000, now());
