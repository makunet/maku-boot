package net.maku.system.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.excel.DateConverter;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "登录日志")
public class SysLogLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @Schema(description = "id")
    private Long id;

    @ExcelProperty("用户名")
    @Schema(description = "用户名")
    private String username;

    @ExcelProperty("登录IP")
    @Schema(description = "登录IP")
    private String ip;

    @ExcelProperty("登录地点")
    @Schema(description = "登录地点")
    private String address;

    @ExcelProperty("User Agent")
    @Schema(description = "User Agent")
    private String userAgent;

    @ExcelProperty(value = "登录状态", converter = StatusConverter.class)
    @Schema(description = "登录状态  0：失败   1：成功")
    private Integer status;

    @ExcelProperty(value = "操作信息", converter = OperationConverter.class)
    @Schema(description = "操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误")
    private Integer operation;

    @ExcelProperty(value = "创建时间", converter = DateConverter.class)
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;


    public static class StatusConverter implements Converter<Integer> {

        @Override
        public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            String dateValue = "";
            if (value == 0) {
                dateValue = "失败";
            } else if (value == 1) {
                dateValue = "成功";
            }
            return new WriteCellData<>(dateValue);
        }
    }

    public static class OperationConverter implements Converter<Integer> {

        @Override
        public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            String dateValue = "";
            if (value == 0) {
                dateValue = "登录成功";
            } else if (value == 1) {
                dateValue = "退出成功";
            } else if (value == 2) {
                dateValue = "验证码错误";
            } else if (value == 3) {
                dateValue = "账号密码错误";
            }
            return new WriteCellData<>(dateValue);
        }
    }

}