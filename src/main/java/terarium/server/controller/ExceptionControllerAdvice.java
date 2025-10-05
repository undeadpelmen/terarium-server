package terarium.server.controller;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import terarium.server.ServerApplication;
import terarium.server.dto.Response.ErrorDto;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private Logger log = ServerApplication.log;
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto ExceptionHandler(Exception e) {
        log.error("Some Error", e);
        
        return new ErrorDto(HttpStatus.BAD_REQUEST, "Something wen wrong", new Timestamp(System.currentTimeMillis()));
    }
}
