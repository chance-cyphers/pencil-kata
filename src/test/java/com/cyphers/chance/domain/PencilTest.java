package com.cyphers.chance.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

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

}