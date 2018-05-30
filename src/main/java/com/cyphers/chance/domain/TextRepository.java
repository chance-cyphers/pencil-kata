package com.cyphers.chance.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository {

    void appendText(String text);

}
