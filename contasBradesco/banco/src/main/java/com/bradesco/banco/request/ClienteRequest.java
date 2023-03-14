package com.bradesco.banco.request;

import com.bradesco.banco.exceptions.ExceptionType;
import com.bradesco.banco.exceptions.PersonException;
import com.bradesco.banco.response.dto.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteRequest {
    public static Cliente consultaCliente(String clienteId) {
        String url = String.format("http://localhost:8090/clientes/buscarClientePorId/%s", clienteId);

        try {
            Cliente request = new RestTemplate().getForEntity(url, Cliente.class).getBody();
            return request;

        } catch (ResourceAccessException exception) {
            throw new PersonException(ExceptionType.valueOf(ExceptionType.SERVICO_INATIVO.getMessage()));
        }
    }
}
