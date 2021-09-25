package com.workevr.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Author rohit
 * @Date 24/09/21
 **/
@Data
@Builder
@Entity
@Table(name="contents")
public class Content implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID contentId;
    private String videoUrl;
    private String imgUrl;
    private String header;
    private String title;
    private String content;

}

