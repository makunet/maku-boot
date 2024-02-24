package net.maku.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 第三方登录 回调参数
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "第三方登录 回调参数")
public class SysThirdCallbackVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "开放平台类型")
    private String openType;

    @Schema(description = "开放平台Code")
    private String code;

    @Schema(description = "state")
    private String state;
}
