package com.cyphers.chance.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pencil {

    private TextRepository textRepository;

    @Autowired
    public Pencil(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public void write(String textToWrite) {
        textRepository.appendText(textToWrite);
    }

}
