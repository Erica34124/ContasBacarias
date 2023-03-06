package com.bradesco.banco.usecases;

import com.bradesco.banco.exceptions.ExceptionClasse;
import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionsType.CONVERSAO_NEGADA;

@Service
public class AtivarChequeEspecial {
    @Autowired
    ContaRepository contaRepository;

    public Conta validarChequeEspecial(String id) throws PersonExceptions {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            try {
                Optional<Corrente> corrente = Optional.of((Corrente) conta.get());
                Corrente contaGet = corrente.get();
                if (contaGet.getSaldo() >= 1000) {
                    contaGet.setChequeEspecial(true);
                } else {
                    contaGet.setChequeEspecial(false);
                }
            } catch (ClassCastException e) {
                     throw new PersonExceptions(ExceptionsType.valueOf(ExceptionsType.CONVERSAO_NEGADA.getMessage()));
            }
        }
        return contaRepository.save(conta.get());
    }
}