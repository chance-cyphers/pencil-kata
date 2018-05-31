package com.cyphers.chance.persistance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileDurabilityPersisterTest {

    @Mock
    FileReaderWrapper fileReader;
    @Mock
    FileWriterWrapper fileWriter;

    FileDurabilityPersister fileDurabilityPersister;

    String pencilDataFilename = "pencil.dat";

    @Before
    public void setup() {
        fileDurabilityPersister = new FileDurabilityPersister(fileReader, fileWriter);
    }

    @Test
    public void getDurability_returnsIntFromFile() throws FileNotFoundException {
        int durabilityFromFile = 53;
        when(fileReader.nextInt(pencilDataFilename)).thenReturn(durabilityFromFile);

        int durability = fileDurabilityPersister.getDurability();

        assertThat(durability).isEqualTo(durabilityFromFile);
    }

    @Test
    public void getDurability_returnsDefaultOnFileNotFoundException() throws FileNotFoundException {
        when(fileReader.nextInt(any())).thenThrow(new FileNotFoundException());

        int durability = fileDurabilityPersister.getDurability();

        int defaultDurability = 100;
        assertThat(durability).isEqualTo(defaultDurability);
    }

    @Test
    public void setDurability_writesToFileAsString() throws IOException {
        fileDurabilityPersister.setDurability(133);
        verify(fileWriter).overwrite("133", pencilDataFilename);
    }

}