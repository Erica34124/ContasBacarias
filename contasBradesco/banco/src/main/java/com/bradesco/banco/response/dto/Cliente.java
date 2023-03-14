package com.bradesco.banco.response.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Cliente {
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
