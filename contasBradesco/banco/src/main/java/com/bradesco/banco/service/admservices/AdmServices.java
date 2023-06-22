package com.bradesco.banco.service.admservices;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.exceptions.ExceptionType;
import com.bradesco.banco.exceptions.PersonException;
import com.bradesco.banco.helpers.ClienteContaResponseHelper;
import com.bradesco.banco.helpers.ContaRequestHelper;
import com.bradesco.banco.helpers.ContaResponseHelper;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.repository.ClienteRepository;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Qualifier("admServices")
public class AdmServices implements IadmServices{
    @Autowired
    ContaRepository repository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteRequest clienteRequest;
    @Autowired
    ContaRequestHelper contaRequestHelper;

    @Autowired
    ContaResponseHelper contaResponseHelper;
    @Autowired
    ClienteContaResponseHelper clienteContaResponseHelper;

    @Override
    public ClienteContaResponse cadastrarPoupanca(ContaRequest request) {
        Optional<Cliente> cliente = clienteRepository.findById(request.getClienteId());

        if (cliente.isPresent()) {
            request.setId(UUID.randomUUID().toString());
            repository.save(contaRequestHelper.converterContaRequest(request));
            ClienteContaResponse response = contaResponseHelper.contaResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED).getBody();
        }
        throw new PersonException(ExceptionType.valueOf(ExceptionType.NAO_CADASTRADO.getMessage()));
    }

    @Override
    public ClienteContaResponse cadastrarCorrente(ContaRequest request) {
        Optional<Cliente> cliente = clienteRepository.findById(request.getClienteId());
        if (cliente.isPresent()) {
            request.setId(UUID.randomUUID().toString());
            repository.save(contaRequestHelper.converterContaRequest(request));
            return contaResponseHelper.contaResponse(request);
        }
        throw new PersonException(ExceptionType.valueOf(ExceptionType.NAO_CADASTRADO.getMessage()));
    }

    @Override
    public ClienteContaResponse buscarContaSaldoPorId(String id) {
        Optional<Conta> conta = repository.findById(id);
        if (conta.isPresent()) {
            return contaResponseHelper.contaResponse((ContaRequest) conta.get());
        }
        throw new PersonException(ExceptionType.valueOf(ExceptionType.CONTA_NAO_ENCONTRADA.getMessage()));
    }

    @Override
    public Conta buscarContaPorId(String id) {
        Optional<Conta> conta = repository.findById(id);
        if (conta.isPresent()) {
        return conta.get();
    }
        throw new PersonException(ExceptionType.valueOf(ExceptionType.CONTA_NAO_ENCONTRADA.getMessage()));
    }

    @Override
    public Page<Conta> mostrarDadosDaContaPagina(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<ClienteContaResponse> lista() {
        return clienteContaResponseHelper.clienteContaResponseHelper();
    }

    @Override
    public Cliente buscarClientePorId(String clienteId) {
        if (clienteId.isEmpty() && clienteId.isBlank()){
            throw new IllegalArgumentException("Identificador inválido. ");
        }
        return clienteRequest.consultaCliente(clienteId);
    }
}
