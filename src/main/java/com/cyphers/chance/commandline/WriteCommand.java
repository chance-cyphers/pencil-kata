package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WriteCommand implements CommandLineRunner {

    private Pencil pencil;
    private CommandLineOutput output;

    @Autowired
    public WriteCommand(Pencil pencil, CommandLineOutput output) {
        this.pencil = pencil;
        this.output = output;
    }

    @Override
    public void run(String... args) {
        if (args.length < 2 || !args[0].equals("write")) { return; }

        String updatedText = pencil.write(args[1]);

        output.printLine("Updated Text:");
        output.printLine(updatedText);
    }
}
