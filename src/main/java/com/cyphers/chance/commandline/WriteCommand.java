package com.cyphers.chance.commandline;

import com.cyphers.chance.domain.Pencil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WriteCommand implements CommandLineRunner {

    private Pencil pencil;

    @Autowired
    public WriteCommand(Pencil pencil) {
        this.pencil = pencil;
    }

    @Override
    public void run(String... args) {
        if (args.length < 2) { return; }

        pencil.write(args[1]);
    }
}
