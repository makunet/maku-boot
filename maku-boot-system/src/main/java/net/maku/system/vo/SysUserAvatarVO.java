package net.maku.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户头像
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "用户头像")
public class SysUserAvatarVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "头像不能为空")
    @Schema(description = "头像")
    private String avatar;

}
