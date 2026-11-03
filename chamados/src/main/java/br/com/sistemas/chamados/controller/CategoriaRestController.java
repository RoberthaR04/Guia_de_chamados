package br.com.sistemas.chamados.controller;

import br.com.sistemas.chamados.dto.AtualizarCategoriaRequest;
import br.com.sistemas.chamados.dto.CategoriaResponse;
import br.com.sistemas.chamados.dto.CriarCategoriaRequest;
import br.com.sistemas.chamados.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

    private final CategoriaService service;

    public CategoriaRestController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponse criar(
            @Valid @RequestBody CriarCategoriaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<CategoriaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CategoriaResponse buscar(@PathVariable Long id) {
        return service.buscarRespostaPorId(id);
    }

    @PutMapping("/{id}")
    public CategoriaResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarCategoriaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}