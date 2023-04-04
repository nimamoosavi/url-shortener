package com.snap.linkshortener.app.web.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class ShorterLink {

    @NotBlank
    @NotNull
    private String originalUrl;
}
