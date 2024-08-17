package net.maku.iot.communication.tcp;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.exception.ServerException;
import net.maku.iot.communication.mqtt.config.MqttConfig;
import net.maku.iot.communication.tcp.config.NettyServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * TCP 网关
 *
 * @author LSF maku_lsf@163.com
 */
@Component
@Slf4j
public class TcpGateway {

    @Autowired
    private final ConcurrentMap<String, Channel> deviceChannels;

    @Autowired
    public TcpGateway(ConcurrentMap<String, Channel> deviceChannels) {
        System.out.printf("-------------------------------->TcpGateway");
        this.deviceChannels = deviceChannels;
    }

    /**
     * 发送命令到设备
     * @param deviceId 设备ID
     * @param commandTopic 命令主题
     * @param payload 命令内容
     */
    public void sendCommandToDevice(Long deviceId, String commandTopic, String payload) {
        Channel channel = deviceChannels.get(deviceId);
        if (channel != null && channel.isActive()) {
            Map payloadMap = new HashMap();
            payloadMap.put("topic", commandTopic);
            payloadMap.put("payload", payload);

            channel.writeAndFlush(Unpooled.copiedBuffer(JSONUtil.toJsonStr(payloadMap), CharsetUtil.UTF_8));
            log.info("发送命令到设备 {}: {}", deviceId, payload);
        } else {
            throw new ServerException("设备"+deviceId+"不在线或通道无效");
        }
    }
}
