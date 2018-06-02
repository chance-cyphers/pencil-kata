package com.cyphers.chance.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelpCommand implements CommandLineRunner {

    private static final String helpText = "To use this application, include the following as command line arguments:\n\n"
            + "\twrite <text to write>\tappends text to end of paper and displays result\n"
            + "\tsharpen\t\t\tresets pencil durability if it has length left";

    private CommandLineOutput output;

    @Autowired
    public HelpCommand(CommandLineOutput output) {
        this.output = output;
    }

    @Override
    public void run(String... args) {
        if (args.length < 1) {
            output.printLine(helpText);
        }
    }
}
