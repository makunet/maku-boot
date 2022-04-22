package net.maku.system.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.query.Query;

/**
 * 用户查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "用户查询")
public class SysUserQuery extends Query {
    @Schema(name = "用户名")
    private String username;

    @Schema(name = "手机号")
    private String mobile;

    @Schema(name = "性别")
    private Integer gender;

    @Schema(name = "机构ID")
    private Long orgId;

}
