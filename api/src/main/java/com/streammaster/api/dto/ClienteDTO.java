package com.streammaster.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
    
    @NotBlank(message = "O nome é obrigatório")
    String nome, 
    
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    String email

) {}

