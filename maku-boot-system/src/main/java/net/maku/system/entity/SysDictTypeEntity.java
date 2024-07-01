package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.mybatis.entity.BaseEntity;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_type")
public class SysDictTypeEntity extends BaseEntity {
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 来源  0：字典数据  1：动态SQL
     */
    private Integer dictSource;
    /**
     * 动态sql
     */
    private String dictSql;
    /**
     * 上级节点
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Long pid;
    /**
     * 是否有子节点  0：无  1：有
     */
    private Integer hasChild;
    /**
     * 租户ID
     */
    private Long tenantId;
}