package net.maku.system.vo.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 客户端管理 更新
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "客户端管理更新")
public class SysOauthClientPutVO extends SysOauthClientPostVO {
    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

}
