package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
        //criar classe de excessões - user defined exception
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Conta não encontrada. ");
    }
}
