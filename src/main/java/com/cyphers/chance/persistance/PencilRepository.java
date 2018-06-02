package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.Pencil;

import java.io.IOException;

public interface PencilRepository {

    void save(Pencil pencil) throws IOException;

    Pencil getPencil();

}
