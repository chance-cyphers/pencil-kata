package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileTextRepository implements TextRepository {

    private static final String FILENAME = "text.txt";
    private FileWriterWrapper fileWriter;

    @Autowired
    public FileTextRepository(FileWriterWrapper fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void appendText(String text) {
        try {
            fileWriter.append(text, FILENAME);
        } catch (IOException e) {

        }
    }
}
