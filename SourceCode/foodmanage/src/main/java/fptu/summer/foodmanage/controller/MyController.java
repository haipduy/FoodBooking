package fptu.summer.foodmanage.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {

    final static  String DEFAULT_IMAGE_PATH = "default.png";
    @GetMapping("/{path}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable(required = true) String path) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("images/"+path);
        if(!imgFile.exists()){
            imgFile = new ClassPathResource("images/"+DEFAULT_IMAGE_PATH);
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}