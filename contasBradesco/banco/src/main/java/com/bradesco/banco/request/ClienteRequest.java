package com.bradesco.banco.request;

import com.bradesco.banco.exceptions.ExceptionsType;
import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.response.dto.Clientes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteRequest {
    public static Clientes consultaCliente(String clienteId) {
        String url = String.format("http://localhost:8090/clientes/buscarClientePorId/%s", clienteId);

        try {
            Clientes request = new RestTemplate().getForEntity(url, Clientes.class).getBody();
            return request;

        } catch (ResourceAccessException exception) {
            throw new PersonExceptions(ExceptionsType.valueOf(ExceptionsType.SERVICO_INATIVO.getMessage()));
        }
    }
}
