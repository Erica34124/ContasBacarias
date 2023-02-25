package com.bradesco.banco.domain;
public class Poupanca extends Conta{
    private Double taxaSaque;


    public Poupanca(String id, Boolean ativo, String clienteId, String clienteNome, Double saldo, String cartao, Double taxaSaque) {
        super(id, ativo, clienteId, clienteNome, saldo, cartao);
        this.taxaSaque = taxaSaque;
    }
}
