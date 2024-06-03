package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "字典类型")
public class SysDictTypeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "字典类型", required = true)
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    @Schema(description = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序", required = true)
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime updateTime;

    @Schema(description = "来源  0：字典数据  1：动态SQL")
    private Integer dictSource;

    @Schema(description = "动态sql")
    private String dictSql;

    @Schema(description = "上级节点")
    private Long pid;

    @Schema(description = "是否有子节点  0：无  1：有")
    private Integer hasChild;
}