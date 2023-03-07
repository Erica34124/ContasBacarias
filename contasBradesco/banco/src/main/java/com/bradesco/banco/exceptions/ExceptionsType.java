package com.bradesco.banco.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ExceptionsType {
    CLIENTE_NAO_ENCONTRADO(1, "Id do cliente não encontrado"),
    CONTA_NAO_ENCONTRADA(2, "Id da conta não encontrado. "),
    CONVERSAO_NEGADA(3, "Conta não pode ser convertida, método válido somente para conta corrente ."),
    SALDO_INSUFICIENTE(4,"Saldo insulficiênte para essa transação. "),
    SERVICO_INATIVO(5,"Serviço temporáriamente fora do ar. "),
    NAO_CADASTRADO(6, "Não foi possível cadastrar cliente. "),
    CONTA_INATIVA(7, "Conta inativa ou negativada. "),
    CARTAO_NAO_APROVADO(8, "Solicitação de cartão negada temporariamente. ");
    private final Logger logger = LogManager.getLogger(ExceptionsType.class);

    private final int code;

    private final String message;

    ExceptionsType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
