package br.com.computador.dto;

import br.com.computador.entity.Aparelho;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private List<Aparelho> aparelhos;
}
