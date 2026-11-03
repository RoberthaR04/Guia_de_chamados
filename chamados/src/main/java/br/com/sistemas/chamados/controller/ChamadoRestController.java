package br.com.sistemas.chamados.controller;

import br.com.sistemas.chamados.dto.AlterarStatusRequest;
import br.com.sistemas.chamados.dto.AtualizarChamadoRequest;
import br.com.sistemas.chamados.dto.ChamadoResponse;
import br.com.sistemas.chamados.dto.CriarChamadoRequest;
import br.com.sistemas.chamados.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController {

    private final ChamadoService service;

    public ChamadoRestController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChamadoResponse criar(
            @Valid @RequestBody CriarChamadoRequest request) {
        return service.abrir(request);
    }

    @GetMapping
    public List<ChamadoResponse> listar() {
        return service.listarRespostas();
    }

    @GetMapping("/{id}")
    public ChamadoResponse buscar(@PathVariable Long id) {
        return service.buscarRespostaPorId(id);
    }

    @PutMapping("/{id}")
    public ChamadoResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarChamadoRequest request) {
        return service.atualizar(id, request);
    }

    @PatchMapping("/{id}/status")
    public ChamadoResponse alterarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AlterarStatusRequest request) {
        return service.alterarStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}