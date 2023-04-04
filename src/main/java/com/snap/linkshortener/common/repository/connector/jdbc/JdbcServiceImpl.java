package com.snap.linkshortener.common.repository.connector.jdbc;

import com.snap.linkshortener.common.repository.model.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @param <T> is the BaseObject class
 * @param <I> is the type of database Identity class such as Long,String, ...
 * @apiNote this class you must extend your service and create a bean of it and is the implementation of General Repository in Crud Library
 */
public abstract class JdbcServiceImpl<T, I extends Serializable> implements JdbcService<T, I> {

    @Autowired
    JdbcRepository<T, I> jdbcRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public T save(T t) {
        return jdbcRepository.save(t);
    }

    @Override
    public T update(T t) {
        return jdbcRepository.save(t);
    }

    @Override
    public List<T> saveAll(List<T> tList) {
        return jdbcRepository.saveAll(tList);
    }

    @Override
    public Optional<T> findById(I id) {
        return jdbcRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return jdbcRepository.findAll();
    }


    @Override
    public Pages<List<T>> findAll(int page, int pageSize) {
        Pageable pageable = pagination(page, pageSize);
        Page<T> all = jdbcRepository.findAll(pageable);
        return convertPageToPageDTO(all);
    }


    @Override
    public long count() {
        return jdbcRepository.count();
    }

    @Override
    public void deleteById(I id) {
        jdbcRepository.deleteById(id);
    }

    public List<Map<String, Object>> queryForList(String sql, MapSqlParameterSource sqlParameterSource) {
        return namedParameterJdbcTemplate.queryForList(sql, sqlParameterSource);
    }

    public List<T> queryForList(String sql, MapSqlParameterSource sqlParameterSource, Class<T> tClass) {
        return namedParameterJdbcTemplate.queryForList(sql, sqlParameterSource, tClass);
    }

    public List<T> queryForList(String sql, int queryTimeOut, Class<T> tClass) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        jdbcTemplate.setQueryTimeout(queryTimeOut);
        return jdbcTemplate.queryForList(sql, tClass);
    }

    public List<Map<String, Object>> queryForList(String sql, int queryTimeOut) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        jdbcTemplate.setQueryTimeout(queryTimeOut);
        return jdbcTemplate.queryForList(sql);
    }

    public T query(String sql, MapSqlParameterSource sqlParameterSource, Class<T> tClass) {
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, tClass);
    }

    public T query(String sql, int queryTimeOut, Class<T> tClass) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        jdbcTemplate.setQueryTimeout(queryTimeOut);
        return jdbcTemplate.queryForObject(sql, tClass);
    }

    public Map<String, Object> query(String sql, MapSqlParameterSource sqlParameterSource) {
        return namedParameterJdbcTemplate.queryForMap(sql, sqlParameterSource);
    }

    public int maxRows() {
        return namedParameterJdbcTemplate.getJdbcTemplate().getMaxRows();
    }

    public int queryTimeOut() {
        return namedParameterJdbcTemplate.getJdbcTemplate().getQueryTimeout();
    }

    public int queryTimeOut(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return namedParameterJdbcTemplate.getJdbcTemplate().getQueryTimeout();
    }

    public int queryTimeOut(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.getQueryTimeout();
    }


    public Pageable pagination(int page, int pageSize) {
        return PageRequest.of(page - 1, pageSize);
    }


    public Pages<List<T>> convertPageToPageDTO(Page<T> page) {
        return Pages.<List<T>>builder().pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .object(page.toList()).build();
    }

}
