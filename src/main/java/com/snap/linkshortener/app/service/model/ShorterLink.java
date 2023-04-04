package com.snap.linkshortener.app.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class ShorterLink {
    private final String link;

    private final LocalDateTime expireDate;
}
