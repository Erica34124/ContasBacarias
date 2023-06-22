package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static com.bradesco.banco.mock.AllMocks.contaComMultiplosSaldos;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidarLimiteCartaoCreditoTest {
    @Mock
    ContaRepository contaRepository;

    @InjectMocks
    ValidarLimiteCartaoCredito validarLimiteCartaoCredito;

    @Test
    void deveriaValidarLimiteCartãoCreditoComSucesso(){
        //Arrange
        //Coloquei valores dinamicos para verificar mensagem do enum mas não rolou..
        Conta conta = contaComMultiplosSaldos(78500d);
        Double valor = conta.getSaldo();
        String id = conta.getId();

        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));
        //Act
        validarLimiteCartaoCredito.validarLimiteCartaoCredito(id);
        //Assert
        Assertions.assertEquals(conta.getSaldo(), valor);
        //Verificar como validar retorno do enum
        Assertions.assertDoesNotThrow(() -> validarLimiteCartaoCredito.validarLimiteCartaoCredito(id) , "PLATINUM");
    }

    @ParameterizedTest
    @MethodSource("invalidarValores")
    void deveriaValidarLimiteCartãoCreditoComFalha(Double valor){
        //Arrange
        Conta conta = contaComMultiplosSaldos(valor);
        Double valor1 = conta.getSaldo();
        String id = conta.getId();
        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));

        //Assert
        Assertions.assertEquals(conta.getSaldo(), valor1);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> validarLimiteCartaoCredito.validarLimiteCartaoCredito(id));
    }

    private static Stream<Double> invalidarValores(){
            return Stream.of(0d, -1d);
        }
}