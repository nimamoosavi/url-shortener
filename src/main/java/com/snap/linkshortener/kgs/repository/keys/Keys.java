package com.snap.linkshortener.kgs.repository.keys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "KEYS")
@Setter
@Getter
public class Keys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String key;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
