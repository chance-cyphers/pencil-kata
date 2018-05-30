package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.TextRepository;
import com.cyphers.chance.util.ErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileTextRepository implements TextRepository {

    private static final String FILENAME = "text.txt";
    private FileWriterWrapper fileWriter;
    private ErrorLogger logger;

    @Autowired
    public FileTextRepository(FileWriterWrapper fileWriter, ErrorLogger logger) {
        this.fileWriter = fileWriter;
        this.logger = logger;
    }

    @Override
    public void appendText(String text) {
        try {
            fileWriter.append(text, FILENAME);
        } catch (IOException e) {
            logger.error("an error occurred appending text to file: " + e.getMessage());
        }
    }

}
