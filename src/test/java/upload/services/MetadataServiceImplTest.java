package upload.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import upload.data.FileMetadataEntity;
import upload.data.FileMetadataRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MetadataServiceImplTest {

    @Mock
    FileMetadataRepository fileMetadataRepository;

    @InjectMocks
    private MetadataServiceImpl metadataService;

    @Test
    public void success() {
        String path = "/the/path";
        String author = "Yours Truly";
        int size = 1024;

        FileMetadataEntity mockedEntity = new FileMetadataEntity(path, author, size);

        when(fileMetadataRepository.save((FileMetadataEntity) anyObject())).thenReturn(mockedEntity);

        FileMetadataEntity entity = metadataService.saveMetadata(path, author, size);

        assertEquals(mockedEntity, entity);
    }
}