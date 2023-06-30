package com.bradesco.banco.controller;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.request.ContaRequest;
import com.bradesco.banco.response.ClienteContaResponse;
import com.bradesco.banco.service.admservices.AdmServices;
import com.bradesco.banco.usecases.AtivarChequeEspecial;
import com.bradesco.banco.usecases.ValidarLimiteCartaoCredito;
import com.bradesco.banco.usecases.VerificacaoContasInativas;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.bradesco.banco.mock.AllMocks.clienteContaResponseComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.contaRequest2ComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.listaComTodosCampos;
import static com.bradesco.banco.mock.AllMocks.listaContasComTodosCampos;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdmServicesController.class)
class AdmServicesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    @Qualifier("admServices")
    AdmServices admServices;
    @MockBean
    AtivarChequeEspecial ativarChequeEspecial;
    @MockBean
    VerificacaoContasInativas verificacaoContasInativas;
    @MockBean
    ValidarLimiteCartaoCredito validarLimiteCartaoCredito;
    @InjectMocks
    AdmServicesController admServicesController;

    @Test
    void deveriaCadastrarPoupancaComSucesso() throws Exception {
        ContaRequest request = contaRequest2ComTodosCampos();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/adm/cadastrar/poupanca")
                                .content(asJsonString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void deveriaCadastrarPoupancaComFalha() throws Exception {
        ContaRequest request = contaRequest2ComTodosCampos();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/adm/cadastrar/poupanca")
                                .content(asJsonString(null))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deveriaCadastrarCorrenteComSucesso() throws Exception {
        ContaRequest corrente = contaRequest2ComTodosCampos();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/adm/cadastrar/corrente")
                                .content(asJsonString(corrente))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andReturn();

        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void deveriaCadastrarCorrenteComFalha() throws Exception {
        ContaRequest corrente = contaRequest2ComTodosCampos();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/adm/cadastrar/corrente")
                                .content(asJsonString(null))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveriaBuscarTodos() throws Exception {
        List<ClienteContaResponse> lista = listaComTodosCampos();

        var response =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/adm/buscarTodos")
                                .content(asJsonString(lista))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void monstraDadosContaPaginada() throws Exception {
        List<ClienteContaResponse> lista = listaContasComTodosCampos();

        var response =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/adm/mostraDadosContaPaginada")
                                .content(asJsonString(lista))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaBuscarContaSaldoPorIdComSucesso() throws Exception {
        ClienteContaResponse conta = clienteContaResponseComTodosCampos();

        var response =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/adm/buscarContaSaldoPorId/" + conta.getContaId())
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void buscarContaPorId() throws Exception {
        ClienteContaResponse conta = clienteContaResponseComTodosCampos();

        var response =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/adm/buscarContaSaldoPorId/" + conta.getContaId())
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void deveriaEstarAtiva() throws Exception {
        Conta conta = contaComTodosCampos();

        var response =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/adm/verificarContasAtivas/" + conta.getId())
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void deveriaBuscarClientePorId() throws Exception {
        ClienteContaResponse conta = clienteContaResponseComTodosCampos();

        var response =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/adm/buscarClientePorId/" + conta.getContaId())
                                .content(asJsonString(conta))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
    }
}