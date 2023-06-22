package com.bradesco.banco.helpers;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.repository.ClienteRepository;
import com.bradesco.banco.repository.ContaRepository;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteContaResponseHelper {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContaRepository contaRepository;

    public List<ClienteContaResponse> clienteContaResponseHelper() {

        List<Cliente> clienteBanco = clienteRepository.findAll();

        List<ClienteContaResponse> todosDados = new ArrayList<>();

     clienteBanco.forEach(cliente -> {
          List<Conta> conta = contaRepository.findByClienteId(cliente.getId());
          conta.forEach(c ->{
              ClienteContaResponse contaClienteDados = new ClienteContaResponse();
              contaClienteDados.setAtivo(c.getAtivo());
              contaClienteDados.setCartao(c.getCartao());
              contaClienteDados.setSaldo(c.getSaldo());
              contaClienteDados.setContaId(c.getId());
              contaClienteDados.setNome(cliente.getNome());
              contaClienteDados.setClienteId(cliente.getId());
              contaClienteDados.setEndereco(cliente.getEndereco());
              contaClienteDados.setCpf(cliente.getCpf());
              todosDados.add(contaClienteDados);
          });
     });
        return todosDados;
    }
}
