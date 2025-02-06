package dev.same.FileSystem.controller;

import dev.same.FileSystem.model.FileEntity;
import dev.same.FileSystem.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("")
    public List<FileEntity> findAll() {
        return fileService.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            fileService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("failed to upload file... " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> donwloadFile(@PathVariable int id) {
        FileEntity fileEntity = fileService.downloadFile(id);

        if (fileEntity == null) {
            return ResponseEntity.status(404).body(null);
        }

        // set http headers for the response
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(fileEntity.getType()));
        httpHeaders.setContentDispositionFormData("attachment", fileEntity.getName());
        httpHeaders.setContentLength(fileEntity.getSize());

        // return file data
        return ResponseEntity.ok().headers(httpHeaders).body(fileEntity.getData());
    }
}
