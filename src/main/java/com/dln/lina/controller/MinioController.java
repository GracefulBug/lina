package com.dln.lina.controller;

import com.dln.lina.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;

@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/test")
    public void test(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName, @RequestParam("path") String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            minioUtils.upload(bucketName, fileName, inputStream);
        } catch (Exception e) {
          log.error("读取文件异常", e);
        }
    }
}
