package com.bradesco.banco.service.clienteservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.helpers.ContaClienteHelper;
import com.bradesco.banco.repository.ContaRepository;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.response.dto.Cliente;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.response.dto.Cpf;
import com.bradesco.banco.response.dto.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.bradesco.banco.mock.AllMocks.clienteComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.clienteRequestComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaDaoComTodosCampos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

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

    @Test
    void deveriaConverterClienteContaComSucesso() {
        //Arrange
        Conta conta = contaComTodosCampos();
        Cliente cliente = clienteComTodosCampos();
        ContaClienteDao clienteDao = contaDaoComTodosCampos();
        Cliente clienteRequest = clienteRequestComTodosCampos();
        mockStatic(ClienteRequest.class);
        when(ClienteRequest.consultaCliente(cliente.getId())).thenReturn(cliente);
        
        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));

        //Act
        contaClienteHelper.converterClienteConta(conta.getId());
        //Assert


    }
}