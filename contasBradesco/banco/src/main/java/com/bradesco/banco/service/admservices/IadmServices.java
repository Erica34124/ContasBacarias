package com.bradesco.banco.service.admservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.ContaSaldo;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.domain.Poupanca;
import com.bradesco.banco.dto.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IadmServices {
    public Poupanca cadastrarPoupanca(Poupanca conta);

    public Corrente cadastrarCorrente(Corrente conta);

    public ContaSaldo buscarContaSaldoPorId(String id);
    public Conta buscarContaPorId(String id);

    Page<Conta> mostrarDadosDaContaPagina(Pageable pageable);

    List<Conta> lista();
    public Clientes buscarClientePorId(String clienteId);
}
