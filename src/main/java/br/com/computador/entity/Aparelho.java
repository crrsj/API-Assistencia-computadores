package br.com.computador.entity;

import br.com.computador.enums.Defeito;
import br.com.computador.enums.Marca;
import br.com.computador.enums.StatusOrcamento;
import br.com.computador.enums.TipoAparelho;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Data
@Entity
@Table(name = "aparelhos")
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataOrcamento = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private Integer ordemServico = new Random().nextInt(9000) + 1000;
    private TipoAparelho tipo;
    @Enumerated(EnumType.STRING)
    private Marca marca;
    private String modelo;
    @Enumerated(EnumType.STRING)
    private Defeito defeito;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Double total;
    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;
    private LocalDate dataEntrega;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

}
