package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Cartao;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ValidarLimiteCartaoCredito implements Validacoes {
    @Autowired
    ContaRepository contaRepository;

    public Double validarLimiteCartaoCredito(String id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            if (conta.get().getSaldo() > 0) {
                Double valor = conta.get().getSaldo();
                if (valor >= 1000 && valor <= 2000) {
                    return Cartao.NACIONAL.valorCredito();
                } else if (valor > 2000 && valor <= 5000) {
                    return Cartao.PLATINUM.valorCredito();
                } else if (valor > 5000 && valor <= 10000) {
                    throw new NullPointerException("Deu ruim! ");
              //                    return Cartao.OURO.valorCredito();
                } else if (valor > 10000) {
                    return Cartao.DIAMANTE.valorCredito();
                } else {
                    return Cartao.NAO_APROVADO.valorCredito();
                }
            } else {
                return 0d;
            }
        }
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Conta não encontrada");
    }


        public Boolean isAtiva (String id){
            return null;
        }

        @Override
        public Boolean validarChequeEspecial (Corrente conta){
            return conta.getChequeEspecial();
        }

        @Override
        public Boolean validarContaNegativada (Conta conta){
            return (conta.getSaldo() >= 0);
        }

    }
