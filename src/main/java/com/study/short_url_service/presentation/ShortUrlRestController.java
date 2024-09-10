package com.study.short_url_service.presentation;

import com.study.short_url_service.application.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlRestController {
    private ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlRestController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping(value = "/short-url")
    public ResponseEntity<ShortUrlCreationResponseDTO> createShortUrl(
            @Valid @RequestBody ShortUrlCreationRequestDTO shortUrlCreationRequestDTO
    ) {
        ShortUrlCreationResponseDTO shortUrlCreationResponseDTO = shortUrlService.generateShortUrl(shortUrlCreationRequestDTO);
        return ResponseEntity.ok(shortUrlCreationResponseDTO);
    }
}