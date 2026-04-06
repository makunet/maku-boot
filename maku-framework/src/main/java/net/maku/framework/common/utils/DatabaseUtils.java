package net.maku.framework.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.constant.DbConstant;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 数据库工具类
 *
 * @author 阿沐 babamu@126.com
 */
@Slf4j
public class DatabaseUtils {

    /**
     * 获取数据库产品名称
     *
     * @return 数据库产品名称
     */
    public static String getProductName() {
        DataSource dataSource = SpringUtil.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection()) {
            return getProductName(connection);
        } catch (Exception e) {
            log.error("Failed to get database product name", e);
        }

        return DbConstant.MySQL;
    }

    /**
     * 获取数据库产品名称
     *
     * @return 数据库产品名称
     */
    public static String getProductName(Connection connection) {
        String productName = DbConstant.MySQL;
        try {
            productName = connection.getMetaData().getDatabaseProductName();

            // 如果是Oracle，则需要判断驱动名，有可能为达梦数据库
            if (StrUtil.contains(productName, DbConstant.Oracle)) {
                String driverName = connection.getMetaData().getDriverName();
                if (driverName.contains("dm.jdbc.driver.DmDriver")) {
                    productName = DbConstant.DM;
                }
            }
        } catch (Exception e) {
            log.error("Failed to get database product name", e);
        }

        return productName;
    }

}