package br.com.sistemas.chamados.dto;

import br.com.sistemas.chamados.model.StatusChamado;
import jakarta.validation.constraints.NotNull;

public record AlterarStatusRequest(
        @NotNull StatusChamado status
) {}