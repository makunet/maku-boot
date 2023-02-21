package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "岗位管理")
public class SysPostVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "岗位编码", required = true)
    @NotBlank(message = "岗位编码不能为空")
    private String postCode;

    @Schema(description = "岗位名称", required = true)
    @NotBlank(message = "岗位名称不能为空")
    private String postName;

    @Schema(description = "排序", required = true)
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "状态  0：停用   1：正常", required = true)
    @Range(min = 0, max = 1, message = "状态不正确")
    private Integer status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

}