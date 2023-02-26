package com.bradesco.banco.response.dto;

public class ContaClienteDao {
    private String id;
    private String nome;
    private Cpf cpf;
    private Endereco endereco;
    private Boolean ativo;
    private String clienteId;
    private Double saldo;
    private String cartao;

    public ContaClienteDao(String id, String nome, Cpf cpf, Endereco endereco,
                           Boolean ativo, String clienteId,
                           Double saldo, String cartao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = ativo;
        this.clienteId = clienteId;
        this.saldo = saldo;
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    @Override
    public String toString() {
        return "ContaClienteDao{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", endereco=" + endereco +
                ", ativo=" + ativo +
                ", clienteId='" + clienteId + '\'' +
                ", saldo=" + saldo +
                ", cartao='" + cartao + '\'' +
                '}';
    }
}
