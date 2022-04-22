package net.maku.system.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 用户
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "用户")
public class SysUserVO extends SysUserPostVO {
    @Schema(name = "id")
    private Long id;

    @Schema(name = "超级管理员   0：否   1：是")
    private Integer superAdmin;

    @Schema(name = "机构名称")
    private String orgName;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;
}
