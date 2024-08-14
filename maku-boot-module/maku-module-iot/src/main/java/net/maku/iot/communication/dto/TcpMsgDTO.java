package net.maku.iot.communication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description TODO
 * @Author LSF
 * @Date 2024/8/14 19:31
 */
@Data
@Schema(description = "tcp通讯数据包装类")
public class TcpMsgDTO {
    private String topic;
    private Object msg;
}
