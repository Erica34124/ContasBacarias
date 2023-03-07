package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionsType.CONTA_INATIVA;

@Service
public class VerificacaoContasInativas {
    @Autowired
    ContaRepository contaRepository;

    public Conta validarContaNegativada(String id) {
        Optional<Conta> conta = contaRepository.findById(id);

        if (conta.isPresent()) {
            if (conta.get().getSaldo() > 0) {
                conta.get().setAtivo(true);
            } else {
                conta.get().setAtivo(false);
            }
            return contaRepository.save(conta.get());
        }
        throw new PersonExceptions(ExceptionsType.valueOf(CONTA_INATIVA.getMessage()));
    }
}
