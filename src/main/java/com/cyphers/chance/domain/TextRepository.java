package com.cyphers.chance.domain;

import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;

@Repository
public interface TextRepository {

    void appendText(String text);

    String getText() throws FileNotFoundException;

}
