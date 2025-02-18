package dev.same.FileSystem.exceptions;

public class FileNotUploadedException extends RuntimeException {
    public FileNotUploadedException() {
        super("failed to upload the file");
    }
}
