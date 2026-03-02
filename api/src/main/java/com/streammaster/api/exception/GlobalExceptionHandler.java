package com.streammaster.api.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroDeResposta> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {

        ErroDeResposta erro = new ErroDeResposta(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(404).body(erro);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDeResposta> tratarErroGaenerico(Exception ex, HttpServletRequest request) {

        ErroDeResposta erro = new ErroDeResposta(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(500).body(erro);
    }

}