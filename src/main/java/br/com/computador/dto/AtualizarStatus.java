package br.com.computador.dto;

import br.com.computador.enums.StatusOrcamento;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AtualizarStatus {

    private StatusOrcamento status;
    private LocalDate dataEntrega;
    private Double total;
}
