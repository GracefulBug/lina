package com.dln.lina.utils;

import com.dln.lina.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;

    public void upload(String bucketName, String fileName) {
        try {
            if (!minioClient.bucketExists(bucketName)) {
                minioClient.makeBucket(bucketName);
                String s = minioClient.presignedPutObject(bucketName, fileName);
            }
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        } catch (InvalidExpiresRangeException e) {
            e.printStackTrace();
        }
    }
}
