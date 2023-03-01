package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class VerificacaoContasInativas {
    @Autowired
    ContaRepository contaRepository;

    public Conta validarContaNegativada(String id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent() && conta.get().getSaldo() > 0) {
            conta.get().setAtivo(true);
            return contaRepository.save(conta.get());
        } else {
            conta.get().setAtivo(false);
            return contaRepository.save(conta.get());
        }
    }
}
