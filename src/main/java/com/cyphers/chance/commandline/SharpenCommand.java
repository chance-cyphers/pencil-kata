package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import com.cyphers.chance.domain.PencilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Pencil pencil = pencilRepository.getPencil();

        pencil.sharpen();
    }

}