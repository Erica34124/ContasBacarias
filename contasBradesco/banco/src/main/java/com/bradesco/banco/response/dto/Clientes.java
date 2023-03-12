package com.bradesco.banco.response.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
@Service
@Data
public class Clientes {
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String cpf;
    private Endereco endereco;

    public Telefone telefone;
}
