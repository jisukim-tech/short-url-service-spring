package com.study.short_url_service.domain;

import java.util.Random;

public class ShortUrl {
    private String originalUrl;
    private String shortUrlKey;
    private Long redirectCount;

    public ShortUrl(String originalUrl, String shortUrlKey) {
        this.originalUrl = originalUrl;
        this.shortUrlKey = shortUrlKey;
        this.redirectCount = 0L;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrlKey() {
        return shortUrlKey;
    }

    public static String generateShortUrlKey() {
        final int KEY_LENGTH = 8;
        Random random = new Random();
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        StringBuilder shortUrlKey = new StringBuilder();

        for (int count = 0; count < KEY_LENGTH; count++) {
            int base56CharactersIndex = random.nextInt(0, base56Characters.length());
            char base56Character = base56Characters.charAt(base56CharactersIndex);
            shortUrlKey.append(base56Character);
        }

        return shortUrlKey.toString();
    }

    public void increaseRedirectCount() {
        this.redirectCount += 1;
    }
}