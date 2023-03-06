package com.bradesco.banco.controller;

import com.bradesco.banco.exceptions.PersonExceptions;
import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.ContaSaldo;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.domain.Poupanca;
import com.bradesco.banco.response.dto.Clientes;
import com.bradesco.banco.service.admservices.AdmServices;
import com.bradesco.banco.usecases.AtivarChequeEspecial;
import com.bradesco.banco.usecases.ValidarLimiteCartaoCredito;
import com.bradesco.banco.usecases.VerificacaoContasInativas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Poupanca> cadastrarPoupanca(@RequestBody @Validated Poupanca poupanca){
        return ResponseEntity.ok(this.admServices.cadastrarPoupanca(poupanca));
    }
    @PostMapping(path = "/cadastrar/corrente")
    public ResponseEntity<Corrente> cadastrarCorrente(@RequestBody Corrente corrente){
        return ResponseEntity.ok(this.admServices.cadastrarCorrente(corrente));
    }
    @GetMapping(path = "/buscarTodos")
    public ResponseEntity<List<Conta>> buscarTodos(){
        return ResponseEntity.ok(this.admServices.lista());
    }
    @GetMapping("/mostraDadosContaPaginada")
    public Page<Conta> MonstraDadosContaPaginada(@PageableDefault (page = 0, size = 10, sort = {"nome"}, direction = DESC)Pageable pageable) {
        return this.admServices.mostrarDadosDaContaPagina(pageable);
    }

    @GetMapping(path = "/buscarContaSaldoPorId/{id}")
    public ResponseEntity<ContaSaldo> buscarContaSaldoPorId(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(this.admServices.buscarContaSaldoPorId(id));
    }
    @GetMapping(path = "/buscarContaPorId/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(this.admServices.buscarContaPorId(id));
    }
    @PostMapping(path = "/ativarChequeEspecial/{id}")
    public ResponseEntity<Conta> AtivarChequeEspecial(@PathVariable(name = "id", required = false) String id) throws PersonExceptions {
        return ResponseEntity.ok(ativarChequeEspecial.validarChequeEspecial(id));
    }
    @PostMapping(path = "/verificarContasAtivas/{id}")
    public ResponseEntity<Conta> verificarContasAtivas(@PathVariable(name = "id", required = false) String id){
        return ResponseEntity.ok(verificacaoContasInativas.validarContaNegativada(id));
    }
    @GetMapping(path = "/buscarClientePorId/{id}")
    public ResponseEntity<Clientes> buscarClientePorId(@PathVariable(name = "id", required = true) String id){
        return ResponseEntity.ok(this.admServices.buscarClientePorId(id));
    }
    @GetMapping(path = "/validarLimiteCartãoDeCredito/{id}")
    public Double validarLimiteCartãoDeCredito(@PathVariable(name = "id", required = true) String id){
        return this.validarLimiteCartaoCredito.validarLimiteCartaoCredito(id);
    }
}
