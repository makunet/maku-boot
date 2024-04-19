package net.maku;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import net.maku.framework.common.excel.ExcelFinishCallBack;
import net.maku.framework.common.excel.LocalDateTimeConverter;
import net.maku.framework.common.utils.ExcelUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * EasyExcel 测试
 *
 * @author eden
 */
public class EasyExcelTest {

    @Test
    public void doImport() {
        File file = new File("D://upload//test01.xlsx");
        ExcelClass excelClass = new ExcelClass();
        excelClass.setNumber(1);
        excelClass.setDecimals(1.2);
        excelClass.setString("test");
        excelClass.setDate(LocalDateTime.now());
        List<ExcelClass> data = Arrays.asList(excelClass, excelClass, excelClass);
        if (file.exists()) {
            ExcelUtils.excelExport(ExcelClass.class, file, data);
            List<ExcelClass> list = ExcelUtils.readSync(file, ExcelClass.class);
            list.forEach(System.out::println);
        }
    }


    @Test
    public void doAImport() {
        File file = new File("D://upload//test01.xlsx");
        ExcelClass excelClass = new ExcelClass();
        excelClass.setNumber(1);
        excelClass.setDecimals(1.2);
        excelClass.setString("test");
        excelClass.setDate(LocalDateTime.now());
        List<ExcelClass> data = Arrays.asList(excelClass, excelClass, excelClass, excelClass, excelClass, excelClass, excelClass);
        if (file.exists()) {
            ExcelUtils.excelExport(ExcelClass.class, file, data);
            ExcelUtils.readAnalysis(file, ExcelClass.class, new ServiceA());
        }

    }

    @Data
    public static class ExcelClass {

        @ExcelProperty("整数")
        private Integer number;

        @ExcelProperty("字符串")
        private String string;

        @ExcelProperty("小数")
        private Double decimals;

        @ExcelProperty(value = "日期", converter = LocalDateTimeConverter.class)
        private LocalDateTime date;
    }

}

class ServiceA implements ExcelFinishCallBack<EasyExcelTest.ExcelClass> {

    @Override
    public void doAfterAllAnalysed(List<EasyExcelTest.ExcelClass> result) {
        System.out.println(result.size());
    }
}

