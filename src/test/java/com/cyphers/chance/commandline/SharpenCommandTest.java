package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SharpenCommandTest {

    @Mock
    PencilRepository pencilRepository;
    @Mock
    CommandLineOutput output;
    @Mock
    Pencil pencilFromRepo;

    SharpenCommand sharpenCommand;

    @Before
    public void setup() {
        when(pencilRepository.getPencil()).thenReturn(pencilFromRepo);
        sharpenCommand = new SharpenCommand(pencilRepository, output);
    }

    @Test
    public void run_sharpensPencil_ifFirstArgIsSharpen() {
        sharpenCommand.run("sharpen");
        verify(pencilFromRepo).sharpen();
    }

    @Test
    public void run_doesNotSharpenIfFirstArgIsNotSharpen() {
        sharpenCommand.run("flarpen");
        verify(pencilFromRepo, never()).sharpen();
    }

}