//package com.bradesco.banco.service.clienteservices;
//
//import com.bradesco.banco.domain.Conta;
//import com.bradesco.banco.domain.Corrente;
//import com.bradesco.banco.repository.ContaRepository;
//import com.bradesco.banco.response.dto.Clientes;
//import com.bradesco.banco.response.dto.ContaClienteDao;
//import com.bradesco.banco.response.dto.Cpf;
//import com.bradesco.banco.response.dto.Endereco;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.util.Assert;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class HelperContaClienteTest {
//    private HelperContaCliente helperContaCliente;
//
//    @Test
//    void converterClienteConta() {
//        //Arrange
//        ContaRepository  conta = Mockito.mock(ContaRepository.class);
//        helperContaCliente = new HelperContaCliente(conta);
//        Corrente corrente = new Corrente("123", true,
//                "456", "Ana", 5000.0, "visa");
//
//
//        Clientes clientes = Mockito.mock(Clientes.class);
//        clientes.setId("123");
//        clientes.setCpf(new Cpf());
//        clientes.setNome("Vanessa");
//        clientes.setEndereco(new Endereco());
//
//        ContaClienteDao contaClienteDao =  new ContaClienteDao("123","ewtpe",
//                new Cpf(), new Endereco(), true, "3453", 8222.5, "master");
//
//        ContaClienteDao contaTest = helperContaCliente.converterClienteConta(corrente.getId());
//
//        Mockito.when(contaTest)
//                .thenReturn(contaClienteDao);
////
////
////        ContaRepository contaRepository = Mockito.mock(ContaRepository.class);
////        ContaClienteDao cliente = Mockito.mock(ContaClienteDao.class);
////
////
////        //Action
////        ContaClienteDao clienteDao = helper.converterClienteConta(corrente.getId());
////
////        //Assert
//        assertEquals(contaTest.getClienteId(), contaClienteDao.getClienteId());
//    }
//}