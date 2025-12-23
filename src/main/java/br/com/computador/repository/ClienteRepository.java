package br.com.computador.repository;

import br.com.computador.dto.ClienteDTO;
import br.com.computador.entity.Cliente;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
  Optional<Cliente> findByTelefone(@Param("telefone") String telefone);

    Optional<Cliente> findByEmail(@Param("email") String email);
}
