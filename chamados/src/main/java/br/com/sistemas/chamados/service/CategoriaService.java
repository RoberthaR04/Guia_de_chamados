package br.com.sistemas.chamados.service;

import br.com.sistemas.chamados.dto.AtualizarCategoriaRequest;
import br.com.sistemas.chamados.dto.CategoriaResponse;
import br.com.sistemas.chamados.dto.CriarCategoriaRequest;
import br.com.sistemas.chamados.exception.RecursoNaoEncontradoException;
import br.com.sistemas.chamados.model.Categoria;
import br.com.sistemas.chamados.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public CategoriaResponse criar(CriarCategoriaRequest request) {
        String nome = normalizar(request.nome());
        validarNomeDisponivel(nome);

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return paraResponse(repository.save(categoria));
    }

    public List<CategoriaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public CategoriaResponse buscarRespostaPorId(Long id) {
        return paraResponse(buscarPorId(id));
    }

    public CategoriaResponse atualizar(Long id, AtualizarCategoriaRequest request) {
        Categoria categoria = buscarPorId(id);
        String nome = normalizar(request.nome());

        if (!categoria.getNome().equalsIgnoreCase(nome)) {
            validarNomeDisponivel(nome);
        }

        categoria.setNome(nome);
        return paraResponse(repository.save(categoria));
    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }

    private Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Categoria " + id + " não encontrada"));
    }

    private String normalizar(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }
        return nome.trim();
    }

    private void validarNomeDisponivel(String nome) {
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalStateException(
                    "Já existe uma categoria com o nome '" + nome + "'");
        }
    }

    private CategoriaResponse paraResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }
}