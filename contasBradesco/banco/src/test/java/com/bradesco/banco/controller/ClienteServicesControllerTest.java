package com.bradesco.banco.controller;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.response.dto.ContaClienteDao;
import com.bradesco.banco.service.admservices.AdmServices;
import com.bradesco.banco.service.clienteservices.ClienteServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.bradesco.banco.mock.AllMocks.conta2ComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaDaoComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaRequestHelperComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.correnteComTodosCampos;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteServicesController.class)
class ClienteServicesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private ClienteServices clienteServices;

    @InjectMocks
    ClienteServicesController clienteServicesController;

    @Test
    void deveriaSacarComSucesso() throws Exception {
        Conta conta = conta2ComTodosCampos();
        String id = "123";
        Double valor = 200d;

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .put("/contas/sacar/" + id + "/" + valor)
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();

    }

    @Test
    void deveriaConsultaSaldoComSucesso() throws Exception {
        Conta conta = conta2ComTodosCampos();

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/contas/saldo/" + conta.getId())
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
    }

    @Test
    void deveriaMonstraDadosContaComSucesso() throws Exception {
        ContaClienteDao conta = contaDaoComTodosCampos();

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/contas/buscarDadosCompletos/" + conta.getId())
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
    }

    @Test
    void deveriaDepositarComSucesso() throws Exception {
        Conta conta = conta2ComTodosCampos();
        String id = "123";
        Double valor = 200d;

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .put("/contas/depositar/" + id + "/" + valor)
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
    }

    @Test
    void deveriaTransferirComSucesso() throws Exception {
        Conta conta = conta2ComTodosCampos();
        Conta conta1 = correnteComTodosCampos();
        Double valor = 200d;

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/contas/transferir/" + conta1.getId() + "/" + conta.getId() + "/" + valor)
                                .content(asJsonString(conta1))
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isInternalServerError())
                        .andReturn();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}