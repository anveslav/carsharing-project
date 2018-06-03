package com.rusoft.carsharing.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T readJson(String filePath, TypeReference typeReference) {
        try {
            return mapper.readValue(file(filePath), typeReference);
        } catch (IOException e) {
            return null;
        }
    }

    private static File file(String path) {
        return new File(ClassLoader.getSystemResource(path)
                .getPath());
    }
}
