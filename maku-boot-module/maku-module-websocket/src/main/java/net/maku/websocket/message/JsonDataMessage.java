package net.maku.websocket.message;

import lombok.Data;

/**
 * websocket json 消息
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
public class JsonDataMessage<T> {
    /**
     * 消息类型 预留
     */
    private String type = "default";
    /**
     * 消息数据
     */
    private T data;

}
