package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtivarChequeEspecial{
    @Autowired
    ContaRepository contaRepository;

    public Conta validarChequeEspecial(String id) {
        //Como validar se é corrente ou poupança?
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent() && conta.get().getSaldo() >= 1000) {
            ((Corrente) conta.get()).setChequeEspecial(true);
            return contaRepository.save(conta.get());
        } else {
            ((Corrente) conta.get()).setChequeEspecial(false);
           return contaRepository.save(conta.get());
        }
    }
}
