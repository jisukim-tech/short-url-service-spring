package com.study.short_url_service.presentation;

import com.study.short_url_service.domain.LackOfShortUrlKeyException;
import com.study.short_url_service.domain.ShortUrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<String> handleShortUrlNotFoundException(
            ShortUrlNotFoundException ex
    ) {
        return new ResponseEntity<>("단축 URL을 찾지 못했습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfShortUrlKeyException.class)
    public ResponseEntity<String> handleLackOfShortUrlKeyException(
            LackOfShortUrlKeyException ex
    ) {
        return new ResponseEntity<>("단축 URL 자원이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
