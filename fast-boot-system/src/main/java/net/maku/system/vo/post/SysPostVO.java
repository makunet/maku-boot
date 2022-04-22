package net.maku.system.vo.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.utils.DateUtils;

import java.util.Date;

/**
* 岗位管理
*
* @author 阿沐 babamu@126.com
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "岗位管理")
public class SysPostVO extends SysPostPostVO {
    @Schema(name = "id")
    private Long id;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

}