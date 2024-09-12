package com.study.short_url_service.application;

import com.study.short_url_service.domain.LackOfShortUrlKeyException;
import com.study.short_url_service.domain.ShortUrl;
import com.study.short_url_service.domain.ShortUrlNotFoundException;
import com.study.short_url_service.domain.ShortUrlRepository;
import com.study.short_url_service.presentation.ShortUrlCreationRequestDTO;
import com.study.short_url_service.presentation.ShortUrlCreationResponseDTO;
import com.study.short_url_service.presentation.ShortUrlDTO;
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
        String shortUrlKey = generateUniqueShortUrlKey();
        ShortUrl shortUrl = new ShortUrl(originalUrl, shortUrlKey);

        shortUrlRepository.save(shortUrl);

        ShortUrlCreationResponseDTO shortUrlCreationResponseDTO = new ShortUrlCreationResponseDTO(shortUrl);

        return shortUrlCreationResponseDTO;
    }

    private String generateUniqueShortUrlKey() {
        final int MAX_RETRY_COUNT = 5;

        for (int count = 0; count < MAX_RETRY_COUNT; count++) {
            String shortUrlKey = ShortUrl.generateShortUrlKey();
            ShortUrl shortUrl = shortUrlRepository.find(shortUrlKey);

            if (null == shortUrl) {
                return shortUrlKey;
            }
        }

        throw new LackOfShortUrlKeyException();
    }

    public String getOriginalUrl(String shortUrlKey) {
        ShortUrl shortUrl = shortUrlRepository.find(shortUrlKey);

        if (null == shortUrl) {
            throw new ShortUrlNotFoundException();
        }

        String originalUrl = shortUrl.getOriginalUrl();

        shortUrl.increaseRedirectCount();

        shortUrlRepository.save(shortUrl);

        return originalUrl;
    }

    public ShortUrlDTO fetchShortUrlInformation(String shortUrlKey) {
        ShortUrl shortUrl = shortUrlRepository.find(shortUrlKey);

        if (null == shortUrl) {
            throw new ShortUrlNotFoundException();
        }

        ShortUrlDTO shortUrlDTO = new ShortUrlDTO(shortUrl);

        return shortUrlDTO;
    }
}