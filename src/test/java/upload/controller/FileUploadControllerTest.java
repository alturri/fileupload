package upload.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import upload.data.FileMetadataEntity;
import upload.services.FileService;
import upload.services.MetadataService;
import upload.web.FileUploadController;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileUploadControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FileService fileService;

    @Mock
    private MetadataService metadataService;

    @InjectMocks
    private FileUploadController fileUploadController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(fileUploadController)
                .build();
    }

    @Test
    public void success() throws Exception {
        String path = "the/path";

        FileMetadataEntity entity =
            new FileMetadataEntity(path, "John Doe", 1024);

        when(fileService.saveUploadedFiles(anyObject())).thenReturn(path);
        when(metadataService.saveMetadata(path, "John Doe", 1024)).thenReturn(entity);

        MockMultipartFile multiPFImage =
            new MockMultipartFile(
                "file",
                "abcpic.png",
                "text/plain",
                "Generate bytes to simulate a picture".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/files/upload")
                .file(multiPFImage)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("author", "John Doe")
                .param("size", "1024"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"absolutePath\":\"the/path")))
                .andExpect(content().string(containsString("\"author\":\"John Doe\"")))
                .andExpect(content().string(containsString("\"size\":1024")));

        verify(fileService).saveUploadedFiles(anyObject());
        verify(metadataService).saveMetadata(path, "John Doe", 1024);
    }

    @Test(expected = IllegalArgumentException.class)
    public void allParametersMustHaveANonNullValue() throws Exception {
        String path = "the/path";

        FileMetadataEntity entity =
            new FileMetadataEntity(path, "John Doe", 1024);

        when(fileService.saveUploadedFiles(anyObject())).thenReturn(path);
        when(metadataService.saveMetadata(path, "John Doe", 1024)).thenReturn(entity);

        MockMultipartFile multiPFImage =
                new MockMultipartFile(
                        "file",
                        "abcpic.png",
                        "text/plain",
                        "Generate bytes to simulate a picture".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/files/upload")
                .file(multiPFImage)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("author", null)
                .param("size", "1024"));
    }
}