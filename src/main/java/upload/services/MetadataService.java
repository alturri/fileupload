package upload.services;

import org.springframework.web.multipart.MultipartFile;
import upload.data.FileMetadataEntity;

import java.io.IOException;

public interface MetadataService {

    public FileMetadataEntity saveMetadata(String absolutePath, String author, int size) throws IOException;
}
