package com.bradesco.banco.response;

import com.bradesco.banco.response.dto.Endereco;
import com.bradesco.banco.response.dto.Telefone;

public class ClienteContaResponse{
    private String nome;
    private String cpf;
    public Telefone telefone;
    private Endereco endereco;
    private String clienteId;
    private String contaId;
    private Double saldo;
     private Boolean ativo;
        private String cartao;
    private Boolean chequeEspecial;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContaId() {
        return contaId;
    }

    public void setContaId(String contaId) {
        this.contaId = contaId;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Boolean getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(Boolean chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }
}
