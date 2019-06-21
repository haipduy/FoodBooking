package fptu.sumer.foodstore_api.controller;


import io.swagger.annotations.Api;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Food Management System")
public class MyController {
    final static String DEFAULT_IMAGE_PATH = "default.png";
    @GetMapping("images/{path}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable(required = true) String path) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("images/"+path);
        if(!imgFile.exists()){
            imgFile = new ClassPathResource("images/"+DEFAULT_IMAGE_PATH);
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
