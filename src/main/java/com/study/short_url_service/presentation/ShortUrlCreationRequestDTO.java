package com.study.short_url_service.presentation;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ShortUrlCreationRequestDTO {
    @NotNull
    @URL(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String originalUrl;

    public ShortUrlCreationRequestDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}