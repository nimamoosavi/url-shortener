package com.snap.linkshortener.app.service.impl;

import com.snap.linkshortener.app.repository.LinkRepository;
import com.snap.linkshortener.app.repository.entity.Links;
import com.snap.linkshortener.app.service.LinkService;
import com.snap.linkshortener.app.service.model.Ratio;
import com.snap.linkshortener.app.service.model.ShorterLink;
import com.snap.linkshortener.kgs.service.KeyGeneration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;

import static com.snap.linkshortener.app.config.Configuration.CACHE_MANAGER_NAME;

@RequiredArgsConstructor
public class SimpleLink implements LinkService {

    private final LinkRepository linkRepository;

    private final KeyGeneration simpleKeyGeneration;

    @Value("${short-link.base-url:https://snap.tr/}")
    private String baseUrl;

    @Override
    public final ShorterLink generateShortLink(String originalUrl) {
        var shortKey = simpleKeyGeneration.getKey();

        var shortUrl = baseUrl + shortKey;

        var model = new LinkModel();
        model.setOriginalLink(originalUrl);
        model.setShortLink(shortUrl);
        model.setExpirationTime(LocalDateTime.now().plusMonths(1));
        store(model);

        storeUsedKey(shortKey);

        return new ShorterLink(shortKey, model.getExpirationTime());
    }

    @Override
    public final String getOriginalLink(String shortLink) {

        var link = findOriginalLink(shortLink);

        if (link == null)
            return null;

        increaseRate(link);

        return link.getOriginalLink();
    }


    @Override
    public final Ratio getShortLinkratio(String shortLink) {
        var link = linkRepository.findByShortLink(shortLink);
        return link.map(links -> new Ratio(links.getOriginalLink()
                        , links.getShortLink()
                        , links.getRate()))
                .orElse(null);
    }


    /**
     * @apiNote this method for cache data for better performance in redis, and you can find the configuration
     * in {@link com.snap.linkshortener.app.config.Configuration}
     */
    @Cacheable(cacheNames = CACHE_MANAGER_NAME, key = "#shortLink", unless = "#result == null")
    public Links findOriginalLink(String shortLink) {
        var link = linkRepository.findByShortLink(shortLink);
        return link.orElse(null);
    }


    /**
     * @apiNote this method called asynchronous, and you can find the config in
     * {@link com.snap.linkshortener.app.config.Configuration}
     */
    @Async("rateThreadPoolTaskExecutor")
    public void increaseRate(Links link) {
        var newRate = link.getRate() + 1;
        link.setRate(newRate);
        linkRepository.update(link);
    }

    /**
     * @apiNote this method called asynchronous and store the used key in key Generation Service
     * {@link com.snap.linkshortener.app.config.Configuration}
     */
    @Async("rateThreadPoolTaskExecutor")
    public void storeUsedKey(String key) {
        simpleKeyGeneration.saveUsedKey(key);
    }

    private void store(LinkModel link) {
        var entity = new Links();
        entity.setOriginalLink(link.getOriginalLink());
        entity.setShortLink(link.shortLink);
        entity.setExpireDate(link.getExpirationTime());
        linkRepository.save(entity);
    }


    @Getter
    @Setter
    private static class LinkModel {

        private String originalLink;

        private String shortLink;

        private LocalDateTime expirationTime;

    }
}
