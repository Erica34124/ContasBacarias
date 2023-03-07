package com.bradesco.banco.service.admservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.ContaSaldo;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.domain.Poupanca;
import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.response.dto.Clientes;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.repository.ClienteRepository;
import com.bradesco.banco.repository.ContaRepository;
import com.bradesco.banco.service.HelperConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AdmServices implements IadmServices{
    @Autowired
    ContaRepository repository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteRequest clienteRequest;
    @Override
    public Poupanca cadastrarPoupanca(Poupanca conta) {
        Optional<Clientes> cliente = clienteRepository.findById(conta.getClienteId());

        if (cliente.isPresent()) {
           conta.setId(UUID.randomUUID().toString());
            return this.repository.save(conta);
        }
        throw new PersonExceptions(ExceptionsType.valueOf(ExceptionsType.NAO_CADASTRADO.getMessage()));
    }

    @Override
    public Corrente cadastrarCorrente(Corrente conta) {
        Optional<Clientes> cliente = clienteRepository.findById(conta.getClienteId());
        if (cliente.isPresent()) {
            conta.setId(UUID.randomUUID().toString());
            return this.repository.save(conta);
        }
        throw new PersonExceptions(ExceptionsType.valueOf(ExceptionsType.NAO_CADASTRADO.getMessage()));
    }

    @Override
    public ContaSaldo buscarContaSaldoPorId(String id) {
        Optional<Conta> conta = repository.findById(id);
        if (conta.isPresent()) {
            return HelperConversor.conversor(conta.get());
        }
        throw new PersonExceptions(ExceptionsType.valueOf(ExceptionsType.CONTA_NAO_ENCONTRADA.getMessage()));
    }

    @Override
    public Conta buscarContaPorId(String id) {
        Optional<Conta> conta = repository.findById(id);
        if (conta.isPresent()) {
        return conta.get();
    }
        throw new PersonExceptions(ExceptionsType.valueOf(ExceptionsType.CONTA_NAO_ENCONTRADA.getMessage()));
    }

    @Override
    public Page<Conta> mostrarDadosDaContaPagina(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<Conta> lista() {
        return this.repository.findAll();
    }

    @Override
    public Clientes buscarClientePorId(String clienteId) {
        return clienteRequest.consultaCliente(clienteId);
    }
}
