package net.maku.websocket.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.maku.websocket.session.WebSocketSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket 消息处理器
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Slf4j
@Component
@AllArgsConstructor
public class MessageWebSocketHandler extends TextWebSocketHandler {
    private final WebSocketSessionManager webSocketSessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 新增session
        webSocketSessionManager.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 移除session
        webSocketSessionManager.removeSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();

        log.info("Received message: {}", payload);

        // 处理接收到的消息
    }
}
