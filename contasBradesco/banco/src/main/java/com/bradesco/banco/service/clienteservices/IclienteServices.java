package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.dto.ContaClienteDao;

import java.util.Optional;

public interface IclienteServices {
    public Object sacar(String id, Double valor);

    public Object depositar(String id, Double valor);

    public Optional<Conta> consularSaldo(String id);

    public Boolean tranferir(Conta idOrigem, Conta idDestino, Double valor);
    public ContaClienteDao buscarDadosCompletos(String contaId);
}
