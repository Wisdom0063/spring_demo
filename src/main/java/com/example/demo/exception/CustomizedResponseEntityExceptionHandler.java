package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;
import com.example.demo.dto.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;

/**
 * Created by Arpit Khandelwal.
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DemoException.EntityNotFoundException.class)
    public final ResponseEntity<Object> handleNotFountExceptions(Exception ex, WebRequest request) {
        Response<Object> response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DemoException.DuplicateEntityException.class)
    public final ResponseEntity<Object> handleDuplicateExceptions(Exception ex, WebRequest request) {
        Response<Object>response = Response.duplicateEntity();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
    }

}
