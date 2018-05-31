package com.cyphers.chance.domain;

import java.io.IOException;

public interface DurabilityPersister {

    int getDurability();

    void setDurability(int durability) throws IOException;

}
