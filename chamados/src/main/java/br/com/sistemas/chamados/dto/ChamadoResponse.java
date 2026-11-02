package br.com.sistemas.chamados.dto;

import br.com.sistemas.chamados.model.StatusChamado;
import java.time.LocalDateTime;

public record ChamadoResponse(
        Long id,
        String titulo,
        String descricao,
        StatusChamado status,
        LocalDateTime criadoEm
) {}