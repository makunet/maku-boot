package net.maku.member.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会员管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "会员管理")
public class MemberUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "出生日期")
    @JsonFormat(pattern = DateUtils.DATE_PATTERN)
    private LocalDateTime birthday;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "最后登录IP")
    private String lastLoginIp;

    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime lastLoginTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

}