package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.DurabilityPersister;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class FileDurabilityPersister implements DurabilityPersister {

    private static final String FILENAME = "pencil.dat";
    private static final int DEFAULT_DURABILITY = 100;

    private FileReaderWrapper fileReader;

    public FileDurabilityPersister(FileReaderWrapper fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public int getDurability() {
        try {
            return fileReader.nextInt(FILENAME);
        } catch (FileNotFoundException e) {
            return DEFAULT_DURABILITY;
        }
    }

    @Override
    public void setDurability(int durability) {
    }

}
