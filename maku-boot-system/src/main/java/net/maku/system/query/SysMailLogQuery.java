package net.maku.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.query.Query;

/**
 * 邮件日志查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "邮件日志查询")
public class SysMailLogQuery extends Query {
    @Schema(description = "发送人邮箱")
    private String mailFrom;

    @Schema(description = "平台类型")
    private Integer platform;
}