package com.bradesco.banco.domain;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public abstract class Conta {

    private String id;
    private Boolean ativo;
    @NonNull
    private String clienteId;
    private String clienteNome;
    private Double saldo;
    private String cartao;

    public Conta(String id, Boolean ativo, String clienteId, String clienteNome, Double saldo, String cartao) {
        this.id = id;
        this.ativo = ativo;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.saldo = saldo;
        this.cartao = cartao;
    }
}
