package br.com.sistemas.chamados.controller;

import br.com.sistemas.chamados.dto.CriarTecnicoRequest;
import br.com.sistemas.chamados.dto.TecnicoResponse;
import br.com.sistemas.chamados.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoRestController {

    private final TecnicoService service;

    public TecnicoRestController(TecnicoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TecnicoResponse criar(
            @Valid @RequestBody CriarTecnicoRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<TecnicoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TecnicoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}