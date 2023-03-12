package com.bradesco.banco.usecases;

import com.bradesco.banco.exceptions.ExceptionType;
import com.bradesco.banco.exceptions.PersonException;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtivarChequeEspecial {
    @Autowired
    ContaRepository contaRepository;

    public Conta validarChequeEspecial(String id) throws PersonException {
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
                     throw new PersonException(ExceptionType.valueOf(ExceptionType.CONVERSAO_NEGADA.getMessage()));
            }
        }
        return contaRepository.save(conta.get());
    }
}