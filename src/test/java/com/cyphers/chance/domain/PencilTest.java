package com.cyphers.chance.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PencilTest {

    @Mock
    TextRepository textRepository;
    @Mock
    DurabilityPersister durabilityPersister;

    Pencil pencil;

    @Before
    public void setup() {
        when(durabilityPersister.getDurability()).thenReturn(100);
        pencil = new Pencil(textRepository, durabilityPersister);
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
    public void write_returnsErrorMessage_onExceptionFromRepo() throws IOException {
        when(textRepository.getText()).thenThrow(new FileNotFoundException());
        String newText = pencil.write("something problematic");
        assertThat(newText).isEqualTo("something went wrong when writing");
    }

    @Test
    public void write_substitutesWhitespaceForEveryCharPastDurabilityNumber() {
        when(durabilityPersister.getDurability()).thenReturn(10);
        pencil.write("nineteen chars long");
        verify(textRepository).appendText("nineteen c         ");
    }

    @Test
    public void write_savesNewDurability() throws IOException {
        when(durabilityPersister.getDurability()).thenReturn(10);
        pencil.write("four");
        verify(durabilityPersister).setDurability(6);
    }

    @Test
    public void write_doesntDecrementDurabilityBelowZero() throws IOException {
        when(durabilityPersister.getDurability()).thenReturn(10);
        pencil.write("more than 10 chars long");
        verify(durabilityPersister).setDurability(0);
    }

}