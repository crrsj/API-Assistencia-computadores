package br.com.computador.repository;

import br.com.computador.dto.AparelhoDTO;
import br.com.computador.entity.Aparelho;
import br.com.computador.enums.StatusOrcamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AparelhoRepository extends JpaRepository<Aparelho,Long> {
    Page<Aparelho> findByStatus(StatusOrcamento status, Pageable pageable);
    Optional<Aparelho> findByOrdemServico(Integer ordemServico);
}
