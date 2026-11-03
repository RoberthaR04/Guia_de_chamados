package br.com.sistemas.chamados.service;

import br.com.sistemas.chamados.dto.ChamadoResponse;
import br.com.sistemas.chamados.dto.CriarChamadoRequest;
import br.com.sistemas.chamados.model.Chamado;
import br.com.sistemas.chamados.model.StatusChamado;
import br.com.sistemas.chamados.repository.ChamadoRepository;
import org.springframework.stereotype.Service;
import br.com.sistemas.chamados.dto.AlterarStatusRequest;
import br.com.sistemas.chamados.dto.AtualizarChamadoRequest;
import br.com.sistemas.chamados.exception.RecursoNaoEncontradoException;

import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public ChamadoResponse abrir(CriarChamadoRequest request) {
        Chamado chamado = new Chamado();
        chamado.setTitulo(request.titulo());
        chamado.setDescricao(request.descricao());
        chamado.setStatus(StatusChamado.ABERTO);

        Chamado salvo = repository.save(chamado);
        return paraResponse(salvo);
    }

    public List<ChamadoResponse> listarRespostas() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public Chamado buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Chamado " + id + " não encontrado"));
    }

    public Chamado concluir(Long id) {
        Chamado chamado = buscarPorId(id);

        if (chamado.getStatus() == StatusChamado.CONCLUIDO) {
            throw new IllegalStateException("Chamado já está concluído");
        }

        chamado.setStatus(StatusChamado.CONCLUIDO);
        return repository.save(chamado);
    }

    public ChamadoResponse buscarRespostaPorId(Long id) {
        return paraResponse(buscarPorId(id));
    }

    public ChamadoResponse atualizar(Long id, AtualizarChamadoRequest request) {
        Chamado chamado = buscarPorId(id);
        chamado.setTitulo(request.titulo());
        chamado.setDescricao(request.descricao());
        return paraResponse(repository.save(chamado));
    }

    public ChamadoResponse alterarStatus(Long id, AlterarStatusRequest request) {
        Chamado chamado = buscarPorId(id);

        if (chamado.getStatus() == StatusChamado.CONCLUIDO) {
            throw new IllegalStateException(
                    "Chamado concluído não pode mudar de status");
        }

        chamado.setStatus(request.status());
        return paraResponse(repository.save(chamado));
    }

    public void excluir(Long id) {
        Chamado chamado = buscarPorId(id);
        repository.delete(chamado);
    }

    public ChamadoResponse paraResponse(Chamado chamado) {
        return new ChamadoResponse(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus(),
                chamado.getCriadoEm());
    }
}