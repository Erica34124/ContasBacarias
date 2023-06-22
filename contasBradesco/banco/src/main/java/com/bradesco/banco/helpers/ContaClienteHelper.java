package com.bradesco.banco.helpers;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.exceptions.ExceptionType;
import com.bradesco.banco.exceptions.PersonException;
import com.bradesco.banco.response.dto.Cliente;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionType.CONVERSAO_NEGADA;
import static com.bradesco.banco.request.ClienteRequest.consultaCliente;

@Service
public class ContaClienteHelper {
    private ContaRepository contaRepository;

    @Autowired
    public ContaClienteHelper(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaClienteDao converterClienteConta(String contaId) {
        Optional<Conta> conta = contaRepository.findById(contaId);
        Conta contaReq = conta.get();

        if (conta.isPresent()) {
            Cliente cliente = consultaCliente(conta.get().getClienteId());

            ContaClienteDao clienteResponse = new ContaClienteDao(contaReq.getId(),cliente.getNome(), cliente.getTelefone(), cliente.getCpf(),
                    cliente.getEndereco(),contaReq.getAtivo(),  contaReq.getClienteId(), contaReq.getSaldo(),
                    contaReq.getCartao());

            return clienteResponse;
        } else {
            throw new PersonException(ExceptionType.valueOf(CONVERSAO_NEGADA.getMessage()));
        }
    }
}
