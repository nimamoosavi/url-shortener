package com.snap.linkshortener.app.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Document(collection = "Links")
@Setter
@Getter
public class Links {

    @Id
    private long id;

    @Indexed(unique = true)
    private String shortLink;

    private String shortKey;

    private String originalLink;

    private Integer rate;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime;

    private LocalDateTime expireDate;


    @PreUpdate
    void preUpdate() {
        updateTime = LocalDateTime.now();
    }
}
