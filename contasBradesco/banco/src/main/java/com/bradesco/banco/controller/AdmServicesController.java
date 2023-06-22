package com.bradesco.banco.controller;

import com.bradesco.banco.exceptions.PersonException;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.ContaSaldo;
import com.bradesco.banco.domain.Poupanca;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import com.bradesco.banco.service.admservices.AdmServices;
import com.bradesco.banco.usecases.AtivarChequeEspecial;
import com.bradesco.banco.usecases.ValidarLimiteCartaoCredito;
import com.bradesco.banco.usecases.VerificacaoContasInativas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/adm")
public class AdmServicesController {

    @Autowired
    AdmServices admServices;
    @Autowired
    AtivarChequeEspecial ativarChequeEspecial;
    @Autowired
    VerificacaoContasInativas verificacaoContasInativas;
    @Autowired
    ValidarLimiteCartaoCredito validarLimiteCartaoCredito;

    @PostMapping(path = "/cadastrar/poupanca")
    public ResponseEntity<ClienteContaResponse> cadastrarPoupanca(@RequestBody @Validated ContaRequest poupanca) {
        ClienteContaResponse response = admServices.cadastrarPoupanca(poupanca);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(path = "/cadastrar/corrente")
    public ResponseEntity<ClienteContaResponse> cadastrarCorrente(@RequestBody ContaRequest corrente) {
        ClienteContaResponse response = this.admServices.cadastrarCorrente(corrente);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/buscarTodos")
    public ResponseEntity<List<ClienteContaResponse>> buscarTodos() {
        return ResponseEntity.ok(this.admServices.lista());
    }

    @GetMapping("/mostraDadosContaPaginada")
    public Page<Conta> MonstraDadosContaPaginada(@PageableDefault(page = 0, size = 10, sort = {"nome"}, direction = DESC) Pageable pageable) {
        return this.admServices.mostrarDadosDaContaPagina(pageable);
    }

    @GetMapping(path = "/buscarContaSaldoPorId/{id}")
    public ResponseEntity<ClienteContaResponse> buscarContaSaldoPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.admServices.buscarContaSaldoPorId(id));
    }

    @GetMapping(path = "/buscarContaPorId/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.admServices.buscarContaPorId(id));
    }

    @PostMapping(path = "/ativarChequeEspecial/{id}")
    public ResponseEntity<Conta> AtivarChequeEspecial(@PathVariable(name = "id", required = false) String id) throws PersonException {
        return ResponseEntity.ok(ativarChequeEspecial.validarChequeEspecial(id));
    }

    @PostMapping(path = "/verificarContasAtivas/{id}")
    public ResponseEntity<Conta> verificarContasAtivas(@PathVariable(name = "id", required = false) String id) {
        return ResponseEntity.ok(verificacaoContasInativas.validarContaNegativada(id));
    }

    @GetMapping(path = "/buscarClientePorId/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable(name = "id", required = true) String id) {
        return ResponseEntity.ok(this.admServices.buscarClientePorId(id));
    }

    @GetMapping(path = "/validarLimiteCartãoDeCredito/{id}")
    public Double validarLimiteCartãoDeCredito(@PathVariable(name = "id", required = true) String id) {
        return this.validarLimiteCartaoCredito.validarLimiteCartaoCredito(id);
    }
}
