package net.maku.framework.common.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import net.maku.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 日期转换
 *
 * @author eden
 */
public class DateConverter implements Converter<Date> {

    @Override
    public Class<Date> supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Date convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String dateString = cellData.getStringValue();
        return dateString == null ? null : DateUtils.parse(dateString, DateUtils.DATE_TIME_PATTERN);
    }

    @Override
    public WriteCellData<Date> convertToExcelData(Date value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String dateValue = DateUtils.format(value, DateUtils.DATE_TIME_PATTERN);
        return new WriteCellData<>(dateValue);
    }
}