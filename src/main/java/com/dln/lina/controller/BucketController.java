package com.dln.lina.controller;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private MinioClient minioClient;

    @GetMapping("/test-bucket")
    public void test() throws IOException, InvalidKeyException, InvalidResponseException, RegionConflictException, InsufficientDataException, NoSuchAlgorithmException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException, BucketPolicyTooLargeException {
        // 判断一个桶是否存在
        if (!minioClient.bucketExists("snh")) {
            // 创建一个桶
            minioClient.makeBucket("snh");
        } else {
            // 删除一个桶
            // 注意: - removeBucket不会删除存储桶里的对象，你需要通过removeObject API来删除它们。
//            minioClient.removeBucket("snh");
            Iterable<Result<Item>> zdq = minioClient.listObjects("zdq");
            zdq.iterator().forEachRemaining(itemResult -> {
                try {
                    String name = itemResult.get().objectName();
                    System.out.println(name);
                } catch (Exception e) {
                    log.error("迭代对象异常", e);
                }
            });
        }
        List<Bucket> buckets = minioClient.listBuckets();
        Set<String> collect = buckets.stream().map(Bucket::name).collect(Collectors.toSet());
        System.out.println("bucket names " + collect);

        String zdq = minioClient.getBucketPolicy("zdq");
        System.out.println(zdq);
    }
}
