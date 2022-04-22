package net.maku.system.vo.dict.type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.query.Query;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "字典类型查询")
public class SysDictTypeQuery extends Query {
    @Schema(name = "字典类型")
    private String dictType;

    @Schema(name = "字典名称")
    private String dictName;

}
