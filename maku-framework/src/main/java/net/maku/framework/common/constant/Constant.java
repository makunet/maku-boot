package net.maku.framework.common.constant;

/**
 * 常量
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface Constant {
    /**
     * 根节点标识
     */
    Long ROOT = 0L;
    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";
    /**
     * 超级管理员
     */
    Integer SUPER_ADMIN = 1;
    /**
     * 禁用
     */
    Integer DISABLE = 0;
    /**
     * 启用
     */
    Integer ENABLE = 1;
    /**
     * 失败
     */
    Integer FAIL = 0;
    /**
     * 成功
     */
    Integer SUCCESS = 1;
    /**
     * OK
     */
    String OK = "OK";

    /**
     * pgsql的driver
     */
    String PGSQL_DRIVER = "org.postgresql.Driver";

    // 字段名 主键
    String COLUMN_ID = "id";
    // 字段名 创建者
    String COLUMN_CREATOR = "creator";
    // 字段名 创建时间
    String COLUMN_CREATE_TIME = "create_time";
    // 字段名 更新者
    String COLUMN_UPDATER = "updater";
    // 字段名 更新时间
    String COLUMN_UPDATE_TIME = "update_time";
    // 字段名 租户ID
    String COLUMN_TENANT_ID = "tenant_id";

}