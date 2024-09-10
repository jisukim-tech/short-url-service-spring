package com.study.short_url_service.presentation;

import com.study.short_url_service.application.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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

    @GetMapping(value = "/{short-url-key}")
    public ResponseEntity<?> redirectShortUrl(
            @PathVariable("short-url-key") String shortUrlKey
    ) throws URISyntaxException {
        String originalUrl = shortUrlService.getOriginalUrl(shortUrlKey);
        URI redirectUri = new URI(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping(value = "/short-url/{short-url-key}")
    public ResponseEntity<ShortUrlDTO> getShortUrlInformation(
            @PathVariable("short-url-key") String shortUrlKey
    ) {
        ShortUrlDTO shortUrlDTO = shortUrlService.fetchShortUrlInformation(shortUrlKey);
        return ResponseEntity.ok(shortUrlDTO);
    }
}