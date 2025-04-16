package br.gsalles.runapi.exception.controller;

import br.gsalles.runapi.exception.EmailAlreadyExistsException;
import br.gsalles.runapi.exception.InvalidCurrentPasswordException;
import br.gsalles.runapi.exception.InvalidPasswordChangeException;
import br.gsalles.runapi.exception.utils.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex,
                                                                HttpServletRequest request){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                HttpServletRequest request){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPasswordChangeException.class)
    public ResponseEntity<ErrorMessage> handleInvalidPasswordChange(InvalidPasswordChangeException ex,
                                                                    HttpServletRequest request) {
        log.error("API error - ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCurrentPasswordException.class)
    public ResponseEntity<ErrorMessage> handleInvalidCurrentPassword(InvalidCurrentPasswordException ex,
                                                                     HttpServletRequest request) {
        log.error("API error - ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> emailAlreadyExistsException(EmailAlreadyExistsException ex,
                                                                HttpServletRequest request){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
}
