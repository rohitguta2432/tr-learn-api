package com.workevr.api.controller;

import com.workevr.api.mapper.ContentDto;
import com.workevr.api.model.Content;
import com.workevr.api.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author rohit
 * @Date 23/09/21
 **/

@RestController
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public ResponseEntity<?> getAllVideos() {
        return new ResponseEntity<>(contentService.getVideo(), HttpStatus.OK);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile image,
                                    @RequestParam("video") MultipartFile video,
                                    @RequestParam String title,
                                    @RequestParam String header,
                                    @RequestParam String content) {
        return new ResponseEntity<>(contentService.upload(image, video, title, header, content), HttpStatus.OK);
    }
}
