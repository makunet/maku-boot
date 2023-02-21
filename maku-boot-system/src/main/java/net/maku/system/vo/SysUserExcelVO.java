package net.maku.system.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;
import lombok.Data;
import net.maku.framework.common.excel.DateConverter;

import java.io.Serializable;
import java.util.Date;

/**
 * excel用户表
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
public class SysUserExcelVO implements Serializable, TransPojo {
    private static final long serialVersionUID = 1L;

    /**
     * 本属性对于导出无用，只是用于翻译
     */
    @ExcelIgnore
    private Long id;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("姓名")
    private String realName;

    @ExcelIgnore
    @Trans(type = TransType.DICTIONARY, key = "user_gender", ref = "genderLabel")
    private Integer gender;

    @ExcelProperty(value = "性别")
    private String genderLabel;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("机构ID")
    private Long orgId;

    @ExcelIgnore
    @Trans(type = TransType.DICTIONARY, key = "user_status", ref = "statusLabel")
    private Integer status;

    @ExcelProperty(value = "状态")
    private String statusLabel;

    @ExcelIgnore
    @Trans(type = TransType.DICTIONARY, key = "user_super_admin", ref = "superAdminLabel")
    private Integer superAdmin;

    @ExcelProperty(value = "超级管理员")
    private String superAdminLabel;

    @ExcelProperty(value = "创建时间", converter = DateConverter.class)
    private Date createTime;

}
