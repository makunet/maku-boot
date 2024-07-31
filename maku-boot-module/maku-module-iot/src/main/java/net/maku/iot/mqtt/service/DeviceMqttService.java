package net.maku.iot.mqtt.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.exception.ServerException;
import net.maku.iot.dto.DeviceClientDTO;
import net.maku.iot.entity.IotDeviceEntity;
import net.maku.iot.enums.DeviceCommandEnum;
import net.maku.iot.enums.DeviceServiceEnum;
import net.maku.iot.enums.DeviceTopicEnum;
import net.maku.iot.mqtt.MqttGateway;
import net.maku.iot.mqtt.dto.Chan;
import net.maku.iot.mqtt.dto.DeviceCommandDTO;
import net.maku.iot.mqtt.dto.DeviceCommandResponseDTO;
import net.maku.iot.service.IotDeviceServiceLogService;
import net.maku.iot.utils.MqttUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * 设备命令发送服务
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceMqttService {
    private final MqttUtils mqttUtils;
    private final MqttGateway mqttGateway;
    private final IotDeviceServiceLogService iotDeviceEventLogService;

    /**
     * 命令等待exchanger缓存，key: command id
     */
    private final ConcurrentMap<String, Exchanger<Object>> commandExchangers = new ConcurrentHashMap<>();

    /**
     * 异步发送命令，返回命令id
     *
     * @param device
     * @param command
     * @param payload
     * @return
     */
    public String asyncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload) {
        return asyncSendCommand(device, command, payload, Boolean.FALSE);
    }


    /**
     * 异步发送命令，返回命令id
     *
     * @param device
     * @param command
     * @param payload
     * @param retained
     * @return
     */
    public String asyncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload, boolean retained) {
        // 构建命令对象
        String commandId = StrUtil.replaceChars(UUID.randomUUID().toString(), "-", "");
        DeviceCommandDTO commandDTO = new DeviceCommandDTO();
        commandDTO.setCommand(command);
        commandDTO.setId(commandId);
        commandDTO.setPayload(payload);
        String commandTopic = DeviceTopicEnum.COMMAND.buildTopic(DeviceClientDTO.from(device));

        // 发送命令到设备命令主题
        try {
            mqttGateway.sendToMqtt(commandTopic, retained, JSONUtil.toJsonStr(commandDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServerException(StrUtil.format("发送'{}'命令:{} 到设备:{}-{}, Topic:{} 失败",
                    command.getTitle(), commandId, device.getCode(), device.getName(), commandTopic));
        }
        log.info("发送'{}'命令:{} 到设备:{}-{}, Topic:{} 成功", command.getTitle(), commandId, device.getCode(), device.getName(), commandTopic);
        iotDeviceEventLogService.createAndSaveDeviceServiceLog(device.getId(), device.getTenantId(), command, commandId, payload);
        return commandId;
    }


    /**
     * 同步发送命令并返回响应结果
     *
     * @param device
     * @param command
     * @param payload
     * @return
     */
    public DeviceCommandResponseDTO syncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload) {
        return syncSendCommand(device, command, payload, Boolean.FALSE);
    }

    /**
     * 发送命令并返回响应结果
     *
     * @param device
     * @param command
     * @param payload
     * @param retained
     * @return
     */
    public DeviceCommandResponseDTO syncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload, boolean retained) {
        // 构建并发送命令
        String commandId = asyncSendCommand(device, command, payload, retained);
        // 等待返回结果 超时3秒(可控)
        Object receiver = Chan.getInstance(commandId, true).get(commandId, 3 * 1000L);
        if (receiver == null) {
            log.error("Failed to receive the message. {}", device.getName());
            throw new ServerException(StrUtil.format("{}设备没有回复", device.getName()));
        }
        return (DeviceCommandResponseDTO) receiver;
    }


    /**
     * 发送命令并返回响应结果，模拟设备响应
     *
     * @param device
     * @param command
     * @param payload
     * @return
     */
    public DeviceCommandResponseDTO syncSendCommandDebug(IotDeviceEntity device, DeviceCommandEnum command, String payload) {
        // 构建并发送命令
        String commandId = asyncSendCommand(device, command, payload);

        // 2秒后模拟设备响应
        new Thread(() -> {
            try {
                //模拟设备正常响应
                Thread.sleep(2000);
                //模拟设备超时响应
                //Thread.sleep(15000);
                DeviceCommandResponseDTO simulateResponseDto = new DeviceCommandResponseDTO();
                simulateResponseDto.setCommandId(commandId);
                simulateResponseDto.setResponsePayload(command.getTitle() + ",设备执行成功！");
                simulateResponseDto.setCommand(command);
                simulateDeviceCommandResponseAttributeData(device, JSONUtil.toJsonStr(simulateResponseDto));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("模拟设备响应线程被中断", e);
            }
        }).start();

        // 等待设备响应
        return waitCommandResponse(command, commandId);
    }


    /**
     * 订阅设备命令响应主题并等待获取返回结果
     *
     * @param command
     * @param commandId
     * @return
     */
    private DeviceCommandResponseDTO waitCommandResponse(DeviceCommandEnum command, String commandId) {
        // 创建命令响应等待exchanger
        Exchanger<Object> commandExchanger = new Exchanger<>();
        commandExchangers.put(commandId, commandExchanger);

        try {
            Object result = commandExchanger.exchange("", 10, TimeUnit.SECONDS);
            return (DeviceCommandResponseDTO) result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerException(StrUtil.format("{} <{}>,{} 命令中断",
                    DeviceServiceEnum.COMMAND_ID.getValue(), commandId, command.getTitle()), e);
        } catch (TimeoutException e) {
            throw new ServerException(StrUtil.format("{} <{}>,{} 命令超时",
                    DeviceServiceEnum.COMMAND_ID.getValue(), commandId, command.getTitle()), e);
        } finally {
            // 移除命令响应等待exchanger
            commandExchangers.remove(commandId);
        }
    }

    /**
     * 设备命令响应处理
     *
     * @param topic
     * @param commandResponse
     */
    public void commandReplied(String topic, DeviceCommandResponseDTO commandResponse) {
        Chan chan = Chan.getInstance(commandResponse.getCommandId(), false);
        chan.put(commandResponse);
    }

    public void simulateDeviceReportAttributeData(IotDeviceEntity device, String payload) {
        // 封装 设备属性上报的 topic
        String commandTopic = DeviceTopicEnum.PROPERTY.buildTopic(DeviceClientDTO.from(device));
        try {
            mqttGateway.sendToMqtt(commandTopic, payload);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServerException(StrUtil.format("模拟设备:{}-{},模拟属性上报失败！ Topic:{} ",
                    device.getCode(), device.getName(), commandTopic));
        }
    }

    public void simulateDeviceCommandResponseAttributeData(IotDeviceEntity device, String payload) {
        // 封装 设备命令执行结果的 topic
        String commandTopic = DeviceTopicEnum.COMMAND_RESPONSE.buildTopic(DeviceClientDTO.from(device));
        try {
            mqttGateway.sendToMqtt(commandTopic, payload);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServerException(StrUtil.format("模拟设备:{}-{},模拟发送命令执行结果失败！ Topic:{} ",
                    device.getCode(), device.getName(), commandTopic));
        }
    }

}
