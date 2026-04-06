package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;
import net.maku.system.entity.SysOrgEntity;
import net.maku.framework.common.trans.Trans;
import net.maku.framework.common.trans.TransType;
import net.maku.framework.common.trans.TransPojo;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "用户")
public class SysUserVO implements Serializable, TransPojo {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "姓名不能为空")
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "签名")
    private String signature;

    @Schema(description = "性别 0：男   1：女   2：未知", requiredMode = Schema.RequiredMode.REQUIRED)
    @Range(min = 0, max = 2, message = "性别不正确")
    private Integer gender;

    @Schema(description = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @Schema(description = "机构ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机构ID不能为空")
    @Trans(type = TransType.SIMPLE, target = SysOrgEntity.class, fields = "name", ref = "orgName")
    private Long orgId;

    @Schema(description = "直属主管ID")
    private Long leaderId;

    @Schema(description = "状态 0：停用    1：正常", requiredMode = Schema.RequiredMode.REQUIRED)
    @Range(min = 0, max = 1, message = "用户状态不正确")
    private Integer status;

    @Schema(description = "角色ID列表")
    private List<Long> roleIdList;

    @Schema(description = "岗位ID列表")
    private List<Long> postIdList;

    @Schema(description = "岗位名称列表")
    private List<String> postNameList;

    @Schema(description = "超级管理员   0：否   1：是")
    private Integer superAdmin;

    @Schema(description = "机构名称")
    private String orgName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;
}
