package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.helpers.ContaClienteHelper;
import com.bradesco.banco.repository.ContaRepository;
import com.bradesco.banco.response.dto.ContaClienteDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.bradesco.banco.mock.AllMocks.conta2ComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaDaoComTodosCampos;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServicesTest {
    @Mock
    ContaRepository contaRepository;
    @Mock
    ContaClienteHelper contaClienteHelper;

    @InjectMocks
    ClienteServices clienteServices;

    @Test
    void ShoudSacarWithSucess() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Double valor = 3000.0;
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));
        //Act
        clienteServices.sacar(id, valor);
        //Assert
        Assertions.assertEquals(conta.getSaldo(), 2000d);
    }

    @Test
    void ShoudSacarWithFail() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Double valor = 6000.0;
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> clienteServices.sacar(id, valor));
    }

    @Test
    void deveriaDepositarComSucesso() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Double valor = 6000.0;
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));

        clienteServices.depositar(id, valor);

        //Assert
        Assertions.assertEquals(conta.getSaldo(), 11000d);
    }

    @Test
    void deveriaDepositarComFalha() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Double valor = 6000.0;
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(null);

        //Assert
        Assertions.assertEquals(conta.getSaldo(), 5000.0);
        Assertions.assertThrows(NullPointerException.class, ()-> clienteServices.depositar(id, valor));
    }

    @Test
    void deveriaconsularSaldoComSucesso() {
        //Arrange
        Conta conta = contaComTodosCampos();
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));
        //Action
        clienteServices.consularSaldo(id);
        //Assert
        Assertions.assertEquals(conta.getSaldo(), 5000.0);
        Assertions.assertEquals(conta.getId(), "1234");
    }

    @Test
    void deveriaconsularSaldoComFalha() {
        //Arrange
        Conta conta = contaComTodosCampos();
        String id = "1";
        when(contaRepository.findById(id)).thenReturn(Optional.empty());
        //Action
        //Assert
        Assertions.assertThrows(RuntimeException.class, ()-> clienteServices.consularSaldo(id));
    }

    @Test
    void deveriatranferirComSucesso() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Conta conta2 = conta2ComTodosCampos();
        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
        when(contaRepository.findById(conta2.getId())).thenReturn(Optional.of(conta2));
        //Action
        clienteServices.tranferir(conta, conta2, 200d);
        //Assert
        Assertions.assertEquals(conta2.getSaldo(), 3200d);
    }

    @Test
    void deveriatranferirComFalha() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Conta conta2 = conta2ComTodosCampos();
        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
        when(contaRepository.findById(conta2.getId())).thenReturn(Optional.empty());
        //Action
        //Assert
        Assertions.assertThrows(RuntimeException.class, ()-> clienteServices.tranferir(conta, conta2, 5000d));
    }

    @Test
    void deveriaBuscarDadosCompletosComSucesso() {
        //Arrange
        ContaClienteDao clienteDao = contaDaoComTodosCampos();
        //Act
        clienteServices.buscarDadosCompletos(clienteDao.getClienteId());
        //Assert
        Assertions.assertEquals(clienteDao.getClienteId(), "2323");
    }

    @Test
    void deveriaBuscarDadosCompletosComFalha() {
        //Arrange
        ContaClienteDao clienteDao = contaDaoComTodosCampos();
        when(contaClienteHelper.converterClienteConta(clienteDao.getClienteId())).thenReturn(clienteDao);
        //Act
        //Assert
        Assertions.assertThrows(RuntimeException.class, ()-> clienteServices.buscarDadosCompletos("22"));
    }
}