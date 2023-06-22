package com.bradesco.banco.helpers;

import com.bradesco.banco.repository.ClienteRepository;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ContaResponseHelper {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteContaResponse contaResponse(ContaRequest request) {
        Optional<Cliente> cliente = clienteRepository.findById(request.getClienteId());

        ClienteContaResponse response = new ClienteContaResponse();

        response.setNome(cliente.get().getNome());
        response.setCpf(cliente.get().getCpf());
        response.setTelefone(cliente.get().getTelefone());
        response.setEndereco(cliente.get().getEndereco());
        response.setClienteId(cliente.get().getId());
        response.setContaId(request.getId());
        response.setSaldo(request.getSaldo());
        response.setAtivo(request.getAtivo());
        response.setCartao(request.getCartao());

        return response;
    }

}