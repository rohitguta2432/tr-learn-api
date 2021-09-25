package com.workevr.api.service;

import com.amazonaws.services.s3.model.*;
import com.workevr.api.config.AwsConfig;
import com.workevr.api.mapper.ContentDto;
import com.workevr.api.model.Content;
import com.workevr.api.repository.ContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author rohit
 * @Date 23/09/21
 **/

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {

    @Autowired
    private AwsConfig awsClient;

    @Value("${alpha.s3.bucketName}")
    private String bucketName;

    @Value("${alpha.s3.endpointUrl}")
    private String endpointUrl;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public List<String> getVideo() {
        ObjectListing objectListing = awsClient.getS3Client().listObjects(bucketName);
        List<S3ObjectSummary> objects = objectListing.getObjectSummaries();
        objects.forEach(o -> {
            System.out.println(o.getKey());
        });
        return null;
    }


    @Override
    public Content upload(MultipartFile image, MultipartFile video, String title, String header, String content) {
        log.info("uploading image and video : {} ");

        String imgFileName = image.getOriginalFilename();
        String videoFileName = video.getOriginalFilename();

        uploadToS3(image, imgFileName);
        uploadToS3(video, videoFileName);

        Content contents = Content
                .builder()
                .content(content)
                .header(header)
                .title(header)
                .imgUrl(getImgUrl(endpointUrl, bucketName, imgFileName))
                .videoUrl(getVideoUrl(endpointUrl, bucketName, videoFileName))
                .build();

        return contentRepository.save(contents);
    }

    private void uploadToS3(MultipartFile file, String fileName) {
        ObjectMetadata objects = new ObjectMetadata();
        objects.setContentType(file.getContentType());
        objects.setContentLength(file.getSize());
        try {
            awsClient
                    .getS3Client()
                    .putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), objects)
                            .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String getVideoUrl(String endpointUrl, String bucketName, String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(bucketName);
        sb.append(".");
        sb.append(endpointUrl);
        sb.append("/");
        sb.append(fileName);
        return sb.toString();
    }

    private String generateFileName() {
        return new Date().getTime() + "-" + UUID.randomUUID().toString();
    }

    private String getImgUrl(String endpointUrl, String bucketName, String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(bucketName);
        sb.append(".");
        sb.append(endpointUrl);
        sb.append("/");
        sb.append(fileName);
        return sb.toString();
    }
}
