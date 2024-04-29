package net.maku.framework.common.excel;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import net.maku.framework.common.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换
 *
 * @author eden
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    @Override
    public Class<LocalDateTime> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String dateString = cellData.getStringValue();
        return dateString == null ? null : LocalDateTimeUtil.parse(dateString, DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN));
    }

    @Override
    public WriteCellData<LocalDateTime> convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String dateValue = LocalDateTimeUtil.format(value, DateUtils.DATE_TIME_PATTERN);
        return new WriteCellData<>(dateValue);
    }
}
