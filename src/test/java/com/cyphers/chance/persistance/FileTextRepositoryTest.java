package com.cyphers.chance.persistance;

import com.cyphers.chance.util.ErrorLogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileTextRepositoryTest {

    @Mock
    FileWriterWrapper fileWriter;
    @Mock
    ErrorLogger errorLogger;
    @Mock
    FileReaderWrapper fileReader;

    FileTextRepository fileTextRepository;

    String expectedTextFilename = "text.txt";

    @Before
    public void setup() {
        fileTextRepository = new FileTextRepository(fileWriter, fileReader, errorLogger);
    }

    @Test
    public void appendText_appendsTextToFile() throws IOException {
        String textToAppend = "any old text";
        fileTextRepository.appendText(textToAppend);
        verify(fileWriter).append(textToAppend, expectedTextFilename);
    }

    @Test
    public void appendText_logsExceptionFromFileWriter() throws IOException {
        doThrow(IOException.class)
                .when(fileWriter)
                .append(any(), any());
        fileTextRepository.appendText("words and stuff");
        verify(errorLogger).error(any());
    }

    @Test
    public void getText_readsFromTextFile() throws FileNotFoundException {
        String textFromFile = "here is the whole text from the whole file";
        when(fileReader.readFile(expectedTextFilename)).thenReturn(textFromFile);

        String text = fileTextRepository.getText();

        assertThat(text).isEqualTo(textFromFile);
    }

}