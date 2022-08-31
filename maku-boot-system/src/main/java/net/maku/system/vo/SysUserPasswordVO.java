package net.maku.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户修改密码
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "用户修改密码")
public class SysUserPasswordVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "原密码", required = true)
    @NotBlank(message = "原密码不能为空")
    private String password;

    @Schema(description = "新密码，密码长度为 4-20 位", required = true)
    @Length(min = 4, max = 20, message = "新密码长度为 4-20 位")
    private String newPassword;

}