package com.example.lab12.ControllerAdvice;

import com.example.lab12.ApiResponse.ApiException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class Controller_Advice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException apiException){
        String message = apiException.getMessage();
        return ResponseEntity.ok().body(message);
    }

    // Server Validation Exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // Server Validation Exception
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e) {
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }


    // SQL Constraint Ex:(Duplicate) Exception
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // wrong write SQL in @column Exception
    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class )
    public ResponseEntity InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
        String msg=e.getMessage();
        return ResponseEntity.status(200).body(msg);
    }

    // Database Constraint Exception
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // Method not allowed Exception
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // Json parse Exception
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // TypesMisMatch Exception
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = UnexpectedTypeException.class)
    public ResponseEntity UnexpectedTypeException(UnexpectedTypeException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity InvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
}


