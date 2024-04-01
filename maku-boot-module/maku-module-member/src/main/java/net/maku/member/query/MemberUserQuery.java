package net.maku.member.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.query.Query;

/**
 * 会员管理查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "会员管理查询")
public class MemberUserQuery extends Query {
    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "状态")
    private Integer status;

}