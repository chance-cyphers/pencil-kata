package com.cyphers.chance.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class Pencil {

    private TextRepository textRepository;

    @Autowired
    public Pencil(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public String write(String textToWrite) {
        textRepository.appendText(textToWrite);
        try {
            return textRepository.getText();
        } catch (FileNotFoundException e) {
            return "something went wrong when writing";
        }
    }

}
