package com.snap.linkshortener.common.repository.connector.mongo.repository;


import com.snap.linkshortener.common.repository.GeneralRepository;

import java.io.Serializable;

public interface MongoRepositoryService<T, I extends Serializable> extends GeneralRepository<T, I> {
     void updateField(I id, T t);
}
