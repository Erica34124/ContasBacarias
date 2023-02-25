package com.bradesco.banco.domain;


public class Corrente extends Conta{
    private Boolean chequeEspecial;

    public Corrente(String id, Boolean ativo, String clienteId, String clienteNome, Double saldo, String cartao) {
        super(id, ativo, clienteId, clienteNome, saldo, cartao);
    }


    public Boolean getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(Boolean chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }
}
