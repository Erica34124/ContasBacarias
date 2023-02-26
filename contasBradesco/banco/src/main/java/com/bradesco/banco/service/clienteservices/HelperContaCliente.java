package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.response.dto.Clientes;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.bradesco.banco.request.ClienteRequest.consultaCliente;
@Service
public class HelperContaCliente {
    @Autowired
    ContaRepository contaRepository;

    public ContaClienteDao converterClienteConta(String contaId) {
        Optional<Conta> conta = contaRepository.findById(contaId);
        Conta contaReq = conta.get();
        if (conta.isPresent()) {
            Clientes cliente = consultaCliente(conta.get().getClienteId());

            ContaClienteDao clienteResponse = new ContaClienteDao(contaReq.getId(), cliente.getNome(), cliente.getCpf(),
                    cliente.getEndereco(), contaReq.getAtivo(), contaReq.getClienteId(), contaReq.getSaldo(), contaReq.getCartao());
            return clienteResponse;
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Conversão incorreta ");
        }
    }
}
