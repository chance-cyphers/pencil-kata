package com.cyphers.chance.domain;

import java.io.IOException;

public class Pencil {

    private static final int DEFAULT_DURABILITY = 100;
    private static final int DEFAULT_LENGTH = 10;
    private int durability;
    private int length;

    public Pencil() {
        durability = DEFAULT_DURABILITY;
        length = DEFAULT_LENGTH;
    }

    public Pencil(int durability, int length) {
        this.durability = durability;
        this.length = length;
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

    public void sharpen() {
        durability = DEFAULT_DURABILITY;
        length--;
    }

    public int getLength() {
        return length;
    }
}
