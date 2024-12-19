package net.maku.websocket.session;

import cn.hutool.core.collection.CollUtil;
import net.maku.framework.security.user.UserDetail;
import net.maku.websocket.util.SessionUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * WebSocket 会话管理器
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Component
public class WebSocketSessionManager {
    /**
     * 用户 WebSocket 会话
     * key1：用户ID
     * key2：SessionID
     */
    private final ConcurrentMap<Long, ConcurrentMap<String, WebSocketSession>> userSessions = new ConcurrentHashMap<>();

    public void addSession(WebSocketSession session) {
        // 当前连接的用户
        UserDetail user = SessionUserUtil.getUser(session);
        if (user == null) {
            return;
        }

        userSessions.computeIfAbsent(user.getId(), k -> new ConcurrentHashMap<>())
                .putIfAbsent(session.getId(), session);
    }

    public void removeSession(WebSocketSession session) {
        // 当前连接的用户
        UserDetail user = SessionUserUtil.getUser(session);
        if (user == null) {
            return;
        }

        userSessions.computeIfPresent(user.getId(), (k, sessionMap) -> {
            sessionMap.remove(session.getId());
            return sessionMap.isEmpty() ? null : sessionMap;
        });
    }

    public List<WebSocketSession> getSessionList(Long userId) {
        ConcurrentMap<String, WebSocketSession> sessionMap = userSessions.get(userId);
        if (CollUtil.isEmpty(sessionMap)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(sessionMap.values());
    }

}