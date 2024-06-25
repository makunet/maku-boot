package net.maku.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.query.Query;

/**
 * 短信配置查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "短信配置查询")
public class SysSmsConfigQuery extends Query {
    @Schema(description = "平台类型")
    private Integer platform;

    @Schema(description = "分组名称")
    private String groupName;

}