package net.maku.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 手机号登录
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "手机号登录")
public class SysMobileLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "验证码")
    private String code;
}
