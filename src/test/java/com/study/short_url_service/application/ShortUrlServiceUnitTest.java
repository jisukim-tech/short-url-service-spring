package com.study.short_url_service.application;

import com.study.short_url_service.domain.LackOfShortUrlKeyException;
import com.study.short_url_service.domain.ShortUrl;
import com.study.short_url_service.domain.ShortUrlRepository;
import com.study.short_url_service.presentation.ShortUrlCreationRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceUnitTest {

    @Mock
    private ShortUrlRepository shortUrlRepository;

    @InjectMocks
    private ShortUrlService shortUrlService;

    @Test
    @DisplayName("단축 URL key가 중복되는 경우 LackOfShortUrlKeyException이 발생해야 한다.")
    void throwLackOfShortUrlKeyExceptionTest() {
        ShortUrlCreationRequestDTO shortUrlCreationRequestDTO = new ShortUrlCreationRequestDTO(null);

        when(shortUrlRepository.find(any())).thenReturn(new ShortUrl(null, null));

        assertThrows(LackOfShortUrlKeyException.class, () -> {
           shortUrlService.generateShortUrl(shortUrlCreationRequestDTO);
        });
    }
}