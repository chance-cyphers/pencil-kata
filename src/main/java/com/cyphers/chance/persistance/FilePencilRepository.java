package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FilePencilRepository implements PencilRepository {

    private static final String FILENAME = "pencil.dat";
    private ObjectMapperWrapper objectMapper;

    public FilePencilRepository(ObjectMapperWrapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(Pencil pencil) throws IOException {
        objectMapper.writeValue(new File(FILENAME), pencil);
    }

    @Override
    public Pencil getPencil() {
        try {
            return objectMapper.readValue(new File(FILENAME), Pencil.class);
        } catch (IOException e) {
            return new Pencil();
        }
    }

}
