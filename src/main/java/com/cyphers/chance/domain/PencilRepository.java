package com.cyphers.chance.domain;

import java.io.IOException;

public interface PencilRepository {

    void save(Pencil pencil) throws IOException;

    Pencil getPencil();

}
