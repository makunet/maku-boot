package net.maku.websocket.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.time.LocalDateTime;


/**
 * 发送消息的对象
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Tag(name = "发送消息的对象")
public class MessageVO {
    @Schema(description = "用户名")
    private String name;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "发送时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime sendTime;
}
