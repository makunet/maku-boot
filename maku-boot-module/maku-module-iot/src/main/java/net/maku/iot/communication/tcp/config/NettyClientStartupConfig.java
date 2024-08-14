package net.maku.iot.communication.tcp.config;

import io.netty.bootstrap.Bootstrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
@Slf4j
public class NettyClientStartupConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Bootstrap nettyClient;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 确保服务器启动完成后再启动客户端
        try {
            Thread.sleep(5000); // 延迟5秒以确保服务器启动
            nettyClient.connect("127.0.0.1", 8888).sync();
            log.info("Connected to Netty server on port 8888");
        } catch (InterruptedException e) {
            log.error("Failed to connect to Netty server", e);
        }
    }
}
