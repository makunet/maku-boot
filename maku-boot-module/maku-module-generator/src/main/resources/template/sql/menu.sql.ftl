<#assign dbTime = "now()">
<#if dbType=="SQLServer">
    <#assign dbTime = "getDate()">
</#if>

-- 初始化菜单
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (1, '${tableComment!}', '${moduleName}/${functionName}/index', <#if authLevel==0>'${authority}'<#else>NULL</#if>, 0, 0, 'icon-menu', 0, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});

<#if authLevel==1>

<#assign pid = "(SELECT max(id) from sys_menu where name = '${tableComment!}')">
<#if dbType=="MySQL">
set @menuId = @@identity;
<#assign pid = "(SELECT @menuId)">
</#if>

INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (${pid}, '查看', '', '${authority}:page', 1, 0, '', 0, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});
<#if tableOperation?seq_contains('insert')>
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (${pid}, '新增', '', '${authority}:save', 1, 0, '', 1, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});
</#if>
<#if tableOperation?seq_contains('update')>
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (${pid}, '修改', '', '${authority}:update,${authority}:info', 1, 0, '', 2, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});
</#if>
<#if tableOperation?seq_contains('delete')>
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (${pid}, '删除', '', '${authority}:delete', 1, 0, '', 3, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});
</#if>
<#if tableOperation?seq_contains('import')>
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (${pid}, '导入', '', '${authority}:import', 1, 0, '', 3, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});
</#if>
<#if tableOperation?seq_contains('export')>
INSERT INTO sys_menu (pid, name, url, authority, type, open_style, icon, sort, version, deleted, creator, create_time, updater, update_time) VALUES (${pid}, '导出', '', '${authority}:export', 1, 0, '', 3, 0, 0, 10000, ${dbTime}, 10000, ${dbTime});
</#if>
</#if>