package com.study.short_url_service.presentation;

import com.study.short_url_service.domain.ShortUrl;

public class ShortUrlCreationResponseDTO {
    private String originalUrl;
    private String shortUrlKey;

    public ShortUrlCreationResponseDTO(ShortUrl shortUrl) {
        this.originalUrl = shortUrl.getOriginalUrl();
        this.shortUrlKey = shortUrl.getShortUrlKey();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrlKey() {
        return shortUrlKey;
    }
}