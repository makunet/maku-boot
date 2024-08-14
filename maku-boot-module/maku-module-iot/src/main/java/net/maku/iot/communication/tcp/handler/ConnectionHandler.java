package net.maku.iot.communication.tcp.handler;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.utils.JsonUtils;
import net.maku.iot.communication.dto.TcpMsgDTO;
import net.maku.iot.communication.tcp.factory.TcpMessageHandlerFactory;

import java.util.concurrent.ConcurrentMap;

/**
 * @Description TODO
 * @Author LSF
 * @Date 2024/8/14 16:52
 */
@Slf4j
public class ConnectionHandler extends ChannelInboundHandlerAdapter {


    @Resource
    private TcpMessageHandlerFactory tcpMessageHandlerFactory;

    private final ConcurrentMap<String, Channel> deviceChannels;

    public ConnectionHandler(ConcurrentMap<String, Channel> deviceChannels) {
        this.deviceChannels = deviceChannels;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 请求设备发送其 ID
        ctx.writeAndFlush("ACK");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg!=null&& StrUtil.contains(msg.toString(),"topic")) {
            // 处理 TCP 消息
            handleTcpMessage(ctx, JsonUtils.parseObject(msg.toString(), TcpMsgDTO.class));
        } else {
            // 处理其他类型的消息
            log.warn("接收到未知的消息类型：{}", msg);
        }
    }



    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String deviceId = getDeviceId(ctx.channel());
        if (deviceId != null) {
            deviceChannels.remove(deviceId);
        }
        log.info(" {} 断开连接", deviceId == null ? "未知设备" : deviceId);
    }

    private void handleTcpMessage(ChannelHandlerContext ctx, TcpMsgDTO message) {
        String topic = message.getTopic();
        if (topic != null) {
            tcpMessageHandlerFactory.getHandlersForTopic(topic).forEach(handler -> {
                handler.handle(topic, message.getMsg().toString());
            });
        } else {
            log.warn("接收到主题为null的消息。");
        }
    }


    private String getDeviceId(Channel channel) {
        // 从 Channel 的属性中获取设备 ID
        return channel.attr(AttributeKey.<String>valueOf("deviceId")).get();
    }
}
