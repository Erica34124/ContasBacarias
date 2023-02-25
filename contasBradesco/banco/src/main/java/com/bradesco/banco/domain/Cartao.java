package com.bradesco.banco.domain;

public enum Cartao {

     NACIONAL(1000.0),
    PLATINUM(5000.0),
    OURO(10000.0),
    DIAMANTE(15000.0), NAO_APROVADO(0.0);
    private final Double valorCredito;

     Cartao(Double valorCredito) {
        this.valorCredito = valorCredito;
    }
    public Double valorCredito() {
        return this.valorCredito;
    }
}
