package com.snap.linkshortener.app.web;

import com.snap.linkshortener.app.service.LinkService;
import com.snap.linkshortener.app.service.model.Ratio;
import com.snap.linkshortener.app.web.model.ShorterLink;
import com.snap.linkshortener.app.web.model.ShorterLinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.snap.linkshortener.app.config.UrlMapping.*;

/**
 * @author nima
 * @version 1.0.1
 * @implNote this class used for generate short link and retrieve the original link with ration
 * @since 1.0.1
 */
@RestController
@RequiredArgsConstructor
public class ShortenerLinkController {

    private final LinkService simpleLink;


    /**
     * @param request is an object for creating a short link {@link ShorterLink}
     * @return an object from unique short link and expiration time
     * @apiNote this api used for generation a new short link with expiration time
     */
    @PostMapping(CREATE_SHORTER_LINK)
    public ResponseEntity<ShorterLinkDto> createShorterLink(@Valid @RequestBody ShorterLink request) {
        var shorterLink = simpleLink.generateShortLink(request.getOriginalUrl());

        var response = new ShorterLinkDto(shorterLink.getLink(), shorterLink.getExpireDate());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * @param shortLink is the short reference that was created before
     * @return {@link String} the original link
     * @apiNote this api use for get the original link that was created before and increased the ration object per fetch
     */
    @GetMapping(FETCH_ORIGINAL_LINK)
    public ResponseEntity<String> getOriginalLink(@PathVariable String shortLink) {
        var originalLink = simpleLink.getOriginalLink(shortLink);

        return new ResponseEntity<>(originalLink, HttpStatus.FOUND);
    }


    /**
     * @param shortLink is the short reference that was created before
     * @return {@link Ratio}{@link String} is an object that show the ration detail,
     * @apiNote this method use for getting the ratio count
     */
    @GetMapping(FETCH_RATIO)
    public ResponseEntity<Ratio> getShortLinRatio(@PathVariable String shortLink) {
        var ratio = simpleLink.getShortLinkratio(shortLink);

        return new ResponseEntity<>(ratio, HttpStatus.FOUND);

    }


}
