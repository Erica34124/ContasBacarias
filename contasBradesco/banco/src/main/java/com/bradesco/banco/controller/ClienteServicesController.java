package com.bradesco.banco.controller;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.service.clienteservices.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.bradesco.banco.exceptions.ExceptionType.SALDO_INSUFICIENTE;

@RestController
@RequestMapping("/contas")
public class ClienteServicesController {
    @Autowired
    private ClienteServices clienteServices;

    @PutMapping(path = "/sacar/{id}/{valor}")
    public ResponseEntity<?> sacar(@PathVariable(name = "id") String id,
                                   @PathVariable(name = "valor") Double valor) {

        try {
            Object saque = clienteServices.sacar(id, valor);
            return ResponseEntity.ok(saque);
        } catch(Exception e){
            return ResponseEntity.noContent().header("Informe: ", SALDO_INSUFICIENTE.getMessage()).build();
        }

    }

    @GetMapping(path = "/saldo/{id}")
    public ResponseEntity<Optional<Conta>> consultaSaldo(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.clienteServices.consularSaldo(id));
    }

    @GetMapping(path = "/buscarDadosCompletos/{id}")
    public ResponseEntity<ContaClienteDao> MonstraDadosConta(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.clienteServices.buscarDadosCompletos(id));
    }

    @PutMapping(path = "/depositar/{id}/{valor}")
    public ResponseEntity<Object> depositar(@PathVariable("id") String id,
                                            @PathVariable(name = "valor") Double valor) {
        return ResponseEntity.ok(this.clienteServices.depositar(id, valor));
    }

    @GetMapping(path = "/transferir/{idO}/{idD}/{valor}")
    public ResponseEntity<Object> transferir(@PathVariable(name = "idO") Conta idO,
                                             @PathVariable(name = "idD") Conta idD,
                                             @PathVariable(name = "valor") Double valor) {
        return ResponseEntity.ok(this.clienteServices.tranferir(idO, idD, valor));
    }
}
