package edu.challengAlkemy.javaSpringBoot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
    
    @org.springframework.web.bind.annotation.ExceptionHandler(value={CustomException.class})
    protected ResponseEntity<Object> handlerConflict(CustomException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }
}
