package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import com.cyphers.chance.domain.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WriteCommand implements CommandLineRunner {

    private PencilRepository pencilRepository;
    private TextRepository textRepository;
    private CommandLineOutput output;

    @Autowired
    public WriteCommand(PencilRepository pencilRepository,
                        TextRepository textRepository,
                        CommandLineOutput output) {
        this.pencilRepository = pencilRepository;
        this.textRepository = textRepository;
        this.output = output;
    }

    @Override
    public void run(String... args) {
        if (args.length < 2 || !args[0].equals("write")) { return; }

        Pencil pencil = pencilRepository.getPencil();
        String updatedText = pencil.write(args[1], textRepository);
        try {
            pencilRepository.save(pencil);
        } catch (IOException e) {
            output.printLine("Something went wrong saving pencil state");
        }

        output.printLine("Remaining Durability:");
        output.printLine(Integer.toString(pencil.getDurability()));
        output.printLine("Updated Text:");
        output.printLine(updatedText);
    }
}
