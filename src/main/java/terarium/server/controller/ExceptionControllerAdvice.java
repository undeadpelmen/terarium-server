package terarium.server.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import terarium.server.ServerApplication;
import terarium.server.model.dto.Response.ErrorDto;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private Logger log = ServerApplication.log;
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorDto ExceptionHandler(Exception e) {
        log.error("Unhandled Exception", e);
        
        return new ErrorDto("Something went wrong");
    }
}
