package com.snap.linkshortener.kgs.repository.usedkeys;


import com.snap.linkshortener.common.repository.connector.jdbc.JdbcServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UsedKeysJdbcService extends JdbcServiceImpl<UsedKeys, Long> {

}
