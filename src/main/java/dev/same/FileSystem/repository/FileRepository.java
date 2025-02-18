package dev.same.FileSystem.repository;

import dev.same.FileSystem.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity findFileById(int id);
    void deleteById(int id);
}
