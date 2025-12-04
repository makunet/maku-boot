package net.maku.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户签名
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "用户签名")
public class SysUserSignatureVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "签名不能为空")
    @Schema(description = "签名")
    private String signature;

}
