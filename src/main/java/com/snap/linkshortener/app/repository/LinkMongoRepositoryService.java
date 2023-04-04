package com.snap.linkshortener.app.repository;

import com.snap.linkshortener.app.repository.entity.Links;
import com.snap.linkshortener.common.repository.connector.mongo.repository.MongoRepositoryServiceImpl;
import com.snap.linkshortener.common.repository.model.Pages;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinkMongoRepositoryService extends MongoRepositoryServiceImpl<Links, Long> {
    @Override
    public Pages<List<Links>> findAll(int page, int pageSize) {
        return null;
    }
}
