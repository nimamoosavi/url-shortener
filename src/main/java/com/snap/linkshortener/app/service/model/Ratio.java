package com.snap.linkshortener.app.service.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class Ratio {

    private final String originalUrl;

    private final String shortLink;

    private final Integer rate;
}
