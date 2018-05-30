package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void run_writesSecondArgumentWithPencil() {
        String secondArgument = "write me with a pencil";
        writeCommand.run("", secondArgument);
        verify(pencil).write(secondArgument);
    }

}