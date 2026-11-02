package br.com.sistemas.chamados.repository;

import br.com.sistemas.chamados.model.Chamado;
import br.com.sistemas.chamados.model.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    List<Chamado> findByStatus(StatusChamado status);
}