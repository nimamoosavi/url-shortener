package com.snap.linkshortener.kgs.service.impl;

import com.snap.linkshortener.kgs.repository.keys.Keys;
import com.snap.linkshortener.kgs.repository.keys.KeysJdbcService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public final class KeyGenerationSchedule {

    private static final int KEY_LENGTH = 6;

    private static final int KEY_COUNT_DEFAULT = 1000;

    private static final long SCHEDULER_DURATION_MILLISECONDS = 50000L;

    private final KeysJdbcService keysJdbcService;


    /**
     * the list of keys that have stored in a list for better performance
     */
    private static List<String> generatedKeys;

    public synchronized String getReadyKey() {
        String key;
        if (generatedKeys.isEmpty())
            createAndStore(KEY_LENGTH);

        key = generatedKeys.get(0);
        generatedKeys.remove(0);

        return key;
    }


    /**
     *
     * @param count the keys count for generation
     */
    private void createAndStore(int count) {
        Set<String> keys = new HashSet<>(count);

        for (int i = 0; i < count; i++) {
            keys.add(generateRandomKey(KEY_COUNT_DEFAULT));
        }
        store(keys);
        generatedKeys.addAll(keys);
    }

    /**
     *
     * @param count the keys length for generation
     * @return a unique key
     */
    private String generateRandomKey(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    private void store(Set<String> keys) {
        var entities = new ArrayList<Keys>(keys.size());
        keys.forEach(shortKey -> {
            var entity = generateEntity(shortKey);
            entities.add(entity);
        });
        keysJdbcService.saveAll(entities);
    }


    /**
     * @apiNote this method run Schedule for check the in memory list and if its empty or diagnoses to increase , start to increase it
     */
    @Scheduled(fixedDelay = SCHEDULER_DURATION_MILLISECONDS)
    private void checkKeyListsSchedule() {
        if (generatedKeys.size() > 100)
            return;
        createAndStore(KEY_COUNT_DEFAULT);
    }

    private Keys generateEntity(String shortKey) {
        var keys = new Keys();
        keys.setKey(shortKey);
        return keys;
    }
}
