package net.maku.monitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 在线用户
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "在线用户")
public class UserOnlineVO {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "accessToken")
    private String accessToken;
}
