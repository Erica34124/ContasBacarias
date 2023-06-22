package com.bradesco.banco.service.admservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IadmServices {
    public ClienteContaResponse cadastrarPoupanca(ContaRequest conta);

    public ClienteContaResponse cadastrarCorrente(ContaRequest request);

    public ClienteContaResponse buscarContaSaldoPorId(String id);
    public Conta buscarContaPorId(String id);

    Page<Conta> mostrarDadosDaContaPagina(Pageable pageable);

    List<ClienteContaResponse> lista();
    public Cliente buscarClientePorId(String clienteId);
}
