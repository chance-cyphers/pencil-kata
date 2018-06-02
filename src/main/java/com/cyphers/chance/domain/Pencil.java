package com.cyphers.chance.domain;

import java.io.IOException;

public class Pencil {

    private int durability;

    //needed for object mapper
    public Pencil() {}

    public Pencil(int durability) {
        this.durability = durability;
    }

    public String write(String requestedText, TextRepository textRepository) {
        try {
            textRepository.appendText(buildTextToAppend(requestedText));
            return textRepository.getText();
        } catch (IOException e) {
            return "something went wrong when writing";
        }
    }

    private String buildTextToAppend(String requestedText) {
        StringBuilder textToWriteBuilder = new StringBuilder();
        for (int i = 0; i < requestedText.length(); i++) {
            if (durability > 0) {
                textToWriteBuilder.append(requestedText.charAt(i));
                durability--;
            } else {
                textToWriteBuilder.append(" ");
            }
        }

        return textToWriteBuilder.toString();
    }

    public int getDurability() { return durability; }

}
