package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Cartao;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;

public class ValidarLimiteCartaoCredito implements Validar {

    public Double validarLimiteCartaoCredito(Conta conta) {
        Double valor = conta.getSaldo();
        if (valor >= 1000 && valor <= 2000) {
            System.out.println("Cartao.NACIONAL");
            return Cartao.NACIONAL.valorCredito();
        } else if (valor > 2000 && valor <= 5000) {
            System.out.println("Cartao.OURO");
            return Cartao.OURO.valorCredito();
        } else if (valor > 5000 && valor <= 10000) {
            System.out.println("Cartao.PLATINUN");
            return Cartao.PLATINUM.valorCredito();
        } else if (valor > 10000) {
            System.out.println("Cartao.DIAMANTE");
            return Cartao.DIAMANTE.valorCredito();
        } else {
            System.out.println("Cartao.NAO_APROVADO");
            return Cartao.NAO_APROVADO.valorCredito();
        }
    }

    public Boolean isAtiva(Corrente conta) {
        if (validarChequeEspecial(conta) && validarContaNegativada(conta)) {
            conta.setAtivo(true);
            System.out.println("Conta válida! ");
            validarLimiteCartaoCredito(conta);
            return true;
        }
        System.out.println("Conta inválida! ");
        return false;
    }

    @Override
    public Boolean validarChequeEspecial(Corrente conta) {
        return (conta.getSaldo() >= 1000);
    }

    @Override
    public Boolean validarContaNegativada(Conta conta) {
        return (conta.getSaldo() >= 0);
    }

}
