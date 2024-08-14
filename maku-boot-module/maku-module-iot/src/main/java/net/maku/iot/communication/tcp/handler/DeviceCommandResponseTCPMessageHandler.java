package net.maku.iot.communication.tcp.handler;

import lombok.extern.slf4j.Slf4j;
import net.maku.iot.communication.mqtt.handler.MqttMessageHandler;
import net.maku.iot.enums.DeviceTopicEnum;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author LSF
 * @Date 2024/8/14 19:23
 */
@Slf4j
@Component
public class DeviceCommandResponseTCPMessageHandler implements TCPMessageHandler {
    @Override
    public boolean supports(String topic) {
        return DeviceTopicEnum.startsWith(topic, DeviceTopicEnum.COMMAND_RESPONSE.getTopic());
    }

    @Override
    public void handle(String topic, String message) {
            //TCP设备响应处理
        System.out.printf("TCP设备响应处理");
    }
}
