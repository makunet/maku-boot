package net.maku.iot.mqtt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 响应消息基础类
 *
 * @author eden on 2024/6/17
 */
@Data
public class BaseCommandResponse {
    /**
     * 命令ID
     */
    @Schema(description = "命令ID", required = true)
    protected String commandId;
}