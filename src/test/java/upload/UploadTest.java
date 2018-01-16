package upload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import upload.web.FileUploadController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class UploadTest {
    @Autowired
    private FileUploadController controller;

    @Test
    public void theControllerShouldHaveLoaded() throws Exception {
        assertThat(controller).isNotNull();
    }
}
