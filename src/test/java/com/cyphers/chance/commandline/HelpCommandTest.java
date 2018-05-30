package com.cyphers.chance.commandline;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HelpCommandTest {

    @Mock
    CommandLineOutput output;

    HelpCommand helpCommand;

    String expectedHelpText = "To use this application, include the following as command line arguments:\n\n"
            + "write <text to write>\tappends text to end of paper and displays result\n";

    @Before
    public void setup() {
        helpCommand = new HelpCommand(output);
    }

    @Test
    public void run_showsHelpText_ifNoArgsAreGiven() {
        helpCommand.run();
        verify(output).printLine(expectedHelpText);
    }

    @Test
    public void run_doesNotShowText_IfThereIsAtLeastOneArgument() {
        helpCommand.run("a command of some sort");
        verify(output, never()).printLine(any());
    }

}