package dev.same.FileSystem.service;

import dev.same.FileSystem.exceptions.FileDoesNotExistException;
import dev.same.FileSystem.exceptions.FileNotUploadedException;
import dev.same.FileSystem.model.FileEntity;
import dev.same.FileSystem.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<FileEntity> findAll() {
        return fileRepository.findAll();
    }

    public void uploadFile(MultipartFile file) {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setType(file.getContentType());
            fileEntity.setSize(file.getSize());
            fileEntity.setData(file.getBytes());
            fileEntity.setUploadedAt(new Date());
            fileRepository.save(fileEntity);
        } catch (Exception e) {
            throw new FileNotUploadedException();
        }
    }

    public FileEntity downloadFile(int id) {
        FileEntity fileEntity = fileRepository.findFileById(id);
        if (fileEntity == null) {
            throw new FileDoesNotExistException();
        }
        return fileEntity;
    }

    public void deleteFileById(Long id) {
        if (!fileRepository.existsById(id)) {
            throw new FileDoesNotExistException();
        }
        fileRepository.deleteById(id);
    }
}
