package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.domain.Poupanca;
import com.bradesco.banco.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class AtivarContaTest {
    @InjectMocks
    ContaRepository contaRepository;

    @Test
    void isAtiva() {
        // Arrange
        AtivarConta ativarConta = new AtivarConta();
        Poupanca conta = new Poupanca("123",true,"456",
                "erica", 2000.0,"master", 0.02 );
//        String id = conta.getId();
//        //Action
//        Corrente c = ativarConta.isAtiva(id);
//        boolean isTrue = c.getAtivo();
//        //Assert
//        assertTrue(isTrue);
    }

    @Test
    void validarChequeEspecial() {
        // Arrange
        AtivarConta ativarConta = new AtivarConta();
        Corrente conta = new Corrente("123",true,"456",
                "erica", 2000.0,"master" );
        //Action
        ativarConta.validarChequeEspecial(conta);

        //Assert
        assertEquals(conta.getSaldo(), 2000.0);

        assertTrue(ativarConta.validarContaNegativada(conta));
    }
    @Test
    void invalidarChequeEspecial() {
        // Arrange
        AtivarConta ativarConta = new AtivarConta();
        Corrente conta = new Corrente("123",true,"456",
                "erica", 200.0,"master" );
        //Action
        Boolean isAtivo = ativarConta.validarChequeEspecial(conta);

        //Assert
        assertEquals(conta.getSaldo(), 200.0);

        assertEquals(conta.getAtivo(), isAtivo);
    }

    @Test
    void deveriaValidarContaNegativada() {
        // Arrange
        AtivarConta ativarConta = new AtivarConta();
        Poupanca conta = new Poupanca("123",true,"456",
                "erica", 200.0,"master",0.2 );

          //Action
        ativarConta.validarContaNegativada(conta);

        //Assert
        assertEquals(conta.getSaldo(), 200);

        assertTrue(ativarConta.validarContaNegativada(conta));
    }
    @Test
    void deveriaInvalidarContaNegativada() {
        // Arrange
        AtivarConta ativarConta = new AtivarConta();

        Corrente corrente = new Corrente("123",true,"456",
                "erica", 0.0,"master");

        //Action
        ativarConta.validarContaNegativada(corrente);

        //Assert

        assertEquals(corrente.getSaldo(), 0.0);

        assertFalse(ativarConta.validarContaNegativada(corrente));
    }
}