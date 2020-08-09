package com.viu.helpers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.viu.models.ExpectedEventDetails;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AnalyticsExpectationsHelper {
    public static List<ExpectedEventDetails> getExpectedEventDetailsFrom(String fileName) {
        List<ExpectedEventDetails> analyticsExpectations = null;
        try {
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            analyticsExpectations = mapper.readValue(getResourceFile(fileName), getCollectionToConstruct(mapper));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return analyticsExpectations;
    }

    private static CollectionType getCollectionToConstruct(ObjectMapper mapper) {
        return mapper
                .getTypeFactory()
                .constructCollectionType(List.class, ExpectedEventDetails.class);
    }

    private static File getResourceFile(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
}
