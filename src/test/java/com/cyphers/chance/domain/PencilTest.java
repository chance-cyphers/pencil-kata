package com.cyphers.chance.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PencilTest {

    @Mock
    TextRepository textRepository;

    Pencil pencil;

    @Before
    public void setup() {
        pencil = new Pencil(textRepository);
    }

    @Test
    public void write_appendsTextInRepository() {
        String textToWrite = "append me";
        pencil.write(textToWrite);
        verify(textRepository).appendText(textToWrite);
    }

    @Test
    public void write_returnsNewTextFromRepository() throws FileNotFoundException {
        String newTextFromRepository = "something something dear diary";
        when(textRepository.getText()).thenReturn(newTextFromRepository);

        String newText = pencil.write("dear diary");

        assertThat(newText).isEqualTo(newTextFromRepository);
        InOrder inOrder = inOrder(textRepository);
        inOrder.verify(textRepository).appendText(any());
        inOrder.verify(textRepository).getText();
    }

    @Test
    public void write_returnsErrorMessage_onExceptionFromRepo() throws FileNotFoundException {
        when(textRepository.getText()).thenThrow(new FileNotFoundException());
        String newText = pencil.write("something problematic");
        assertThat(newText).isEqualTo("something went wrong when writing");
    }

}