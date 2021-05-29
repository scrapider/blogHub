package com.qzy.dfs;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qzy.exception.BlogException;
import com.qzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadService {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private UploadProperties uploadProperties;

    public String uploadFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (!uploadProperties.getAllowTypes().contains(contentType)) {
            throw new BlogException("文件类型不支持！");
        }
        try {
            // 获取扩展名
            String extName = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
            return uploadProperties.getBaseUrl() + "/" + storePath.getFullPath();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException("上传文件失败！");
        }
    }
}
