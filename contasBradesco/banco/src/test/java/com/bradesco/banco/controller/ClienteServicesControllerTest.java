package com.bradesco.banco.controller;

import com.bradesco.banco.domain.Conta;
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
    void consultaSaldo() {
    }

    @Test
    void monstraDadosConta() {
    }

    @Test
    void depositar() {
    }

    @Test
    void transferir() {
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}