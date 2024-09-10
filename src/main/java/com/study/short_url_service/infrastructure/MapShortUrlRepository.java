package com.study.short_url_service.infrastructure;

import com.study.short_url_service.domain.ShortUrl;
import com.study.short_url_service.domain.ShortUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapShortUrlRepository implements ShortUrlRepository {
    Map<String, ShortUrl> shortUrls = new ConcurrentHashMap<>();

    @Override
    public void save(ShortUrl shortUrl) {
        shortUrls.put(shortUrl.getOriginalUrl(), shortUrl);
    }
}