package com.bradesco.banco.domain;


public class Corrente extends Conta {
    private boolean chequeEspecial;

    public Corrente() {
    }

    public boolean isChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(boolean chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }
}
