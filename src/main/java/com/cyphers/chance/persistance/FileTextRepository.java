package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.TextRepository;
import org.springframework.stereotype.Component;

@Component
public class FileTextRepository implements TextRepository {
    @Override
    public void appendText(String text) {

    }
}
