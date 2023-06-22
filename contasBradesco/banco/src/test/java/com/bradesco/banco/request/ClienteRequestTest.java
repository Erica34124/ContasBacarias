package com.bradesco.banco.request;

import com.bradesco.banco.exceptions.PersonException;
import com.bradesco.banco.response.dto.Cliente;
import org.apache.el.stream.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static com.bradesco.banco.mock.AllMocks.clienteComTodosCampos;
import static org.junit.jupiter.api.Assertions.*;

class ClienteRequestTest {

    @Test
    void deveriaConsultaClienteComSucesso() {
        Cliente cliente = clienteComTodosCampos();
        try (MockedStatic<ClienteRequest> request = Mockito.mockStatic(ClienteRequest.class)) {
            request.when(() -> ClienteRequest.consultaCliente(cliente.getId()))
                    .thenReturn(cliente);
            Assertions.assertEquals(cliente.getNome(), "Vanessa");
            Assertions.assertEquals(cliente.getId(), "1234");
            Assertions.assertEquals(cliente.getCpf(), "999.999.000-22");
        }
    }

    @ParameterizedTest
    @MethodSource("idComExceptions")
    void deveriaConsultaClienteComFalha(String id) {
        Assertions.assertThrows(RuntimeException.class, () -> ClienteRequest.consultaCliente(id));
    }

    public static Stream<String> idComExceptions() {
        return Stream.of("444", null, "");
    }
}
