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

import static com.bradesco.banco.mock.AllMocks.contaComTodosCampos;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerificacaoContasInativasTest {
    @Mock
    ContaRepository contaRepository;

    @InjectMocks
    VerificacaoContasInativas verificacaoContasInativas;

    @Test
    void deveriaValidarContaNegativaComSucesso() {
        //Arrange
        Conta conta = contaComTodosCampos();
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));
        //Act
        verificacaoContasInativas.validarContaNegativada(id);
        //Assert
        Assertions.assertEquals(conta.getSaldo(), 5000d);
        Assertions.assertEquals(conta.getAtivo(), true);

    }


    @ParameterizedTest
    @MethodSource("invalidIdParameters")
    void deveriaValidarContaNegativaComFalha(String id) {
        //Arrange
        Conta conta = contaComTodosCampos();
        String iden = "1";
        //Assert
        Assertions.assertThrows(RuntimeException.class, () -> verificacaoContasInativas.validarContaNegativada(id));

    }

    private static Stream<String> invalidIdParameters() {
        return Stream.of(null, "45y498", "");
    }
}