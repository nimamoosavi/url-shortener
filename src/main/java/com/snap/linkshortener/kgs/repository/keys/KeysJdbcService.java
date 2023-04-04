package com.snap.linkshortener.kgs.repository.keys;


import com.snap.linkshortener.common.repository.connector.jdbc.JdbcServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class KeysJdbcService extends JdbcServiceImpl<Keys, Long> {

}
