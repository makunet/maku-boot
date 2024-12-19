package net.maku.websocket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.Result;
import net.maku.framework.security.user.SecurityUser;
import net.maku.framework.security.user.UserDetail;
import net.maku.websocket.message.JsonDataMessage;
import net.maku.websocket.sender.WebSocketMessageSender;
import net.maku.websocket.vo.MessageVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 测试 发送消息
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Tag(name = "WebSocket，发送消息")
@AllArgsConstructor
@RestController
@RequestMapping("ws/message")
public class SendMessageController {
    private final WebSocketMessageSender webSocketMessageSender;

    @PostMapping("send")
    @Operation(summary = "发送消息")
    public Result<String> send(Long userId, String content) {
        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();

        // 封装消息内容
        MessageVO messageVO = new MessageVO();
        messageVO.setName(user.getRealName());
        messageVO.setAvatar(user.getAvatar());
        messageVO.setContent(content);
        messageVO.setSendTime(LocalDateTime.now());

        JsonDataMessage<MessageVO> message = new JsonDataMessage<>();
        message.setData(messageVO);

        // 发送消息
        webSocketMessageSender.send(userId, message);

        return Result.ok();
    }
}
