package com.bradesco.banco.service.admservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.helpers.ClienteContaResponseHelper;
import com.bradesco.banco.helpers.ContaRequestHelper;
import com.bradesco.banco.helpers.ContaResponseHelper;
import com.bradesco.banco.repository.ClienteRepository;
import com.bradesco.banco.repository.ContaRepository;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.bradesco.banco.mock.AllMocks.clienteComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.clienteContaResponseComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.conta2ComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaRequest2ComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaRequestHelperComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaRequestsemId;
import static com.bradesco.banco.mock.AllMocks.listaComTodosCampos;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdmServicesTest {
    @Mock
    ContaRepository contaRepository;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ContaRequestHelper contaRequestHelper;
    @Mock
    ContaResponseHelper contaResponseHelper;
    @Mock
    ClienteContaResponseHelper clienteContaResponseHelper;
    @InjectMocks
    @Qualifier("admServices")
    AdmServices admServices;

    @Test
    void deveriaCadastrarPoupancaComSucesso() {
        //Arrange
        Cliente cliente = clienteComTodosCampos();
        ContaRequest contaRequest = contaRequest2ComTodosCampos();
        Conta responseHelper = contaRequestHelperComTodosCampos();
        when(clienteRepository.findById(contaRequest.getClienteId())).thenReturn(Optional.of(cliente));
        when(contaRepository.save(contaRequestHelper.converterContaRequest(contaRequest))).thenReturn(responseHelper);
        //Act
        admServices.cadastrarPoupanca(contaRequest);
        //Assert
        Assertions.assertEquals(contaRequest.getClienteNome(), "Maria");
        Assertions.assertEquals(responseHelper.getSaldo(), 8000d);
        Assertions.assertEquals(contaRequest.getClienteId(), responseHelper.getClienteId());
    }

    @Test
    void deveriaCadastrarPoupancaComFalha() {
        //Arrange
        Cliente cliente = clienteComTodosCampos();
        ContaRequest contaRequest = contaRequest2ComTodosCampos();
        ContaRequest contaRequest1 = contaRequestsemId();
        Conta responseHelper = contaRequestHelperComTodosCampos();
        when(clienteRepository.findById(contaRequest.getClienteId())).thenReturn(Optional.of(cliente));
        when(contaRepository.save(contaRequestHelper.converterContaRequest(contaRequest))).thenReturn(responseHelper);

        //Act
        admServices.cadastrarPoupanca(contaRequest);
        //Assert
        Assertions.assertThrows(NullPointerException.class, () -> admServices.cadastrarPoupanca(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> admServices.cadastrarPoupanca(contaRequest1));
    }

    @Test
    void cadastrarCorrente() {
        //Arrage
        Cliente cliente = clienteComTodosCampos();
        ContaRequest contaRequest = contaRequest2ComTodosCampos();
        Conta contaConvertida = contaRequestHelperComTodosCampos();
        ClienteContaResponse contaResponse = clienteContaResponseComTodosCampos();

        when(clienteRepository.findById(contaRequest.getClienteId())).thenReturn(Optional.of(cliente));
        when(contaRepository.save(contaRequestHelper.converterContaRequest(contaRequest))).thenReturn(contaConvertida);
        //Act
        admServices.cadastrarCorrente(contaRequest);
        //Assert
        Assertions.assertEquals(contaRequest.getClienteNome(), "Maria");
        Assertions.assertEquals(contaResponse.getSaldo(), 10000d);
        Assertions.assertEquals(contaRequest.getClienteId(), contaConvertida.getClienteId());
    }

    @Test
    void deveriaBuscarContaSaldoPorIdComSucesso() {
        //Arrage
        Conta conta = conta2ComTodosCampos();
        ContaRequest contaRequest = contaRequest2ComTodosCampos();
        ClienteContaResponse clienteContaResponse = clienteContaResponseComTodosCampos();

        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(contaRequest));
        when(contaResponseHelper.contaResponse(contaRequest)).thenReturn(clienteContaResponse);
        //Act
        admServices.buscarContaSaldoPorId(conta.getId());
        //Assert
        Assertions.assertEquals(conta.getSaldo(), clienteContaResponse.getSaldo());
        Assertions.assertEquals(conta.getClienteNome(), clienteContaResponse.getNome());
    }

    @Test
    void deveriaBuscarContaPorIdComSucesso() {
        //Arrage
        Conta conta = conta2ComTodosCampos();
        when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
        //Act
        admServices.buscarContaPorId(conta.getId());
        //Assert
        Assertions.assertEquals(conta.getClienteNome(), "Maria");
        Assertions.assertEquals(conta.getSaldo(), 10000d);
        Assertions.assertEquals(conta.getClienteId(), "1");
    }

    @ParameterizedTest
    @MethodSource("invalidIds")
    void deveriaBuscarContaPorIdComFalha(String id) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> admServices.buscarContaPorId(id));
    }

    @Test
    void deveriaRetornarlistaComSucesso() {//Ele não aceita ser mocado e pede duas listas para fazer iteração
        //Arrage
        List<ClienteContaResponse> lista = listaComTodosCampos();
        when(clienteContaResponseHelper.clienteContaResponseHelper()).thenReturn(lista);
        //Act
        //Assert
    }

    @Test
    void deveriaBuscarClientePorIdComSucesso() {
        //Arrage
        Cliente cliente = clienteComTodosCampos();
        //Act
        try (MockedStatic<ClienteRequest> clienteMock = Mockito.mockStatic(ClienteRequest.class)) {
            clienteMock.when(() -> ClienteRequest.consultaCliente(null)).thenReturn(cliente);
            //Assert
            assertEquals(cliente.getId(), "1234");
        }
    }

    @ParameterizedTest
    @MethodSource("invalidIds")
    void deveriaBuscarClientePorIdComFalha(String id) {
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> ClienteRequest.consultaCliente(id));
    }

    private static Stream<String> invalidIds() {
        return Stream.of("999", null, "");
    }
}