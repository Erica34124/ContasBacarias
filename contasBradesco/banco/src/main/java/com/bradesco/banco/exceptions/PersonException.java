package com.bradesco.banco.exceptions;

public class PersonException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ExceptionType exceptionsType;

    public PersonException(ExceptionType exceptionClasse) {
         super(exceptionClasse.name());
        this.exceptionsType = exceptionClasse;
    }

    public ExceptionType getExceptionsType() {
        return exceptionsType;
    }
}
