package com.project.petclinic.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorInfo errorInfo = new ErrorInfo(ex);
        String responseJsonString = "{}";
        try {
            responseJsonString = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(responseJsonString);
    }

    private static class ErrorInfo {
        private final String className;
        private final String exMessage;

        public ErrorInfo(Exception ex) {
            this.className = ex.getClass().getName();
            this.exMessage = ex.getLocalizedMessage();
        }
    }
}
