package net.maku.quartz.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@TableName("schedule_job")
public class ScheduleJobEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	* 任务名称
	*/
	private String jobName;

	/**
	* 任务组名
	*/
	private String jobGroup;

	/**
	* bean名称
	*/
	private String beanName;

	/**
	 * 执行方法
	 */
	private String method;

	/**
	* 方法参数
	*/
	private String params;

	/**
	* cron表达式
	*/
	private String cronExpression;

	/**
	* 状态 
	*/
	private Integer status;

	/**
	* 是否并发  0：禁止  1：允许
	*/
	private Integer concurrent;

	/**
	* 备注
	*/
	private String remark;

	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long  creator;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long  updater;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	 * 版本号
	 */
	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;

	/**
	 * 删除标记
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

}