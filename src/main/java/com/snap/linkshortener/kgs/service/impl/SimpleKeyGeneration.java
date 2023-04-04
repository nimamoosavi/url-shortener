package com.snap.linkshortener.kgs.service.impl;

import com.snap.linkshortener.kgs.repository.usedkeys.UsedKeys;
import com.snap.linkshortener.kgs.repository.usedkeys.UsedKeysJdbcService;
import com.snap.linkshortener.kgs.service.KeyGeneration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimpleKeyGeneration implements KeyGeneration {

    private final KeyGenerationSchedule keyGenerationSchedule;

    private final UsedKeysJdbcService usedKeysJdbcService;

    @Override
    public String getKey() {
        return keyGenerationSchedule.getReadyKey();
    }


    @Override
    public void saveUsedKey(String key) {
        var entity = new UsedKeys();
        entity.setKeys(key);
        usedKeysJdbcService.save(entity);
    }
}
