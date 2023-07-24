package com.pachico.arithmetic.shared.utils;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class IdProviderTest {

    @Test
    public void testProvide() {
        IdProvider idProvider = new IdProvider();

        UUID uuid1 = idProvider.provide();
        assertNotNull(uuid1, "UUID should not be null");

        UUID uuid2 = idProvider.provide();
        assertNotEquals(uuid1, uuid2, "Subsequent UUIDs should be different");

        Set<UUID> uuidSet = new HashSet<>();
        int numberOfIdsToGenerate = 1000;
        for (int i = 0; i < numberOfIdsToGenerate; i++) {
            UUID newUuid = idProvider.provide();
            assertFalse(uuidSet.contains(newUuid), "Generated UUID should be unique");
            uuidSet.add(newUuid);
        }
    }
}
