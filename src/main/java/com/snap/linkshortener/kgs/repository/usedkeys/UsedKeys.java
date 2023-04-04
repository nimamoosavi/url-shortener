package com.snap.linkshortener.kgs.repository.usedkeys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "USED_KEYS")
@Setter
@Getter
public final class UsedKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String keys;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
