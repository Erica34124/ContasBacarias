package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.response.dto.Clientes;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionsType.CONVERSAO_NEGADA;
import static com.bradesco.banco.request.ClienteRequest.consultaCliente;

@Service
public class HelperContaCliente {
    private ContaRepository contaRepository;

    @Autowired
    public HelperContaCliente(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaClienteDao converterClienteConta(String contaId) {
        Optional<Conta> conta = contaRepository.findById(contaId);
        Conta contaReq = conta.get();

        if (conta.isPresent()) {
            Clientes cliente = consultaCliente(conta.get().getClienteId());

            ContaClienteDao clienteResponse = new ContaClienteDao(contaReq.getId(), cliente.getNome(), cliente.getCpf(),
                    cliente.getEndereco(), contaReq.getAtivo(), contaReq.getClienteId(), contaReq.getSaldo(), contaReq.getCartao());
            return clienteResponse;
        } else {
            throw new PersonExceptions(ExceptionsType.valueOf(CONVERSAO_NEGADA.getMessage()));
        }
    }
}
