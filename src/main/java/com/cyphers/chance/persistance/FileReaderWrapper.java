package com.cyphers.chance.persistance;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class FileReaderWrapper {

    public String readFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        return stringBuilder.toString();
    }

    public int nextInt(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        return scanner.nextInt();
    }

}
