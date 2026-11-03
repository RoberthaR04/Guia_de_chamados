package br.com.sistemas.chamados.service;

import br.com.sistemas.chamados.dto.CriarTecnicoRequest;
import br.com.sistemas.chamados.dto.TecnicoResponse;
import br.com.sistemas.chamados.exception.RecursoNaoEncontradoException;
import br.com.sistemas.chamados.model.Tecnico;
import br.com.sistemas.chamados.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public TecnicoResponse criar(CriarTecnicoRequest request) {
        String email = request.email().trim().toLowerCase();

        if (repository.existsByEmailIgnoreCase(email)) {
            throw new IllegalStateException(
                    "Já existe um técnico com o e-mail '" + email + "'");
        }

        Tecnico tecnico = new Tecnico();
        tecnico.setNome(request.nome().trim());
        tecnico.setEmail(email);
        return paraResponse(repository.save(tecnico));
    }

    public List<TecnicoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public TecnicoResponse buscarPorId(Long id) {
        Tecnico tecnico = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Técnico " + id + " não encontrado"));
        return paraResponse(tecnico);
    }

    private TecnicoResponse paraResponse(Tecnico tecnico) {
        return new TecnicoResponse(
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEmail()
        );
    }
}
