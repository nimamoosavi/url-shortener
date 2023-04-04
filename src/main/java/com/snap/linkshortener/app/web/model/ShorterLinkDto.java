package com.snap.linkshortener.app.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@RequiredArgsConstructor
@JsonAutoDetect(fieldVisibility = ANY)
public class ShorterLinkDto {

    private final String shorterUrl;

    private final LocalDateTime expirationTime;
}
