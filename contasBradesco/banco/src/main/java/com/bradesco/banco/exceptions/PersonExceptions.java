package com.bradesco.banco.exceptions;

public class PersonExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ExceptionsType exceptionsType;

    public PersonExceptions(ExceptionsType exceptionClasse) {
         super(exceptionClasse.name());
        this.exceptionsType = exceptionClasse;
    }

    public ExceptionsType getExceptionsType() {
        return exceptionsType;
    }
}
