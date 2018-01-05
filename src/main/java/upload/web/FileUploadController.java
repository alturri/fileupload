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
import upload.services.MetadataService;

import java.io.IOException;

@RequestMapping("/files")
@RestController
public class FileUploadController {

    private FileService fileService;

    private MetadataService metadataService;

    @Autowired
    public FileUploadController(FileService fileService, MetadataService metadataService) {
        this.fileService = fileService;
        this.metadataService = metadataService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
        @RequestParam("author") String author,
        @RequestParam("size") String sizeString,
        @RequestParam("file") MultipartFile file) {

        try {
            String absolutePath = fileService.saveUploadedFiles(file);

            int size = Integer.valueOf(sizeString);

            FileMetadataEntity entity = metadataService.saveMetadata(absolutePath, author, size);

            //
            //  It is not uncommon that one creates another object in the middle and
            //  exposes that object through the GUI instead:  for example, there may very well
            //  be an impedance mismatch between what a user sees and how the data is actually
            //  stored in the database.  I've elected not to go that far in this sample code.
            //
            //  Also, suppose that there were more than just three (3) pieces of metadata: for
            //  example, ten (10) elements.  One would not want the 'saveMetadata' method to
            //  have all of ten (10) parameters.  At that point, I would have taken another
            //  approach entirely, whereby I would have two (2) endpoints (one for the file
            //  and one for the metadata) and have had the user specify the metadata as JSON
            //  instead, so that then Spring could have read such an object in one fell swoop,
            //  rather than reading each parameter as a RequestParam.
            //
            return new ResponseEntity(entity, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
