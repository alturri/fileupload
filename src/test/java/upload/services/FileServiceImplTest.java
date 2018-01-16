package upload.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FileServiceImplTest {

    @InjectMocks
    private FileServiceImpl fileService;

    @Test
    public void success() throws IOException {
        String filename = "abc.jpg";
        String originalFilename = "original.jpeg";

        String absolutePath =
            fileService.saveUploadedFiles(new MockMultipartFile(filename, originalFilename, null, new byte[0]));

        assert(absolutePath.contains(originalFilename));
    }

    @Test(expected = IllegalArgumentException.class)
    public void theOriginalNameCannotBeNull() throws IOException {
        fileService.saveUploadedFiles(new MockMultipartFile("", new byte[0]));
    }
}