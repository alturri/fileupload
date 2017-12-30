package upload.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class FileMetadataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "file_generator", sequenceName = "file_sequence", initialValue = 1)
    @GeneratedValue(generator = "file_generator")
    private Long id;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String absolutePath;

    public FileMetadataEntity(String absolutePath, String author, int size) {
        this.absolutePath = absolutePath;
        this.author = author;
        this.size = size;
    }
}