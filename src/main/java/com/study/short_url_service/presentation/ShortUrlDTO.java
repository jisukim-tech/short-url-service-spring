package com.study.short_url_service.presentation;

import com.study.short_url_service.domain.ShortUrl;

public class ShortUrlDTO {
    private String originalUrl;
    private String shortUrlKey;
    private Long redirectCount;

    public ShortUrlDTO(ShortUrl shortUrl) {
        this.originalUrl = shortUrl.getOriginalUrl();
        this.shortUrlKey = shortUrl.getShortUrlKey();
        this.redirectCount = shortUrl.getRedirectCount();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrlKey() {
        return shortUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }
}
