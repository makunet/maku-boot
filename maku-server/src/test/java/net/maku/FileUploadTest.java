package net.maku;

import cn.hutool.core.io.IoUtil;
import net.maku.storage.service.StorageService;
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
    public void uploadTest() throws Exception {
        File file = new File("D://upload//1.png");
        byte[] data = IoUtil.readBytes(Files.newInputStream(file.toPath()));

        String path = storageService.getPath(file.getName());
        String url = storageService.upload(data, path);
        System.out.println(url);
    }

}
