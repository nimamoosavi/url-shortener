package com.snap.linkshortener.kgs.service.impl;

import com.snap.linkshortener.kgs.repository.keys.KeysJdbcService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SimpleKeyGeneration.class, KeyGenerationSchedule.class, KeysJdbcService.class})
@ExtendWith(SpringExtension.class)
class SimpleKeyGenerationTest {

    @Autowired
    private SimpleKeyGeneration simpleKeyGeneration;

    /**
     * Method under test: {@link SimpleKeyGeneration#getKey()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetKey() {

        simpleKeyGeneration.getKey();
    }
}

