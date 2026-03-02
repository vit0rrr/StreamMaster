package com.streammaster.api.exception;

import java.time.LocalDateTime;

public record ErroDeResposta(
    LocalDateTime timestamp,
    Integer status,
    String error,
    String caminho
){
    
}