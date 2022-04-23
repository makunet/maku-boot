package net.maku.system.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户更新
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户更新")
public class SysUserPutVO extends SysUserPostVO {
    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

}
