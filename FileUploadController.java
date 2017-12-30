package upload.web;

import upload.data.FileMetadataEntity;
import upload.data.FileMetadataRepository;
import upload.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/files")
@RestController
public class FileUploadController {

    private final FileService fileService;

    private FileMetadataRepository repository;

    @Autowired
    public FileUploadController(FileService fileService, FileMetadataRepository repository) {
        this.fileService = fileService;
        this.repository = repository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getOne() {
        return fileService.getOne();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAll() {
        return fileService.getAll();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
        @RequestParam("author") String author,
        @RequestParam("size") String sizeString,
        @RequestParam("file") MultipartFile file) {

        try {
            String absolutePath = fileService.saveUploadedFiles(file);

            int size = Integer.valueOf(sizeString);

            FileMetadataEntity entity =
                new FileMetadataEntity(absolutePath, author, size);

            repository.save(entity);

            return new ResponseEntity(
            "Successfully uploaded ", new HttpHeaders(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
