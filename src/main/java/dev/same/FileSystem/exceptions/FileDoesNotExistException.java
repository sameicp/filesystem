package dev.same.FileSystem.exceptions;

public class FileDoesNotExistException extends RuntimeException{
    public FileDoesNotExistException() {
        super("Could not find the file");
    }
}
