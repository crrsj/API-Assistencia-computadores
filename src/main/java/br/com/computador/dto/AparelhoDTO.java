package br.com.computador.dto;

import br.com.computador.entity.Cliente;
import br.com.computador.enums.Defeito;
import br.com.computador.enums.Marca;
import br.com.computador.enums.StatusOrcamento;
import br.com.computador.enums.TipoAparelho;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Data
public class AparelhoDTO {
    private Long id;
    private String dataOrcamento = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private Integer ordemServico = new Random().nextInt(9000) + 1000;
    private TipoAparelho tipo;
    private Marca marca;
    private String modelo;
    private Defeito defeito;
    private String descricao;
    private Double total;
    private StatusOrcamento status;
    private LocalDate dataEntrega;
    private Cliente cliente;

  
}
