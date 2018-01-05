package upload.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upload.data.FileMetadataEntity;
import upload.data.FileMetadataRepository;

import java.io.IOException;

@Service
public class MetadataServiceImpl implements MetadataService {

    private FileMetadataRepository fileMetadataRepository;

    @Autowired
    public MetadataServiceImpl(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    private FileMetadataRepository repository;

    public FileMetadataEntity saveMetadata(String absolutePath, String author, int size) throws IOException {
        FileMetadataEntity entity =
            new FileMetadataEntity(absolutePath, author, size);

        FileMetadataEntity fileMetadataEntity = fileMetadataRepository.save(entity);

        return fileMetadataEntity;
    }
}
