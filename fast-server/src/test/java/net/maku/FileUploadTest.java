package net.maku;

import cn.hutool.core.io.IoUtil;
import net.maku.api.module.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;

@SpringBootTest
public class FileUploadTest {
    @Autowired
    private StorageService storageService;

    @Test
    public void uploadTest() throws Exception{
        byte[] data = IoUtil.readBytes(Files.newInputStream(new File("D://upload//1.png").toPath()));
        String url = storageService.uploadSuffix(data, "png");
        System.out.println(url);
    }

}
