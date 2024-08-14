package net.maku.iot.communication.tcp.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.maku.iot.communication.mqtt.factory.MqttMessageHandlerFactory;
import net.maku.iot.communication.tcp.factory.TcpMessageHandlerFactory;
import net.maku.iot.communication.tcp.handler.ConnectionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Configuration
@Slf4j
public class NettyServerConfig {

    @Bean
    public ConcurrentMap<String, Channel> deviceChannels() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public ServerBootstrap nettyServer(ConcurrentMap<String, Channel> deviceChannels) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(
                                new StringDecoder(),
                                new StringEncoder(),
//                                new DeviceMsgHandler(deviceChannels), // 添加设备身份处理器
                                new ConnectionHandler(deviceChannels) // 添加设备连接处理器
                        );
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        return bootstrap;
    }

    @Bean
    public ChannelFuture serverChannelFuture(ServerBootstrap serverBootstrap) throws InterruptedException {
        try {
            ChannelFuture future = serverBootstrap.bind(8888).sync();
            log.info("------------------------ Netty 服务器在端口 8888 启动成功");
            return future;
        } catch (Exception e) {
            log.error("------------------------ Netty 服务器启动失败", e);
            throw e;
        }
    }
}


//    // 发送命令到设备
//    public void sendCommandToDevice(String deviceId, String command) {
//        Channel channel = deviceChannels.get(deviceId);
//        if (channel != null && channel.isActive()) {
//            channel.writeAndFlush(Unpooled.copiedBuffer(command, CharsetUtil.UTF_8));
//            log.info("发送命令到设备 {}: {}", deviceId, command);
//        } else {
//            log.warn("设备 {} 不在线或通道无效", deviceId);
//        }
//    }
//
//    // 假设有方法通过通道获取设备 ID
//    private String getDeviceId(Channel channel) {
//        // 这里应该有逻辑来从通道获取设备 ID
//        return "deviceId";
//    }
//}
