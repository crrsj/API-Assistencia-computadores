package br.com.computador.dto;

import org.springframework.validation.FieldError;

public record CanposValidos(String campo, String mensagem) {
    public CanposValidos(FieldError erros){
        this(erros.getField(),erros.getDefaultMessage());
    }
}
