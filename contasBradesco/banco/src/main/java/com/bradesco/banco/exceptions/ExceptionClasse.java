package com.bradesco.banco.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionClasse implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty
    private int code;
    @JsonProperty
    private String mensagem;

    public ExceptionClasse(ExceptionsType exceptionsType) {
        super();
        this.code = exceptionsType.getCode();
        this.mensagem = exceptionsType.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMensagem() {
        return mensagem;
    }
}
