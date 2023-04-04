package com.snap.linkshortener.kgs.repository.usedkeys;


import com.snap.linkshortener.common.repository.connector.jdbc.JdbcRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsedKeysRepository extends JdbcRepository<UsedKeys, Long> {
}
