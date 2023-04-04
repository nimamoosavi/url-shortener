package com.snap.linkshortener.common.repository.connector.mongo.repository;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Mongo Repo is integrator of {@link MongoRepository} and {@link com.snap.linkshortener.common.repository.GeneralRepository}
 *
 * @param <T> is the entity class that you must Be Extended to BaseEntity
 * @param <I> is the type of database Identity class such as Long,String, ...
 * @version 1.0.0
 */

public abstract class MongoRepositoryServiceImpl<T, I extends Serializable> implements MongoRepositoryService<T, I> {
    @Autowired
    private MongoRepository<T, I> repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private Class<T> entityClass;

    @PostConstruct
    public void init() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * this method replace current document on old document
     *
     * @param t the Entity View Model that you must Add To Data Base
     * @return t object with filled id
     * Note: t before save t.id will set id
     */
    @Override
    public T update(T t) {
        return repository.save(t);
    }

    /**
     * this method update not null field of document
     *
     * @param id
     * @param t
     */
    @Override
    public void updateField(I id, T t) {
        Document doc = new Document();
        mongoTemplate.getConverter().write(t, doc);
        mongoTemplate.getCollection(mongoTemplate.getCollectionName(entityClass)).updateOne(Filters.eq("_id", new ObjectId(id.toString())), new Document("$set", doc));
    }

    /**
     * @param t the Entity View Model that you must Add To Data Base
     * @return Note: before t save t.id will set null
     */

    @Override
    public T save(T t) {
        return repository.insert(t);
    }

    /**
     * @param ts List of T for create documents
     */
    @Override
    public void saveAll(List<T> ts) {
        repository.insert(ts);
    }


    @Override
    public Optional<T> findById(I id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(I id) {
        repository.deleteById(id);
    }


}
