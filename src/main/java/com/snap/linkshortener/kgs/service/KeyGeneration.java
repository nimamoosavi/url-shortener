package com.snap.linkshortener.kgs.service;

/**
 * @author nima
 * @version 1.0.1
 * @implNote this class used for generate short key
 * @since 1.0.1
 */
public interface KeyGeneration {

    /**
     * @return a unique short key in default size
     * @apiNote this methode used for get and store some short keys in memory and return it fast
     */
    String getKey();
}
