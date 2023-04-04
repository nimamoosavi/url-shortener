package com.snap.linkshortener.app.service;

import com.snap.linkshortener.app.service.model.Ratio;
import com.snap.linkshortener.app.service.model.ShorterLink;

/**
 * @author nima
 * @version 1.0.1
 * @implNote this class used for generate short link and retrieve the original link with ration
 * @since 1.0.1
 */
public interface LinkService {

    /**
     * @param originalUrl is the original link that we want to generate a shorter link
     * @return {@link ShorterLink} an object with th short link reference
     * @apiNote this methode use for generation a short link
     */
    ShorterLink generateShortLink(String originalUrl);

    /**
     * @return the original link
     * @apiNote this method use for fetching the original object and increase the ratio
     */
    String getOriginalLink(String shortLink);

    /**
     * @return {@link Ratio} is an object with Ratio detail
     * @apiNote this method use for fetching the ratio count
     */
    Ratio getShortLinkratio(String shortLink);


}
