package com.dln.lina.controller;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @GetMapping("/test")
    public void test(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
//            MinioClient minioClient = new MinioClient("http://123.56.142.9", 9000, "minioadmin", "minioadmin");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(bucketName);
            }

            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject(bucketName,fileName, "C:\\Users\\ThinkPad\\Pictures\\Camera Roll\\qqq.jpg", null);
            System.out.println("/home/user/Photos/asiaphotos.zip is successfully uploaded as asiaphotos.zip to `asiatrip` bucket.");
        } catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
