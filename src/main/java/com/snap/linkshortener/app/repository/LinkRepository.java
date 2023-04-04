package com.snap.linkshortener.app.repository;

import com.snap.linkshortener.app.repository.entity.Links;
import com.snap.linkshortener.common.repository.connector.mongo.repository.MongoRepositoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LinkRepository extends MongoRepositoryService<Links, Long> {

    Optional<Links> findByShortLink(String shortLink);
}
