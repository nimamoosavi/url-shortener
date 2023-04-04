package com.snap.linkshortener.kgs.service.impl;

import com.snap.linkshortener.kgs.service.KeyGeneration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimpleKeyGeneration implements KeyGeneration {

    private final KeyGenerationSchedule keyGenerationSchedule;

    @Override
    public String getKey() {
        return keyGenerationSchedule.getReadyKey();
    }

}
