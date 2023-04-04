package com.snap.linkshortener.common.repository.connector.mongo.mapper;

import org.bson.Document;

public interface ValueGenerator<T>{
    Object getValue(T t ,Document doc);
}
