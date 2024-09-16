package com.study.short_url_service.application;

import com.study.short_url_service.domain.ShortUrlNotFoundException;
import com.study.short_url_service.presentation.ShortUrlCreationRequestDTO;
import com.study.short_url_service.presentation.ShortUrlCreationResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ShortUrlServiceTest {

    @Autowired
    private ShortUrlService shortUrlService;

    @Test
    @DisplayName("URL을 단축한 후 단축된 URL key로 기존 URL을 조회할 수 있어야 한다.")
    void shortUrlAddAndFindByShortUrlKeyTest() {
        String originalUrl = "https://www.spring.com";
        ShortUrlCreationRequestDTO shortUrlCreationRequestDTO = new ShortUrlCreationRequestDTO(originalUrl);

        ShortUrlCreationResponseDTO shortUrlCreationResponseDTO = shortUrlService.generateShortUrl(shortUrlCreationRequestDTO);
        String shortUrlKey = shortUrlCreationResponseDTO.getShortUrlKey();

        String fetchedOriginalUrl = shortUrlService.getOriginalUrl(shortUrlKey);

        assertTrue(originalUrl.equals(fetchedOriginalUrl));
    }

    @Test
    @DisplayName("존재하지 않는 단축된 URL key로 기존 URL을 조회하는 경우 ShortUrlNotFoundException이 발생해야 한다.")
    void getOriginalUrlByNotExistShortUrlKeyTest() {
        String fakeShortUrl = "fakeShortUrl";

        assertThrows(ShortUrlNotFoundException.class, () -> {
            shortUrlService.getOriginalUrl(fakeShortUrl);
        });
    }

    @Test
    @DisplayName("존재하지 않는 단축된 URL key로 단축 URL 정보를 조회하는 경우 ShortUrlNotFoundException이 발생해야 한다.")
    void fetchShortUrlInformationByNotExistShortUrlKeyTest() {
        String fakeShortUrl = "fakeShortUrl";

        assertThrows(ShortUrlNotFoundException.class, () -> {
            shortUrlService.fetchShortUrlInformation(fakeShortUrl);
        });
    }
}
