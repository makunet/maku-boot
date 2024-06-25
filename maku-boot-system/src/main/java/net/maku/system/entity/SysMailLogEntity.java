package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@TableName("sys_mail_log")
public class SysMailLogEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 平台ID
     */
    private Long platformId;

    /**
     * 平台类型
     */
    private Integer platform;

    /**
     * 发件人邮箱
     */
    private String mailFrom;

    /**
     * 接受人邮箱
     */
    private String mailTos;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 状态  0：失败   1：成功
     */
    private Integer status;

    /**
     * 异常信息
     */
    private String error;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}