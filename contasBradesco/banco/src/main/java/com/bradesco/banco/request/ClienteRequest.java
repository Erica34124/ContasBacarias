package com.bradesco.banco.request;


import com.bradesco.banco.dto.Clientes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteRequest {
    public static Clientes consultaCliente(String clienteId) {
        String url = String.format("http://localhost:8090/clientes/buscarClientePorId/%s", clienteId);
        Clientes request = new RestTemplate().getForEntity(url, Clientes.class).getBody();
        try {
            return request;
        } catch (ResourceAccessException exception) {
            throw new ResponseStatusException
                    (HttpStatus.BAD_GATEWAY, "Serviço fora do ar ");
        }
    }
}
