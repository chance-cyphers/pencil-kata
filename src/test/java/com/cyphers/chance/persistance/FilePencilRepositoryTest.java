package com.cyphers.chance.persistance;

import com.cyphers.chance.domain.Pencil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilePencilRepositoryTest {

    @Mock
    ObjectMapperWrapper objectMapper;

    FilePencilRepository filePencilRepository;

    String pencilDataFilename = "pencil.dat";

    @Before
    public void setup() {
        filePencilRepository = new FilePencilRepository(objectMapper);
    }

    @Test
    public void save_writesWithObjectMapper() throws IOException {
        Pencil pencil = new Pencil(32);

        filePencilRepository.save(pencil);

        ArgumentCaptor<File> fileCaptor = ArgumentCaptor.forClass(File.class);
        verify(objectMapper).writeValue(fileCaptor.capture(), eq(pencil));
        assertThat(fileCaptor.getValue().getPath()).isEqualTo(pencilDataFilename);
    }

    @Test
    public void getPencil_readsFromObjectMapper() throws IOException {
        Pencil pencilFromMapper = new Pencil(321);
        when(objectMapper.readValue(any(), eq(Pencil.class))).thenReturn(pencilFromMapper);

        Pencil pencil = filePencilRepository.getPencil();

        ArgumentCaptor<File> fileCaptor = ArgumentCaptor.forClass(File.class);
        verify(objectMapper).readValue(fileCaptor.capture(), any());
        assertThat(fileCaptor.getValue().getPath()).isEqualTo(pencilDataFilename);
        assertThat(pencil).isSameAs(pencilFromMapper);
    }

    @Test
    public void getPencil_returnsDefault_onExceptionFromRead() throws IOException {
        when(objectMapper.readValue(any(), eq(Pencil.class))).thenThrow(new IOException());
        Pencil pencil = filePencilRepository.getPencil();
        assertThat(pencil).isNotNull();
    }

}