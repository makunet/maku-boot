package net.maku.system.vo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户新增
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(name = "用户新增")
public class SysUserPostVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(name = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(name = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String realName;

    @Schema(name = "头像")
    private String avatar;

    @Schema(name = "性别   0：男   1：女", required = true)
    @Range(min = 0, max = 1, message = "性别不正确")
    private Integer gender;

    @Schema(name = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(name = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @Schema(name = "机构ID", required = true)
    @NotNull(message = "机构ID不能为空")
    private Long orgId;

    @Schema(name = "状态  0：停用    1：正常", required = true)
    @Range(min = 0, max = 1, message = "用户状态不正确")
    private Integer status;

    @Schema(name = "角色ID列表")
    private List<Long> roleIdList;

    @Schema(name = "岗位ID列表")
    private List<Long> postIdList;

}
