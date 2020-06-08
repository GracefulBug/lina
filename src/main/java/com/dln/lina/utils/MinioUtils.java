package com.dln.lina.utils;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;

    public void upload(String bucketName, String objectName, InputStream inputStream) {
        try {
            if (!minioClient.bucketExists(bucketName)) {
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName, objectName, inputStream, new PutObjectOptions(-1, PutObjectOptions.MIN_MULTIPART_SIZE));
        } catch (Exception e) {
            log.error("minio 上传异常", e);
        }
    }

    public void download(String bucketName, String objectName) {
        try {
            InputStream object = minioClient.getObject(bucketName, objectName);
        } catch (Exception e) {
            log.error("minio 下载异常", e);
        }
    }

}
