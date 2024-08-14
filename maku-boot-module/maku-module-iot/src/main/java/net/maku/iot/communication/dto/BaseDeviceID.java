package net.maku.iot.communication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description TODO
 * @Author LSF
 * @Date 2024/8/14 17:24
 */
@Data
@Schema(description = "设备ID")
public class BaseDeviceID {

    @Schema(description = "设备ID")
    protected String deviceID;
}
