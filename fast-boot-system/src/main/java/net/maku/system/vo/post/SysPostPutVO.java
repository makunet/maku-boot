package net.maku.system.vo.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
* 岗位管理
*
* @author 阿沐 babamu@126.com
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "岗位管理更新")
public class SysPostPutVO extends SysPostPostVO {
    @Schema(name = "id", required = true)
    @NotNull(message = "ID不能为空")
    private Long id;

}