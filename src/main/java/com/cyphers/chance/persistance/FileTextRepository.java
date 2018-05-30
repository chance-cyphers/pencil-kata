package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.TextRepository;
import com.cyphers.chance.util.ErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FileTextRepository implements TextRepository {

    private static final String FILENAME = "text.txt";
    private FileWriterWrapper fileWriter;
    private FileReaderWrapper fileReader;
    private ErrorLogger logger;

    @Autowired
    public FileTextRepository(FileWriterWrapper fileWriter, FileReaderWrapper fileReader, ErrorLogger logger) {
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
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

    @Override
    public String getText() throws FileNotFoundException {
        return fileReader.readFile(FILENAME);
    }

}
