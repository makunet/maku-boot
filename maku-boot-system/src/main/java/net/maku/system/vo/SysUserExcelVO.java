package net.maku.system.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.Data;
import net.maku.framework.common.excel.DateConverter;
import net.maku.framework.common.utils.DateUtils;
import net.maku.system.enums.SuperAdminEnum;
import net.maku.system.enums.UserGenderEnum;
import net.maku.system.enums.UserStatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * excel用户表
 *
 * @author 阿沐 babamu@126.com
 */
@Data
public class SysUserExcelVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("姓名")
    private String realName;

    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private Integer gender;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("机构ID")
    private Long orgId;

    @ExcelProperty(value = "状态", converter = StatusConverter.class)
    private Integer status;

    @ExcelProperty(value = "超级管理员", converter = SuperConverter.class)
    private Integer superAdmin;

    @ExcelProperty(value = "创建时间", converter = DateConverter.class)
    private Date createTime;

    public static class GenderConverter implements Converter<Integer> {

        @Override
        public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            String dateString = cellData.getStringValue();
            return UserGenderEnum.getValueByName(dateString);
        }

        @Override
        public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            return new WriteCellData<>(UserGenderEnum.getNameByValue(value));
        }
    }

    public static class StatusConverter implements Converter<Integer> {

        @Override
        public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            String dateString = cellData.getStringValue();
            return UserStatusEnum.getValueByName(dateString);
        }

        @Override
        public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            return new WriteCellData<>(UserStatusEnum.getNameByValue(value));
        }
    }

    public static class SuperConverter implements Converter<Integer> {

        @Override
        public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            String dateString = cellData.getStringValue();
            return SuperAdminEnum.getValueByName(dateString);
        }

        @Override
        public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
            return new WriteCellData<>(SuperAdminEnum.getNameByValue(value));
        }
    }
}
