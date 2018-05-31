package com.cyphers.chance.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Pencil {

    private TextRepository textRepository;
    private DurabilityPersister durabilityPersister;

    @Autowired
    public Pencil(TextRepository textRepository, DurabilityPersister durabilityPersister) {
        this.textRepository = textRepository;
        this.durabilityPersister = durabilityPersister;
    }

    public String write(String requestedText) {
        int durability = durabilityPersister.getDurability();

        try {
            textRepository.appendText(buildTextToAppend(requestedText, durability));
            return textRepository.getText();
        } catch (IOException e) {
            return "something went wrong when writing";
        }
    }

    private String buildTextToAppend(String requestedText, int durability) throws IOException {
        StringBuilder textToWriteBuilder = new StringBuilder();
        for (int i = 0; i < requestedText.length(); i++) {
            if (durability > 0) {
                textToWriteBuilder.append(requestedText.charAt(i));
                durability--;
            } else {
                textToWriteBuilder.append(" ");
            }
        }

        durabilityPersister.setDurability(durability);
        return textToWriteBuilder.toString();
    }

}
