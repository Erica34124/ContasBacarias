package com.bradesco.banco.service;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.ContaSaldo;

public class HelperConversor {
    public static ContaSaldo conversor(Conta conta) {
        ContaSaldo contaSaldo = new ContaSaldo();
        contaSaldo.setClienteId(conta.getClienteId());
        contaSaldo.getNomeCliente();
        contaSaldo.setSaldo(conta.getSaldo());
        return contaSaldo;
    }
}
