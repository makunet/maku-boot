package net.maku.iot.communication.tcp.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.utils.IpUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
public class NettyClientConfig {

    private ChannelHandlerContext ctx;


    @Bean
    public Bootstrap nettyClient() {
        Bootstrap nettyClient = new Bootstrap();
        // 设置事件循环组（主线程组和从线程组）
        nettyClient.group(new io.netty.channel.nio.NioEventLoopGroup())
                //指定使用 NioServerSocketChannel 作为服务器通道
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                log.info("<------------------------ 客户端接收到: {}", msg);
                            }

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                String msg = IpUtils.getHostName();
                                log.info("------------------------> 发送消息到服务端: 我是 {}", msg);
                                ctx.writeAndFlush(msg);
                            }
                        });
                    }
                });
        return nettyClient;
    }

}
