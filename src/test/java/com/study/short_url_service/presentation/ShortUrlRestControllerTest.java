package com.study.short_url_service.presentation;

import com.study.short_url_service.application.ShortUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShortUrlRestController.class)
public class ShortUrlRestControllerTest {

    @MockBean
    private ShortUrlService shortUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("단축 URL을 이용하여 기존 URL로 리다이렉트할 수 있어야 한다.")
    void redirectTest() throws Exception {
        String originalUrl = "https://www.google.com";

        when(shortUrlService.getOriginalUrl(any())).thenReturn(originalUrl);

        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", originalUrl));
    }
}
