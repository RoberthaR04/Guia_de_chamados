package br.com.sistemas.chamados.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarChamadoRequest(
        @NotBlank @Size(max = 120) String titulo,
        @NotBlank @Size(max = 1000) String descricao
) {}