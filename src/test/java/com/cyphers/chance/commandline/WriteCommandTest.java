package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import com.cyphers.chance.domain.TextRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WriteCommandTest {

    @Mock
    PencilRepository pencilRepository;
    @Mock
    CommandLineOutput output;
    @Mock
    TextRepository textRepository;
    @Mock
    Pencil pencilFromRepo;


    WriteCommand writeCommand;

    @Before
    public void setup() {
        when(pencilRepository.getPencil()).thenReturn(pencilFromRepo);
        writeCommand = new WriteCommand(pencilRepository, textRepository, output);
    }

    @Test
    public void run_writesSecondArgumentWithPencil_IfFirstArgumentIsWrite() {
        String secondArgument = "write me with a pencilRepository";
        writeCommand.run("write", secondArgument);
        verify(pencilFromRepo).write(secondArgument, textRepository);
    }

    @Test
    public void run_doesNotWrite_IfFirstArgumentIsNotWrite() {
        writeCommand.run("not right", "text to write");
        verify(pencilFromRepo, never()).write(any(), any());
    }

    @Test
    public void run_printsUpdatedTextFromPencil() {
        String updatedText = "here is the new and updated text";
        when(pencilFromRepo.write(any(), any())).thenReturn(updatedText);

        writeCommand.run("write", "any text at all");

        verify(output).printLine("Updated Text:");
        verify(output).printLine(updatedText);
    }

    @Test
    public void run_printsPencilDurabilityAfterWriting() {
        when(pencilFromRepo.getDurability()).thenReturn(14);

        writeCommand.run("write", "this wears down the pencilRepository");

        InOrder inOrder = inOrder(pencilFromRepo);
        inOrder.verify(pencilFromRepo).write(anyString(), any());
        inOrder.verify(pencilFromRepo).getDurability();
        verify(output).printLine("Remaining Durability:");
        verify(output).printLine("14");
    }

    @Test
    public void run_savesPencilAfterWriting() throws IOException {
        writeCommand.run("write", "whatever pencils write");

        InOrder inOrder = inOrder(pencilFromRepo, pencilRepository);
        inOrder.verify(pencilFromRepo).write(any(), any());
        inOrder.verify(pencilRepository).save(pencilFromRepo);
    }

    @Test
    public void run_printsErrorMessageIfSaveThrowsException() throws IOException {
        doThrow(IOException.class).
                when(pencilRepository)
                .save(any());

        writeCommand.run("write", "whatever pencils write");

        verify(output).printLine(contains("Something went wrong saving pencil state"));
    }

}