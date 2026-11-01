package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.util.CloudStorageHelper;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UploadController {
    final CloudStorageHelper cloudStorageHelper;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || !CloudStorageHelper.isSupportedImage(file.getContentType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only PNG, JPEG, GIF or WEBP images are allowed");
        }
        return ResponseEntity.ok(cloudStorageHelper.uploadImage(file));
    }
}
