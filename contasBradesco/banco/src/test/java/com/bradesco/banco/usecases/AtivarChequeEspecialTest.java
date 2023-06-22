package com.bradesco.banco.usecases;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
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
import static com.bradesco.banco.mock.AllMocks.correnteComTodosCampos;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtivarChequeEspecialTest {
    @Mock
    ContaRepository contaRepository;

    @InjectMocks
    AtivarChequeEspecial ativarChequeEspecial;

    @Test
    void deveriaAtivarChequeEspecialComSucesso(){
        //Arrange
        Corrente conta = correnteComTodosCampos();
        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
        //Act
        ativarChequeEspecial.validarChequeEspecial(conta.getId());
        //Assert
        Assertions.assertEquals(conta.getSaldo(), 5000d);
    }

    @ParameterizedTest
    @MethodSource("invalidarId")
    void deveriaAtivarChequeEspecialComFalha(String id){
        //Arrange
        Corrente conta = correnteComTodosCampos();
        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
        //Act
        //Assert
        Assertions.assertThrows(RuntimeException.class, ()-> ativarChequeEspecial.validarChequeEspecial(id));
    }

   private static Stream<String> invalidarId(){
        return Stream.of(null, "1213", "");
    }
}