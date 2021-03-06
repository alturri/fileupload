package upload.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Value("${uploadDir}")
    private String uploadDir;

    public String saveUploadedFiles(MultipartFile fileInRequest) throws IOException {
        String absolutePath = uploadDir + fileInRequest.getOriginalFilename();

        fileInRequest.transferTo(
            new File(absolutePath));

        return absolutePath;
    }
}