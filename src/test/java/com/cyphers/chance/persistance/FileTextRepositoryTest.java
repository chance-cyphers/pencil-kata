package com.cyphers.chance.persistance;

import com.cyphers.chance.util.ErrorLogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FileTextRepositoryTest {

    @Mock
    FileWriterWrapper fileWriter;
    @Mock
    ErrorLogger errorLogger;

    FileTextRepository fileTextRepository;

    @Before
    public void setup() {
        fileTextRepository = new FileTextRepository(fileWriter, errorLogger);
    }

    @Test
    public void appendText_appendsTextToFile() throws IOException {
        String textToAppend = "any old text";
        fileTextRepository.appendText(textToAppend);
        verify(fileWriter).append(textToAppend, "text.txt");
    }

    @Test
    public void appendText_logsExceptionFromFileWriter() throws IOException {
        doThrow(IOException.class)
                .when(fileWriter)
                .append(any(), any());
        fileTextRepository.appendText("words and stuff");
        verify(errorLogger).error(any());
    }

}