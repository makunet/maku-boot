package net.maku.framework.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.util.ReflectUtils;
import com.fhs.core.trans.vo.TransPojo;
import com.fhs.trans.service.impl.DictionaryTransService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.maku.framework.common.excel.ExcelDataListener;
import net.maku.framework.common.excel.ExcelFinishCallBack;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Excel utils.
 * {@link <a href="https://easyexcel.opensource.alibaba.com/"></a>}
 *
 * @author eden
 */
public class ExcelUtils {

    /**
     * 读取excel文件
     *
     * @param <T>      数据类型
     * @param file     excel文件
     * @param head     列名
     * @param callBack 回调 导入时传入定义好的回调接口，excel数据解析完毕之后监听器将数据传入回调函数
     *                 这样调用工具类时可以通过回调函数获取导入的数据，如果数据量过大可根据实际情况进行分配入库
     */
    public static <T> void readAnalysis(MultipartFile file, Class<T> head, ExcelFinishCallBack<T> callBack) {
        try {
            EasyExcel.read(file.getInputStream(), head, new ExcelDataListener<>(callBack)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel文件
     *
     * @param <T>      数据类型
     * @param file     excel文件
     * @param head     列名
     * @param callBack 回调 导入时传入定义好的回调接口，excel数据解析完毕之后监听器将数据传入回调函数
     *                 这样调用工具类时可以通过回调函数获取导入的数据，如果数据量过大可根据实际情况进行分配入库
     */
    public static <T> void readAnalysis(File file, Class<T> head, ExcelFinishCallBack<T> callBack) {
        try {
            EasyExcel.read(new FileInputStream(file), head, new ExcelDataListener<>(callBack)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel文件 同步
     *
     * @param <T>   数据类型
     * @param file  文件
     * @param clazz 模板类
     * @return java.util.List list
     */
    public static <T> List<T> readSync(File file, Class<T> clazz) {
        return readSync(file, clazz, 1, 0, ExcelTypeEnum.XLSX);
    }

    /**
     * 读取excel文件 同步
     *
     * @param <T>       数据类型
     * @param file      文件
     * @param clazz     模板类
     * @param rowNum    数据开始行 1
     * @param sheetNo   第几张表
     * @param excelType 数据表格式类型
     * @return java.util.List list
     */
    public static <T> List<T> readSync(File file, Class<T> clazz, Integer rowNum, Integer sheetNo, ExcelTypeEnum excelType) {
        return EasyExcel.read(file).headRowNumber(rowNum).excelType(excelType).head(clazz).sheet(sheetNo).doReadSync();
    }


    /**
     * 导出数据到文件
     *
     * @param <T>  数据类型
     * @param head 类名
     * @param file 导入到文件
     * @param data 数据
     */
    public static <T> void excelExport(Class<T> head, File file, List<T> data) {
        excelExport(head, file, "sheet1", data);
    }

    /**
     * 导出数据到文件
     *
     * @param <T>       写入格式
     * @param head      类名
     * @param file      写入到文件
     * @param sheetName sheet名称
     * @param data      数据列表
     */
    public static <T> void excelExport(Class<T> head, File file, String sheetName, List<T> data) {
        try {
            EasyExcel.write(file, head).sheet(sheetName).doWrite(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 导出数据到web
     * 文件下载（失败了会返回一个有部分数据的Excel）
     *
     * @param head      类名
     * @param excelName excel名字
     * @param sheetName sheet名称
     * @param data      数据
     */
    public static <T> void excelExport(Class<T> head, String excelName, String sheetName, List<T> data) {
        try {
            HttpServletResponse response = HttpContextUtils.getHttpServletResponse();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easy excel没有关系
            String fileName = URLUtil.encode(excelName).replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), head).sheet(StringUtils.isBlank(sheetName) ? "sheet1" : sheetName).doWrite(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析字典数据到字段上
     * 比如 T中有 genderLabel字段 为男 需要给 gender 字段自动设置为0
     *
     * @param dataList 需要被反向解析的数据
     */
    @SneakyThrows
    public static <T extends TransPojo> void parseDict(List<T> dataList) {
        //没有数据就不需要初始化
        if (CollectionUtil.isEmpty(dataList)) {
            return;
        }
        Class<? extends TransPojo> clazz = dataList.get(0).getClass();
        //拿到所有需要反向翻译的字段
        List<Field> fields = ReflectUtils.getAnnotationField(clazz, Trans.class);
        //过滤出字典翻译
        fields = fields.stream().filter(field -> TransType.DICTIONARY.equals(field.getAnnotation(Trans.class).type())).collect(Collectors.toList());
        DictionaryTransService dictionaryTransService = SpringUtil.getBean(DictionaryTransService.class);
        for (T data : dataList) {
            for (Field field : fields) {
                Trans trans = field.getAnnotation(Trans.class);
                // key不能为空并且ref不为空的才自动处理
                if (StrUtil.isAllNotBlank(trans.key(), trans.ref())) {
                    Field ref = ReflectUtils.getDeclaredField(clazz, trans.ref());
                    ref.setAccessible(true);
                    // 获取字典反向值
                    String value = dictionaryTransService.getUnTransMap().get(trans.key() + "_" + ref.get(data));
                    if (StringUtils.isBlank(value)) {
                        continue;
                    }
                    // 一般目标字段是int或者string字段 后面有添加单独抽离方法
                    if (Integer.class.equals(field.getType())) {
                        field.setAccessible(true);
                        field.set(data, ConverterUtils.toInteger(value));
                    } else {
                        field.setAccessible(true);
                        field.set(data, ConverterUtils.toString(value));
                    }
                }
            }
        }

    }

}
