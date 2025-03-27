package cn.iocoder.yudao.module.infra.api.file;

import cn.iocoder.yudao.module.infra.service.file.FileService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 文件 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FileApiImpl implements FileApi {

    @Resource
    private FileService fileService;

    @Override
    public String createFile(String name, String path, byte[] content) {
        return fileService.createFile(name, path, content);
    }

    @Override
    public byte[] getFileContent(Long configId, String path) throws Exception {
        // 读取内容
        byte[] content = fileService.getFileContent(configId, path);
        if (content == null) {
            return null;
        }
        return content;
    }
}
