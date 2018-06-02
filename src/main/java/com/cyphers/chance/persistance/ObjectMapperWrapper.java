package com.cyphers.chance.persistance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ObjectMapperWrapper {

    public void writeValue(File file, Object objectToWrite) throws IOException {
        new ObjectMapper().writeValue(file, objectToWrite);
    }

    public <T> T readValue(File src, Class<T> type) throws IOException {
        return new ObjectMapper().readValue(src, type);
    }

}
