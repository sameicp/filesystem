package dev.same.FileSystem.controller;

import dev.same.FileSystem.model.FileEntity;
import dev.same.FileSystem.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    void uploadFile(@RequestParam("file")MultipartFile file) {
        fileService.uploadFile(file);
    }

    @GetMapping("/download/{id}")
    ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
        FileEntity fileEntity = fileService.downloadFile(id);

        // set http headers for the response
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(fileEntity.getType()));
        httpHeaders.setContentDispositionFormData("attachment", fileEntity.getName());
        httpHeaders.setContentLength(fileEntity.getSize());

        // return file data
        return ResponseEntity.ok().headers(httpHeaders).body(fileEntity.getData());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    String deleteFileById(@PathVariable Long id) {
        fileService.deleteFileById(id);
        return "File deleted successful";
    }
}
