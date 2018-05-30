package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WriteCommandTest {

    @Mock
    Pencil pencil;

    WriteCommand writeCommand;

    @Before
    public void setup() {
        writeCommand = new WriteCommand(pencil);
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

}