package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SharpenCommand implements CommandLineRunner{

    private PencilRepository pencilRepository;
    private CommandLineOutput output;

    @Autowired
    public SharpenCommand(PencilRepository pencilRepository, CommandLineOutput output) {
        this.pencilRepository = pencilRepository;
        this.output = output;
    }

    @Override
    public void run(String... args) {
        if (args.length < 1 || !args[0].equals("sharpen")) { return; }

        Pencil pencil = pencilRepository.getPencil();
        pencil.sharpen();

        try {
            pencilRepository.save(pencil);
        } catch (IOException e) {
            output.printLine("Something went wrong saving pencil state");
        }

        output.printLine("New pencil length: " + pencil.getLength());
        output.printLine("New pencil durability: " + pencil.getDurability());
    }

}
