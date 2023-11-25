package com.rup;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootTest
class S3Test {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void saveFile() throws IOException {
        System.out.println("bucket = " + bucket);
        // 파일 내용을 단순 문자열로 설정
        String content = "테스트 파일";
        byte[] contentBytes = content.getBytes();

        // MockMultipartFile 객체를 생성
        // 여기서는 파일 이름, 타입 등을 하드코딩으로 설정
        MultipartFile multipartFile = new MockMultipartFile(
                "testFile.txt", // 파일 이름
                "testFile.txt", // 원래 파일 이름
                "text/plain", // 컨텐츠 타입
                contentBytes // 파일 데이터
        );
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        String source = amazonS3.getUrl(bucket, originalFilename).toString();
        System.out.println("source = " + source);
    }
}
