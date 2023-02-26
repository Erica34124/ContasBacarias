package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtivarConta implements Validar {
    @Autowired
    ContaRepository contaRepository;
    Corrente corrente;

    public Corrente isAtiva(String id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (validarChequeEspecial((Corrente) conta.get()) || validarContaNegativada(conta.get())) {
            conta.get().setAtivo(true);
            ((Corrente) conta.get()).setChequeEspecial(true);
            contaRepository.save(conta.get());
            return (Corrente) conta.get();
        } else {
            if (conta.get().getAtivo() == true) {
                conta.get().setAtivo(false);
                ((Corrente) conta.get()).setChequeEspecial(false);
                contaRepository.save(conta.get());
                return (Corrente) conta.get();
            }
            contaRepository.save(this.corrente);
            return (Corrente) conta.get();
        }
    }

    @Override
    public Boolean validarChequeEspecial(Corrente conta) {
        if (conta.getSaldo() >= 1000) {
            conta.setAtivo(true);
            return true;
        }
        conta.setAtivo(false);
        return false;
//        throw new ResponseStatusException
//                (HttpStatus.NOT_FOUND, "Cheque especial não aprovado");
    }

    @Override
    public Boolean validarContaNegativada(Conta conta) {
        if (conta.getSaldo() > 0) {
            conta.setAtivo(true);
            return true;
        }
        conta.setAtivo(false);
        return false;
//        throw new ResponseStatusException
//                (HttpStatus.NOT_FOUND, "Conta negativa");

    }
}
