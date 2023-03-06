package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionsType.CONTA_NAO_ENCONTRADA;
import static com.bradesco.banco.exceptions.ExceptionsType.SALDO_INSUFICIENTE;
import static com.bradesco.banco.exceptions.ExceptionsType.SERVICO_INATIVO;

@Service
public class ClienteServices implements IclienteServices {
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    HelperContaCliente helperContaCliente;
    private Double saldo;

    @Override
    public Object sacar(String id, Double valor) {
        Optional<Conta> contaDados = this.contaRepository.findById(id);

        if (contaDados.isPresent()) {
            Conta conta = contaDados.get();
            if (conta.getSaldo() >= valor) {
                saldo = (conta.getSaldo());
                conta.setSaldo(saldo -= valor);
                contaRepository.save(conta);
            } else {
                throw new PersonExceptions(ExceptionsType.valueOf(SALDO_INSUFICIENTE.getMessage()));
            }
        }
        return contaDados;
    }

    @Override
    public Object depositar(String id, Double valor) {
        Optional<Conta> contaRequest = this.contaRepository.findById(id);

        if (contaRequest.isPresent()) {
            Conta conta = contaRequest.get();
            Double capitalAtual = conta.getSaldo();
            capitalAtual += valor;
            conta.setSaldo(capitalAtual);
            contaRepository.save(conta);
            return contaRequest;
        }
        throw new PersonExceptions(ExceptionsType.valueOf(CONTA_NAO_ENCONTRADA.getMessage()));
    }


    @Override
    public Optional<Conta> consularSaldo(String id) {
        Optional<Conta> conta = this.contaRepository.findById(id);
        if (conta.isPresent()) {
            return conta;
        }
        throw new PersonExceptions(ExceptionsType.valueOf(CONTA_NAO_ENCONTRADA.getMessage()));
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
        throw new PersonExceptions(ExceptionsType.valueOf(CONTA_NAO_ENCONTRADA.getMessage()));
    }

    @Override
    public ContaClienteDao buscarDadosCompletos(String contaId) {
        try {
            return helperContaCliente.converterClienteConta(contaId);
        } catch (ResourceAccessException exception) {
            throw new PersonExceptions(ExceptionsType.valueOf(SERVICO_INATIVO.getMessage()));
        }
    }
}
