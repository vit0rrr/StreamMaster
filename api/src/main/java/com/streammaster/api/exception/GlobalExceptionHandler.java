package com.streammaster.api.exception;

import java.time.LocalDateTime;
import java.util.List;      
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDeResposta> tratarErroDeValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        // Pega todos os erros dos campos (ex: erro no nome, erro no email) e junta num texto só
        List<FieldError> errosDeCampo = ex.getBindingResult().getFieldErrors();
        String mensagensDeErro = errosDeCampo.stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErroDeResposta erro = new ErroDeResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), // Retorna Status 400 (Bad Request - Culpa do usuário)
                "Erro de Validação: " + mensagensDeErro,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}