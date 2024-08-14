package net.maku.iot.communication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.maku.iot.communication.dto.DeviceCommandResponseDTO;
import net.maku.iot.entity.IotDeviceEntity;
import net.maku.iot.enums.DeviceCommandEnum;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author LSF
 * @Date 2024/8/9 14:21
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TCPService implements BaseCommunication {

    @Override
    public String asyncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload) {
//        nettyClientConfig.sendMessage("asdddddddddddddddddd");
        return "";
    }

    @Override
    public DeviceCommandResponseDTO syncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload) {
        return null;
    }

    @Override
    public DeviceCommandResponseDTO syncSendCommandDebug(IotDeviceEntity device, DeviceCommandEnum command, String payload) {
        return null;
    }

    @Override
    public void simulateDeviceReportAttributeData(IotDeviceEntity device,  String payload) {
        return;
    }

    @Override
    public void simulateDeviceCommandResponseAttributeData(IotDeviceEntity device, String payload) {
        return;
    }
}
