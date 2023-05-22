package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */

@Data
@TableName("sys_log_operate")
public class SysLogOperateEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 操作人
     */
    private String realName;

    /**
     * 模块名
     */
    private String module;

    /**
     * 操作名
     */
    private String name;

    /**
     * 请求URI
     */
    private String reqUri;

    /**
     * 请求方法
     */
    private String reqMethod;

    /**
     * 请求参数
     */
    private String reqParams;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 登录地点
     */
    private String address;

    /**
     * User Agent
     */
    private String userAgent;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 执行时长
     */
    private Integer duration;

    /**
     * 操作状态
     */
    private Integer status;

    /**
     * 返回消息
     */
    private String resultMsg;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}