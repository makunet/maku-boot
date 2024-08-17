package net.maku.iot.communication.tcp.config;

import io.netty.bootstrap.Bootstrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
@Slf4j
public class NettyClientStartupConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final ObjectProvider<Bootstrap> nettyClientProvider;
    private final NettyClientConfig nettyClientConfig;

    public NettyClientStartupConfig(ObjectProvider<Bootstrap> nettyClientProvider, NettyClientConfig nettyClientConfig) {
        this.nettyClientProvider = nettyClientProvider;
        this.nettyClientConfig = nettyClientConfig;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            Thread.sleep(5000); // 延迟5秒以确保服务器启动
            Bootstrap nettyClient = nettyClientProvider.getIfAvailable();
            if (nettyClient != null) {
                nettyClientConfig.configureBootstrap(nettyClient);
                nettyClient.connect("127.0.0.1", 8888).sync();
                log.info("Connected to Netty server on port 8888");
            }
        } catch (InterruptedException e) {
            log.error("Failed to connect to Netty server", e);
        }
    }
}
