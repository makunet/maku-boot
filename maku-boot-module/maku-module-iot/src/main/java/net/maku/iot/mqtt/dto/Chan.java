package net.maku.iot.mqtt.dto;


import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 数据生产消费者
 */
public class Chan {

    // 存储通道的 ConcurrentHashMap
    private static final ConcurrentHashMap<String, Chan> CHANNEL = new ConcurrentHashMap<>();

    private final CompletableFuture<Object> future = new CompletableFuture<>();

    // 私有构造函数，不允许外部直接实例化
    private Chan() {
    }

    /**
     * 获取或创建通道实例
     *
     * @param commandId    通道标识
     * @param isNeedCreate 是否需要创建新的通道实例
     * @return 通道实例
     */
    public static Chan getInstance(String commandId, boolean isNeedCreate) {
        if (!isNeedCreate) {
            return CHANNEL.get(commandId);
        }
        return CHANNEL.computeIfAbsent(commandId, k -> new Chan());
    }

    /**
     * 从通道中获取数据，支持超时设置
     *
     * @param commandId 通道标识
     * @param timeout   超时时间（毫秒）
     * @return 获取的数据，如果超时返回 null
     */
    public Object get(String commandId, long timeout) {
        Chan chan = CHANNEL.get(commandId);
        if (Objects.isNull(chan)) {
            return null;
        }
        try {
            return chan.future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return null;
        } finally {
            CHANNEL.remove(commandId, chan);
        }
    }

    /**
     * 向通道中放入数据，并唤醒可能正在等待数据的线程
     *
     * @param response 要放入的数据
     */
    public void put(BaseCommandResponse response) {
        String commandId = response.getCommandId();
        if (commandId == null) {
            return;
        }
        Chan chan = CHANNEL.get(commandId);
        if (Objects.isNull(chan)) {
            return;
        }
        chan.future.complete(response);
    }
}
