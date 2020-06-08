package com.dln.lina.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author zhengdeqi
 * @since 2020/06/08 11:07:08
 */
@Slf4j
@Configuration
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private Integer port;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(endpoint, port, accessKey, secretKey);
        } catch (Exception e) {
            log.error("构造 -minioClient- 异常", e);
        }
        return minioClient;
    }
}
