package com.bradesco.banco.mock;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.domain.Poupanca;
import com.bradesco.banco.request.ClienteRequest;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.response.dto.Cliente;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.response.dto.Endereco;
import com.bradesco.banco.response.dto.Telefone;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllMocks {
    public static Conta contaComTodosCampos(){
        Conta conta = new Conta();
        conta.setId("1234");
        conta.setAtivo(true);
        conta.setSaldo(5000d);
        conta.setCartao("Visa");
        conta.setClienteId("1");
        conta.setClienteNome("Maria");

        return conta;
    }

    public static Conta contaComMultiplosSaldos(Double valor) {
        Conta conta = new Conta();
        conta.setId("1234");
        conta.setAtivo(true);
        conta.setSaldo(valor);
        conta.setCartao("Visa");
        conta.setClienteId("1");
        conta.setClienteNome("Maria");

        return conta;
    }

    public static Corrente correnteComTodosCampos(){
        Corrente conta = new Corrente();
        conta.setId("1234");
        conta.setAtivo(true);
        conta.setSaldo(5000d);
        conta.setCartao("Visa");
        conta.setClienteId("1");
        conta.setClienteNome("Maria");
        conta.setChequeEspecial(true);

        return conta;
    }

    public static Conta conta2ComTodosCampos(){
        Conta conta = new Conta();
        conta.setId("12345");
        conta.setAtivo(true);
        conta.setSaldo(10000d);
        conta.setCartao("Visa");
        conta.setClienteId("58ac8878-f9d3-4bbe-93eb-4a970b54bb1f");
        conta.setClienteNome("Maria");

        return conta;
    }

    public static ContaRequest contaRequest2ComTodosCampos(){
        ContaRequest contaRequest = new ContaRequest();
        contaRequest.setId("12345");
        contaRequest.setAtivo(true);
        contaRequest.setSaldo(3000d);
        contaRequest.setCartao("Visa");
        contaRequest.setClienteId("58ac8878-f9d3-4bbe-93eb-4a970b54bb1f");
        contaRequest.setClienteNome("Maria");

        return contaRequest;
    }

    public static Poupanca contaRequesRest(){
        Poupanca poupanca = new Poupanca("1234", true,
                "58ac8878-f9d3-4bbe-93eb-4a970b54bb1f", "Ana",
                20000d, "Master", 0.2);

        return poupanca;
    }

    public static ContaRequest contaRequestsemId(){
        ContaRequest contaRequest = new ContaRequest();
        contaRequest.setId("12345");
        contaRequest.setAtivo(true);
        contaRequest.setSaldo(3000d);
        contaRequest.setCartao("Visa");
        contaRequest.setClienteId("4564447");
        contaRequest.setClienteNome("Maria");

        return contaRequest;
    }

    public static Conta contaRequestHelperComTodosCampos() {
        Conta conta = new Conta();
        conta.setAtivo(true);
        conta.setSaldo(8000d);
        conta.setClienteId("4567");
        conta.setCartao("Elo");

        return conta;
    }

    public static ContaClienteDao contaDaoComTodosCampos(){
        ContaClienteDao conta = new ContaClienteDao("899", "Joana",
                new Telefone("19", "33333-3333"),
                "222.444.555-66", new Endereco(), true, "2323",
                5000d,"Master");

        return conta;
    }

    public static Cliente clienteComTodosCampos(){
    Cliente cliente = new Cliente();
        cliente.setId("1234");
        cliente.setCpf("999.999.000-22");
        cliente.setNome("Vanessa");
        cliente.setEndereco(new Endereco());

        return cliente;
    }

    public static Cliente clienteRequestComTodosCampos(){
        Cliente cliente = clienteComTodosCampos();
        try (MockedStatic<ClienteRequest> theMock = Mockito.mockStatic(ClienteRequest.class)) {
            theMock.when(() -> ClienteRequest.consultaCliente(cliente.getId()))
                    .thenReturn(cliente);

            assertEquals(cliente.getNome(), "Vanessa");
        }
        return cliente;
    }

    public static ClienteContaResponse clienteContaResponseComTodosCampos(){
        ClienteContaResponse conta = new ClienteContaResponse();
        conta.setNome("Maria");
        conta.setCpf("999.999.999-99");
        conta.setEndereco(new Endereco());
        conta.setClienteId("ci123");
        conta.setContaId("co123");
        conta.setSaldo(10000d);
        conta.setAtivo(true);
        conta.setCartao("Master");

        return conta;
    }

    public static List<ClienteContaResponse> listaComTodosCampos(){
        List<ClienteContaResponse> lista = new ArrayList<>(5);
        ClienteContaResponse conta1 = clienteContaResponseComTodosCampos();
        ClienteContaResponse conta2 = clienteContaResponseComTodosCampos();
        ClienteContaResponse conta3 = clienteContaResponseComTodosCampos();
        ClienteContaResponse conta4 = clienteContaResponseComTodosCampos();
        ClienteContaResponse conta5 = clienteContaResponseComTodosCampos();

        lista.add(conta1);
        lista.add(conta2);
        lista.add(conta3);
        lista.add(conta4);
        lista.add(conta5);

        return lista;
    }
}
