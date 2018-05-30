package com.cyphers.chance.persistance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FileTextRepositoryTest {

    @Mock
    FileWriterWrapper fileWriter;

    FileTextRepository fileTextRepository;

    @Before
    public void setup() {
        fileTextRepository = new FileTextRepository(fileWriter);
    }

    @Test
    public void appendText_appendsTextToFile() throws IOException {
        String textToAppend = "any old text";
        fileTextRepository.appendText(textToAppend);
        verify(fileWriter).append(textToAppend, "text.txt");
    }

}