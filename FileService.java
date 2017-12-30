package upload.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    public String getOne();

    public String getAll();

    public String saveUploadedFiles(MultipartFile file) throws IOException;
}
