package com.bradesco.banco.domain;

import lombok.Data;

@Data
public class Conta {

    private String id;
    private Boolean ativo;
    private String clienteId;
    private String clienteNome;
    private Double saldo;
    private String cartao;

    public Conta() {
    }

    public Conta(String id, Boolean ativo, String clienteId, String clienteNome, Double saldo, String cartao) {
        this.id = id;
        this.ativo = ativo;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.saldo = saldo;
        this.cartao = cartao;
    }
}
