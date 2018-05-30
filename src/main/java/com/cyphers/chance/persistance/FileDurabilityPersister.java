package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.DurabilityPersister;
import org.springframework.stereotype.Component;

@Component
public class FileDurabilityPersister implements DurabilityPersister {

    @Override
    public int getDurability() {
        return 100;
    }

}
