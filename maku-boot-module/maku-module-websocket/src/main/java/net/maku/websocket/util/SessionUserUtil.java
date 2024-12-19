package net.maku.websocket.util;

import net.maku.framework.security.user.UserDetail;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class SessionUserUtil {
    private static final String SESSION_USER = "SESSION_USER";

    /**
     * 设置用户
     *
     * @param user       用户
     * @param attributes attributes
     */
    public static void setUser(UserDetail user, Map<String, Object> attributes) {
        attributes.put(SESSION_USER, user);
    }

    /**
     * 获取用户
     */
    public static UserDetail getUser(WebSocketSession session) {
        return (UserDetail) session.getAttributes().get(SESSION_USER);
    }

}
