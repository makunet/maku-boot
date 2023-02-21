package net.maku.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页 欢迎信息
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "您好，项目已启动，祝您使用愉快！";
    }
}
