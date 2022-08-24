package net.maku.storage.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import net.maku.storage.properties.StorageProperties;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.Date;

/**
 * 存储服务
 *
 * @author 阿沐 babamu@126.com
 */
public abstract class StorageService {
    public StorageProperties properties;

    /**
     * 根据文件名，生成带时间戳的新文件名
     *
     * @param fileName 文件名
     * @return 返回带时间戳的文件名
     */
    public String getNewFileName(String fileName) {
        // 主文件名，不包含扩展名
        String prefix = FileNameUtil.getPrefix(fileName);
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(fileName);
        // 把当天HH:mm:ss，转换成秒
        long time = DateUtil.timeToSecond(DateUtil.formatTime(new Date()));
        // 新文件名
        return prefix + "_" + time + "." + suffix;
    }

    /**
     * 生成路径，不包含文件名
     *
     * @return 返回生成的路径
     */
    public String getPath() {
        // 文件路径
        String path = DateUtil.format(new Date(), "yyyyMMdd");

        // 如果有前缀，则也带上
        if (StringUtils.hasText(properties.getConfig().getPrefix())) {
            path = properties.getConfig().getPrefix() + "/" + path;
        }

        return path;
    }

    /**
     * 根据文件名，生成路径
     *
     * @param fileName 文件名
     * @return 生成文件路径
     */
    public String getPath(String fileName) {
        return getPath() + "/" + getNewFileName(fileName);
    }

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

}
