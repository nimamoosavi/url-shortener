package com.snap.linkshortener.kgs.repository.keys;


import com.snap.linkshortener.common.repository.connector.jdbc.JdbcRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeysRepository extends JdbcRepository<Keys, Long> {
}
