package com.study.short_url_service.application;

import com.study.short_url_service.domain.ShortUrl;
import com.study.short_url_service.domain.ShortUrlRepository;
import com.study.short_url_service.presentation.ShortUrlCreationRequestDTO;
import com.study.short_url_service.presentation.ShortUrlCreationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrlCreationResponseDTO generateShortUrl(
            ShortUrlCreationRequestDTO shortUrlCreationRequestDTO
    ) {
        String originalUrl = shortUrlCreationRequestDTO.getOriginalUrl();
        String shortUrlKey = ShortUrl.generateShortUrlKey();
        ShortUrl shortUrl = new ShortUrl(originalUrl, shortUrlKey);

        shortUrlRepository.save(shortUrl);

        ShortUrlCreationResponseDTO shortUrlCreationResponseDTO = new ShortUrlCreationResponseDTO(shortUrl);

        return shortUrlCreationResponseDTO;
    }

    public String getOriginalUrl(String shortUrlKey) {
        ShortUrl shortUrl = shortUrlRepository.find(shortUrlKey);
        String originalUrl = shortUrl.getOriginalUrl();

        shortUrl.increaseRedirectCount();

        shortUrlRepository.save(shortUrl);

        return originalUrl;
    }
}