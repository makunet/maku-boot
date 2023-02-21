package net.maku.api.module.system;

import java.io.InputStream;

/**
 * 存储服务API
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface StorageApi {

    /**
     * 根据文件名，生成带时间戳的新文件名
     *
     * @param fileName 文件名
     * @return 返回带时间戳的文件名
     */
    String getNewFileName(String fileName);

    /**
     * 生成路径，不包含文件名
     *
     * @return 返回生成的路径
     */
    String getPath();

    /**
     * 根据文件名，生成路径
     *
     * @param fileName 文件名
     * @return 生成文件路径
     */
    String getPath(String fileName);

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    String upload(InputStream inputStream, String path);
}
