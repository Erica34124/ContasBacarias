package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Cartao;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionsType.CARTAO_NAO_APROVADO;
import static com.bradesco.banco.exceptions.ExceptionsType.CONTA_NAO_ENCONTRADA;

@Service
public class ValidarLimiteCartaoCredito {
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
                        return Cartao.OURO.valorCredito();
                    } else if (valor > 10000) {
                        return Cartao.DIAMANTE.valorCredito();
                    }
                } else {

                    throw new PersonExceptions(ExceptionsType.valueOf(CARTAO_NAO_APROVADO.getMessage()));
                }
            }
            throw new PersonExceptions(ExceptionsType.valueOf(CONTA_NAO_ENCONTRADA.getMessage()));
        }
    }
