package com.workevr.api.service;

import com.workevr.api.mapper.ContentDto;
import com.workevr.api.model.Content;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author rohit
 * @Date 23/09/21
 **/

public interface ContentService {
    public List<String> getVideo();

    public Content upload(MultipartFile image, MultipartFile video, String title, String header, String content);
}
