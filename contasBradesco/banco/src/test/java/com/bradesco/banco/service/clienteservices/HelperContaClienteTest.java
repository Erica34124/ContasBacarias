package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.helpers.ContaClienteHelper;
import com.bradesco.banco.repository.ContaRepository;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.response.dto.ContaClienteDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class HelperContaClienteTest {
    @Mock
    ContaRepository contaRepository;
    @Mock
    ClienteRequest clienteRequest;
    @Mock
    ContaClienteDao clienteDao;
    @InjectMocks
    ContaClienteHelper contaClienteHelper;

//    @Test
//    void deveriaConverterClienteContaComSucesso() {
//        //Arrange
//        Conta conta = contaComTodosCampos();
//        Cliente cliente = clienteComTodosCampos();
//        ContaClienteDao clienteDao = contaDaoComTodosCampos();
//        Cliente clienteRequest = clienteRequestComTodosCampos();
//        mockStatic(ClienteRequest.class);
//        when(ClienteRequest.consultaCliente(cliente.getId())).thenReturn(cliente);
//
//        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
//
//        //Act
//        contaClienteHelper.converterClienteConta(conta.getId());
//        //Assert
//
//
//    }
}