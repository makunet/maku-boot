package net.maku.iot.communication.service;

import lombok.AllArgsConstructor;
import net.maku.framework.common.exception.ServerException;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author LSF
 * @Date 2024/8/9 14:53
 */
@Service
@AllArgsConstructor
public class CommunicationServiceFactory {

    private final MQTTService mqttService;
    private final TCPService tcpService;

    public BaseCommunication getProtocol(String protocolType) {
        if (protocolType == null) {
            new ServerException("协议不存在！");
        }
        switch (protocolType) {
            case "MQTT":
                return mqttService;
            case "TCP":
                return tcpService;
//            case "Modbus":
//                return tcpService;
            default:
                return null;
        }
    }


}
