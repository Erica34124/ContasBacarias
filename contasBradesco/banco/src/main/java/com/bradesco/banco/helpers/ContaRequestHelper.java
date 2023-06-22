package com.bradesco.banco.helpers;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.request.ContaRequest;
import org.springframework.stereotype.Service;

@Service
public class ContaRequestHelper {
    public Conta converterContaRequest(ContaRequest request) {
        Conta conta = new Conta();
        conta.setAtivo(request.getAtivo());
        conta.setSaldo(request.getSaldo());
        conta.setClienteId(request.getClienteId());
        conta.setCartao(request.getCartao());

        return conta;
    }
}
