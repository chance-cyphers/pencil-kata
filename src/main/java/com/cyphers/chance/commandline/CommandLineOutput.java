package com.cyphers.chance.commandline;

import org.springframework.stereotype.Component;

@Component
public class CommandLineOutput {

    public void printLine(String line) {
        System.out.println(line);
    }

}
