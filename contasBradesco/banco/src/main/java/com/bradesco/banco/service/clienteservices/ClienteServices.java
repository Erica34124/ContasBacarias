package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class ClienteServices implements IclienteServices {
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    HelperContaCliente helperContaCliente;
    private Double saldo;

    @Override
    public Object sacar(String id, Double valor) {
        Optional<Conta> conta = this.contaRepository.findById(id);
        if (conta.isPresent() && conta.get().getSaldo() >= valor) {
            saldo = (conta.get().getSaldo());
            conta.get().setSaldo(saldo -= valor);
            contaRepository.save(conta.get());
            return conta;
        }
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Conta não encontrada");
    }

    @Override
    public Object depositar(String id, Double valor) {
        Optional<Conta> conta = this.contaRepository.findById(id);

        if (conta.isPresent()) {
            Double capitalAtual = conta.get().getSaldo();
            capitalAtual += valor;
            conta.get().setSaldo(capitalAtual);
            contaRepository.save(conta.get());
            return conta;
        }
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, " não encontrado");
    }

    @Override
    public Optional<Conta> consularSaldo(String id) {
        Optional<Conta> conta = this.contaRepository.findById(id);
        if (conta.isPresent()) {
            return conta;
        }
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Cliente não encontrado");
    }

    @Override
    public Boolean tranferir(Conta idOrigem, Conta idDestino, Double valor) {
        Optional<Conta> origem = this.contaRepository.findById(idOrigem.getId());
        Optional<Conta> destino = this.contaRepository.findById(idDestino.getId());

        if (origem.isPresent() && destino.isPresent() && origem.get().getSaldo() >= valor) {
            String co = origem.get().getId();
            String cd = destino.get().getId();

            this.sacar(co, valor);
            this.depositar(cd, valor);

            return true;
        }
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Cliente não encontrado");
    }

    @Override
    public ContaClienteDao buscarDadosCompletos(String contaId) {
        try {
            return helperContaCliente.converterClienteConta(contaId);
        } catch (ResourceAccessException exception) {
            throw new ResponseStatusException
                    (HttpStatus.SERVICE_UNAVAILABLE, "Serviço inativo ");
        }
    }
}
