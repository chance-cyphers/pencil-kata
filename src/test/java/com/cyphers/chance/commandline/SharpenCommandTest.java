package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

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
    public void run_doesNotSharpenIfFirstArgIsNotSharpen() {
        sharpenCommand.run("flarpen");
        verify(pencilFromRepo, never()).sharpen();
    }

    @Test
    public void run_doesNotSharpen_ifNoArgsAreGiven() {
        sharpenCommand.run();
        verify(pencilFromRepo, never()).sharpen();
    }

    @Test
    public void run_sharpensPencil_ifFirstArgIsSharpen() {
        sharpenCommand.run("sharpen");
        verify(pencilFromRepo).sharpen();
    }

    @Test
    public void run_savesPencilState_afterSharpening() throws IOException {
        sharpenCommand.run("sharpen");

        InOrder inOrder = inOrder(pencilFromRepo, pencilRepository);
        inOrder.verify(pencilFromRepo).sharpen();
        inOrder.verify(pencilRepository).save(pencilFromRepo);
    }

    @Test
    public void run_printsError_onExceptionFromSaving() throws IOException {
        doThrow(IOException.class)
                .when(pencilRepository)
                .save(any());

        sharpenCommand.run("sharpen");

        verify(output).printLine(contains("Something went wrong saving pencil state"));
    }

    @Test
    public void run_displaysNewPencilStateAfterSharpening() {
        when(pencilFromRepo.getDurability()).thenReturn(32);
        when(pencilFromRepo.getLength()).thenReturn(64);

        sharpenCommand.run("sharpen");

        verify(output).printLine("New pencil length: " + 64);
        verify(output).printLine("New pencil durability: " + 32);
    }

}