package dev.same.FileSystem.controller;

import dev.same.FileSystem.errors.ErrorDetails;
import dev.same.FileSystem.exceptions.FileDoesNotExistException;
import dev.same.FileSystem.exceptions.FileNotUploadedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileNotUploadedException.class)
    ErrorDetails exceptionFileNotUploadedHandler(FileNotUploadedException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(e.getMessage());
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileDoesNotExistException.class)
    ErrorDetails exceptionFileDoesNotExistHandler(FileDoesNotExistException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(e.getMessage());
        return errorDetails;
    }
}
