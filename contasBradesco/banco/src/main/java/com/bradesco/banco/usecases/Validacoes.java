package com.bradesco.banco.usecases;


import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;

public interface Validacoes {
    Boolean validarContaNegativada(Conta conta);

    Boolean validarChequeEspecial(Corrente corrente);
}
