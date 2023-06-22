package com.bradesco.banco.request;

import com.bradesco.banco.domain.Conta;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ContaRequest extends Conta {
    private String id;
    private Boolean ativo;
    @NonNull
    @NotBlank
    private String clienteId;
    private Double saldo;
    private String cartao;
    private String chequeEspecial;

    public String getChequeEspecial() {
        return chequeEspecial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public void setChequeEspecial(String chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }
}
