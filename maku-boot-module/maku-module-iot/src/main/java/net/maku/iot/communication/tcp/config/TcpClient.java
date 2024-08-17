package net.maku.iot.communication.tcp.config;

import cn.hutool.json.JSONUtil;
import net.maku.iot.communication.dto.DevicePropertyDTO;
import net.maku.iot.communication.dto.TcpMsgDTO;
import net.maku.iot.enums.DevicePropertyEnum;
import net.maku.iot.enums.DeviceTopicEnum;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        String serverAddress = ""; // 服务端的地址
        int port = 8888; // 服务端的端口号

        try (Socket socket = new Socket(serverAddress, port);
             OutputStream outputStream = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true)) {

            DevicePropertyDTO dto = new DevicePropertyDTO();
            dto.setDeviceId("123456");
            dto.setPropertyType(DevicePropertyEnum.TEMPERATURE);
            dto.setPayload("60");


            TcpMsgDTO tcpMsgDTO = new TcpMsgDTO();
            tcpMsgDTO.setMsg(dto);
            tcpMsgDTO.setTopic(DeviceTopicEnum.PROPERTY.getTopic());


            writer.println(JSONUtil.toJsonStr(tcpMsgDTO)); // 发送消息到服务端

            System.out.println("Message sent: " + JSONUtil.toJsonStr(tcpMsgDTO));

            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
