package com.cyphers.chance.domain;

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
    int expectedDefaultDurability = 100;
    int expectedDefaultLength = 10;

    @Test
    public void write_appendsTextInRepository() {
        String textToWrite = "append me";
        new Pencil(10, 5).write(textToWrite, textRepository);
        verify(textRepository).appendText(textToWrite);
    }

    @Test
    public void write_returnsNewTextFromRepository() throws FileNotFoundException {
        String newTextFromRepository = "something something dear diary";
        when(textRepository.getText()).thenReturn(newTextFromRepository);

        String newText = new Pencil(100, 5).write("dear diary", textRepository);

        assertThat(newText).isEqualTo(newTextFromRepository);
        InOrder inOrder = inOrder(textRepository);
        inOrder.verify(textRepository).appendText(any());
        inOrder.verify(textRepository).getText();
    }

    @Test
    public void write_returnsErrorMessage_onExceptionFromRepo() throws IOException {
        when(textRepository.getText()).thenThrow(new FileNotFoundException());
        String newText = new Pencil(32, 5).write("something problematic", textRepository);
        assertThat(newText).isEqualTo("something went wrong when writing");
    }

    @Test
    public void write_substitutesWhitespaceForEveryCharPastDurabilityNumber() {
        new Pencil(10, 5).write("nineteen chars long", textRepository);
        verify(textRepository).appendText("nineteen c         ");
    }

    @Test
    public void write_doesntDecrementDurabilityBelowZero() {
        Pencil pencil = new Pencil(10, 5);
        pencil.write("more than 10 chars long", textRepository);
        assertThat(pencil.getDurability()).isEqualTo(0);
    }

    @Test
    public void newPencil_hasDefaultDurabilityAndLength() {
        assertThat(new Pencil().getDurability()).isEqualTo(expectedDefaultDurability);
        assertThat(new Pencil().getLength()).isEqualTo(expectedDefaultLength);
    }

    @Test
    public void sharpen_setsDurabilityBackToDefault() {
        Pencil pencil = new Pencil(0, 5);
        pencil.sharpen();
        assertThat(pencil.getDurability()).isEqualTo(expectedDefaultDurability);
    }

    @Test
    public void sharpen_reducesLengthByOne() {
        Pencil pencil = new Pencil(0, 5);
        pencil.sharpen();
        assertThat(pencil.getLength()).isEqualTo(4);
    }

    @Test
    public void sharpen_doesNotRenewDurability_ifLengthLessThanOne() {
        Pencil pencil = new Pencil(0, 0);
        pencil.sharpen();
        assertThat(pencil.getLength()).isEqualTo(0);
    }

}