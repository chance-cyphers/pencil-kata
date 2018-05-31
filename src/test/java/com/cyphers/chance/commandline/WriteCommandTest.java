package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WriteCommandTest {

    @Mock
    Pencil pencil;
    @Mock
    CommandLineOutput output;

    WriteCommand writeCommand;

    @Before
    public void setup() {
        writeCommand = new WriteCommand(pencil, output);
    }

    @Test
    public void run_writesSecondArgumentWithPencil_IfFirstArgumentIsWrite() {
        String secondArgument = "write me with a pencil";
        writeCommand.run("write", secondArgument);
        verify(pencil).write(secondArgument);
    }

    @Test
    public void run_doesNotWrite_IfFirstArgumentIsNotWrite() {
        writeCommand.run("not right", "text to write");
        verify(pencil, never()).write(any());
    }

    @Test
    public void run_printsUpdatedTextFromPencil() {
        String updatedText = "here is the new and updated text";
        when(pencil.write(any())).thenReturn(updatedText);

        writeCommand.run("write", "any text at all");

        verify(output).printLine("Updated Text:");
        verify(output).printLine(updatedText);
    }

}