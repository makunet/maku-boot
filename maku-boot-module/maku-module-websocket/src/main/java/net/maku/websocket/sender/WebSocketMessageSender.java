package net.maku.websocket.sender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.utils.JsonUtils;
import net.maku.websocket.message.JsonDataMessage;
import net.maku.websocket.session.WebSocketSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * WebSocket 消息发送
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Slf4j
@Component
@AllArgsConstructor
public class WebSocketMessageSender {
    private final WebSocketSessionManager webSocketSessionManager;

    /**
     * 发送信息
     *
     * @param userId  用户ID
     * @param message 消息内容
     */
    public void send(Long userId, JsonDataMessage<?> message) {
        webSocketSessionManager.getSessionList(userId).forEach(session -> send(session, message));
    }

    /**
     * 发送信息
     *
     * @param userIdList 用户ID列表
     * @param message    消息内容
     */
    public void send(List<Long> userIdList, JsonDataMessage<?> message) {
        userIdList.forEach(userId -> send(userId, message));
    }

    /**
     * 发送信息
     *
     * @param session WebSocketSession
     * @param message 消息内容
     */
    public void send(WebSocketSession session, JsonDataMessage<?> message) {
        try {
            session.sendMessage(new TextMessage(JsonUtils.toJsonString(message)));
        } catch (Exception e) {
            log.error("send websocket message error，{}", e.getMessage(), e);
        }
    }
}
